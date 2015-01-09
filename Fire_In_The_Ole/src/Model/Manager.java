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
            List<Incendie> incendies = simulation.getIncendies();
            
            Iterator iteratorRobots = robots.iterator();
            //itération sur tout les robots
            if (iteratorRobots.hasNext())
            {
                Robot robotActuel = (Robot) iteratorRobots.next();
                //si le robot est à l'arret
                if (robotActuel.etat == EtatRobot.ARRET)
                {
                    //assigner à un feu
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
        
        
        //assigner les robots pour éteindre les feux
        
}
