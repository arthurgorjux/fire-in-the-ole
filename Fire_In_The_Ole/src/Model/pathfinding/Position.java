/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.pathfinding;

/**
 * Les coordonées d'une case
 * Immutable
 * @author S219
 */
public class Position {
    private final int x;
    private final int y;
    
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
