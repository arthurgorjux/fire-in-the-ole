package Model;

/**
 * Donn�es geographiques d'une carte
 * immutable
 * @author S219
 *
 */
public final class CarteDeTerrain {
	private final int carte [][];
	
	public CarteDeTerrain() {
		int carteGeneree [][] = {
		{0,0,0,0,0,0,2,2},
		{0,40,40,0,0,0,0},
		{0,40,40,2,2,0,0},
		{2,0,0,0,2,2,0,0},
                {2,0,0,0,2,2,0,0},
                {2,0,0,0,2,2,0,0},
                {2,0,0,0,2,2,0,0}
		};
		carte = carteGeneree;
	}
	
	public int getDifficulte(int x, int y) {
		return carte[x][y];
	}
        
        public int[][] getCarte(){
            return carte;
        }

}
