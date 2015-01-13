package Controller;
import Model.*;
import View.Main;

public class Simulateur {
	ArchiveSimulation archive;
		
	public void jouerSimulation(CarteDeTerrain carte){
		Simulation simulation = new Simulation();
		while (!simulation.estTerminee()) {
			simulation.mettreAJour();
                        simulation.archiverTour();
		}
		archive = simulation.getArchiveResultat();
	}
	
	public void rejouerSimulation() {
		archive.afficher();
	}
}
