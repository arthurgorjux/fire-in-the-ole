package Model;

public class EtatEntite {
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
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
	
	public EtatEntite(int x, int y, String nom, String type) {
		this.x = x;
		this.y = y;
		this.nom = nom;
		this.type = type;
	}
	
	@Override
	/**
	 * Debugage log
	 */
	public String toString() {
		return "Entit� "+nom+" de type "+type+" en x="+x+" et y="+y;		
	}

}
