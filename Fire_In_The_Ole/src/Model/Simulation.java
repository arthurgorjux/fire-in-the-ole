package Model;

import java.util.LinkedList;
import java.util.List;
import Model.pathfinding.Position;
import Model.stockage.InitialisationIncendie;
import Model.stockage.InitialisationRobot;
import Model.stockage.JeuDeParametres;

public class Simulation {
        private final SensVent sens_du_vent;
	private final Manager manager;
	private final ArchiveSimulation archive;
	private final CarteDeTerrain carte;
	private final List<Robot> robots;
	private final List<Incendie> incendies;
        private final List<Incendie> incendiesFutur;
        private final List<Incendie> incendiesEteints;
	private int duree = 1;
	
        public Simulation(JeuDeParametres parametres) {
		manager = new Manager(this);
		archive = new ArchiveSimulation();
		carte = new CarteDeTerrain();
                sens_du_vent = parametres.getSens_du_vent();
		robots = new LinkedList<>();
		incendies = new LinkedList<>();
                incendiesFutur = new LinkedList<>();
                incendiesEteints = new LinkedList<>();
		
		// On ajoute les robots et les incendies aux listes
                for (InitialisationIncendie departs_incendie : parametres.getDeparts_incendie()) {
                    incendies.add(new Incendie(departs_incendie, this));
                }
                
                for (InitialisationRobot depart_robot : parametres.getDeparts_robot()) {
                    robots.add(new Robot(depart_robot, "NomRandom", this)); //TODO gerer generation de noms
                }
                
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
            System.out.println("ROBOTS MAJ=====");
            System.out.println(robots);
            System.out.println("INCENDIES MAJ=====");
            System.out.println(incendies);
            System.out.println("INCENDIES ETEINTS=====");
            System.out.println(incendiesEteints);
            if(!incendies.isEmpty()){
                manager.agir();
                for (Robot robot : robots) {
                        robot.agir();
                }
                for (Incendie incendie : incendies) {
                        incendie.agir();
                }
            }
	}

	public void archiverTour() {
		final List<EtatEntite> etatsEntite;
		etatsEntite = new LinkedList<>();
		for (Robot robot : robots) {
			etatsEntite.add(robot.getEtatEntite());
		}
		for (Incendie incendie : incendies) {
			etatsEntite.add(incendie.getEtatEntite());
		}
                
		ArchiveTourSimulation tour = new ArchiveTourSimulation(etatsEntite);
		archive.addTour(tour);
//		return tour;
	}
	

	public boolean estTerminee() {            
            //on termine la simulation si la liste des incendies est vide
            //ou au bout d'un certain nombre de tours dans un premier temps...
            duree = duree +1;
            if (incendies.isEmpty())
                return true;
            else
                return (duree>=60);
        }

	public ArchiveSimulation getArchiveResultat() {
		// TODO Auto-generated method stub
		return archive;
	}

        public boolean ajouterFeu(int intensite, int x, int y) {
            if (estUnEmplacementLibre(new Position(x, y))) {
                incendiesFutur.add(new Incendie(x,y, this));
                return true;
            }
            return false;
        }
        
        public boolean ajouterFeu(int intensite, Position position) {
            if (estUnEmplacementLibre(position)) {
                incendiesFutur.add(new Incendie(position, this));
                return true;
            }
            return false;
        }

        /**
         * Indique si un emplacement ne contient ni incendies ni robots
         * @param emplacement
         * @return 
         */
        public boolean estUnEmplacementLibre(Position emplacement) {
            return !contientUnIncendie(emplacement) && !contientUnRobot(emplacement);
        }
        
        /**
         * Indique si un emplacement contient un incendie
         * @param emplacement
         * @return 
         */
        public boolean contientUnIncendie(Position emplacement) {
            for (Incendie incendie : incendies) {
		if (incendie.getPosition().equals(emplacement)) {
                    System.out.println(emplacement + " Ya un feu ici");
                    return true;
                }
            }
            return false;
        }
        
        public boolean contientUnRobot(Position emplacement) {
            for (Robot robot : robots) {
		if (robot.getPosition().equals(emplacement)) {
                    return true;
                }
            }
            return false;
        }


    public List<Incendie> getIncendies() {
        return incendies;
    }

    public List<Robot> getRobots() {
        return robots;
    }
    
    public Manager getManager(){
        return this.manager;
    }
    
    public CarteDeTerrain getCarte(){
        return this.carte;
    }
    
    void eteindreFeu(Incendie feu) {
        incendiesEteints.add(feu);
        System.out.println("ETEINTS FEU : " + feu);
        System.out.println("FEU ETEINTS SIMU===");
        System.out.println(incendiesEteints);
    }

    Incendie getIncendieAt(Position position) {
        for (Incendie incendie : incendies) {
            if(incendie.getPosition().equals(position)) {
                return incendie;
            }
        }
            return null;
    }
}
