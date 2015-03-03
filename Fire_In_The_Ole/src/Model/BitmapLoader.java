/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author S219
 */
public class BitmapLoader {
    private int[][] carte;
    private BufferedImage  img;
    
    public BitmapLoader(){
        try {
            img = ImageIO.read(getClass().getResource("/IMG/test_1.bmp"));
        } catch (Exception e) {
        }
        int height = img.getHeight();
        int width = img.getWidth();

        int rgb;
        int red;
        int green;
        int blue;

        double percentPixel = 0;
        for (int h = 1; h<height; h++){
            for (int w = 1; w<width; w++){
                rgb = img.getRGB(w, h);
                System.out.println("Code RGBV : " + rgb);
            }
        }
    }
}
