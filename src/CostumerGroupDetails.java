
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
public class CostumerGroupDetails {
    Map<String, RentalRequest> _rentalRequestMap=new TreeMap<>();
    Map<String,Costumer> _customersMap=new TreeMap<>();
    String _groupManagerName;

    public CostumerGroupDetails(String _groupManagerName) {
        this._groupManagerName = _groupManagerName;
    }
    
    public void addCostumer(Costumer costumer){
        _customersMap.put(costumer._name, costumer);
        
    }
    public void addRentalRequest(RentalRequest  rentalRequest){
        _rentalRequestMap.put(rentalRequest._id, rentalRequest);
    }
}
