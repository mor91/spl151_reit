
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
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
        while(_numberOfRentalRequests.get()>0){
            int totalTimeOfSleep=0;
            while(totalTimeOfSleep<8 ){
                RentalRequest cuurentRentalRequest=null;
                try {
                    cuurentRentalRequest = _rentalRequestQueue.poll(8-totalTimeOfSleep, TimeUnit.SECONDS);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RunnableClerk.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(cuurentRentalRequest==null){
                    break;
                }
                for (Map.Entry<String, Asset> asset : _assets._assets.entrySet()) {
                    if(asset.getValue()._type.equalsIgnoreCase(cuurentRentalRequest._assetType) && asset.getValue()._size>=cuurentRentalRequest._assetSize && asset.getValue()._status==AssetStatus.Available){
                            asset.getValue().setCountDownLatch();
                            cuurentRentalRequest._asset=asset.getValue();
                            asset.getValue()._status=AssetStatus.Booked;
                            System.out.println(asset.getValue()._name+ " Booked");

                        break;
                    }

                }
                if(cuurentRentalRequest._asset==null){
                    _rentalRequestQueue.add(cuurentRentalRequest);
                    System.out.println("no asset found");
                    continue;
                }
                cuurentRentalRequest._requestStatus=RentalRequestStatus.Fulfilled;
                _numberOfRentalRequests.decrementAndGet();
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
                System.out.println("waiting: " + cyclicBarrier.getNumberWaiting());
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
    
}
