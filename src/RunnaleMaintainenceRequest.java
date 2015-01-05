
import java.util.Map;
import java.util.TreeMap;
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
public class RunnaleMaintainenceRequest implements Runnable{
    Map<String, RepairToolInformation> repairToolInformationMap=new TreeMap<>();//need to initial
    Map<String, RepairMaterialInformation> repairMaterialInformationMap=new TreeMap<>();//need to initial
    Asset _asset;
    Warehouse _warehouse;
    Statistics _statistics;

    public RunnaleMaintainenceRequest(Asset _asset, Warehouse _warehouse ,Statistics statistics) {
        this._asset = _asset;
        this._warehouse = _warehouse;
        this._statistics=statistics;
    }
    
    @Override
    public void run() {
        for(Map.Entry<String, AssetContent> assetContent:_asset._assetContents.entrySet()){
            if(assetContent.getValue()._health<65){
                synchronized(_warehouse){
                    for(Map.Entry<String,Integer> repairTool:repairToolInformationMap.get(assetContent)._repairToolMap.entrySet()){
                       for(int i=0;i<repairTool.getValue();i++){
                            _warehouse.acquireTool(repairTool.getKey());
                            _statistics.addUsedRepairTool(repairTool.getKey(), repairTool.getValue());
                       }
                    }
                    for(Map.Entry<String,Integer> repairMaterial:repairMaterialInformationMap.get(assetContent)._repairMaterialMap.entrySet()){
                       for(int i=0;i<repairMaterial.getValue();i++){
                            _warehouse.consumeMaterial(repairMaterial.getKey());
                            _statistics.addConsumedMaterial(repairMaterial.getKey(), repairMaterial.getValue());
                       }    
                    }
                }    
                try {
                    Thread.sleep((long)assetContent.getValue().repairTime());
                } catch (InterruptedException ex) {
                    Logger.getLogger(RunnaleMaintainenceRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(Map.Entry<String,Integer> repairTool:repairToolInformationMap.get(assetContent)._repairToolMap.entrySet()){
                    for(int i=0;i<repairTool.getValue();i++)
                       _warehouse.releaseTool(repairTool.getKey());
                }
                assetContent.getValue()._health=100;
                
            }   
        }
        this._asset._status=AssetStatus.Available;
        System.out.println(_asset._name+"Available");
        this._asset.assetCountDownLatch.countDown();

    }
    
}
