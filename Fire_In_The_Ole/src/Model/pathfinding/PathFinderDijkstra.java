/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.pathfinding;

import Model.CarteDeTerrain;
import Model.Simulation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author S219
 */
public class PathFinderDijkstra implements PathFinder{
    
    private Simulation simu;
    private Chemin cheminDijkstra;
    private int[][] map;
    private int[][] matrice;
    
    public PathFinderDijkstra(Simulation simulation) {
        this.simu = simulation;
    }
    
    @Override
    public Chemin getCheminLePlusCourt(Position debut, Position fin) {
        this.map = this.simu.getCarte().getCarte();
        return calculerChemin(map,debut,fin);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Chemin calculerChemin(int[][] carte, Position depart, Position fin) {
        List<Position> chemin = new ArrayList<>();
        chemin.add(depart);
        this.matrice = this.initialisation_matrice(carte, depart, fin);
        Position suivant = depart;
        
        while(chemin.get(chemin.size()) != fin){
            chemin.add(this.getMinAdjacent(suivant.getX(), suivant.getY()));
            suivant = this.getMinAdjacent(suivant.getX(), suivant.getY());
        }
        // appel initialisation
        Chemin cheminCalcule = new Chemin(chemin);
        return cheminCalcule;
    }
    
    private int[][] initialisation_matrice(int[][] carte, Position debut, Position fin){    
        int[][] matrice = new int[carte.length][carte.length];
        //int[][] matrice = new int[this.simu.getCarte().getLargeur()][this.simu.getCarte().getHauteur()];
        /*for(int i = 0 ; i < this.simu.getCarte().getLargeur() ; i++){
            for(int j = 0 ; j < this.simu.getCarte().getHauteur(); j++){
                if(i == j){
                    matrice [i][j] = 0;
                }else{
                    matrice [i][j] = carte[i][j];
                }
            }
        }*/
        for(int i = 0 ; i < carte.length ; i++){
            for(int j = 0 ; j < carte.length; j++){
                if(i == j){
                    matrice [i][j] = 0;
                }else{
                    matrice [i][j] = carte[i][j];
                }
            }
        }
        return matrice;
    }
    
    private Position getMinAdjacent(int x, int y){
        Position suivant = null;
        Position[] adjacents = new Position[4];
        adjacents[0] = new Position(x, y-1);
        adjacents[1] = new Position(x+1, y);
        adjacents[2] = new Position(x, y+1);
        adjacents[3] = new Position(x-1, y);
        int difficulte = 0;
        for(Position pos : adjacents){
            if(checkAdjacentExiste(pos.getX(), pos.getY())){
                if(difficulte == 0 || difficulte > this.matrice[pos.getX()][pos.getY()]){
                    difficulte = this.matrice[pos.getX()][pos.getY()];
                    suivant = new Position(pos.getX(), pos.getY());
                }
            }
        }        
        return suivant;
    }
    
    private boolean checkAdjacentExiste(int x, int y){
        if((x < 0 && y < 0) || (x > this.simu.getCarte().getLargeur() && y > this.simu.getCarte().getHauteur())){
            return false;
        }
        return true;
    }
}
