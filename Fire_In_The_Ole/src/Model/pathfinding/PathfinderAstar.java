/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.pathfinding;

import java.util.HashMap;
import java.util.List;


public class PathfinderAstar implements PathFinder{
    private int[][] matrice;
    private Chemin chemin;
    
    @Override
    public Chemin getCheminLePlusCourt(Position debut, Position fin) {
        return calculerChemin(matrice,debut,fin);
    }

    private Chemin calculerChemin(int[][] matrice, Position debut, Position fin) {
        HashMap listeouverte = new HashMap();
        
        return chemin;
    }
    
}
