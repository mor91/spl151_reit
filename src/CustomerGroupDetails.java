
import java.util.Map;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public class CustomerGroupDetails {
    Map<String, RentalRequest> _rentalRequestMap;
    Map<String,Customer> _customersMap;
    String _groupManagerName;
    int numOfMembers;

    public CustomerGroupDetails(String _groupManagerName) {
        this._groupManagerName = _groupManagerName;
        this.numOfMembers=0;
        this._customersMap=new TreeMap<>();
        this._rentalRequestMap=new TreeMap<>();
    }
    
    public void addCostumer(Customer costumer){
        this._customersMap.put(costumer._name, costumer);
        this.numOfMembers+=1;
        
    }
    public void addRentalRequest(RentalRequest  rentalRequest){
        _rentalRequestMap.put(rentalRequest._id, rentalRequest);
    }
}
