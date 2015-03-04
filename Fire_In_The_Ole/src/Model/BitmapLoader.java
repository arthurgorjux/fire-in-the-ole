/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author S219
 */
public class BitmapLoader {
    
    public BitmapLoader(){}
    
    /**
     * Extrait la valeur de la composante vert d'un pixel
     * @param imageBitmap L'iamge dont on veut lire un pixel
     * @param x L'indice x du pixel à lire
     * @param y L'indice y du pixel à lire
     * @return La valeur de la composante de vert d'un pixel, comprise entre 0 et 255
     */
    private int getValeurDeVertDuPixel(BufferedImage imageBitmap, int x, int y ) {
        int valeurRGB;
        int valeurVert;
        
        valeurRGB = imageBitmap.getRGB(x, y);
        valeurVert = (valeurRGB >> 8 ) & 0x000000FF;
        return valeurVert;
    }
    
     /**
     * Calcule une matrice de difficulté à partir d'une image Bitmap
     * @param imageBitmap L'image dont on veut extraire la matrice
     * @return La matrice de difficulté d'une image, sous la forme d'un tableau d'entiers à double entrée
     */
    private int[][] extraireMatriceDeDifficulte(BufferedImage imageBitmap) {
        int hauteurImage = imageBitmap.getHeight();
        int largeurImage = imageBitmap.getWidth();
        int[][] matriceDeDifficulte = new int[largeurImage][hauteurImage];
        
        for (int x = 1; x < largeurImage; x++){
            for (int y = 1; y < hauteurImage; y++){
                    matriceDeDifficulte[x][y] = getValeurDeVertDuPixel(imageBitmap, x, y);
            }
        }
        return matriceDeDifficulte;
    }
    
    /**
     * @return La matrice de difficulté d'une image, sous la forme d'un tableau d'entiers à double entrée
     * @throws IOException 
     */
    public int[][] lireCarteParDefaut() throws IOException {
        BufferedImage imageBitmap;
        
        imageBitmap = ImageIO.read(getClass().getResource("/IMG/test_1.bmp"));
        return extraireMatriceDeDifficulte(imageBitmap);
    }
    
    /**
     * @param chemin_acces_fichier Le chemin d'accès du fichier image Bitmap
     * @return La matrice de difficulté de l'image, sous la forme d'un tableau d'entiers à double entrée
     * @throws IOException 
     */
    public int[][] lireFichierBitmap(String chemin_acces_fichier) throws IOException {
        BufferedImage imageBitmap;

        imageBitmap = ImageIO.read(new File(chemin_acces_fichier));
        return extraireMatriceDeDifficulte(imageBitmap);
    }
    
}