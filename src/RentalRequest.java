
import java.util.concurrent.CountDownLatch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public class RentalRequest {
    String _id;
    String _assetType;
    int _assetSize;
    int _durationOfStay;
    Asset _asset;
    RentalRequestStatus _requestStatus;
    CountDownLatch rentalRequestCountDownLatch; 

    public RentalRequest(String _id, String _assetType, int _assetSize, int _durationOfStay) {
        this._id = _id;
        this._assetType = _assetType;
        this._assetSize = _assetSize;
        this._durationOfStay = _durationOfStay;
        this._requestStatus=RentalRequestStatus.Incomplete;
        this.rentalRequestCountDownLatch=new CountDownLatch(1);
    }
    public CountDownLatch getCountDownLatch(){
        return rentalRequestCountDownLatch;
    }
    public String toString(){
        StringBuilder rentalRequestStringBuilder=new StringBuilder();
        rentalRequestStringBuilder.append(_id);
        rentalRequestStringBuilder.append(": ");
        rentalRequestStringBuilder.append(_requestStatus.toString());
        return(rentalRequestStringBuilder.toString());
    }
}
