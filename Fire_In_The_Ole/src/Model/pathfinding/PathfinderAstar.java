/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.pathfinding;

import Model.Simulation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class PathfinderAstar implements PathFinder{
    private int[][] matrice;
    private List<Position> chemin = new ArrayList<>();
    private Simulation simu;
    private int[][] map;
    private Chemin cheminastar;
    HashMap<Position, String[]> listeouverte = new HashMap<Position, String[]>();
    HashMap<Position, String[]> listefermee = new HashMap<Position, String[]>();
    private String[] infonoeud = new String[4];
//private HashMap listeouverte = new HashMap();
    //private HashMap listefermee = new HashMap();
    

    public PathfinderAstar(Simulation simulation) {
        this.simu = simulation;
    }
    
    @Override
    public Chemin getCheminLePlusCourt(Position debut, Position fin) {
        this.map = this.simu.getCarte().getCarte();
        return calculerChemin(matrice,debut,fin);
    }

    public Chemin calculerChemin(int[][] carte, Position debut, Position fin) {
        List<Position> retour = new LinkedList<>();
        Chemin cheminastar;
        
        infonoeud[0] = "0";
        infonoeud[1] = calculheuristique(carte, debut, fin);
        infonoeud[2] = Integer.toString(Integer.valueOf(infonoeud[0]) + Integer.valueOf(infonoeud[1]));
        infonoeud[3] = null;
        //listefermee.put(debut, infonoeud);
        listefermee.put(debut, infonoeud.clone());
        
        //retour = listefermee.get(debut);
        while(debut.getX() != fin.getX() || debut.getY() != fin.getY()){
            //System.out.println(debut);
            debut = getMinAdjacent(debut.getX(), debut.getY(), carte, fin, debut);
            retour.add(debut);
        }
        cheminastar = new Chemin(retour);
//this.matrice = this.initialisation_matrice(carte, debut, fin);
        return cheminastar;
    }
    
    private String calculheuristique(int[][] carte, Position debut, Position fin){
        int heuristique = 0;
        String heuristique_string;
        int heuristique_x = debut.getX();
        int heuristique_y = debut.getY();
        while (heuristique_x != fin.getX() || heuristique_y != fin.getY()){
            if (heuristique_x < fin.getX() && heuristique_y < fin.getY()){
                heuristique_x += 1;
                heuristique_y += 1;
                heuristique = heuristique + carte[heuristique_x][heuristique_y];
            }
            else if (heuristique_x < fin.getX() && heuristique_y > fin.getY()){
                heuristique_x += 1;
                heuristique_y -= 1;
                heuristique = heuristique + carte[heuristique_x][heuristique_y];
            } 
            else if (heuristique_x < fin.getX()){
                heuristique_x += 1;
                heuristique = heuristique + carte[heuristique_x][heuristique_y];
            }
            else if (heuristique_x > fin.getX() && heuristique_y < fin.getY()){
                heuristique_x -= 1;
                heuristique_y += 1;
                heuristique = heuristique + carte[heuristique_x][heuristique_y];
            }
            else if (heuristique_x > fin.getX() && heuristique_y > fin.getY()){
                heuristique_x -= 1;
                heuristique_y -= 1;
                heuristique = heuristique + carte[heuristique_x][heuristique_y];
            }
            else if (heuristique_x > fin.getX()){
                heuristique_x -= 1;
                heuristique = heuristique + carte[heuristique_x][heuristique_y];
            }
            else if (heuristique_y < fin.getY()){
                heuristique_y += 1;
                heuristique = heuristique + carte[heuristique_x][heuristique_y];
            }
            else if (heuristique_y > fin.getY()){
                heuristique_y -= 1;
                heuristique = heuristique + carte[heuristique_x][heuristique_y];
            }
            //System.out.println("H = " + heuristique_x + " " + heuristique_y);
        }
        System.out.println(heuristique_x + " " + heuristique_y + " = " + heuristique);
        heuristique_string = Integer.toString(heuristique);
        return heuristique_string;
    }
    
    /*private int[][] initialisation_matrice(int[][] carte, Position debut, Position fin){    
        int[][] matrice = new int[carte.length][carte.length];
        
        for(int i = 0 ; i < carte.length ; i++){
            for(int j = 0 ; j < carte.length; j++){
                matrice [i][j] = carte[j][i];
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println("bla");
        }
        return matrice;
    }*/
    
    public Position getMinAdjacent(int x, int y, int[][] carte, Position fin, Position debut){
        String g;
        int test;
        Position suivant = null;
        Position[] adjacents = new Position[4];
        adjacents[0] = new Position(x, y-1);
        adjacents[1] = new Position(x+1, y);
        adjacents[2] = new Position(x, y+1);
        adjacents[3] = new Position(x-1, y);
        for(Position pos : adjacents){
            if (checkAdjacentExiste(pos, carte)){
                //Collection<String[]> values;
                System.out.println(pos.getX());
                System.out.println(pos.getY());
                //System.out.println(Integer.valueOf(listefermee.get(debut)[0]));
                g = Integer.toString(Integer.valueOf(listefermee.get(debut)[0]) + carte[pos.getX()][pos.getY()]);
                infonoeud[0] = g;
                infonoeud[3] = "test " + x + " " + y;
                calculnoeud(pos, carte, fin); 
            }            
        }
        suivant = getMeilleurAdjacent();
        System.out.println("SUIVANT CHOISI : " + suivant.getX() + " " + suivant.getY());
        return suivant;
    }
    
    private void calculnoeud(Position pos, int[][] carte, Position fin){
        Set<Position> keys;
        
        infonoeud[1] = calculheuristique(carte, pos, fin);
        infonoeud[2] = Integer.toString(Integer.valueOf(infonoeud[0]) + Integer.valueOf(infonoeud[1]));
        
        keys = listeouverte.keySet();
    
        if (keys.contains(pos) == false)
            listeouverte.put(pos, infonoeud.clone());
    }
    
    private boolean checkAdjacentExiste(Position pos, int[][] carte){
        /*if(x < 0 && y < 0 && x > this.simu.getCarte().getLargeur() && y > this.simu.getCarte().getHauteur()){
        return false;
        }*/ 
        int x = pos.getX();
        int y = pos.getY();
        //System.out.println(carte.length);
        if(x < 0 || x >= carte.length)
            return false;
        if(y < 0 || y >= carte.length)
            return false;
        
        return true;
    }
    
    private Position getMeilleurAdjacent(){
        Position coord;
        Set<Position> keys;
        Collection<String[]> values;
        int indice = 0;
        int resultat = 0;
        String[] infos = null;
        //int f_courant;
        int f_prec = 999999999;
        keys = listeouverte.keySet();
        values = listeouverte.values();
        for(String[] val : values){
            System.out.println(val[0] + " " + val[1] + " " + val[2]);
            if (Integer.valueOf(val[2]) < f_prec){
                
                resultat = indice;
                infos = val;
                indice ++;
                f_prec = Integer.valueOf(val[2]);
            } 
        }
        coord = keys.toArray(new Position[keys.size()])[resultat];
        
        //test = values.toArray(new String[values.size()])[resultat];
        //System.out.println(test + "ttttt");
        listefermee.put(coord, infos.clone());
        listeouverte.remove(coord);
        return coord;
    }
    
    private boolean existsInPath(Position pos){
        System.out.println("Exist ??? " + chemin.contains(pos));
       return (chemin.contains(pos));
    }
}
