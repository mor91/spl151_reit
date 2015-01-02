
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
public class RunnaleMaintainenceRequest implements Runnable{
    Map<String, RepairToolInformation> repairToolInformationMap=new TreeMap<>();//need to initial
    Map<String, RepairMaterialInformation> repairMaterialInformationMap=new TreeMap<>();//need to initial
    Asset _asset;
    Warehouse _warehouse;
    Map<String , Integer> consumedMaterials=new TreeMap<>();

    public RunnaleMaintainenceRequest(Asset _asset, Warehouse _warehouse) {
        this._asset = _asset;
        this._warehouse = _warehouse;
    }
    
    @Override
    public void run() {
        for(Map.Entry<String, AssetContent> assetContent:_asset._assetContents.entrySet()){
            if(assetContent.getValue()._health<65){
                synchronized(_warehouse){
                    for(Map.Entry<String,Integer> repairTool:repairToolInformationMap.get(assetContent)._repairToolMap.entrySet()){
                       for(int i=0;i<repairTool.getValue();i++)
                            _warehouse.acquireTool(repairTool.getKey());
                    }
                    for(Map.Entry<String,Integer> repairMaterial:repairMaterialInformationMap.get(assetContent)._repairMaterialMap.entrySet()){
                       for(int i=0;i<repairMaterial.getValue();i++){
                            _warehouse.consumeMaterial(repairMaterial.getKey());
                       }    
                    }
                }    
                //sleep(assetContent.getValue().repairTime())
                for(Map.Entry<String,Integer> repairTool:repairToolInformationMap.get(assetContent)._repairToolMap.entrySet()){
                    for(int i=0;i<repairTool.getValue();i++)
                       _warehouse.releaseTool(repairTool.getKey());
                }
                assetContent.getValue()._health=100;
            }   
        }
    }
    
}
