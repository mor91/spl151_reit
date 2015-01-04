
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public class Management {
    Map<String, RunnableClerk> clerksMap=new TreeMap<>();
    List<RunnableCostumerGroupManager> costumerGroupManagersList=new LinkedList<>();
    Assets _assets;
    Warehouse _warehouse;
    Map<String, RepairToolInformation> repairToolInformationMap=new TreeMap<>();
    Map<String, RepairMaterialInformation> repairMaterialInformationMap=new TreeMap<>();
    BlockingQueue<RentalRequest> rentalRequestsQueue;
    CyclicBarrier cyclicBarrier;
    Statistics _statistics;
    AtomicInteger numberOfRentalRequests ;
    AtomicInteger numberOfMaintainancePersons;

    public void setNumberOfRentalRequests(AtomicInteger numberOfRentalRequests) {
        this.numberOfRentalRequests.set(numberOfRentalRequests.get());
    }

    public void setNumberOfMaintenancePersons(AtomicInteger numberOfMaintainancePersons) {
        this.numberOfMaintainancePersons.set(numberOfMaintainancePersons.get());
    }
    
    public Management( Warehouse _warehouse,Assets _assets) {
        this._assets = _assets;
        this._warehouse = _warehouse;
        this.rentalRequestsQueue=new LinkedBlockingQueue<>();
        this._statistics=new Statistics();
        this.numberOfMaintainancePersons=new AtomicInteger(0);
        this.numberOfRentalRequests=new AtomicInteger(0);
        
    }
    
    public void addClerk(ClerkDetails clerkDetails){
        RunnableClerk runnableClerk=new RunnableClerk(clerkDetails ,rentalRequestsQueue, _assets, numberOfRentalRequests);
        clerksMap.put(clerkDetails._name, runnableClerk);
    } 
    public void addCostumerGroup(CustomerGroupDetails costumerGroupDetails){
        RunnableCostumerGroupManager runnableCostumerGroupManager=new RunnableCostumerGroupManager(costumerGroupDetails, rentalRequestsQueue, _assets,_statistics);
        costumerGroupManagersList.add(runnableCostumerGroupManager);
    }
    public void addItemRepairTool(String contentName,RepairToolInformation repairToolInformation){
        repairToolInformationMap.put(contentName, repairToolInformation);
    }
    public void addItemRepairMaterial(String contentName, RepairMaterialInformation repairMaterialInformation){
        repairMaterialInformationMap.put(contentName, repairMaterialInformation);
    }
    public void work(){
        List<Thread> customerGroupThreads=new ArrayList<>();
        List<Thread> maintainencePersons=new ArrayList<>();
        
        
        for (RunnableCostumerGroupManager costumerGroupManager : costumerGroupManagersList) {
            Thread thread=new Thread(costumerGroupManager);
            customerGroupThreads.add(thread);
        }
        
        List<Thread> clerkThreads=new ArrayList<>();
        final ExecutorService executorService=Executors.newFixedThreadPool(numberOfMaintainancePersons.get());

        cyclicBarrier = new CyclicBarrier(clerksMap.size(),new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("end shift");
                    Thread.sleep(16000);
                    System.out.println("start shift");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
                if (numberOfRentalRequests.get() > 0) {
                    while (_assets._damagedAssets.size()>0) {                        
                        try {
                            Asset asset =_assets._damagedAssets.take();
                            executorService.execute(new RunnaleMaintainenceRequest(asset, _warehouse, _statistics));
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                            Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                 
                }
                else printStatistiscs();
            }
            
        });
        for(Map.Entry<String, RunnableClerk> clerk:clerksMap.entrySet()){
            clerk.getValue().setCyclicBarier(cyclicBarrier);
            Thread thread=new Thread(clerk.getValue());
            clerkThreads.add(thread); 
            
        }
        
        for (Thread clerkThread : clerkThreads) {
            clerkThread.start();
        }
        for (Thread customerGroupThread : customerGroupThreads) {
            customerGroupThread.start();
        }
        

    }
   
    public void printStatistiscs(){
        _statistics.outPut();
    }
}
