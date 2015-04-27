package Model;

import modele.CarteDeTerrain;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gael
 */
public class CarteDeTerrainTest {
    
    public CarteDeTerrainTest() {
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
     * Test of getHauteur method, of class CarteDeTerrain.
     */
    @Test
    public void testGetHauteur() {
        System.out.println("getHauteur");
        CarteDeTerrain instance = new CarteDeTerrain();
        int expResult = 0;
        int result = instance.getHauteur();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLargeur method, of class CarteDeTerrain.
     */
    @Test
    public void testGetLargeur() {
        System.out.println("getLargeur");
        CarteDeTerrain instance = new CarteDeTerrain();
        int expResult = 0;
        int result = instance.getLargeur();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDifficulte method, of class CarteDeTerrain.
     */
    @Test
    public void testGetDifficulte() {
        System.out.println("getDifficulte");
        int x = 0;
        int y = 0;
        CarteDeTerrain instance = new CarteDeTerrain();
        int expResult = 0;
        int result = instance.getDifficulte(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCarte method, of class CarteDeTerrain.
     */
    @Test
    public void testGetCarte() {
        System.out.println("getCarte");
        CarteDeTerrain instance = new CarteDeTerrain();
        int[][] expResult = null;
        int[][] result = instance.getCarte();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
