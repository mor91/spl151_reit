
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

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
    
    public Management( Warehouse _warehouse,Assets _assets) {
        this._assets = _assets;
        this._warehouse = _warehouse;
        this.rentalRequestsQueue=new LinkedBlockingQueue<>();
    }
    
    public void addClerk(ClerkDetails clerkDetails){
        RunnableClerk runnableClerk=new RunnableClerk(clerkDetails, cyclicBarrier ,rentalRequestsQueue, _assets);
        clerksMap.put(clerkDetails._name, runnableClerk);
    } 
    public void addCostumerGroup(CustomerGroupDetails costumerGroupDetails){
        RunnableCostumerGroupManager runnableCostumerGroupManager=new RunnableCostumerGroupManager(costumerGroupDetails, rentalRequestsQueue);
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
        for (RunnableCostumerGroupManager costumerGroupManager : costumerGroupManagersList) {
            Thread thread=new Thread(costumerGroupManager);
            customerGroupThreads.add(thread);
        }
        cyclicBarrier = new CyclicBarrier(clerksMap.size());
        List<Thread> clerkThreads=new ArrayList<>();
        for(Map.Entry<String, RunnableClerk> clerk:clerksMap.entrySet()){
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
}
