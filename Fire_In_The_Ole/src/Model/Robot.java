package Model;

import static Model.EtatRobot.DEPLACEMENT;
import Model.pathfinding.Chemin;
import Model.pathfinding.PathFinder;
import Model.pathfinding.Position;
import Observer.Observateur;
import java.util.LinkedList;
import java.util.List;

public class Robot implements Entite{
        private Position destination;
	public final String typeRobot;
	public final String nom;
        public final List<Observateur> observateurs;
        public EtatRobot etat;
        private final PathFinder pathFinder;
        private Position positionActuelle;
        private Chemin chemin;
        private final Simulation simulation;
        
	public Robot(int origineX, int origineY, String type, String nom, PathFinder pathFinder, Simulation simulation ){
            positionActuelle = new Position(origineX, origineY);
            this.simulation = simulation;
            destination = new Position(origineY, origineY);
            typeRobot = type;
            this.nom = nom;
            observateurs = new LinkedList<>();
            etat = EtatRobot.ARRET;
            this.pathFinder = pathFinder;
            calculerChemin();
	}

        public void definirDestination(Position nouvelleDestination) {
            //le robot à reçu des coordonnées du manager, il se met en déplacement
            destination = nouvelleDestination;
            this.etat = EtatRobot.DEPLACEMENT;
        }
        
        private void calculerChemin() {
            chemin = pathFinder.getCheminLePlusCourt(positionActuelle, destination);
        }
        
        public void ajouterObservateur(Observateur observateur) {
            observateurs.add(observateur);
        }
        
        public Position getPosition() {
            return positionActuelle;
        }
        
        /**
         * Présence possible du patron de conception "Etat" non implémenté pour des raison de simplicité. Seulements deux etats présent/
         */
        @Override
	public void agir() {
            Position suivant = chemin.getPositionSuivante(positionActuelle);
            boolean aAgis = false;
		switch(etat){
                    case DEPLACEMENT:
                        if (estArriveDestination()) {
                            prevenirObservateurs();
                        } else {
                           while(!aAgis){
                                if (simulation.contientUnIncendie(suivant)) {
                                         etat = EtatRobot.EXTINCTION;
                                          aAgis = true;
                                } else if  (simulation.contientUnRobot(suivant)) {
                                    calculerChemin();
                                    aAgis = true;//TODO Actuelement un robot qui en 'percute" un autre perds un tour
                                } else {
                                    positionActuelle = suivant;
                                    aAgis = true;
                                }
                            }
                        }
                        break;
                    case EXTINCTION:
                        if (simulation.contientUnIncendie(suivant)) {
                            Incendie incendie = simulation.getIncendieAt(positionActuelle);
                            incendie.arroser(150);//TODO changer variable en dur
                        } else {
                            prevenirObservateurs();
                            etat = DEPLACEMENT;
                        }
                        break;
                    default:
                        throw new Error("Erreur : robot sans etat");
                }
        }
	
        private boolean estArriveDestination() {
            return positionActuelle == destination;
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
		return new EtatEntite(positionActuelle.getX(), positionActuelle.getY(), this.nom, "typeRobot");
	}
}