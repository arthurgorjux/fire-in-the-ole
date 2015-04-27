package Model;

import static Model.EtatRobot.DEPLACEMENT;
import static Model.TypeRobot.PATTE;
import Model.pathfinding.Chemin;
import Model.pathfinding.PathFinder;
import Model.pathfinding.PathFinderToutDroit;
import Model.pathfinding.Position;
import Model.stockage.InitialisationRobot;
import Observer.Observateur;
import java.util.LinkedList;
import java.util.List;

public class Robot implements Entite {

    private Position destination;
    public final int typeRobot;
    public final String nom;
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

    public Robot(int origineX, int origineY, int type, String nom, Simulation simulation) {
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

    public Robot(InitialisationRobot parametres, String nom, Simulation simulation) {
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
        TypeRobot type = null;

        switch (this.typeRobot) {
            case 0:
                type = TypeRobot.PATTE;
                break;
            case 1:
                type = TypeRobot.ROUE;
                break;
            case 2:
                type = TypeRobot.CHENILLE;
                break;
            case 3:
                type = TypeRobot.JETPACK;
                break;
        }
        return type;
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

        switch (this.getType()) {
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

}
