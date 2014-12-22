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
    //collection of clerk details
    //collection of costumer group details
    Assets _assets;
    Warehouse _warehouse;
    //collection of repair toolInformation
    //collection of repair materialInformation

    public Management( Warehouse _warehouse,Assets _assets) {
        this._assets = _assets;
        this._warehouse = _warehouse;
    }
    
    public void addClerk(ClerkDetails clerkDetails){
        
    } 
    public void addCostumerGroup(CostumerGroupDetails costumerGroupDetails){
        
    }
    public void addItemRepairTool(String contentName,RepairToolInformation repairToolInformation){
        
    }
    public void addItemRepairMaterial(String contentName, RepairMaterialInformation repairMaterialInformation){
        
    }
}
