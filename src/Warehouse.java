
import java.util.Map;

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

    @Override
    public void acquireTool(String toolName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void releaseTool(String toolName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consumeMaterial(String materialName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getToolCount(String toolName){
        return _repairTools.get(toolName).getQuantity();
    }
    public int getMaterialCount(String materialName){
        return _repairMaterials.get(materialName).getQuantity();
    }

}
