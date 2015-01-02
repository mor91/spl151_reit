
import java.util.Random;
import java.util.concurrent.Callable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public class CallableSimulateStayInAsset implements Callable{
    private Customer _customer;
    RentalRequest currentRentalRequest;

    public CallableSimulateStayInAsset(Customer _customer, RentalRequest currentRentalRequest) {
        //sleep(stayInAsset)
        this._customer = _customer;
        this.currentRentalRequest=currentRentalRequest;
    }
    
    @Override
    public Object call() throws Exception {
        Thread.sleep(this.currentRentalRequest._durationOfStay*2400);
        return this.damagePercentage();
    }
    private double damagePercentage(){
        double damageaPer=0;
        if(_customer._vandalismType.compareTo("ARBITRARY")==0){
            Random rand =new Random();
            damageaPer = rand.nextInt((_customer._maximumDamage - _customer._minimumDamage) + 1) + _customer._minimumDamage;
        }
        if(_customer._vandalismType.compareTo("FIXED")==0){
            damageaPer=(_customer._maximumDamage+_customer._minimumDamage)/2;
        }
        if(_customer._vandalismType.compareTo("NONE")==0){
            damageaPer=0.5;
        }
        return damageaPer;
    }
}
