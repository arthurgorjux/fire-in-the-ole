package Model;

import Model.pathfinding.Position;
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
            //le robot à reçu des coordonnées du manager, il se met en déplacement
            destinationX = x;
            destinationY = y;
            this.etat = EtatRobot.DEPLACEMENT;
        }
        
        public void ajouterObservateur(Observateur observateur) {
            observateurs.add(observateur);
        }
        
        public Position getPosition() {
            return new Position(x,y);
        }
        
        /**
         * Présence possible du patron de conception "Etat" non implémenté pour des raison de simplicité. Seulements deux etats présent/
         */
	public void agir() {
		switch(etat){
                    case DEPLACEMENT:
                        if (estArriveDestination()) {
                            prevenirObservateurs();//prevenur le manager qu'on est en train de glander
                        } else {
                           // boolean aAgis = false
                           // tant que n'a pas agis :
                                //reagrde dans son chemin si la case suivante est vide.
                                     //si la case contient un incendie
                                         // mode extintion
                                         // aAgis = true
                                     //si la case contient un obstacle
                                         // recalculer cheminvers destination
                                     //si la case est libre
                                        // position = position suivante dans le chemin
                                        //aAgis = true
                            //fin tant que
                            
                        }
                        break;
                    case EXTINCTION:
                        // Si il y a un feu dans la case suivante du chemin
                            // feu = getIncendiePosition(x,y)
                            // feu.arroser(forceArrosage)
                        // Sinon
                            prevenirObservateurs();// prévenir le manager qu'on glande
                            // etat = DEPLACEMENT
                        //FINSI
                        break;
                    default:
                        throw new Error("Erreur : robot sans etat");
                }
        }
	
        private boolean estArriveDestination() {
            return ((x == destinationX) && (y == destinationY));
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