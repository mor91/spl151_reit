
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
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
public class RunnableCostumerGroupManager implements Runnable{
    CustomerGroupDetails _costumerGroupDetails;
    private final BlockingQueue<RentalRequest> rentalRequestsPool;
    private DamageReport damageReport;

    public RunnableCostumerGroupManager(CustomerGroupDetails _costumerGroupDetails, BlockingQueue<RentalRequest> rentalRequestsPool) {
        this._costumerGroupDetails = _costumerGroupDetails;
        this.rentalRequestsPool = rentalRequestsPool;
    }
    
    
    @Override
    public void run() {
        for (Map.Entry<String, RentalRequest> rentalRequest : _costumerGroupDetails._rentalRequestMap.entrySet()){
            rentalRequestsPool.add(rentalRequest.getValue());
            try {
                rentalRequest.getValue().rentalRequestCountDownLatch.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(RunnableCostumerGroupManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            Executor ex= Executors.newCachedThreadPool();
            CompletionService<Double> cs = new ExecutorCompletionService<Double>(ex);   
            double totalDamage=0;
            rentalRequest.getValue()._asset._status=AssetStatus.Occupied;
            rentalRequest.getValue()._requestStatus=RentalRequestStatus.InProgress;
            for (Map.Entry<String ,Customer> customer : _costumerGroupDetails._customersMap.entrySet()) {   
                cs.submit(new CallableSimulateStayInAsset(customer.getValue(),rentalRequest.getValue()));
                try {
                    try {
                        totalDamage+=cs.take().get();
                    } catch (ExecutionException ex1) {
                        Logger.getLogger(RunnableCostumerGroupManager.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                } catch (InterruptedException ex1) {
                    Logger.getLogger(RunnableCostumerGroupManager.class.getName()).log(Level.SEVERE, null, ex1);
                }
                
            }
            rentalRequest.getValue()._asset._status=AssetStatus.UnAvailale;
            rentalRequest.getValue()._requestStatus=RentalRequestStatus.Complete;
            damageReport=new DamageReport(rentalRequest.getValue()._asset, totalDamage);
        } 
        
    }

    
    public Map<String, RentalRequest> customerGroupRentalRequest(){
        return _costumerGroupDetails._rentalRequestMap; 
    }
}
