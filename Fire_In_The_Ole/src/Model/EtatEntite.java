package Model;

import Model.pathfinding.Position;

public class EtatEntite {
	private final String nom;
	private final String type;
        private final Position position;	
    
	public int getX() {
            return position.getX();
	}
	public int getY() {
            return position.getY();
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
	
	public EtatEntite(Position position, String nom, String type) {
                this.position = position;
		this.nom = nom;
		this.type = type;
	}
        
        public EtatEntite(int x, int y, String nom, String type) {
	this(new Position(x,y), nom, type);
	}
	
	@Override
	/**
	 * Debugage log
	 */
	public String toString() {
		return " "+ getX() + "   " + getY() + "   " + nom+" de type "+type;
	}

}
