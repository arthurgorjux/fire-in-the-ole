/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import fr.fito.utilitaire.BitmapLoader;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author S219
 */
public class BitmapLoaderTest {

    /**
     * Test of lireFichierBitmap method, of class BitmapLoader.
     * @throws java.lang.Exception
     */
    @Test
    public void testLireFichierBitmap() throws Exception {
        System.out.println("lireFichierBitmap");
        BufferedImage imageBitmap;
        imageBitmap = ImageIO.read(getClass().getResource("/IMG/Test/mapTest.bmp"));
        BitmapLoader instance = new BitmapLoader();
        int[][] expResult = {
		{0  ,0  ,255,255,255},
                {0  ,255,255,255,255},
                {255,255,255,255,255},
                {255,255,255,255,0  },
                {255,255,255,0  ,0  },
		};
        int[][] result = instance.extraireMatriceDeDifficulte(imageBitmap);
        assertArrayEquals(expResult, result);
    }
    
}
