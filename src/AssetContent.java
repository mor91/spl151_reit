/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public class AssetContent{
    String _name;
    double _health;
    int _repairCostMultiplier;
   
    public double repairTime(){//calculate the time it takes the item to e repaired
        return (100-_health)*_repairCostMultiplier;
    } 
    
}
