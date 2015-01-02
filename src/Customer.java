/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public class Customer {
    String _name;
    String _vandalismType;
    int _minimumDamage;
    int _maximumDamage;
    
    
    public Customer(String _name, String _vandalismType, int _minimumDamage, int _maximumDamage) {
        this._name = _name;
        this._vandalismType = _vandalismType;
        this._minimumDamage = _minimumDamage;
        this._maximumDamage = _maximumDamage;
    }
    public int damageapercentage(){
        if(this._vandalismType.compareTo("FIXED")==0){
            return _maximumDamage+_minimumDamage/2;
        }
        else return 0;
    }
  
}
