package Model.stockage;

import Model.TypeRobot;

/**
 * Les paramètre d'initialisation d'un robot : sa position de depart et son type.
 * @author S219
 */
public class InitialisationRobot {
    private final int x_depart;
    private final int y_depart;
    private final TypeRobot type;
    
    public InitialisationRobot(int x_depart, int y_depart, TypeRobot type) {
        this.x_depart = x_depart;
        this.y_depart = y_depart;
        this.type = type;
    }

    public int getX_depart() {
        return x_depart;
    }

    public int getY_depart() {
        return y_depart;
    }

    public TypeRobot getType() {
        return type;
    }
    
    
}
