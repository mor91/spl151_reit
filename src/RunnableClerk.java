
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
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
public class RunnableClerk implements Runnable{
    ClerkDetails _clarkDetails;
    AtomicInteger _numberOfRentalRequests;
    private CyclicBarrier cyclicBarrier;
    private BlockingQueue<RentalRequest> _rentalRequestQueue=new LinkedBlockingQueue<>();
    Assets _assets;

    
    public RunnableClerk(ClerkDetails _clarkDetails, BlockingQueue<RentalRequest> rentalRequestQueue, Assets assets, AtomicInteger numberOfRentalRequests ) {
        this._clarkDetails = _clarkDetails;
        this._rentalRequestQueue = rentalRequestQueue;
        this._assets=assets;
        this._numberOfRentalRequests=numberOfRentalRequests;
        
    }
    
    public void setCyclicBarier(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }
    
    @Override
    public void run() {
        
        int totalTimeOfSleep=0;
        while(totalTimeOfSleep<8 && _numberOfRentalRequests.doubleValue()>0){
            RentalRequest cuurentRentalRequest=_rentalRequestQueue.poll();
            _numberOfRentalRequests.getAndIncrement();
            for (Map.Entry<String, Asset> asset : _assets._assets.entrySet()) {
                if(asset.getValue()._type==cuurentRentalRequest._assetType && asset.getValue()._size>=cuurentRentalRequest._assetSize ){
                    try {
                        asset.getValue().getCountDownLatch().await();
                        asset.getValue().setCountDownLatch();
                        cuurentRentalRequest._asset=asset.getValue();
                        asset.getValue()._status=AssetStatus.Booked;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RunnableClerk.class.getName()).log(Level.SEVERE, null, ex);
                        ex.printStackTrace();
                    }      
                }
                    
            }
            cuurentRentalRequest._requestStatus=RentalRequestStatus.Fulfilled;
            long distance =(long) _clarkDetails._location.calculateDistance(cuurentRentalRequest._asset._location);//calculate distance from asset location
            try {
                Thread.sleep(distance*2000) ;
            } catch (InterruptedException ex) {
                Logger.getLogger(RunnableClerk.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            totalTimeOfSleep+=distance;
            cuurentRentalRequest.getCountDownLatch().countDown();
            
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(RunnableClerk.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(RunnableClerk.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
}
