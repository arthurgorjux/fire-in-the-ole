package Model;

import Observer.Observateur;
import java.util.LinkedList;
import java.util.List;

public class Robot implements Entite{
	public int x;
	public int y;
        private int destinationX;
        private int destinationY;
	public final String typeRobot;
	public final String nom;
        public final List<Observateur> observateurs;
        public EtatRobot etat;
        
	public Robot(int origineX, int origineY, String type, String nom ){
		x = origineX;
		y = origineY;
                destinationX = x;
                destinationY = y;
		typeRobot = type;
		this.nom = nom;
		observateurs = new LinkedList<>();
                etat = EtatRobot.ARRET;
	}

        public void definirDestination(int x, int y) {
            destinationX = x;
            destinationY = y;
        }
        
        public void ajouterObservateur(Observateur observateur) {
            observateurs.add(observateur);
        }
        
	public void agir() {
		// TODO Auto-generated method stub
		
	}
	
        /**
         * Prévient tous les observateurs de la liste.
         */
        private void prevenirObservateurs() {
            for (Observateur observateur : observateurs) {
                observateur.prevenir();
            }
        }
        
	public EtatEntite getEtatEntite() {
		return new EtatEntite(x, y, this.nom, "typeRobot");
	}
}

/*
etats robots 
arret --> EnDeplacement --> Extinction --> Arret

*/