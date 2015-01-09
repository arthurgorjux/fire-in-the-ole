package Model;

import Observer.Observateur;

public class Manager implements Entite, Observateur{
	boolean besoinAnalyse;
	
	public Manager() {
		besoinAnalyse = false;
	}

	public void analyserSituation() {
		// se debrouille pour occuper tous les robots
		besoinAnalyse = false;
	}

	public void agir() {
		if (besoinAnalyse) {
			analyserSituation();
		}
	}

	@Override
	public void prevenir() {
		besoinAnalyse = true;
	}
}
