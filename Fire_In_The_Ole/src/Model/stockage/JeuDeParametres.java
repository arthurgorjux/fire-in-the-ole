package Model.stockage;

import Model.SensVent;
import java.util.List;

/**
 * Tous les paramètres initiaux d'une simulation.
 * Comprends : la carte du terrain choisie
 * Les positions de départ des incendies
 * le sens du vent
 * le placement des robots
 * @author S219
 */
public class JeuDeParametres {
    private final SensVent sens_du_vent;
    private final String fichier_carte;
    private final InitialisationIncendie[] departs_incendie;
    private final InitialisationRobot[] departs_robot;
    
    public SensVent getSens_du_vent() {
        return sens_du_vent;
    }

    public String getFichier_carte() {
        return fichier_carte;
    }

    public InitialisationIncendie[] getDeparts_incendie() {
        return departs_incendie;
    }

    public InitialisationRobot[] getDeparts_robot() {
        return departs_robot;
    }
    
    public JeuDeParametres(String chemin_carte, SensVent sens_du_vent, List<InitialisationIncendie> departs_incendie,List<InitialisationRobot> departs_robot) {
        this.sens_du_vent = sens_du_vent;
        this.fichier_carte = chemin_carte;
        this.departs_incendie = departs_incendie.toArray(new InitialisationIncendie[departs_incendie.size()]);
        this.departs_robot = departs_robot.toArray(new InitialisationRobot[departs_robot.size()]);
    }
    
    
        
}
