
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

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
    Map<Integer, RentalRequest> _rentalRequestMap=new TreeMap<Integer, RentalRequest>();
    AtomicInteger _numberOfRentalRequests;
    private final CountDownLatch countDownLatch;
    private final BlockingQueue blockingQueue;

    public RunnableClerk(ClerkDetails _clarkDetails, CountDownLatch countDownLatch, BlockingQueue blockingQueue ) {
        this._clarkDetails = _clarkDetails;
        this.countDownLatch = countDownLatch;
        this.blockingQueue = blockingQueue;
        
    }

    
    @Override
    public void run() {
        
        int totalTimeOfSleep=0;
        while(totalTimeOfSleep<8 && _numberOfRentalRequests.doubleValue()>0){
           RentalRequest cuurentRentalRequest=_rentalRequestMap.get(_numberOfRentalRequests);
           _numberOfRentalRequests.getAndIncrement();
           //find suitale asset, when found change asset to Booked 
           cuurentRentalRequest._requestStatus="INPROGRESS";
           long distance =(long) _clarkDetails._location.calculateDistance(null);//calculate distance from asset location
           //this.sleep(distance*2000);
           totalTimeOfSleep+=distance;
           //cuurentRentalRequest._asset=asset;
           cuurentRentalRequest.getCountDownLatch().countDown();
        }
        countDownLatch.countDown();
    }
    
}
