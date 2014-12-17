/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chen
 */
public class WarehouseTest {
    
    public WarehouseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of acquireTool method, of class Warehouse.
     */
    @Test
    public void testAcquireTool() {
        System.out.println("acquireTool");
        String toolName = "hammer";
        Warehouse instance = new Warehouse();
        
        int toolCount=instance.getToolCount(toolName);
        instance.acquireTool(toolName);
        assertEquals(toolCount-1, instance.getToolCount(toolName));
    }

    /**
     * Test of releaseTool method, of class Warehouse.
     */
    @Test
    public void testReleaseTool() {
        System.out.println("releaseTool");
        String toolName = "hammer";
        Warehouse instance = new Warehouse();
        
        int toolCount=instance.getToolCount(toolName);
        instance.releaseTool(toolName);
        assertEquals(toolCount+1, instance.getToolCount(toolName));

    }

    /**
     * Test of consumeMaterial method, of class Warehouse.
     */
    @Test
    public void testConsumeMaterial() {
        System.out.println("consumeMaterial");
        String materialName = "concrete";
        Warehouse instance = new Warehouse();
        
        int materialCount=instance.getMaterialCount(materialName);
        instance.consumeMaterial(materialName);
        assertTrue(materialCount<instance.getMaterialCount(materialName));
    }

    
}
