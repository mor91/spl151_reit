/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chen
 */
public interface IWarehouse {
    public void acquireTool(String toolName);
    public void releaseTool(String toolName);
    public void consumeMaterial(String materialName);
}
