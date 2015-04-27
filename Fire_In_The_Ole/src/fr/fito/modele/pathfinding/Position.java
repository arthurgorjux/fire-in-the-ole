/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.modele.pathfinding;

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
     * Retourne le numéro de colonne dans la grille
     * @return la coordonnée x de la position.
     */
    public int getX() {
        return x;
    }
    
    /** 
     * Retourne le numéro de ligne dans la grille
     * @return la coordonnée y de la position.
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
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.x;
        hash = 23 * hash + this.y;
        return hash;
    }

    /**
     * Retourne la distance entre cette position et celle passée en paramètre.
     * @param autrePosition La position dont on veut connaitre la distance.
     * @return La distance entre cette position et celle passée en paramètre.
     */
    public double getDistanceAvec(Position autrePosition) {
        double distanceAbscisse, distanceOrdonnee, distanceTotale;
        
        distanceAbscisse = Math.pow(Math.abs(this.getX() - autrePosition.getX()), 2);
        distanceOrdonnee = Math.pow(Math.abs( this.getY() - autrePosition.getY()), 2);
        distanceTotale = Math.sqrt(distanceAbscisse + distanceOrdonnee);
        
        return distanceTotale;
    }
    
    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
