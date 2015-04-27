package modele;

import modele.archivage.EtatEntite;
import static modele.EtatRobot.DEPLACEMENT;
import static modele.TypeRobot.PATTE;
import modele.pathfinding.Chemin;
import modele.pathfinding.PathFinder;
import modele.pathfinding.PathFinderToutDroit;
import modele.pathfinding.Position;
import modele.stockage.InitialisationRobot;
import observateur.Observateur;
import java.util.LinkedList;
import java.util.List;

public class Robot implements Entite {

    
    private Position destination;
    public final String nom;
    public final TypeRobot typeRobot;
    public final List<Observateur> observateurs;
    public EtatRobot etat;
    private final PathFinder pathFinder;
    private Position positionActuelle;
    private Chemin chemin;
    private final Simulation simulation;

    private static int PUISSANCE_ROBOT_PATTE = 300;
    private static int PUISSANCE_ROBOT_ROUE = 200;
    private static int PUISSANCE_ROBOT_CHENILLE = 200;
    private static int PUISSANCE_ROBOT_JETPACK = 50;
    
    private static int FACTEUR_PATTE_CHEMIN = 1;
    private static int FACTEUR_PATTE_PLAINE = 2;
    private static int FACTEUR_PATTE_TERRAIN_ACCIDENTE = 3;
    private static int FACTEUR_PATTE_FORET = 4;
    private static int FACTEUR_PATTE_ROCHER = 5;
    private static int FACTEUR_PATTE_MONTAGNE = 6;
    
    private static int FACTEUR_ROUE_CHEMIN = 1;
    private static int FACTEUR_ROUE_PLAINE = 1;
    private static int FACTEUR_ROUE_TERRAIN_ACCIDENTE = 2;
    private static int FACTEUR_ROUE_FORET = 2;
    private static int FACTEUR_ROUE_ROCHER = 3;
    private static int FACTEUR_ROUE_MONTAGNE = 4;
    
    private static int FACTEUR_CHENILLE_CHEMIN = 1;
    private static int FACTEUR_CHENILLE_PLAINE = 1;
    private static int FACTEUR_CHENILLE_TERRAIN_ACCIDENTE = 2;
    private static int FACTEUR_CHENILLE_FORET = 2;
    private static int FACTEUR_CHENILLE_ROCHER = 10;
    private static int FACTEUR_CHENILLE_MONTAGNE = 10;
    
    private static int FACTEUR_JETPACK_CHEMIN = 1;
    private static int FACTEUR_JETPACK_PLAINE = 1;
    private static int FACTEUR_JETPACK_TERRAIN_ACCIDENTE = 1;
    private static int FACTEUR_JETPACK_FORET = 1;
    private static int FACTEUR_JETPACK_ROCHER = 1;
    private static int FACTEUR_JETPACK_MONTAGNE = 1;

    public Robot(int origineX, int origineY, TypeRobot type, String nom, Simulation simulation ){
        positionActuelle = new Position(origineX, origineY);
        this.simulation = simulation;
        destination = new Position(origineY, origineY);
        typeRobot = type;
        this.nom = nom;
        observateurs = new LinkedList<>();
        etat = EtatRobot.ARRET;
        this.pathFinder = new PathFinderToutDroit(simulation);
        calculerChemin();
    }

    public Robot(InitialisationRobot parametres ,String nom, Simulation simulation) {
        this(parametres.getX_depart(), parametres.getY_depart(), parametres.getType(), nom, simulation);
    }

    public void definirDestination(Position nouvelleDestination) {
        //le robot à reçu des coordonnées du manager, il se met en déplacement
        destination = nouvelleDestination;
        this.etat = EtatRobot.DEPLACEMENT;
    }

    private void calculerChemin() {
        chemin = pathFinder.getCheminLePlusCourt(positionActuelle, destination);
    }

    public void setChemin(Chemin chemin) {
        this.chemin = chemin;
    }

    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    public Position getPosition() {
        return positionActuelle;
    }

    public TypeRobot getType() {
        return typeRobot;
    }

    /**
     * Présence possible du patron de conception "Etat" non implémenté pour des
     * raison de simplicité. Seulements deux etats présent/
     */
    @Override
    public void agir() {
        Position suivant = chemin.getPositionSuivante(positionActuelle);
        switch (etat) {
            case DEPLACEMENT:
                if (simulation.contientUnIncendie(suivant)) {
                    //prevenirObservateurs();
                    etat = EtatRobot.EXTINCTION;
                } else {
                    if (simulation.contientUnRobot(suivant)) {
                        calculerChemin();
                    } else {
                        positionActuelle = suivant;
                    }
                }
                break;
            case EXTINCTION:
                if (simulation.getIncendieAt(suivant) != null) {
                    System.out.println(this + " eteint un feu");
                    Incendie incendie = simulation.getIncendieAt(suivant);
                    incendie.arroser(puissance_robot());
                } else {
                    prevenirObservateurs();
                    etat = DEPLACEMENT;
                }
                break;
            case ARRET:
                break;
            default:
                throw new Error("Erreur : robot sans etat");
        }
    }
    
    /**
     * Retourne la puissance du robot.
     * @return La puissance du robot.
     */
    private int puissance_robot() {
        int puissance = 0;
        
        switch(this.getType()){

            case PATTE:
                puissance = PUISSANCE_ROBOT_PATTE;
                break;
            case ROUE:
                puissance = PUISSANCE_ROBOT_ROUE;
                break;
            case CHENILLE:
                puissance = PUISSANCE_ROBOT_CHENILLE;
                break;
            case JETPACK:
                puissance = PUISSANCE_ROBOT_JETPACK;
                break;
        }
        return puissance;
    }

    /**
     * Retourne si le robot est arrivé à sa destination courante.
     * @return True si le robot est arrivé à sa destination courante.
     */
    private boolean estArriveDestination() {
        return positionActuelle == destination;
    }

    /**
     * Prévient tous les observateurs du robot.
     */
    private void prevenirObservateurs() {
        for (Observateur observateur : observateurs) {
            observateur.prevenir();
        }
    }

    /**
     * Retourne un EtatEntite représentant le robot au moment de l'appel.
     * @return Un EtatEntite représentant le robot au moment de l'appel.
     */
    public EtatEntite getEtatEntite() {
        return new EtatEntite(positionActuelle.getX(), positionActuelle.getY(), this.nom, "typeRobot");
    }

    @Override
    public String toString() {
        return this.nom + " : " + this.positionActuelle.getX() + ", " + this.positionActuelle.getY();
    }

    /**
     * Retourne la destination courante du robot.
     * @return La destination courante du robot.
     */
    public Position getDestination() {
        return this.destination;
    }

    /**
     * @return the FACTEUR_PATTE_CHEMIN
     */
    public static int getFACTEUR_PATTE_CHEMIN() {
        return FACTEUR_PATTE_CHEMIN;
    }

    /**
     * @return the FACTEUR_PATTE_PLAINE
     */
    public static int getFACTEUR_PATTE_PLAINE() {
        return FACTEUR_PATTE_PLAINE;
    }

    /**
     * @return the FACTEUR_PATTE_TERRAIN_ACCIDENTE
     */
    public static int getFACTEUR_PATTE_TERRAIN_ACCIDENTE() {
        return FACTEUR_PATTE_TERRAIN_ACCIDENTE;
    }

    /**
     * @return the FACTEUR_PATTE_FORET
     */
    public static int getFACTEUR_PATTE_FORET() {
        return FACTEUR_PATTE_FORET;
    }

    /**
     * @return the FACTEUR_PATTE_ROCHER
     */
    public static int getFACTEUR_PATTE_ROCHER() {
        return FACTEUR_PATTE_ROCHER;
    }

    /**
     * @return the FACTEUR_PATTE_MONTAGNE
     */
    public static int getFACTEUR_PATTE_MONTAGNE() {
        return FACTEUR_PATTE_MONTAGNE;
    }

    /**
     * @return the FACTEUR_ROUE_CHEMIN
     */
    public static int getFACTEUR_ROUE_CHEMIN() {
        return FACTEUR_ROUE_CHEMIN;
    }

    /**
     * @return the FACTEUR_ROUE_PLAINE
     */
    public static int getFACTEUR_ROUE_PLAINE() {
        return FACTEUR_ROUE_PLAINE;
    }

    /**
     * @return the FACTEUR_ROUE_TERRAIN_ACCIDENTE
     */
    public static int getFACTEUR_ROUE_TERRAIN_ACCIDENTE() {
        return FACTEUR_ROUE_TERRAIN_ACCIDENTE;
    }

    /**
     * @return the FACTEUR_ROUE_FORET
     */
    public static int getFACTEUR_ROUE_FORET() {
        return FACTEUR_ROUE_FORET;
    }

    /**
     * @return the FACTEUR_ROUE_ROCHER
     */
    public static int getFACTEUR_ROUE_ROCHER() {
        return FACTEUR_ROUE_ROCHER;
    }

    /**
     * @return the FACTEUR_ROUE_MONTAGNE
     */
    public static int getFACTEUR_ROUE_MONTAGNE() {
        return FACTEUR_ROUE_MONTAGNE;
    }

    /**
     * @return the FACTEUR_CHENILLE_CHEMIN
     */
    public static int getFACTEUR_CHENILLE_CHEMIN() {
        return FACTEUR_CHENILLE_CHEMIN;
    }

    /**
     * @return the FACTEUR_CHENILLE_PLAINE
     */
    public static int getFACTEUR_CHENILLE_PLAINE() {
        return FACTEUR_CHENILLE_PLAINE;
    }

    /**
     * @return the FACTEUR_CHENILLE_TERRAIN_ACCIDENTE
     */
    public static int getFACTEUR_CHENILLE_TERRAIN_ACCIDENTE() {
        return FACTEUR_CHENILLE_TERRAIN_ACCIDENTE;
    }

    /**
     * @return the FACTEUR_CHENILLE_FORET
     */
    public static int getFACTEUR_CHENILLE_FORET() {
        return FACTEUR_CHENILLE_FORET;
    }
    
    /**
     * @return the FACTEUR_CHENILLE_ROCHER
     */
    public static int getFACTEUR_CHENILLE_ROCHER() {
        return FACTEUR_CHENILLE_ROCHER;
    }

    /**
     * @return the FACTEUR_CHENILLE_MONTAGNE
     */
    public static int getFACTEUR_CHENILLE_MONTAGNE() {
        return FACTEUR_CHENILLE_MONTAGNE;
    }

    /**
     * @return the FACTEUR_JETPACK_CHEMIN
     */
    public static int getFACTEUR_JETPACK_CHEMIN() {
        return FACTEUR_JETPACK_CHEMIN;
    }

    /**
     * @return the FACTEUR_JETPACK_PLAINE
     */
    public static int getFACTEUR_JETPACK_PLAINE() {
        return FACTEUR_JETPACK_PLAINE;
    }

    /**
     * @return the FACTEUR_JETPACK_TERRAIN_ACCIDENTE
     */
    public static int getFACTEUR_JETPACK_TERRAIN_ACCIDENTE() {
        return FACTEUR_JETPACK_TERRAIN_ACCIDENTE;
    }

    /**
     * @return the FACTEUR_JETPACK_FORET
     */
    public static int getFACTEUR_JETPACK_FORET() {
        return FACTEUR_JETPACK_FORET;
    }

    /**
     * @return the FACTEUR_JETPACK_ROCHER
     */
    public static int getFACTEUR_JETPACK_ROCHER() {
        return FACTEUR_JETPACK_ROCHER;
    }

    /**
     * @return the FACTEUR_JETPACK_MONTAGNE
     */
    public static int getFACTEUR_JETPACK_MONTAGNE() {
        return FACTEUR_JETPACK_MONTAGNE;
    }
}
