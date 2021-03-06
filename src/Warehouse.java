
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
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
public class Warehouse implements IWarehouse{
    Map<String, RepairTool> _repairTools;
    Map<String, RepairMaterial> _repairMaterials;


    public Warehouse() {
        _repairMaterials=new HashMap<>();
        _repairTools=new HashMap<>();
    }
    
    
    @Override
    public void acquireTool(String toolName) {
        try {
            _repairTools.get(toolName).acquireTool();
        } catch (InterruptedException ex) {
            Logger.getLogger(Warehouse.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        _repairTools.get(toolName)._quantity--;
        
    }

    @Override
    public void releaseTool(String toolName) {
        _repairTools.get(toolName).releaseTool();
        _repairTools.get(toolName)._quantity++;
    }

    @Override
    public void consumeMaterial(String materialName) {
        _repairMaterials.get(materialName)._quantity--;
    }
    public void addRepairTool(RepairTool repairTool){
        _repairTools.put(repairTool._name, repairTool);
    }
    public void addRepairMaterial(RepairMaterial repairMaterial){
        _repairMaterials.put(repairMaterial._name, repairMaterial);
                
    }

}
