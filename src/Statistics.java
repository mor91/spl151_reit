
import java.util.LinkedList;
import java.util.List;
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
public class Statistics {
    int _moneyGained;
    List<RentalRequest> _rentalRequests;
    Map<String, Integer> _usedRepairToolMap;
    Map<String, Integer> _consumedRepairMaterial;

    public Statistics() {
        this._moneyGained = 0;
        this._rentalRequests = new  LinkedList<>();
        this._usedRepairToolMap = new TreeMap<>();
        this._consumedRepairMaterial = new TreeMap<>();
    }

    public void addMoneyGained(int _moneyGained) {
        this._moneyGained += _moneyGained;
    }
    public void addRentalRequest(RentalRequest rentalRequest){
        _rentalRequests.add(rentalRequest);
    }
    public void addUsedRepairTool(String repairToolName, int quantity){
        if(_usedRepairToolMap.containsKey(repairToolName))
            _usedRepairToolMap.put(repairToolName, _usedRepairToolMap.get(repairToolName) + quantity);
        else _usedRepairToolMap.put(repairToolName, quantity);
    }
    public void addConsumedMaterial(String repairMaterialName, int quantity){
         if(_consumedRepairMaterial.containsKey(repairMaterialName))
            _consumedRepairMaterial.put(repairMaterialName, _consumedRepairMaterial.get(repairMaterialName) + quantity);
        else _consumedRepairMaterial.put(repairMaterialName, quantity);
    
    } 
    
    
}
