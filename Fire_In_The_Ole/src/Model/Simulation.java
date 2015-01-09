package Model;

import java.util.LinkedList;
import java.util.List;

public class Simulation {
	private final Manager manager;
	private final ArchiveSimulation archive;
	private final CarteDeTerrain carte;
	private final List<Robot> robots;
	private final List<Incendie> incendies;
	
	public Simulation() {
		manager = new Manager();
		archive = new ArchiveSimulation();
		carte = new CarteDeTerrain();
		
		robots = new LinkedList<Robot>();
		incendies = new LinkedList<Incendie>();
		
		// On ajoute les robots et les incendies aux listes
		// On inscrit le manager en tant qu'observateur sur tous les incendies et tous les robots.
	}
	
	public void mettreAJour() {
		manager.agir();
		for (Robot robot : robots) {
			robot.agir();
		}
		for (Incendie incendie : incendies) {
			incendie.agir();
		}
		
	}

	public void archiverTour() {
		final List<EtatEntite> etatsEntite;
		etatsEntite = new LinkedList<EtatEntite>();
		for (Robot robot : robots) {
			etatsEntite.add(robot.getEtatEntite());
		}
		for (Incendie incendie : incendies) {
			etatsEntite.add(incendie.getEtatEntite());
		}
		
		archive.addTour(new ArchiveTourSimulation(etatsEntite));
	}
	
	public boolean estTerminee() {
		// TODO Auto-generated method stub
		return false;
	}

	public ArchiveSimulation getArchiveResultat() {
		// TODO Auto-generated method stub
		return null;
	}

}
