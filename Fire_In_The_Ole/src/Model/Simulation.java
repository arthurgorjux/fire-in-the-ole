package Model;

import java.util.LinkedList;
import java.util.List;

public class Simulation {
	private final Manager manager;
	private final ArchiveSimulation archive;
	private final CarteDeTerrain carte;
	private final List<Robot> robots;
	private final List<Incendie> incendies;
        private final List<Incendie> incendiesFutur;
        private final List<Incendie> incendiesEteints;
	//temp
	private int cpttest = 1;
	
	public Simulation() {
		manager = new Manager();
		archive = new ArchiveSimulation();
		carte = new CarteDeTerrain();
		
		robots = new LinkedList<>();
		incendies = new LinkedList<>();
                incendiesFutur = new LinkedList<>();
                incendiesEteints = new LinkedList<>();
		
		// On ajoute les robots et les incendies aux listes
		robots.add(new Robot(1, 1,"typerobotbidon","Toto"));
		robots.add(new Robot(2, 2,"typerobotbidon","Titi"));
		incendies.add(new Incendie(5,5, this));
                incendies.add(new Incendie(6,5, this));
		// On inscrit le manager en tant qu'observateur sur tous les incendies et tous les robots.
                for (Robot robot : robots) {
			robot.ajouterObservateur(manager);
		}
                for (Incendie incendie : incendies) {
			incendie.ajouterObservateur(manager);
		}
	}
	
	public void mettreAJour() {
            // on fait apparaitre les incendies supplémentaires
            incendies.addAll(incendiesFutur);
            incendiesFutur.removeAll(incendiesFutur);
            
            incendies.removeAll(incendiesEteints);
            incendiesEteints.removeAll(incendiesEteints);
            
		manager.agir();
		for (Robot robot : robots) {
			robot.agir();
		}
		for (Incendie incendie : incendies) {
			incendie.agir();
		}
		
	}

	public ArchiveTourSimulation archiverTour() {
		final List<EtatEntite> etatsEntite;
		etatsEntite = new LinkedList<EtatEntite>();
		for (Robot robot : robots) {
			etatsEntite.add(robot.getEtatEntite());
		}
		for (Incendie incendie : incendies) {
			etatsEntite.add(incendie.getEtatEntite());
		}
		
		ArchiveTourSimulation tour = new ArchiveTourSimulation(etatsEntite);
		archive.addTour(tour);
		return tour;
	}
	
	public boolean estTerminee() {
		// TODO Auto-generated method stub
		cpttest = cpttest +1;
		if (cpttest < 15) {
			return false;
		} else {
			return true;
		}
	}

	public ArchiveSimulation getArchiveResultat() {
		// TODO Auto-generated method stub
		return archive;
	}

        public boolean ajouterFeu(int intensite, int x, int y) {
            if (estUnEmplacementLibre(x, y)) {
                incendiesFutur.add(new Incendie(x,y, this));
                return true;
            }
            return false;
        }

        public boolean estUnEmplacementLibre(int x, int y) {
            for (Robot robot : robots) {
		if (robot.x == x && robot.y == y) {
                    return false;
                }
            }
            for (Incendie incendie : incendies) {
		if (incendie.x == x && incendie.y == y) {
                    return false;
                }
            }
            return true;
        }
        
    void eteindreFeu(Incendie feu) {
        incendiesEteints.add(feu);
    }
}
