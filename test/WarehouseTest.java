/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.TestCase;

/**
 *
 * @author chen
 */
public class WarehouseTest extends TestCase {
    
    public WarehouseTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of acquireTool method, of class Warehouse.
     */
    public void testAcquireTool() {
        System.out.println("acquireTool");
        String toolName = "";
        Warehouse instance = new Warehouse();
        instance.acquireTool(toolName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of releaseTool method, of class Warehouse.
     */
    public void testReleaseTool() {
        System.out.println("releaseTool");
        String toolName = "";
        Warehouse instance = new Warehouse();
        instance.releaseTool(toolName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consumeMaterial method, of class Warehouse.
     */
    public void testConsumeMaterial() {
        System.out.println("consumeMaterial");
        String materialName = "";
        Warehouse instance = new Warehouse();
        instance.consumeMaterial(materialName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRepairTool method, of class Warehouse.
     */
    public void testAddRepairTool() {
        System.out.println("addRepairTool");
        RepairTool repairTool = null;
        Warehouse instance = new Warehouse();
        instance.addRepairTool(repairTool);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRepairMaterial method, of class Warehouse.
     */
    public void testAddRepairMaterial() {
        System.out.println("addRepairMaterial");
        RepairMaterial repairMaterial = null;
        Warehouse instance = new Warehouse();
        instance.addRepairMaterial(repairMaterial);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
