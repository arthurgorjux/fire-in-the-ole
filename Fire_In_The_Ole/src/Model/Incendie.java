package Model;

import Model.pathfinding.Position;
import Observer.Observateur;
import java.util.LinkedList;
import java.util.List;

public class Incendie implements Entite{
    private int x;
    private int y;
    private Position position;
    private int intensite;
    private final Simulation simulation;
    private final List<Observateur> observateurs;

    /**
     * Constructeur par défaut, crée un feu d'intensite 1 aux coordonées indiquées
     * @param origineX
     * @param origineY 
     * @param simulation
     */
    public Incendie(int origineX, int origineY, Simulation simulation) {
        position = new Position(x, y);
        x = origineX;
        y = origineY;
        intensite = 1;//intensite par defaut
        this.simulation = simulation;
        observateurs = new LinkedList<>();
    }

    public Position getPosition(){
        return position;
    }
    
    @Override
    public void agir() {
        if (intensite >= 5) {
            sePropager();
        } else if (intensite <= 0) {
            sEteindre();
        }
        intensite += 1;

    }

    /**
     * Propagation primitive : le feu essaye de se propager a doirte puis vers le haut puis vers le bas puis vers la gauche
     */
    private void sePropager() {
        boolean sEstPropage = false;
        
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
            simulation.eteindreFeu(this);
            prevenirObservateurs();
        }
        
         /**
         * Prévient tous les observateurs de la liste.
         */
        private void prevenirObservateurs() {
            for (Observateur observateur : observateurs) {
                observateur.prevenir();
            }
        }
        
        public void ajouterObservateur(Observateur observateur) {
            observateurs.add(observateur);
        }
        
	public EtatEntite getEtatEntite() {
		return new EtatEntite(x, y, "Flammes", "incendie");
	}
	
}
