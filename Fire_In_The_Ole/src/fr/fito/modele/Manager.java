package fr.fito.modele;

import fr.fito.modele.pathfinding.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Le module d'Itelligence Artificielle qui analyse la sitation et donne les ordres aux robots.
 */
public class Manager implements Entite, Observateur {
    private boolean besoinAnalyse;
    private final Simulation simulation;
    private HashMap <Incendie,Integer> occupation_incendies;

    //contiendra la liste des robots occupés (ayant un incendie à éteindre)
    //HashMap<Robot, Incendie> listeOccupation = new HashMap<>();
    
    /**
     * Constructeur à partir d'une simulation.
     * @param simulation La simulation dans laquelle doit travailler le manager.
     */
    public Manager(Simulation simulation) {
        this.simulation = simulation;
        besoinAnalyse = true;
        occupation_incendies = new HashMap();
    }

    /**
     * Méthode déclanchant une analyse de la situation par le manager, et la mise à jour des affectations de feu pour les robots.
     */
    public void analyserSituation() {
        List<Robot> robots = simulation.getRobots();
        //construction de la liste des feu occupés...
        List<Incendie> incendies = simulation.getIncendies();
//        System.out.println("Liste des incendies avant affectation : ");
//        System.out.println(incendies);
        
        
        occupation_incendies.clear();
        //initialisation des occupations
        for (Incendie incendieActuel : incendies) {
            occupation_incendies.put(incendieActuel, 0);
        }
        //remplissage des occupations
        for (Robot robotActuel : robots) {
            //si robot pas à l'arret, c'est qu'il est assigné à un incendie
            if (!(robotActuel.getEtatCourant() == EtatRobot.ARRET)){
                // on récupère l'incendie vers lequel il se dirige
                Position destination_robot = robotActuel.getDestination();
                for (Incendie incendieActuel : incendies) {
                    //si cela correspond à un incendie
                    if(incendieActuel.getPosition() == destination_robot) {
                        //on rajoute 1 au nombre de robots qui sont dessus
                        int nb_occup = occupation_incendies.get(incendieActuel); //on récup la valeur
                        occupation_incendies.put(incendieActuel, nb_occup+1); //on ajoute 1
                    }
                }
            }
        }
        //System.out.println("Liste des occupations avant affectation : ");
        //System.out.println(occupation_incendies);
        
        for (Robot robotActuel : robots) {
            if (!(robotActuel.getEtatCourant() == EtatRobot.EXTINCTION)) {
                Incendie incendieProche = calculIncendieLePlusProche(robotActuel);
                robotActuel.definirDestination(incendieProche.getPosition());
                
                int nb_occup = occupation_incendies.get(incendieProche); //on récup la valeur
                occupation_incendies.put(incendieProche, nb_occup+1); //on ajoute 1
            }
        }
        
        System.out.println("Liste des occupations...");
        System.out.println(occupation_incendies);

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
                
        List<Incendie> incendies_eligibles = calcul_liste_eligible();
        
        for (Incendie incendieCourant : incendies_eligibles) {
            distanceTotale = robot.getPosition().getDistanceAvec(incendieCourant.getPosition());
            //on garde le plus court...
            if (distanceMinimum == -1 || distanceMinimum > distanceTotale) {
                distanceMinimum = distanceTotale;
                incendieLePlusProche = incendieCourant;
            }
        }
        System.out.println("Affection du robot "+robot+" à l'incendie "+incendieLePlusProche);
        return incendieLePlusProche;
    }
    
    
    /**
     * Retourne la liste des incendies auxquels un robot peut être affecté
     * @return la liste des incendies auxquels un robot peut être affecté
     */
    private List calcul_liste_eligible(){
        
        int occup_min = 0;
        Boolean min_trouve = false;
        while (!min_trouve) {
            if (occupation_incendies.values().contains(occup_min))
                min_trouve = true;
            else
                occup_min++;
        }
        
        List<Incendie> incendies_avec_min_occup = new LinkedList<>();
        Iterator it_incendies = occupation_incendies.keySet().iterator();
        Incendie incendie_courant = null;
        
        while(it_incendies.hasNext()) {
            incendie_courant = (Incendie) it_incendies.next();
            if(occupation_incendies.get(incendie_courant) == occup_min)
                incendies_avec_min_occup.add(incendie_courant);
        }
        
        return incendies_avec_min_occup;
    }

}
