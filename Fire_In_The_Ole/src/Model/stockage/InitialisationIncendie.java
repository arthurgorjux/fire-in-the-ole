package Model.stockage;

/**
 * Les paramètre d'initialisation d'un incendie : sa position de depart et son intensite
 * @author S219
 */
public class InitialisationIncendie {
    private final int x_depart;
    private final int y_depart;
    
    public InitialisationIncendie(int x_depart, int y_depart) {
        this.x_depart = x_depart;
        this.y_depart = y_depart;
    }

    public int getX_depart() {
        return x_depart;
    }

    public int getY_depart() {
        return y_depart;
    }
    
}
