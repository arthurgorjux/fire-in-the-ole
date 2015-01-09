package Model;

public class Robot implements Entite{
	public int x;
	public int y;
	public String typeRobot;
	public String nom;
	
	public Robot(int origineX, int origineY, String type, String nom) {
		x = origineX;
		y = origineY;
		typeRobot = type;
		this.nom = nom;
		
	}

	public void agir() {
		// TODO Auto-generated method stub
		
	}
	
	public EtatEntite getEtatEntite() {
		return new EtatEntite(x, y, this.nom, "typeRobot");
	}
}

/*
etats robots 
arret --> EnDeplacement --> Extinction --> Arret

*/