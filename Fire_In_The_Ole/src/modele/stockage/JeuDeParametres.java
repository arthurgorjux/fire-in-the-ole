package modele.stockage;

import modele.SensVent;
import java.util.List;

/**
 * Les paramètres initiaux d'une simulation.
 */
public class JeuDeParametres {
    private final SensVent sens_du_vent;
    private final String fichier_carte;
    private final InitialisationIncendie[] departs_incendie;
    private final InitialisationRobot[] departs_robot;
    
    /**
     * Retourne le sens du vent dans la simulation.
     * @return Le sens du vent dans la simulation.
     */
    public SensVent getSens_du_vent() {
        return sens_du_vent;
    }

    /**
     * Retourne l'URI de la carte du terrain de la simulation.
     * @return l'URI de la carte du terrain de la simulation.
     */
    public String getFichier_carte() {
        return fichier_carte;
    }

    /**
     * Retourne la liste de paramètres des départs d'incendies de la simulation.
     * @return  La liste de paramètres des départs d'incendies de la simulation.
     */
    public InitialisationIncendie[] getDeparts_incendie() {
        return departs_incendie;
    }

     /**
     * Retourne la liste de paramètres des robots de la simulation.
     * @return  La liste de paramètres des robots d'incendies de la simulation.
     */
    public InitialisationRobot[] getDeparts_robot() {
        return departs_robot;
    }
    
    /**
     * Constructeur.
     * @param chemin_carte Le chemin d'accès au fichier bitmap de la carte du terrain.
     * @param sens_du_vent Le sens du vent au cours de la simulation.
     * @param departs_incendie La liste de paramètres d'initialisation des incendies.
     * @param departs_robot La liste de paramètres d'initialisation des robots.
     */
    public JeuDeParametres(String chemin_carte, SensVent sens_du_vent, List<InitialisationIncendie> departs_incendie,List<InitialisationRobot> departs_robot) {
        this.sens_du_vent = sens_du_vent;
        this.fichier_carte = chemin_carte;
        this.departs_incendie = departs_incendie.toArray(new InitialisationIncendie[departs_incendie.size()]);
        this.departs_robot = departs_robot.toArray(new InitialisationRobot[departs_robot.size()]);
    }
    
    
        
}
