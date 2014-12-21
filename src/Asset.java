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
public class Asset{
    String _name;
    String _type;
    Location _location;
    Map<String, AssetContent> _assetContents=new TreeMap();//key:name of assetContent 
    String _status;
    int _costPerNight;
    int _size;

    public Asset(String _name, String _type, Location _location,  int _costPerNight, int _size) {
        this._name = _name;
        this._type = _type;
        this._location = _location;
        this._status = "AVAILABLE";
        this._costPerNight = _costPerNight;
        this._size = _size;
    }
    public void addAssetContent(AssetContent assetContent){
        _assetContents.put(assetContent._name, assetContent);
    } 
    
    
}
