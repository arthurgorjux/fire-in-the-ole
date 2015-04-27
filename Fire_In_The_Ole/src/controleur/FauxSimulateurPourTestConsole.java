package controleur;

import modele.archivage.ArchiveSimulation;
import modele.Simulation;
import modele.stockage.JeuDeParametres;

/**
 * Un simulateur sans interface graphique.
 */
public class FauxSimulateurPourTestConsole {
	ArchiveSimulation archive;
        
        /**
         * Déroule une simulation à partir du JeuDeParametres et enregistre son résultat.
         * @param parametres Les paramètres initiaux de la simulation.
         */
	public void jouerSimulation(JeuDeParametres parametres){
		Simulation simulation = new Simulation(parametres);
		while (!simulation.estTerminee()) {
			simulation.mettreAJour();
                        simulation.archiverTour();
		}
		archive = simulation.getArchiveResultat();
	}
	
        /**
         * Affiche l'archive de la dernière simulation jouée.
         */
	public void rejouerSimulation() {
		archive.afficher();
	}
        
        
}
