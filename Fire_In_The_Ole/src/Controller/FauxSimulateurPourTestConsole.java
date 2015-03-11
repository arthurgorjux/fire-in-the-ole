package Controller;
import Model.*;
import Model.stockage.JeuDeParametres;

public class FauxSimulateurPourTestConsole {
	ArchiveSimulation archive;
		
	public void jouerSimulation(CarteDeTerrain carte, JeuDeParametres parametres){
            
		Simulation simulation = new Simulation(parametres);
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
