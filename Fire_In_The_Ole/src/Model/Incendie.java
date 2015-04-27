package Model;

import Model.pathfinding.Position;
import Model.stockage.InitialisationIncendie;
import Observer.Observateur;
import java.util.LinkedList;
import java.util.List;

/**
 * Modélise un incendie occupant une case.

 */
public class Incendie implements Entite {

    private final Position position;
    private int intensite;
    private final Simulation simulation;
    private final List<Observateur> observateurs;

    /**
     * Constructeur avec en paramètre la Position d'origine et la simulation. Crée un feu d'intensite 1.
     * @param origine La Position d'origine de l'incendie.
     * @param simulation La simulation dans laquelle évolue l'incendie.
     */
    public Incendie(Position origine, Simulation simulation) {
        position = origine;
        intensite = 1;//intensite par defaut
        this.simulation = simulation;
        observateurs = new LinkedList<>();
    }

    /**
     * Constructeur avec en paramètre la les coordonés x et y d'origine et la simulation. Crée un feu d'intensite 1.
     * @param origineX La coordoné x d'origine de l'incendie.
     * @param origineY La coordoné y d'origine de l'incendie.
     * @param simulation La simulation dans laquelle évolue l'incendie.
     */
    public Incendie(int origineX, int origineY, Simulation simulation) {
        this(new Position(origineX, origineY), simulation);
    }
    
    /**
     * Constructeur avec en paramètre un InitialisationIncendie et la simulation. Crée un feu d'intensite 1.
     * @param parametres Les paramètres d'initialisation de l'incendie.
     * @param simulation La simulation dans laquelle évolue l'incendie.
     */
    public Incendie(InitialisationIncendie parametres, Simulation simulation) {
        this(parametres.getX_depart(), parametres.getY_depart(), simulation);
    }

    /**
     * Retourne la Position de l'incendie.
     * @return la Position de l'incendie.
     */
    public Position getPosition() {
        return position;
    }

    @Override
    public void agir() {
        if (intensite >= 7) {
            sePropager();
        } else if (intensite <= 0) {
            sEteindre();
        }
        intensite += 1;

    }

    /**
     * Indique si un encendie est éteint(intensité nulle).
     * @return True si l'incendie est eteint.
     */
    public boolean isEteint() {
        return this.intensite == 0;
    }

    /**
     * Action de propagation de l'incendie.
     * Propagation primitive : le feu essaye de se propager a doirte puis vers
     * le haut puis vers le bas puis vers la gauche
     */
    private void sePropager() {
        boolean sEstPropage;
        int x, y;
        x = position.getX();
        y = position.getY();
        Position a_droite, a_gauche, en_haut, en_bas;

        a_droite = new Position(x + 1, y);
        a_gauche = new Position(x - 1, y);
        en_haut = new Position(x, y - 1);
        en_bas = new Position(x, y + 1);

        sEstPropage = simulation.ajouterFeu(1, x + 1, y);
        if (!sEstPropage) {
            sEstPropage = simulation.ajouterFeu(1, x, y + 1);
        }
        if (!sEstPropage) {
            sEstPropage = simulation.ajouterFeu(1, x, y - 1);
        }
        if (!sEstPropage) {
            sEstPropage = simulation.ajouterFeu(1, x - 1, y);
        }
        if (sEstPropage) {
            intensite = intensite - 5;
        }
    }

    /**
     * Action d'extinction de l'incendie.
     */
    private void sEteindre() {
        System.out.println("JE M'ETEINTS : " + this);
        simulation.eteindreFeu(this);
        prevenirObservateurs();
    }

    /**
     * Prévient tous les observateurs de l'incendie qu'un évènement à eu lieu.
     */
    private void prevenirObservateurs() {
        for (Observateur observateur : observateurs) {
            observateur.prevenir();
        }
    }

    /**
     * Ajouter un observateur à l'incendie.
     * @param observateur L'observateur à ajouter.
     */
    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    /**
     * Génère un EtatEntite représentant l'état de l'incendie au moment de l'appel.
     * @return Un EtatEntite représentant l'état de l'incendie au moment de l'appel.
     */
    public EtatEntite getEtatEntite() {
        return new EtatEntite(position.getX(), position.getY(), "Flammes", "incendie");
    }

    /**
     * Méthode permettant d'arroser un incendie et donc de baisser son intensité.
     * @param puissanceDuJet 
     */
    void arroser(int puissanceDuJet) {
        System.out.println(this + " Se fait arroser !"); //TODO sortir version finale
        intensite -= puissanceDuJet;
    }

    @Override
    public String toString() {
        return "Incendie : " + this.position.getX() + ", " + this.position.getY();

    }

}
