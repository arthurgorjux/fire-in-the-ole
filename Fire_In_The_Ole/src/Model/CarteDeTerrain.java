package Model;

import java.io.IOException;

/**
 * Données geographiques d'une carte.
 * Immutable
 * @author S219
 *
 */
public final class CarteDeTerrain {
	private final int carte [][];
	
        /**
         * Retourne la hauteur en cases de la carte.
         * @return la hauteur en cases de la carte.
         */
        public int getHauteur() {
            return  carte.length;
        }
        
        /**
         * Retourne la largeur en cases de la carte.
         * @return la largeur en cases de la carte.
         */
        public int getLargeur() {
            if (getHauteur() == 0) {
                return 0;
            } else {
                return carte[0].length;
            }
        }
        
        /**
         * Génère une carte par défaut.
         */
	public CarteDeTerrain() {
		int carteGeneree [][] = {
		{0,0,0,0,0,0,2,2,2,2,2,0,2,0,2,0,0,0},
                {0,40,40,0,0,0,0,0,0,0,0,2,0,2,0,0,0},
                {0,40,40,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,40,40,40,40,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,40,40,40,40,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,40,40,40,40,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,40,40,40,40,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,40,40,40,40,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,40,40,40,40,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,40,40,40,40,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,40,40,40,40,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,40,40,40,40,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0},
                {2,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,0}
		};
		carte = carteGeneree;
	}
        
        /**
         * Constructeur à partir d'un tableau d'entiers.
         * @param bitmap Un tableau d'entier à double entrée.
         */
        public CarteDeTerrain (int[][] bitmap){
            carte = bitmap;
        }
        
        /**
         * Constructeur à partir de l'URI d'un fichier bitmap de carte.
         * @param cheminAccesFichierBitmap
         * @throws java.io.IOException
         */
        public CarteDeTerrain(String cheminAccesFichierBitmap) throws IOException {
            BitmapLoader bitmapLoader = new BitmapLoader();
            carte = bitmapLoader.lireFichierBitmap(cheminAccesFichierBitmap);
        }
        
        /**
         * Retourne la difficultée d'un case dont on indique les coordonées.
         * @param x La coordonée x de la case dont on veut la difficulté.
         * @param y La coordonée y de la case dont on veut la difficulté.
         * @return La difficultée de la case dont on veut la difficulté.
         */
	public int getDifficulte(int x, int y) {
		return carte[x][y];
	}
        
        /**
         * Retourne une copie du tableau d'entier interne de la carte.
         * @return Une copie du tableau d'entier interne de la carte.
         */
        public int[][] getCarte(){
            return carte.clone();
        }
}
