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
        
        //contiendra la liste des robots occupés (ayant un incendie à éteindre)
//        HashMap<Robot, Incendie> listeOccupation = new HashMap<>();
	
	public Manager(Simulation simulation) {
            this.simulation = simulation;
            besoinAnalyse = true;
	}

	public void analyserSituation() {
            // se debrouille pour occuper tous les robots
            List<Robot> robots = simulation.getRobots();
            List<Incendie> incendies = simulation.getIncendies();
            
            
            //itération sur tout les robots
            for (Robot robotActuel : robots) 
            {
                //si le robot est en extinction de feu -> pas de réafectation
                //sinon on réafecte au feu le plus proche
                if (!(robotActuel.etat == EtatRobot.EXTINCTION))
                {
                    Incendie incendieProche = calculIncendieLePlusProche(robotActuel);
                    robotActuel.etat = EtatRobot.DEPLACEMENT;
                    //affectation de la destionation
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
            //2 robots sur le même feu est un cas possible
            int xRobot = robot.x;
            int yRobot = robot.y;
            
            int xIncendie;
            int yIncendie;
            
            double distanceAbscisse;
            double distanceOrdonnee;
            
            double distanceTotale = -1;
            double distanceMinimum = -1;
            Incendie incendieTemp = null;
            
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
                    incendieTemp = i;
                }  
            }
            System.out.println("Robot : ");
            System.out.println("x = "+robot.x+" y = "+robot.y);
            System.out.println("Icendie choisit : ");
            System.out.println("x = "+incendieTemp.x+" y = "+incendieTemp.y);
            return incendieTemp;
        }
        
        
}
