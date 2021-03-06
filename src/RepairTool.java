
import java.util.concurrent.Semaphore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public class RepairTool {
    String _name;
    int _quantity;
    Semaphore toolSemaphore;

    public RepairTool(String _name, int _quantity) {
        this._name = _name;
        this._quantity = _quantity;
        toolSemaphore=new Semaphore(_quantity);
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public int getQuantity() {
        return _quantity;
    }

    public void setQuantity(int _quantity) {
        this._quantity = _quantity;
    }
    public void acquireTool() throws InterruptedException{
        toolSemaphore.acquire();
    }
    public void releaseTool(){
        toolSemaphore.release();
    }
    
}
