package modele.stockage;

import modele.TypeRobot;

/**
 * Les paramètre d'initialisation d'un robot.
 */
public class InitialisationRobot {
    private final int x_depart;
    private final int y_depart;
    private final TypeRobot type;
    
    /**
     * Constructeur à partir des coordonnées x et y du robot et de son type.
     * @param x_depart La coordonée x de départ du robot.
     * @param y_depart La coordonée y de départ du robot.
     * @param type Le type du robot.
     */
    public InitialisationRobot(int x_depart, int y_depart, TypeRobot type) {
        this.x_depart = x_depart;
        this.y_depart = y_depart;
        this.type = type;
    }

     /**
     * Retourne la coordonée x de départ du robot.
     * @return La coordonée x de départ du robot.
     */
    public int getX_depart() {
        return x_depart;
    }

     /**
     * Retourne la coordonée y de départ du robot.
     * @return La coordonée y de départ du robot.
     */
    public int getY_depart() {
        return y_depart;
    }

    /**
     * Retourne le type du robot.
     * @return Le type du robot.
     */
    public TypeRobot getType() {
        return type;
    }
    
    
}
