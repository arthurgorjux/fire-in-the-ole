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
import fr.fito.stats.Statistique;
import fr.fito.stats.StatistiqueTour;
import java.io.File;
import java.io.FileWriter;

/**
 * La simulation de l'extinction d'incendies par des robots sur un carte.
 */
public class Simulation {
    private final SensVent sens_du_vent;
    private final Manager manager;
    private final ArchiveSimulation archive;
    private final CarteDeTerrain carte;
    private final List<Robot> robots;
    //private final List<Robot> robots_initiaux;
    private final List<Incendie> incendies;
    //private final List<Incendie> incendies_initiaux;
    private final List<Incendie> incendiesFutur;
    private final List<Incendie> incendiesEteints;
    private int duree = 1;
    private JeuDeParametres parametres;
    private int nbIncendiesAjoutes = 0;
    private int nbIncendiesEteints = 0;
    private Statistique stat;
    private boolean isReset;

    public boolean isReset() {
        return isReset;
    }

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
        //incendies_initiaux = new LinkedList<>();
        incendiesFutur = new LinkedList<>();
        incendiesEteints = new LinkedList<>();
        this.parametres = parametres;
        isReset = false;
        
        this.majListes();
        this.stat = new Statistique();
        this.statistique("debut");
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
    public ArchiveTourSimulation archiverTour() {
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
        return tour;
    }

    /**
     * Retourne si la simulation est terminée.
     * @return True si la simulation est terminé.
     */
    public boolean estTerminee() {
        //on termine la simulation si la liste des incendies est vide
        //ou au bout d'un certain nombre de tours dans un premier temps...
        duree = duree + 1;
        
        //si la simu a été reset, 
        if (isReset) {
            return true;
        }
        else {
            if (incendies.isEmpty()) {
                return true;
            } else {
                return (duree >= 60);
            }   
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
            nbIncendiesAjoutes = nbIncendiesAjoutes + 1;
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
            nbIncendiesAjoutes = nbIncendiesAjoutes + 1;
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
                //System.out.println(emplacement + " Ya un feu ici");
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
    
    public int getNbIncendiesPropages(){
        return this.nbIncendiesAjoutes;
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
    
    public Statistique getStat(){
        return this.stat;
    }

    /**
     * Demande le retrait de l'incendie en paramètres de la simulation pour cause d'extinction.
     * @param feu L'Incendie à retirer de la simulation.
     */
    void eteindreFeu(Incendie feu) {
        incendiesEteints.add(feu);
        nbIncendiesEteints = nbIncendiesEteints + 1;
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

    public void resetSimulation() {
        isReset = true;
        duree = 1;
        incendies.clear();
        robots.clear();
        this.majListes();
    }

    private void majListes() {
        // On ajoute les robots et les incendies aux listes
        for (InitialisationIncendie departs_incendie : parametres.getDeparts_incendie()) {
            incendies.add(new Incendie(departs_incendie, this));
        }
        int compteur = 0;
        for (InitialisationRobot depart_robot : parametres.getDeparts_robot()) {
            String nom = "Robot " + depart_robot.getType().toString() + " #" + compteur;
            compteur++;
            robots.add(new Robot(depart_robot, nom, this));
        }

        // On inscrit le manager en tant qu'observateur sur tous les incendies et tous les robots.
        for (Robot robot : robots) {
            robot.ajouterObservateur(manager);
        }
        for (Incendie incendie : incendies) {
            incendie.ajouterObservateur(manager);
        }
    }
    
    public void statistique(String avance) {
        final File rep = new File("C:/statistiques FITO");
        final String chemin = "C:/statistiques FITO/statistiques.txt";
        rep.mkdir();
        final File fichier = new File(chemin);
        try{
            final FileWriter writer = new FileWriter(fichier, true);
            if(avance == "debut"){
                writer.write("------------------NOUVELLE SIMULATION------------------\n\n");
                writer.write("Nombre de robots : " + robots.size() + "\n");
                writer.write("Nombre de feux initial : " + incendies.size() + "\n");
            }else{
                writer.write("Nombre de feux ajoutés pendant la simulation : " + nbIncendiesAjoutes + "\n");
                writer.write("Nombre de feux éteints pendant la simulation : " + nbIncendiesEteints + "\n");
                writer.write("Nombre de tours avant la fin de la simulation : " + duree + "\n");
            }
            
            writer.close();
        }catch (Exception e){
            System.out.println("Impossible d'écrire les statistiques");
        }
    }
}
