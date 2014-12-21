
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
public class RepairMaterialInformation {
    Map<String, Integer> _repairMaterialMap=new TreeMap<>();    
    
    public RepairMaterialInformation() {
    }

    public void addRepairMaterial(String material,int quantity){
       _repairMaterialMap.put(material, quantity);
    }
    
}
