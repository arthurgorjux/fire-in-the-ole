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
    private List<Position> chemin = new ArrayList<>();
    
    public PathFinderDijkstra(Simulation simulation) {
        this.simu = simulation;
    }
    
    @Override
    public Chemin getCheminLePlusCourt(Position debut, Position fin) {
        this.map = this.simu.getCarte().getCarte();
        return calculerChemin(map,debut,fin);
    }
    
    public Chemin calculerChemin(int[][] carte, Position depart, Position fin) {
        chemin.add(depart);
        this.matrice = this.initialisation_matrice(carte, depart, fin);
        Position suivant = depart;
        while(!suivant.equals(fin)){
            suivant = this.getMinAdjacent(suivant.getX(), suivant.getY(), this.matrice);
            chemin.add(suivant);            
        }
        
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
                matrice [i][j] = carte[j][i];
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
        return matrice;
    }
    
    public Position getMinAdjacent(int x, int y, int[][] carte){
        Position suivant = null;
        Position[] adjacents = new Position[4];
        adjacents[0] = new Position(x, y-1);
        adjacents[1] = new Position(x+1, y);
        adjacents[2] = new Position(x, y+1);
        adjacents[3] = new Position(x-1, y);
        int difficulte = 0;
        for(Position pos : adjacents){
            if(checkAdjacentExiste(pos, carte)){
                System.out.println("Adjacent possible : " + pos);
                System.out.println("Difficulte de la pos : " + carte[pos.getX()][pos.getY()]);
                if((difficulte == 0 
                || carte[pos.getX()][pos.getY()] < difficulte)
                && !existsInPath(pos)){
                    int difficulteTmp = difficulte + carte[pos.getX()][pos.getY()];
                    if(difficulteTmp < difficulte){
                        difficulte += carte[pos.getX()][pos.getY()];
                        System.out.println("Difficulte : " + difficulte);
                        suivant = new Position(pos.getX(), pos.getY());
                    }
                }
            }
        }    
        System.out.println("SUIVANT CHOISI : " + suivant);
        return suivant;
    }
    
    private boolean checkAdjacentExiste(Position pos, int[][] carte){
        /*if(x < 0 && y < 0 && x > this.simu.getCarte().getLargeur() && y > this.simu.getCarte().getHauteur()){
        return false;
        }*/ 
        int x = pos.getX();
        int y = pos.getY();
        if(x < 0 || x > carte.length)
            return false;
        if(y < 0 || y > carte.length)
            return false;
        return true;
    }
    
    private boolean existsInPath(Position pos){
        System.out.println("Exist ??? " + chemin.contains(pos));
       return (chemin.contains(pos));
    }
}
