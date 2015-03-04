/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.pathfinding;

import Model.CarteDeTerrain;
import Model.Simulation;

/**
 *
 * @author S219
 */
public class PathFinderDijkstra implements PathFinder{
    
    private Simulation simu;
    private Chemin cheminDijkstra;
    private int[][] matrice;
    
    public PathFinderDijkstra(Simulation simulation) {
        this.simu = simulation;
    }
    
    @Override
    public Chemin getCheminLePlusCourt(Position debut, Position fin) {
        return calculerChemin(matrice,debut,fin);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Chemin calculerChemin(int[][] matrice, Position depart, Position fin) {
        Chemin cheminCalcule = new Chemin(null);
        
        
        
        return cheminCalcule;
    }
    
}
