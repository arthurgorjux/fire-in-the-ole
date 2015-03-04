/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author S219
 */
public class BitmapLoader {
    private int[][] carte;
    private BufferedImage  img;
    private HashMap<Integer,Integer> mapping;
    
    public BitmapLoader(){
        this.initMapping();
        try {
            img = ImageIO.read(getClass().getResource("/IMG/test_1.bmp"));
        } catch (Exception e) {
        }
        int height = img.getHeight();
        int width = img.getWidth();
        carte = new int[width][height];
        int rgb;
        int red;
        int green;
        int blue;
        
        for (int h = 1; h<height; h++){
            for (int w = 1; w<width; w++){
                rgb = img.getRGB(w, h);
                green = (rgb >> 8 ) & 0x000000FF;
                carte[w][h] = this.mapping.get(green);
            }
        }
    }
    
    public BitmapLoader(String filename){
        this.initMapping();
        try {
            File file = new File(filename);
            System.out.println("File : " + file);
            img = ImageIO.read(file);
        } catch (Exception e) {
        }
        int height = img.getHeight();
        int width = img.getWidth();
        carte = new int[width][height];
        int rgb;
        int red;
        int green;
        int blue;
        
        for (int h = 1; h<height; h++){
            for (int w = 1; w<width; w++){
                rgb = img.getRGB(w, h);
                green = (rgb >> 8 ) & 0x000000FF;
                carte[w][h] = this.mapping.get(green);
            }
        }
    }
    
    public int[][] getCarte(){
        return this.carte;
    }

    private void initMapping() {
        this.mapping = new HashMap<>();
        this.mapping.put(51, 40);
        this.mapping.put(153, 2);
        this.mapping.put(255, 0);
    }
}