package Model;

public class Robot implements Entite{
	public int x;
	public int y;
	
	public Robot(int origineX, int origineY) {
		x = origineX;
		y = origineY;
	}

	public void agir() {
		// TODO Auto-generated method stub
		
	}
	
	public EtatEntite getEtatEntite() {
		return new EtatEntite(x, y, "Robot", "typeRobot");
	}
}

/*
etats robots 
arret --> EnDeplacement --> Extinction --> Arret

*/