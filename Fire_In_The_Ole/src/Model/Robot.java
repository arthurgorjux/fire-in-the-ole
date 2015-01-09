package Model;

import Observer.Observateur;
import java.util.LinkedList;
import java.util.List;

public class Robot implements Entite{
	public int x;
	public int y;
	public final String typeRobot;
	public final String nom;
        public final List<Observateur> observateurs;
	
	public Robot(int origineX, int origineY, String type, String nom ){
		x = origineX;
		y = origineY;
		typeRobot = type;
		this.nom = nom;
		observateurs = new LinkedList<>();
	}

        public void ajouterObservateur(Observateur observateur) {
            observateurs.add(observateur);
        }
        
	public void agir() {
		// TODO Auto-generated method stub
		
	}
	
	public EtatEntite getEtatEntite() {
		return new EtatEntite(x, y, this.nom, "typeRobot");
	}
}

/*
etats robots 
arret --> EnDeplacement --> Extinction --> Arret

*/