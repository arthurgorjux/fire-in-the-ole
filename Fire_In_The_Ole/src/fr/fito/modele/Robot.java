package fr.fito.modele;

import fr.fito.modele.archivage.EtatEntite;
import static fr.fito.modele.ConstantesTypesRobot.*;
import static fr.fito.modele.EtatRobot.*;
import static fr.fito.modele.TypeRobot.*;
import fr.fito.modele.pathfinding.Chemin;
import fr.fito.modele.pathfinding.PathFinder;
import fr.fito.modele.pathfinding.PathFinderToutDroit;
import fr.fito.modele.pathfinding.Position;
import fr.fito.modele.parametrage.InitialisationRobot;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Modélise les robots évoluant dans les simulations.
 */
public class Robot implements Entite {
    private static final Map<TypeRobot,Integer> puissance;
    private Position destination;
    private final String nom;
    private final TypeRobot typeRobot;
    private final List<Observateur> observateurs;
    private EtatRobot etat;
    private final PathFinder pathFinder;
    private Position positionActuelle;
    private Chemin chemin;
    private final Simulation simulation;

    /**
     * Constructeur statique qui initialise les possitilité de puissance. Un peu crade mais on a vu pire. Suivez mon regard.
     */
    static {
        puissance = new HashMap<>();
        puissance.put(PATTE, PUISSANCE_ROBOT_PATTE);
        puissance.put(ROUE, PUISSANCE_ROBOT_ROUE);
        puissance.put(CHENILLE, PUISSANCE_ROBOT_CHENILLE);
        puissance.put(JETPACK, PUISSANCE_ROBOT_JETPACK);
    }

    /**
     * Instancie un objet de classe Robot en utilisant les coordonées d'origine x et y, le type du robot, son nom et la simulation dans laquelle il évolue.
     * @param origineX La coordonnée x d'origine du robot.
     * @param origineY La coordonnée y d'origine du robot.
     * @param type Le type du robot.
     * @param nom Le nom du robot.
     * @param simulation La simulation dans laquelle évolue le robot.
     */
    public Robot(int origineX, int origineY, TypeRobot type, String nom, Simulation simulation ){
        this.positionActuelle = new Position(origineX, origineY);
        this.simulation = simulation;
        this.destination = new Position(origineY, origineY);
        this.typeRobot = type;
        this.nom = nom;
        this.observateurs = new LinkedList<>();
        this.etat = EtatRobot.ARRET;
        this.pathFinder = new PathFinderToutDroit(simulation);
        calculerChemin();
    }
    /**
     * Instancie un objet de classe Robot en utilisant les coordonées d'origine x et y, le type du robot, son nom et la simulation dans laquelle il évolue.
     * @param parametres Les parametres d'initialisation du robot.
     * @param nom Le nom du robot.
     * @param simulation La simulation dans laquelle évolue le robot.
     */
    public Robot(InitialisationRobot parametres ,String nom, Simulation simulation) {
        this(parametres.getX_depart(), parametres.getY_depart(), parametres.getType(), nom, simulation);
    }

    /**
     * Donne une nouvelle destination au robot.
     * @param nouvelleDestination La nouvelle destination du robot.
     */
    public void definirDestination(Position nouvelleDestination) {
        destination = nouvelleDestination;
        this.etat = DEPLACEMENT;
    }

    /**
     * Calcule le chemin à suivre pour aller vers la destination courante.
     */
    private void calculerChemin() {
        chemin = pathFinder.getCheminLePlusCourt(positionActuelle, destination);
    }

    /**
     * Inscrit un nouvel observateur auprès du robot.
     * @param observateur 
     */
    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    /**
     * Retourne la position actuelle du robot.
     * @return La position actuelle du robot.
     */
    public Position getPosition() {
        return positionActuelle;
    }

    /**
     * Retourne le type du robot.
     * @return le type du robot.
     */
    public TypeRobot getType() {
        return typeRobot;
    }

    /**
     * Fait agir le robot durant ce tour de simulation.
     * Le patron de conception "Etat" serait possible mais n'est pas implémenté pour des raison de simplicité, seuls deux états entrainant des actions.
     */
    @Override
    public void agir() {
        switch (etat) {
            case DEPLACEMENT:
                agirEnEtatDeplacement();
                break;
            case EXTINCTION:
                agirEnEtatExtinction();
                break;
            case ARRET:
                break;
            default:
                throw new Error("Erreur : robot sans etat");
        }
    }
    
    /**
     * Un robot en etat d'extinction teste la présence d'un feu devant lui.
     * S'il en trouve un il l'arrose. 
     * Sinon il prévient les observateurs que la situation n'est pas normale.
     */
    private void agirEnEtatExtinction() {
        Position suivant = chemin.getPositionSuivante(positionActuelle);
        if (simulation.getIncendieAt(suivant) != null) {
            System.out.println(this + " eteint un feu");
            Incendie incendie = simulation.getIncendieAt(suivant);
            incendie.arroser(getPuissance());
        } else {
            prevenirObservateurs();
           etat = DEPLACEMENT;
        }
    }
    
    /**
     * Un robot en etat de déplacement teste la présence d'un incendie ou d'un robot devant lui.
     * S'il trouve un incendie il passe en mode extinction.
     * Si un robot lui fait obstacle, il recalcule son chemin pour l'éviter.
     * Sinon il se déplace.
     */
    private void agirEnEtatDeplacement() {
        Position suivant = chemin.getPositionSuivante(positionActuelle);
        if (simulation.contientUnIncendie(suivant)) {
            etat = EtatRobot.EXTINCTION;
        } else {
            if (simulation.contientUnRobot(suivant)) {
                calculerChemin();
            } else {
                positionActuelle = suivant;
            }
        }
    }
    
    /**
     * Retourne la puissance du robot.
     * @return La puissance du robot.
     */
    private int getPuissance() {
        return puissance.get(typeRobot);
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
     * Retourne l'état courant du robot.
     * @return L'état courant du robot.
     */
    EtatRobot getEtatCourant() {
        return this.etat;
    }
}
