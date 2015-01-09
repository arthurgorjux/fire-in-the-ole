package Model;

import Observer.Observateur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Manager implements Entite, Observateur{
	boolean besoinAnalyse;
        Simulation simulation;
//        public String[] etatsRobots; //liste des états que peut prendre un robot
        
        //contiendra la liste des robots occupés (ayant un incendie à éteindre)
        HashMap<Robot, Incendie> listeOccupation = new HashMap<>();
	
	public Manager(Simulation simulation) {
            this.simulation = simulation;
            besoinAnalyse = false;
	}

	public void analyserSituation() {
		// se debrouille pour occuper tous les robots
            List<Robot> robots = simulation.getRobots();
            
            
            //gérer si incendie éteint pour virer de la liste etc...
            
            
            Iterator iteratorRobots = robots.iterator();
            //itération sur tout les robots
            for (Robot robotActuel : robots) 
            {
                //si le robot est à l'arret
                if (robotActuel.etat == EtatRobot.ARRET)
                {
                    //assigner à un feu
                    Incendie incendieProche = calculIncendieLePlusProche(robotActuel);
                    listeOccupation.put(robotActuel, incendieProche);
                    //lancer le déplacement
                    robotActuel.definirDestination(incendieProche.x, incendieProche.y);
                }
            }
            
            
          
            besoinAnalyse = false;
	}

	public void agir() {
		if (besoinAnalyse) {
			analyserSituation();
		}
	}

	@Override
	public void prevenir() {
		besoinAnalyse = true;
	}
        
        private Incendie calculIncendieLePlusProche(Robot robot){
            //calcul de l'incendie le plus proche à vol d'oiseau
            int xRobot = robot.x;
            int yRobot = robot.y;
            
            int xIncendie;
            int yIncendie;
            
            double distanceAbscisse;
            double distanceOrdonnee;
            
            double distanceTotale = -1;
            double distanceMinimum = -1;
            Incendie temp = null;
            
            List<Incendie> incendies = simulation.getIncendies();
            for (Incendie i : incendies)
            {
                xIncendie = i.x;
                yIncendie = i.y;
                
                //calcul diagonale
                distanceAbscisse = Math.pow(Math.abs(xRobot - xIncendie), 2);
                distanceOrdonnee = Math.pow(Math.abs(yRobot - yIncendie),2);
                
                distanceTotale = Math.sqrt(distanceAbscisse+distanceOrdonnee);
                
                //on garde le plus court...
                if (distanceMinimum == -1 || distanceMinimum > distanceTotale) {
                    distanceMinimum = distanceTotale;
                    temp = i;
                }   
            }
            
            return temp;
        }
        
        
}
