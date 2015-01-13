package Model;

import static Model.EtatRobot.DEPLACEMENT;
import Model.pathfinding.Chemin;
import Model.pathfinding.PathFinder;
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
        private final PathFinder pathFinder;
        private Position position;
        private Chemin chemin;
        private final Simulation simulation;
        
	public Robot(int origineX, int origineY, String type, String nom, PathFinder pathFinder, Simulation simulation ){
            x = origineX;
            y = origineY;
            this.simulation = simulation;
            position = new Position(x, y);
            destinationX = x;
            destinationY = y;
            typeRobot = type;
            this.nom = nom;
            observateurs = new LinkedList<>();
            etat = EtatRobot.ARRET;
            this.pathFinder = pathFinder;
            calculerChemin();
	}

        public void definirDestination(Position position) {
            //le robot à reçu des coordonnées du manager, il se met en déplacement
            destinationX = position.getX();
            destinationY = position.getY();
            this.etat = EtatRobot.DEPLACEMENT;
        }
        
        private void calculerChemin() {
            chemin = pathFinder.getCheminLePlusCourt(getPosition(), new Position(destinationX, destinationY));
        }
        
        public void ajouterObservateur(Observateur observateur) {
            observateurs.add(observateur);
        }
        
        public Position getPosition() {
            return position;
        }
        
        /**
         * Présence possible du patron de conception "Etat" non implémenté pour des raison de simplicité. Seulements deux etats présent/
         */
        @Override
	public void agir() {
            Position suivant = chemin.getPositionSuivante(position);
            boolean aAgis = false;
		switch(etat){
                    case DEPLACEMENT:
                        if (estArriveDestination()) {
                            prevenirObservateurs();//prevenur le manager qu'on est en train de glander
                        } else {
                           while(!aAgis){
                                if (simulation.contientUnIncendie(suivant)) {
                                         etat = EtatRobot.EXTINCTION;
                                          aAgis = true;
                                } else if  (simulation.contientUnRobot(suivant)) {
                                    calculerChemin();
                                    aAgis = true;//TODO Actuelement un robot qui en 'percute" un autre perds un tour
                                } else {
                                    // TODO nettoyer bordel des position des x et des y
                                    position = suivant;
                                    x = suivant.getX();
                                    y = suivant.getY();
                                    aAgis = true;
                                }
                            }
                        }
                        break;
                    case EXTINCTION:
                        if (simulation.contientUnIncendie(suivant)) {
                            Incendie incendie = simulation.getIncendieAt(position);
                            incendie.arroser(15);//TODO changer variable en dur
                        } else {
                            prevenirObservateurs();// prévenir le manager qu'on glande
                            etat = DEPLACEMENT;
                        }
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