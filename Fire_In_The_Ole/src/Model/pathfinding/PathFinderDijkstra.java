/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.pathfinding;

import Model.CarteDeTerrain;
import Model.Simulation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;

/**
 *
 * @author S219
 */
public class PathFinderDijkstra implements PathFinder{
    
    private Simulation simu;
    private Chemin cheminDijkstra;
    private int[][] map;
    private int[][] matrice;
    public List<Arete> aretes;
    private List<Position> chemin = new ArrayList<>();
    
    public PathFinderDijkstra(Simulation simulation) {
        this.simu = simulation;
    }
    
    @Override
    public Chemin getCheminLePlusCourt(Position debut, Position fin) {
        this.map = this.simu.getCarte().getCarte();
        return calculerChemin(map,debut,fin);
    }
    
    public Chemin calculerChemin(int[][] carte, Position debut, Position fin){
        this.initialisation_matrice(carte);
        this.dijkstra(debut, fin);        
        
        Chemin cheminCalcule = new Chemin(this.chemin);
        return cheminCalcule;
    }
    
    private void initialisation_matrice(int[][] carte){    
        int[][] matrice = new int[carte.length][carte.length];
        List<Arete> edges = new LinkedList<>();
        for(int i = 0 ; i < carte.length ; i++){
            for(int j = 0 ; j < carte.length; j++){
                matrice [i][j] = carte[j][i];
            }
        }
        this.map = matrice;
        List<Arete> adjacentsFinaux = new LinkedList<>();
        for(int i = 0 ; i < this.map.length ; i++){
            for(int j = 0 ; j < this.map.length; j++){
                Position base = new Position(i, j);
                this.aretes = this.getAllAdjacents(base, adjacentsFinaux);
            }
        }
    }
    
    private List<Arete> getAllAdjacents(Position base, List<Arete> adjacentsFinaux){
        Position[] adjacents = new Position[4];
        int x = base.getX();
        int y = base.getY();
        adjacents[0] = new Position(x, y-1);
        adjacents[1] = new Position(x+1, y);
        adjacents[2] = new Position(x, y+1);
        adjacents[3] = new Position(x-1, y);
        for(Position adjacent : adjacents){
           if(checkAdjacentExiste(adjacent)){
                adjacentsFinaux.add(new Arete(base, adjacent, this.map[adjacent.getX()][adjacent.getY()]));
           }               
        }
        return adjacentsFinaux;
    }
    
    private boolean checkAdjacentExiste(Position pos){
        int x = pos.getX();
        int y = pos.getY();
        if(x < 0 || x > (this.map.length)-1)
            return false;
        if(y < 0 || y > (this.map.length)-1)
            return false;
        return true;
    }
    
    private void dijkstra(Position debut, Position fin) {
        Graph graph = new Graph(this.aretes);
        graph.dijkstra(debut);
        List<Vecteur> z = graph.z;
        Vecteur arrivee = null;
        List<Position> cheminTmp = new ArrayList<>();
        for(Vecteur v : z){
            if(v.pos.equals(fin))
                arrivee = v;
        }
        if(arrivee != null){
            Vecteur tmp = arrivee;
            while(!tmp.pos.equals(debut)){
                chemin.add(tmp.pos);
                tmp = tmp.precedent;
            }
            chemin.add(debut);
            Collections.reverse(chemin);
        }
    }
}
