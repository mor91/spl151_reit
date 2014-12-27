
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

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
    BlockingQueue<RunnableCostumerGroupManager> customers = new LinkedBlockingDeque<>();
    Assets _assets;
    Warehouse _warehouse;
    Map<String, RepairToolInformation> repairToolInformationMap=new TreeMap<>();
    Map<String, RepairMaterialInformation> repairMaterialInformationMap=new TreeMap<>();
    int _numberOfRentalRequests;
    
    public Management( Warehouse _warehouse,Assets _assets) {
        this._assets = _assets;
        this._warehouse = _warehouse;
    }
    
    public void addClerk(ClerkDetails clerkDetails){
        RunnableClerk runnableClerk=new RunnableClerk(clerkDetails);
        clerksMap.put(clerkDetails._name, runnableClerk);
    } 
    public void addCostumerGroup(CustomerGroupDetails costumerGroupDetails){
        RunnableCostumerGroupManager runnableCostumerGroupManager=new RunnableCostumerGroupManager(costumerGroupDetails);
        customers.add(runnableCostumerGroupManager);
    }
    public void addItemRepairTool(String contentName,RepairToolInformation repairToolInformation){
        repairToolInformationMap.put(contentName, repairToolInformation);
    }
    public void addItemRepairMaterial(String contentName, RepairMaterialInformation repairMaterialInformation){
        repairMaterialInformationMap.put(contentName, repairMaterialInformation);
    }
    public void work(){
        CountDownLatch countDownLatch = new CountDownLatch(clerksMap.size());
        while(_numberOfRentalRequests>0){
           for(Map.Entry<String, RunnableClerk> entry:clerksMap.entrySet()){
               entry.getValue().run();
           } 
        }

    }
}
