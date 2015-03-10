package Model;

import static Model.EtatRobot.DEPLACEMENT;
import Model.pathfinding.Chemin;
import Model.pathfinding.PathFinder;
import Model.pathfinding.PathFinderToutDroit;
import Model.pathfinding.Position;
import Observer.Observateur;
import java.util.LinkedList;
import java.util.List;

public class Robot implements Entite{
        private Position destination;
	public final String typeRobot;
        public final int numTypeRobot;
	public final String nom;
        public final List<Observateur> observateurs;
        public EtatRobot etat;
        private PathFinder pathFinder;
        private Position positionActuelle;
        private Chemin chemin;
        private Simulation simulation;
        
	public Robot(int origineX, int origineY, String type, int numTypeRobot, String nom, PathFinder pathFinder, Simulation simulation ){
            positionActuelle = new Position(origineX, origineY);
            this.simulation = simulation;
            destination = new Position(origineY, origineY);
            typeRobot = type;
            this.numTypeRobot = numTypeRobot;
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
        
        public void setChemin (Chemin chemin){
            this.chemin = chemin;
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
		switch(etat){
                    case DEPLACEMENT:
                        if (simulation.contientUnIncendie(suivant)) {
                            //prevenirObservateurs();
                            etat = EtatRobot.EXTINCTION;
                        } else {                           
                            if(simulation.contientUnRobot(suivant)) {
                                calculerChemin();
                            }else{
                                positionActuelle = suivant;
                            }
                        }
                        break;
                    case EXTINCTION:
                        if (simulation.getIncendieAt(suivant) != null) {
                            System.out.println(this + " eteint un feu");
                            Incendie incendie = simulation.getIncendieAt(suivant);
                            incendie.arroser(150);//TODO changer variable en dur
                        } else {
                            prevenirObservateurs();
                            etat = DEPLACEMENT;
                        }
                        break;
                    case ARRET:
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
        
        @Override
        public String toString(){
            return this.nom + " : " + this.positionActuelle.getX() + ", " + this.positionActuelle.getY();
        }

    public void setPathFinder(PathFinderToutDroit pathFinderToutDroit) {
        this.pathFinder = pathFinderToutDroit;
    }

    public void setSimulation(Simulation simu) {
        this.simulation = simu;
    }
    
    public Position getDestination(){
        return this.destination;
    }
    
}