package Controller;
import Model.*;
import View.Main;

public class Simulateur {
	ArchiveSimulation archive;
		
	public void jouerSimulation(CarteDeTerrain carte, Main window){
		Simulation simulation = new Simulation();
		while (!simulation.estTerminee()) {
			simulation.mettreAJour();
                        window.setMap(simulation.archiverTour());
		}
		archive = simulation.getArchiveResultat();
	}
	
	public void rejouerSimulation() {
		archive.afficher();
	}
}
