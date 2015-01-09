package fr.gfg.fireintheole;

public class Simulateur {
	ArchiveSimulation archive;
	
	
	public void jouerSimulation(CarteDeTerrain carte) {
		Simulation simulation = new Simulation();
		while (!simulation.estTerminee()) {
			simulation.mettreAJour();
		}
		archive = simulation.getArchiveResultat();
	}
	
	public void rejouerSimulation() {
		archive.afficher();
	}
}
