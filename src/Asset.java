import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public class Asset{
    String _name;
    String _type;
    Location _location;
    Map<String, AssetContent> _assetContents=new TreeMap();//key:name of assetContent 
    AssetStatus _status;
    int _costPerNight;
    int _size;
    CountDownLatch assetCountDownLatch;

    public Asset(String _name, String _type, Location _location,  int _costPerNight, int _size) {
        this._name = _name;
        this._type = _type;
        this._location = _location;
        this._status = AssetStatus.Available;
        this._costPerNight = _costPerNight;
        this._size = _size;
        this.assetCountDownLatch=new CountDownLatch(0);
    }
    public void addAssetContent(AssetContent assetContent){
        _assetContents.put(assetContent._name, assetContent);
    } 
    public CountDownLatch getCountDownLatch(){
        return assetCountDownLatch;
    }
    public void setCountDownLatch(){
        this.assetCountDownLatch=new CountDownLatch(1);
    }
}
