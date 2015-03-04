package Model;

import Model.pathfinding.Position;

public class EtatEntite {
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
        
        public Position getPosition(){
            return this.position;
        }
        
	public String getNom() {
		return nom;
	}
	public String getType() {
		return type;
	}
	private final int x;
	private final int y;
	private final String nom;
	private final String type;
        private Position position;
	
	public EtatEntite(int x, int y, String nom, String type) {
		this.x = x;
		this.y = y;
                this.position = new Position(x, y);
		this.nom = nom;
		this.type = type;
	}
	
	@Override
	/**
	 * Debugage log
	 */
	public String toString() {
		return " "+ x + "   " + y + "   " + nom+" de type "+type;
	}

}
