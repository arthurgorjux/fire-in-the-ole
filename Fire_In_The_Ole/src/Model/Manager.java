package Model;

import Observer.Observateur;
import java.util.List;

/**
 * Le module d'Itelligence Artificielle qui analyse la sitation et donne les ordres aux robots.
 */
public class Manager implements Entite, Observateur {
    private boolean besoinAnalyse;
    private Simulation simulation;

    //contiendra la liste des robots occupés (ayant un incendie à éteindre)
    //HashMap<Robot, Incendie> listeOccupation = new HashMap<>();
    
    /**
     * Constructeur à partir d'une simulation.
     * @param simulation La simulation dans laquelle doit travailler le manager.
     */
    public Manager(Simulation simulation) {
        this.simulation = simulation;
        besoinAnalyse = true;
    }

    public void analyserSituation() {
        // assigne des feux aux robots
        List<Robot> robots = simulation.getRobots();
        //itération sur tout les robots
        for (Robot robotActuel : robots) {
            //si le robot est en extinction de feu -> pas de réafectation
            //sinon on réafecte au feu le plus proche
            //cela permet de prendre en compte un feu qui se serait déclaré
            //plus proche que le feu fixé initialement
            if (!(robotActuel.etat == EtatRobot.EXTINCTION)) {
                Incendie incendieProche = calculIncendieLePlusProche(robotActuel);
                //affectation de la destionation
                robotActuel.definirDestination(incendieProche.getPosition());
            }

        }
        besoinAnalyse = false;
    }

    @Override
    public void agir() {
        if (besoinAnalyse) {
            analyserSituation();
        }
    }

    @Override
    public void prevenir() {
        besoinAnalyse = true;
    }

    private Incendie calculIncendieLePlusProche(Robot robot) {
        //calcul de l'incendie le plus proche à vol d'oiseau
        //2 robots sur le même feu est un cas possible
        int xRobot = robot.getPosition().getX();
        int yRobot = robot.getPosition().getY();
        int xIncendie;
        int yIncendie;

        double distanceAbscisse;
        double distanceOrdonnee;

        double distanceTotale = -1;
        double distanceMinimum = -1;
        Incendie incendieTemp = null;

        List<Incendie> incendies = simulation.getIncendies();
        for (Incendie i : incendies) {
            xIncendie = i.getPosition().getX();
            yIncendie = i.getPosition().getY();
            //calcul diagonale
            distanceAbscisse = Math.pow(Math.abs(xRobot - xIncendie), 2);
            distanceOrdonnee = Math.pow(Math.abs(yRobot - yIncendie), 2);
            distanceTotale = Math.sqrt(distanceAbscisse + distanceOrdonnee);

            //on garde le plus court...
            if (distanceMinimum == -1 || distanceMinimum > distanceTotale) {
                distanceMinimum = distanceTotale;
                incendieTemp = i;
            }
        }
        return incendieTemp;
    }

}
