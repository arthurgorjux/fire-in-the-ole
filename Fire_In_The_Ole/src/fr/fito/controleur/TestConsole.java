package fr.fito.controleur;

import fr.fito.modele.archivage.ArchiveSimulation;
import fr.fito.modele.Simulation;
import fr.fito.modele.parametrage.JeuDeParametres;

/**
 * Un controleur permettant de manipuler une simulation de facon très basique pour les tests.
 */
public class TestConsole {
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
