/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.modele.pathfinding;

import fr.fito.modele.CarteDeTerrain;
import fr.fito.modele.Simulation;
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
    private CarteDeTerrain map;
    private Chemin cheminastar;
    HashMap<Position, String[]> listeouverte = new HashMap<Position, String[]>();
    HashMap<Position, String[]> listefermee = new HashMap<Position, String[]>();
    private String[] infonoeud = new String[4];
    

    public PathfinderAstar(Simulation simulation) {
        this.simu = simulation;
    }
    
    @Override
    public Chemin getCheminLePlusCourt(Position debut, Position fin) {
        this.map = this.simu.getCarte();
        return calculerChemin(this.map.getCarte(),debut,fin);
    }

    public Chemin calculerChemin(int[][] carte, Position debut, Position fin) {
        List<Position> retour = new LinkedList<>();
        Chemin cheminastar;
        
        infonoeud[0] = "0";
        infonoeud[1] = calculheuristique(carte, debut, fin);
        infonoeud[2] = Integer.toString(Integer.valueOf(infonoeud[0]) + Integer.valueOf(infonoeud[1]));
        infonoeud[3] = null;
        listefermee.put(debut, infonoeud.clone());
        
        while(debut.getX() != fin.getX() || debut.getY() != fin.getY()){
            listeouverte.clear();
            debut = getMinAdjacent(debut.getX(), debut.getY(), carte, fin, debut);
            retour.add(debut);
        }
        cheminastar = new Chemin(retour);
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
        }
        heuristique_string = Integer.toString(heuristique);
        return heuristique_string;
    }
    
    
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
            if (checkAdjacentExiste(pos)){
                System.out.println("Position : " + pos);
                g = Integer.toString(Integer.valueOf(listefermee.get(debut)[0]) + carte[pos.getX()][pos.getY()]);
                infonoeud[0] = g;
                infonoeud[3] = x + " " + y;
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
        
        keys = listefermee.keySet();
    
        if (keys.contains(pos) == false)
            listeouverte.put(pos, infonoeud.clone());
    }
    
    private boolean checkAdjacentExiste(Position pos){
        int x = pos.getX();
        int y = pos.getY();
        if(x < 0 || x >= (this.map.getLargeur())-1)
            return false;
        if(y < 0 || y >= (this.map.getHauteur())-1)
            return false;
        
        return true;
    }
    
    private Position getMeilleurAdjacent(){
        Position coord;
        Set<Position> keys;
        Set<Position> keys_ferme;
        Collection<String[]> values;
        int indice = 0;
        int resultat = 0;
        String[] infos = null;
        int f_prec = 999999999;
        keys = listeouverte.keySet();
        values = listeouverte.values();
        for(String[] val : values){
            if (Integer.valueOf(val[2]) < f_prec){
                resultat = indice;
                infos = val;
                f_prec = Integer.valueOf(val[2]);
            } 
            indice ++;
        }
        coord = keys.toArray(new Position[keys.size()])[resultat];
        
        keys_ferme = listefermee.keySet();
        if (keys_ferme.contains(coord) == false){
            listefermee.put(coord, infos.clone());
            listeouverte.remove(coord);
        }
        return coord;
    }
    
    private boolean existsInPath(Position pos){
        System.out.println("Exist ??? " + chemin.contains(pos));
       return (chemin.contains(pos));
    }
}
