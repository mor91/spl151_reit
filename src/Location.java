import java.lang.Math;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public class Location {
    int _x;
    int _y;

    public Location(int _x, int _y) {
        this._x = _x;
        this._y = _y;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }
    public double calculateDistance(Location other){//caiculate the distance between two locations 
        return Math.sqrt((Math.pow(Math.abs(this.getX()-other.getX()),2))+(Math.pow(Math.abs(this.getY()-other.getY()),2)));
    }
}
