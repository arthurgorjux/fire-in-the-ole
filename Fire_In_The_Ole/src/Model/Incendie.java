package Model;

import Observer.Observateur;
import java.util.LinkedList;
import java.util.List;

public class Incendie implements Entite{
	public int x;
	public int y;
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
		x = origineX;
		y = origineY;
                intensite = 1;//intensite par defaut
                this.simulation = simulation;
                observateurs = new LinkedList<>();
	}

        @Override
	public void agir() {
            if (intensite >= 5) {
                boolean succesPropagation;
                intensite = intensite -5;
                succesPropagation = simulation.ajouterFeu(1,x+1,y);
            } else if (intensite <= 0) {
                sEteindre();
            }
            intensite += 1;
            
	}
	
        private void sEteindre() {
            simulation.eteindreFeu(this);
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
