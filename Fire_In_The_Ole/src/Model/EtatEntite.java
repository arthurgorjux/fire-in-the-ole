package Model;

import Model.pathfinding.Position;

public class EtatEntite {
	
        public Position getPosition(){
            return this.position;
        }
        
	public String getNom() {
		return nom;
	}
	public String getType() {
		return type;
	}
	private final String nom;
	private final String type;
        private Position position;
	
	public EtatEntite(int x, int y, String nom, String type) {
                this.position = new Position(x, y);
		this.nom = nom;
		this.type = type;
	}
	
	@Override
	/**
	 * Debugage log
	 */
	public String toString() {
		return " "+ position.getX() + "   " + position.getY() + "   " + nom+" de type "+type;
	}

}
