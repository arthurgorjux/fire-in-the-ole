package Model;

/**
 * Donn�es geographiques d'une carte
 * immutable
 * @author S219
 *
 */
public final class CarteDeTerrain {
	private final int carte [][];
	
        public int getHauteur() {
            return 38; //TODO placeholder
        }
        
        public int getLargeur() {
            return 18; //TODO placeholder
        }
        
        private int[][] analyserImageBitmap() {
            int[][] matrice = null;
            
            return matrice;
        }
        
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
	
	public int getDifficulte(int x, int y) {
		return carte[x][y];
	}
        
        public int[][] getCarte(){
            return carte.clone();
        }

}
