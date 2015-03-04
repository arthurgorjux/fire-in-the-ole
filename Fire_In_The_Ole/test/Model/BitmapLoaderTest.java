/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author S219
 */
public class BitmapLoaderTest {
    
    public BitmapLoaderTest() {
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
     * Test of lireCarteParDefaut method, of class BitmapLoader.
     */
    @Test
    public void testLireCarteParDefaut() throws Exception {
        System.out.println("lireCarteParDefaut");
        BitmapLoader instance = new BitmapLoader();
        int[][] expResult = null;
        int[][] result = instance.lireCarteParDefaut();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lireFichierBitmap method, of class BitmapLoader.
     */
    @Test
    public void testLireFichierBitmap() throws Exception {
        System.out.println("lireFichierBitmap");
        String chemin_acces_fichier = "";
        BitmapLoader instance = new BitmapLoader();
        int[][] expResult = null;
        int[][] result = instance.lireFichierBitmap(chemin_acces_fichier);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
