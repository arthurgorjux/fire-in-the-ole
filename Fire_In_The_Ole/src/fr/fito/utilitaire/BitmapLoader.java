package fr.fito.utilitaire;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Cette classe permet d'extraire d'un fichier bitmap un tableau d'entrée. Elle pourrait tout à fait être statique.
 */
public class BitmapLoader {

    /**
     * Constructeur. Ne fait rien.
     */
    public BitmapLoader() {
    }

    /**
     * Retourne la valeur de la composante vert du pixel aux coordonées passées en paramètre (comprise entre 0 et 255).
     * @param imageBitmap L'iamge dont on veut lire un pixel.
     * @param x L'indice x du pixel à lire.
     * @param y L'indice y du pixel à lire.
     * @return La valeur de la composante de vert du pixel aux coordonées passées en paramètre, (comprise entre 0 et 255).
     */
    private int getValeurDeVertDuPixel(BufferedImage imageBitmap, int x, int y) {
        int valeurRGB;
        int valeurVert;

        valeurRGB = imageBitmap.getRGB(x, y);
        valeurVert = (valeurRGB & 0x0000ff00) >> 8;
        return valeurVert;
    }

    /**
     * Retourne une matrice de difficulté calculée à partir d'une image Bitmap.
     * @param imageBitmap L'image dont on veut extraire la matrice.
     * @return La matrice de difficulté d'une image, sous la forme d'un tableau d'entiers à double entrée.
     */
    public int[][] extraireMatriceDeDifficulte(BufferedImage imageBitmap) {
        int hauteurImage = imageBitmap.getHeight();
        int largeurImage = imageBitmap.getWidth();
        int[][] matriceDeDifficulte = new int[largeurImage][hauteurImage];

        for (int x = 0; x < largeurImage; x++) {
            for (int y = 0; y < hauteurImage; y++) {
                matriceDeDifficulte[x][y] = getValeurDeVertDuPixel(imageBitmap, x, y);
            }
        }
        return matriceDeDifficulte;
    }

    /**
     * @return La matrice de difficulté d'une image, sous la forme d'un tableau d'entiers à double entrée.
     * @throws IOException
     */
    public int[][] lireCarteParDefaut() throws IOException {
        BufferedImage imageBitmap;

        imageBitmap = ImageIO.read(getClass().getResource("/IMG/test_1.bmp"));
        return extraireMatriceDeDifficulte(imageBitmap);
    }

    /**
     * @param chemin_acces_fichier Le chemin d'accès du fichier image Bitmap
     * @return La matrice de difficulté de l'image, sous la forme d'un tableau
     * d'entiers à double entrée
     * @throws IOException
     */
    public int[][] lireFichierBitmap(String chemin_acces_fichier) throws IOException {
        BufferedImage imageBitmap;

        imageBitmap = ImageIO.read(new File(chemin_acces_fichier));
        return extraireMatriceDeDifficulte(imageBitmap);
    }

}
