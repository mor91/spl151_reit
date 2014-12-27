
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
public class RepairToolInformation{

    
    private Map<String, Integer> _repairToolMap=new TreeMap<>();
   
    public RepairToolInformation() {
    }
   
    public void addRepairTool(String tool, int quantity){
        _repairToolMap.put(tool,quantity);
    }
    
}
