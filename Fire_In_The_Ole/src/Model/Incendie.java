package Model;

import Model.pathfinding.Position;
import Observer.Observateur;
import java.util.LinkedList;
import java.util.List;

public class Incendie implements Entite{
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
        position = new Position(origineX, origineY);
        intensite = 1;//intensite par defaut
        this.simulation = simulation;
        observateurs = new LinkedList<>();
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
     * Propagation primitive : le feu essaye de se propager a doirte puis vers le haut puis vers le bas puis vers la gauche
     */
    private void sePropager() {
        boolean sEstPropage = false;
        
        sEstPropage = simulation.ajouterFeu(1,position.getX()+1,position.getY());
        if (!sEstPropage) { 
            sEstPropage = simulation.ajouterFeu(1,position.getX(),position.getY()+1);
        }
        if (!sEstPropage) { 
            sEstPropage = simulation.ajouterFeu(1,position.getX(),position.getY()-1);
        }
        if (!sEstPropage) { 
            sEstPropage = simulation.ajouterFeu(1,position.getX()-1,position.getY());
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
		return new EtatEntite(position.getX(), position.getY(), "Flammes", "incendie");
	}

    void arroser(int puissanceDuJet) {
        System.out.println("JE SUIS ARROSE" + this);
        intensite -= puissanceDuJet;
    }
    
    @Override
    public String toString(){
        return "Incendie : " + position.getX() + ", " + this.position.getY();
        
    }
	
}
