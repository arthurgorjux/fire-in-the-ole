package Model;

import Model.pathfinding.Position;
import Model.stockage.InitialisationIncendie;
import Observer.Observateur;
import java.util.LinkedList;
import java.util.List;

/**
 * Modélise un incendie occupant une case
 * @author S219
 */
public class Incendie implements Entite{
    private final Position position;
    private int intensite;
    private final Simulation simulation;
    private final List<Observateur> observateurs;

     /**
     * Constructeur par défaut, crée un feu d'intensite 1 à la position par défaut
     * @param origine
     * @param simulation
     */
    public Incendie(Position origine, Simulation simulation) {
        position = origine;
        intensite = 1;//intensite par defaut
        this.simulation = simulation;
        observateurs = new LinkedList<>();
    }
    
    /**
     * Constructeur par défaut, crée un feu d'intensite 1 aux coordonées indiquées
     * @param origineX
     * @param origineY 
     * @param simulation
     */
    public Incendie(int origineX, int origineY, Simulation simulation) {
        this(new Position(origineX, origineY), simulation);
    }

    public Incendie(InitialisationIncendie parametres, Simulation simulation) {
        this(parametres.getX_depart(), parametres.getY_depart(), simulation);
    }
    
    public Position getPosition(){
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
     * Indique si un encendie est eteint, c'est à dire si son intensité est nulle.
     * @return 
     */
    public boolean isEteint(){
        return this.intensite == 0;
    }

    /**
     * Propagation primitive : le feu essaye de se propager a doirte puis vers le haut puis vers le bas puis vers la gauche
     */
    private void sePropager() {
        boolean sEstPropage;
        int x,y;
        x = position.getX();
        y = position.getY();
        Position a_droite, a_gauche, en_haut, en_bas;
        
        a_droite = new Position( x+1, y   );
        a_gauche = new Position( x-1, y   );
        en_haut  = new Position( x  , y-1 );
        en_bas   = new Position( x  , y+1 );
        
        sEstPropage = simulation.ajouterFeu(1,x+1,y);
        if (!sEstPropage) { 
            sEstPropage = simulation.ajouterFeu(1,x,y+1);
        }
        if (!sEstPropage) { 
            sEstPropage = simulation.ajouterFeu(1,x,y-1);
        }
        if (!sEstPropage) { 
            sEstPropage = simulation.ajouterFeu(1,x-1,y);
        }
        if (sEstPropage) {
            intensite = intensite -5;
        }
    }
    
    private void sEteindre() {
        System.out.println("JE M'ETEINTS : " + this);
        simulation.eteindreFeu(this);
        prevenirObservateurs();
    }

     /**
     * Prévient tous les observateurs de l'incendie.
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
     * @return 
     */
    public EtatEntite getEtatEntite() {
            return new EtatEntite(position.getX(), position.getY(), "Flammes", "incendie");
    }


    void arroser(int puissanceDuJet) {
        System.out.println(this + " Se fait arroser !"); //TODO sortir version finale
        intensite -= puissanceDuJet;
    }
    
    @Override
    public String toString(){
        return "Incendie : " + this.position.getX() + ", " + this.position.getY();
        
    }
	
}
