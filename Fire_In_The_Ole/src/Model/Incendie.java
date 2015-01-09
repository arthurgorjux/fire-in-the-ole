package fr.gfg.fireintheole;

public class Incendie implements Entite{
	public int x;
	public int y;
	
	public Incendie(int origineX, int origineY) {
		x = origineX;
		y = origineY;
	}

	public void agir() {
		// TODO Auto-generated method stub
		
	}
	
	public EtatEntite getEtatEntite() {
		return new EtatEntite(x, y, "Flammes", "incendie");
	}
	
}
