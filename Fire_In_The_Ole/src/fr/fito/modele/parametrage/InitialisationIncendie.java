package fr.fito.modele.parametrage;

/**
 * Paramètres d'initialisation d'un incendie.
 */
public class InitialisationIncendie {
    private final int x_depart;
    private final int y_depart;
    
    /**
     * Constructeur à partir des coordonnées x et y de l'incendie.
     * @param x_depart La coordonée x de départ de l'incendie.
     * @param y_depart La coordonée y de départ de l'incendie.
     */
    public InitialisationIncendie(int x_depart, int y_depart) {
        this.x_depart = x_depart;
        this.y_depart = y_depart;
    }

    /**
     * Retourne la coordonée x de départ de l'incendie.
     * @return La coordonée x de départ de l'incendie.
     */
    public int getX_depart() {
        return x_depart;
    }

    /**
     * Retourne la coordonée y de départ de l'incendie.
     * @return La coordonée y de départ de l'incendie.
     */
    public int getY_depart() {
        return y_depart;
    }
    
}
