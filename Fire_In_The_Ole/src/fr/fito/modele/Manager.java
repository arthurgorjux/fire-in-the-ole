package fr.fito.modele;

import java.util.List;

/**
 * Le module d'Itelligence Artificielle qui analyse la sitation et donne les ordres aux robots.
 */
public class Manager implements Entite, Observateur {
    private boolean besoinAnalyse;
    private final Simulation simulation;

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

    /**
     * Méthode déclanchant une analyse de la situation par le manager, et la mise à jour des affectations de feu pour les robots.
     */
    public void analyserSituation() {
        List<Robot> robots = simulation.getRobots();

        for (Robot robotActuel : robots) {
            if (!(robotActuel.getEtatCourant() == EtatRobot.EXTINCTION)) {
                Incendie incendieProche = calculIncendieLePlusProche(robotActuel);
                robotActuel.definirDestination(incendieProche.getPosition());
            }
        }
        besoinAnalyse = false;
    }

    @Override
    /**
     * Méthode d'action du manager effectuée à chaque tour de simulation. 
     * Pour améliorer les performances il n'analyse la situation que si des évènements l'exigeant ont eu lieu.
     */
    public void agir() {
        if (besoinAnalyse) {
            analyserSituation();
        }
    }

    @Override
    /**
     * Méthode permettant de prévenir le manager qu'une nouvelle analyse de la situation est nécéssaire.
     */
    public void prevenir() {
        besoinAnalyse = true;
    }

    /**
     * Retourne l'incendie le plus proche du robot passé en paramètre.
     * @param robot Le robot dont on veut déterminer l'incendie le plus proche.
     * @return L'incendie le plus proche du robot passé en paramètre.
     */
    private Incendie calculIncendieLePlusProche(Robot robot) {
        //calcul de l'incendie le plus proche à vol d'oiseau
        //2 robots sur le même feu est un cas possible
        double distanceTotale;
        double distanceMinimum = -1;
        Incendie incendieLePlusProche = null;

        List<Incendie> incendies = simulation.getIncendies();
        for (Incendie incendieCourant : incendies) {
            distanceTotale = robot.getPosition().getDistanceAvec(incendieCourant.getPosition());
            //on garde le plus court...
            if (distanceMinimum == -1 || distanceMinimum > distanceTotale) {
                distanceMinimum = distanceTotale;
                incendieLePlusProche = incendieCourant;
            }
        }
        return incendieLePlusProche;
    }

}
