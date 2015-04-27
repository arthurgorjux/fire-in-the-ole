package fr.fito.modele;

import fr.fito.modele.archivage.EtatEntite;
import fr.fito.modele.archivage.ArchiveSimulation;
import fr.fito.modele.archivage.ArchiveTourSimulation;
import java.util.LinkedList;
import java.util.List;
import fr.fito.modele.pathfinding.Position;
import fr.fito.modele.parametrage.InitialisationIncendie;
import fr.fito.modele.parametrage.InitialisationRobot;
import fr.fito.modele.parametrage.JeuDeParametres;

/**
 * La simulation de l'extinction d'incendies par des robots sur un carte.
 */
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

    /**
     * Constucteur à partir d'un JeuDeParametres.
     * @param parametres Le jeu de paramètres initiaux de la simulation.
     */
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

    /**
     * Fait progresser la simulation d'un tour.
     */
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
        if (!incendies.isEmpty()) {
            manager.agir();
            for (Robot robot : robots) {
                robot.agir();
            }
            for (Incendie incendie : incendies) {
                incendie.agir();
            }
        }
    }

    /**
     * Archive l'état de la simulation.
     */
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

    /**
     * Retourne si la simulation est terminée.
     * @return True si la simulation est terminé.
     */
    public boolean estTerminee() {
        //on termine la simulation si la liste des incendies est vide
        //ou au bout d'un certain nombre de tours dans un premier temps...
        duree = duree + 1;
        if (incendies.isEmpty()) {
            return true;
        } else {
            return (duree >= 60);
        }
    }

    /**
     * Retourne l'archive de la simulation terminée.
     * @return L'archive de la simulation terminée.
     */
    public ArchiveSimulation getArchiveResultat() {
        // TODO Auto-generated method stub
        return archive;
    }

    /**
     * Essaie d'ajouter un incendie à la position indiquée, indique s'il a pu être ajouté.
     * @param intensite L'intensité de départ de l'incendie.
     * @param x La coordonné x de l'incendie à ajouter.
     * @param y La coordonné y de l'incendie à ajouter.
     * @return True si l'incendie à pu être ajouté.
     */
    public boolean ajouterFeu(int intensite, int x, int y) {
        if (estUnEmplacementLibre(new Position(x, y))) {
            incendiesFutur.add(new Incendie(x, y, this));
            return true;
        }
        return false;
    }

    /**
     * Essaie d'ajouter un incendie à la position indiquée, indique s'il a pu être ajouté.
     * @param intensite L'intensité de départ de l'incendie.
     * @param position La position de l'incendie à ajouter.
     * @return True si l'incendie à pu être ajouté.
     */
    public boolean ajouterFeu(int intensite, Position position) {
        if (estUnEmplacementLibre(position)) {
            incendiesFutur.add(new Incendie(position, this));
            return true;
        }
        return false;
    }

    /**
     * Retourne si l'emplacement en paramètres ne contient ni incendies ni robots.
     * @param emplacement La position à tester.
     * @return True si l'emplacement est libre.
     */
    public boolean estUnEmplacementLibre(Position emplacement) {
        return !contientUnIncendie(emplacement) && !contientUnRobot(emplacement);
    }

    /**
     * Retourne si l'emplacement en paramètres contient un incendie.
     * @param emplacement La Position à tester.
     * @return True si l'emplacement contient un incendie.
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

    /**
     * Retourne si un robot est présent à l'emplacement en paramètres.
     * @param emplacement La position à tester.
     * @return True si un robot est présent à cette Position.
     */
    public boolean contientUnRobot(Position emplacement) {
        for (Robot robot : robots) {
            if (robot.getPosition().equals(emplacement)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne la liste des incendies.
     * @return La liste des incendies.
     */
    public List<Incendie> getIncendies() {
        return incendies;
    }

    /**
     * Retourne la liste des robots.
     * @return La liste des robots.
     */
    public List<Robot> getRobots() {
        return robots;
    }

    /**
     * Retourne le manager de la simulation.
     * @return Le manager de la simulation.
     */
    public Manager getManager() {
        return this.manager;
    }

    /**
     * Retourne la carte de la simulation.
     * @return La carte de la simulation.
     */
    public CarteDeTerrain getCarte() {
        return this.carte;
    }

    /**
     * Demande le retrait de l'incendie en paramètres de la simulation pour cause d'extinction.
     * @param feu L'Incendie à retirer de la simulation.
     */
    void eteindreFeu(Incendie feu) {
        incendiesEteints.add(feu);
        System.out.println("ETEINTS FEU : " + feu);
        System.out.println("FEU ETEINTS SIMU===");
        System.out.println(incendiesEteints);
    }

    /**
     * Retourne l'incendie à la position en paramètre ou null si la case n'en contient pas.
     * @param position La position dont on veut récupérer l'Incendie éventuel.
     * @return L'incendie à la position en paramètre ou null si la case n'en contient pas.
     */
    Incendie getIncendieAt(Position position) {
        for (Incendie incendie : incendies) {
            if (incendie.getPosition().equals(position)) {
                return incendie;
            }
        }
        return null;
    }
}
