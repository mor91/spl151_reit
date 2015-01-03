
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

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
    Semaphore toolSemaphore;
    Semaphore materialShemaphore;

    public Warehouse() {
        _repairMaterials=new HashMap<>();
        _repairTools=new HashMap<>();
    }
    
    
    @Override
    public void acquireTool(String toolName) {
        _repairMaterials.get(toolName)._quantity--;
        
    }

    @Override
    public void releaseTool(String toolName) {
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
     public void semaphorePermits(){
         toolSemaphore=new Semaphore(_repairTools.size());
         materialShemaphore=new Semaphore(_repairMaterials.size());
     } 
}
