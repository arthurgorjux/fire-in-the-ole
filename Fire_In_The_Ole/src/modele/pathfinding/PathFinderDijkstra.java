/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.pathfinding;

import modele.Robot;
import modele.CarteDeTerrain;
import modele.Simulation;
import modele.TypeRobot;
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
    private Robot robot;
    private Chemin cheminDijkstra;
    private int[][] map;
    private int[][] matrice;
    public List<Arete> aretes;
    private List<Position> chemin = new ArrayList<>();
    
    private static int CHEMIN_MIN = 0;
    private static int CHEMIN_MAX = 42;
    private static int PLAINE_MIN = 43;
    private static int PLAINE_MAX = 85;
    private static int TERRAIN_ACCIDENTE_MIN = 86;
    private static int TERRAIN_ACCIDENTE_MAX = 128;
    private static int FORET_MIN = 129;
    private static int FORET_MAX = 171;
    private static int ROCHER_MIN = 172;
    private static int ROCHER_MAX = 173;
    private static int MONTAGNE_MIN = 214;
    private static int MONTAGNE_MAX = 255;
    
    
    public PathFinderDijkstra(Simulation simulation, Robot robot) {
        this.simu = simulation;
        this.robot = robot;
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
    
    /* 
        Permet de calculer les difficultés en fonction des difficultés de terrain
        et du type de robot
    */
    private int check_difficulte_typeRobot(Robot pRobot, int difficulte){
        int  difficulte_robot = 0;

        switch(pRobot.getType()){
            case PATTE:
                if(difficulte >= CHEMIN_MIN && difficulte < CHEMIN_MAX){
                    difficulte_robot = pRobot.getFACTEUR_PATTE_CHEMIN() * difficulte;
                }else if(difficulte > PLAINE_MIN && difficulte < PLAINE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_PATTE_PLAINE() * difficulte;
                }else if(difficulte > TERRAIN_ACCIDENTE_MIN  && difficulte < TERRAIN_ACCIDENTE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_PATTE_TERRAIN_ACCIDENTE() * difficulte;
                }else if(difficulte > FORET_MIN  && difficulte < FORET_MAX){
                    difficulte_robot = pRobot.getFACTEUR_PATTE_FORET() * difficulte;
                }else if(difficulte > ROCHER_MIN && difficulte < ROCHER_MAX){
                    difficulte_robot = pRobot.getFACTEUR_PATTE_ROCHER() * difficulte;
                }else if(difficulte > MONTAGNE_MIN && difficulte < MONTAGNE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_PATTE_MONTAGNE() * difficulte;
                }else{
                    difficulte_robot = pRobot.getFACTEUR_PATTE_MONTAGNE() * difficulte;
                }
                break;
                
            case ROUE:
                if(difficulte >= CHEMIN_MIN && difficulte < CHEMIN_MAX){
                    difficulte_robot = pRobot.getFACTEUR_ROUE_CHEMIN() * difficulte;
                }else if(difficulte > PLAINE_MIN && difficulte < PLAINE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_ROUE_PLAINE() * difficulte;
                }else if(difficulte > TERRAIN_ACCIDENTE_MIN  && difficulte < TERRAIN_ACCIDENTE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_ROUE_TERRAIN_ACCIDENTE() * difficulte;
                }else if(difficulte > FORET_MIN  && difficulte < FORET_MAX){
                    difficulte_robot = pRobot.getFACTEUR_ROUE_FORET() * difficulte;
                }else if(difficulte > ROCHER_MIN && difficulte < ROCHER_MAX){
                    difficulte_robot = pRobot.getFACTEUR_ROUE_ROCHER() * difficulte;
                }else if(difficulte > MONTAGNE_MIN && difficulte < MONTAGNE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_ROUE_MONTAGNE()* difficulte;
                }else{
                    difficulte_robot = pRobot.getFACTEUR_ROUE_MONTAGNE() * difficulte;
                }
                break;
                
            case CHENILLE:
                if(difficulte >= CHEMIN_MIN && difficulte < CHEMIN_MAX){
                    difficulte_robot = pRobot.getFACTEUR_CHENILLE_CHEMIN() * difficulte;
                }else if(difficulte > PLAINE_MIN && difficulte < PLAINE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_CHENILLE_PLAINE()* difficulte;
                }else if(difficulte > TERRAIN_ACCIDENTE_MIN  && difficulte < TERRAIN_ACCIDENTE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_CHENILLE_TERRAIN_ACCIDENTE() * difficulte;
                }else if(difficulte > FORET_MIN  && difficulte < FORET_MAX){
                    difficulte_robot = pRobot.getFACTEUR_CHENILLE_FORET() * difficulte;
                }else if(difficulte > ROCHER_MIN && difficulte < ROCHER_MAX){
                    difficulte_robot = pRobot.getFACTEUR_CHENILLE_ROCHER() * difficulte;
                }else if(difficulte > MONTAGNE_MIN && difficulte < MONTAGNE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_CHENILLE_MONTAGNE() * difficulte;
                }else{
                    difficulte_robot = pRobot.getFACTEUR_CHENILLE_MONTAGNE() * difficulte;
                }
                break;
                
            case JETPACK:
                if(difficulte >= CHEMIN_MIN && difficulte < CHEMIN_MAX){
                    difficulte_robot = pRobot.getFACTEUR_JETPACK_CHEMIN() * difficulte;
                }else if(difficulte > PLAINE_MIN && difficulte < PLAINE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_JETPACK_PLAINE() * difficulte;
                }else if(difficulte > TERRAIN_ACCIDENTE_MIN  && difficulte < TERRAIN_ACCIDENTE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_JETPACK_TERRAIN_ACCIDENTE() * difficulte;
                }else if(difficulte > FORET_MIN  && difficulte < FORET_MAX){
                    difficulte_robot = pRobot.getFACTEUR_JETPACK_FORET() * difficulte;
                }else if(difficulte > ROCHER_MIN && difficulte < ROCHER_MAX){
                    difficulte_robot = pRobot.getFACTEUR_JETPACK_ROCHER() * difficulte;
                }else if(difficulte > MONTAGNE_MIN && difficulte < MONTAGNE_MAX){
                    difficulte_robot = pRobot.getFACTEUR_JETPACK_MONTAGNE() * difficulte;
                }else{
                    difficulte_robot = pRobot.getFACTEUR_JETPACK_MONTAGNE() * difficulte;
                }
                break;
        }
        
        return difficulte_robot;
    }
    
    
    private void initialisation_matrice(int[][] carte){    
        int[][] matrice = new int[carte.length][carte.length];
        List<Arete> edges = new LinkedList<>();
        for(int i = 0 ; i < carte.length ; i++){
            for(int j = 0 ; j < carte.length; j++){
                //matrice [i][j] = carte[j][i];
                matrice [i][j] = check_difficulte_typeRobot(this.robot, carte[i][j]);
                System.out.println(matrice[i][j] + " ");
            }
            System.out.println("\n");
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