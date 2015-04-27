/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.pathfinding;

/**
 * Les coordonées d'une case.Immutable.
 */
public class Position {
    private final int x;
    private final int y;
    
    /**
     * Constructeur à partir des coordonées x et y de la case.
     * @param x Coordonée x de la case.
     * @param y Coordonée y de la case.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Le numéro de colonne dans la grille
     * @return 
     */
    public int getX() {
        return x;
    }
    
    /** 
     * Le numéro de ligne dans la grille
     * @return 
     */
    public int getY() {
        return y;
    }
    
    @Override
    /**
     * Surcharge de la comparaison, deux positions sont identiques si leur coordonées le sont.
     * @return True si les deux cases ont les mêmes coordonées
     */
    public boolean equals(Object obj) {
        if (obj instanceof Position){
            Position autre = (Position)obj;
            return (autre.x == x && autre.y == y);
        }
        return false;
    }

    
    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
