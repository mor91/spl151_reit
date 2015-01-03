import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
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
    BlockingQueue<Asset> _damagedAssets;

    public Assets() {
    
    }
    

    public Assets(Map<String, Asset> _assets) {
        this._assets = _assets;
        this._damagedAssets=new LinkedBlockingQueue<>();
    }

    public void addAsset(Asset asset) {
        _damagedAssets.add(asset);
    }

    public Map<String, Asset> getAssets() {
        return _assets;
    }
    
    
}
