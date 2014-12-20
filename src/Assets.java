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
public class Assets{
    Map<String, Asset> _assets;
    Map<String, Asset> _damagedAssets;

    public Assets() {
    
    }
    

    public Assets(Map<String, Asset> _assets) {
        this._assets = _assets;
    }

    public void addAsset(Asset asset) {
        //this._assets.put(asset._name, asset);
    }

    public Map<String, Asset> getAssets() {
        return _assets;
    }
    
}
