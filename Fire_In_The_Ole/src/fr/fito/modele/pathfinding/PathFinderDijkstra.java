package fr.fito.modele.pathfinding;

import fr.fito.modele.CarteDeTerrain;
import static fr.fito.modele.ConstantesTypesRobot.*;
import fr.fito.modele.Robot;
import fr.fito.modele.Simulation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author S219
 */
public class PathFinderDijkstra implements PathFinder{
    
    private Simulation simu;
    private Robot robot;
    private Chemin cheminDijkstra;
    private CarteDeTerrain map;
    private int[][] matrice;
    public List<Arete> aretes;
    private List<Position> chemin = new ArrayList<>();
    
    private static final int CHEMIN_MIN = 0;
    private static final int CHEMIN_MAX = 42;
    private static final int PLAINE_MIN = 43;
    private static final int PLAINE_MAX = 85;
    private static final int TERRAIN_ACCIDENTE_MIN = 86;
    private static final int TERRAIN_ACCIDENTE_MAX = 128;
    private static final int FORET_MIN = 129;
    private static final int FORET_MAX = 171;
    private static final int ROCHER_MIN = 172;
    private static final int ROCHER_MAX = 173;
    private static final int MONTAGNE_MIN = 214;
    private static final int MONTAGNE_MAX = 255;
    
    
    public PathFinderDijkstra(Simulation simulation, Robot robot) {
        this.simu = simulation;
        this.robot = robot;
    }
    
    @Override
    public Chemin getCheminLePlusCourt(Position debut, Position fin) {
        this.map = this.simu.getCarte();
        return calculerChemin(map,debut,fin);
    }
    
    public Chemin calculerChemin(CarteDeTerrain carte, Position debut, Position fin){
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
                    difficulte_robot = FACTEUR_PATTE_CHEMIN * difficulte;
                }else if(difficulte > PLAINE_MIN && difficulte < PLAINE_MAX){
                    difficulte_robot = FACTEUR_PATTE_PLAINE * difficulte;
                }else if(difficulte > TERRAIN_ACCIDENTE_MIN  && difficulte < TERRAIN_ACCIDENTE_MAX){
                    difficulte_robot = FACTEUR_PATTE_TERRAIN_ACCIDENTE * difficulte;
                }else if(difficulte > FORET_MIN  && difficulte < FORET_MAX){
                    difficulte_robot = FACTEUR_PATTE_FORET * difficulte;
                }else if(difficulte > ROCHER_MIN && difficulte < ROCHER_MAX){
                    difficulte_robot = FACTEUR_PATTE_ROCHER * difficulte;
                }else if(difficulte > MONTAGNE_MIN && difficulte < MONTAGNE_MAX){
                    difficulte_robot = FACTEUR_PATTE_MONTAGNE * difficulte;
                }else{
                    difficulte_robot = FACTEUR_PATTE_MONTAGNE * difficulte;
                }
                break;
                
            case ROUE:
                if(difficulte >= CHEMIN_MIN && difficulte < CHEMIN_MAX){
                    difficulte_robot = FACTEUR_ROUE_CHEMIN * difficulte;
                }else if(difficulte > PLAINE_MIN && difficulte < PLAINE_MAX){
                    difficulte_robot = FACTEUR_ROUE_PLAINE * difficulte;
                }else if(difficulte > TERRAIN_ACCIDENTE_MIN  && difficulte < TERRAIN_ACCIDENTE_MAX){
                    difficulte_robot = FACTEUR_ROUE_TERRAIN_ACCIDENTE * difficulte;
                }else if(difficulte > FORET_MIN  && difficulte < FORET_MAX){
                    difficulte_robot = FACTEUR_ROUE_FORET * difficulte;
                }else if(difficulte > ROCHER_MIN && difficulte < ROCHER_MAX){
                    difficulte_robot = FACTEUR_ROUE_ROCHER * difficulte;
                }else if(difficulte > MONTAGNE_MIN && difficulte < MONTAGNE_MAX){
                    difficulte_robot = FACTEUR_ROUE_MONTAGNE* difficulte;
                }else{
                    difficulte_robot = FACTEUR_ROUE_MONTAGNE * difficulte;
                }
                break;
                
            case CHENILLE:
                if(difficulte >= CHEMIN_MIN && difficulte < CHEMIN_MAX){
                    difficulte_robot = FACTEUR_CHENILLE_CHEMIN * difficulte;
                }else if(difficulte > PLAINE_MIN && difficulte < PLAINE_MAX){
                    difficulte_robot = FACTEUR_CHENILLE_PLAINE* difficulte;
                }else if(difficulte > TERRAIN_ACCIDENTE_MIN  && difficulte < TERRAIN_ACCIDENTE_MAX){
                    difficulte_robot = FACTEUR_CHENILLE_TERRAIN_ACCIDENTE * difficulte;
                }else if(difficulte > FORET_MIN  && difficulte < FORET_MAX){
                    difficulte_robot =FACTEUR_CHENILLE_FORET * difficulte;
                }else if(difficulte > ROCHER_MIN && difficulte < ROCHER_MAX){
                    difficulte_robot = FACTEUR_CHENILLE_ROCHER * difficulte;
                }else if(difficulte > MONTAGNE_MIN && difficulte < MONTAGNE_MAX){
                    difficulte_robot = FACTEUR_CHENILLE_MONTAGNE * difficulte;
                }else{
                    difficulte_robot = FACTEUR_CHENILLE_MONTAGNE * difficulte;
                }
                break;
                
            case JETPACK:
                if(difficulte >= CHEMIN_MIN && difficulte < CHEMIN_MAX){
                    difficulte_robot = FACTEUR_JETPACK_CHEMIN * difficulte;
                }else if(difficulte > PLAINE_MIN && difficulte < PLAINE_MAX){
                    difficulte_robot = FACTEUR_JETPACK_PLAINE * difficulte;
                }else if(difficulte > TERRAIN_ACCIDENTE_MIN  && difficulte < TERRAIN_ACCIDENTE_MAX){
                    difficulte_robot = FACTEUR_JETPACK_TERRAIN_ACCIDENTE * difficulte;
                }else if(difficulte > FORET_MIN  && difficulte < FORET_MAX){
                    difficulte_robot = FACTEUR_JETPACK_FORET * difficulte;
                }else if(difficulte > ROCHER_MIN && difficulte < ROCHER_MAX){
                    difficulte_robot = FACTEUR_JETPACK_ROCHER * difficulte;
                }else if(difficulte > MONTAGNE_MIN && difficulte < MONTAGNE_MAX){
                    difficulte_robot = FACTEUR_JETPACK_MONTAGNE * difficulte;
                }else{
                    difficulte_robot = FACTEUR_JETPACK_MONTAGNE * difficulte;
                }
                break;
        }
        
        return difficulte_robot;
    }
    
    
    private void initialisation_matrice(CarteDeTerrain carte){    
        int[][] matrice = new int[carte.getHauteur()][carte.getLargeur()];
        List<Arete> edges = new LinkedList<>();
        System.out.println("Hauteur : " + carte.getHauteur());
        for(int i = 0 ; i < (this.map.getHauteur())-1; i++){
            for(int j = 0 ; j < (this.map.getLargeur())-1; j++){
                matrice[i][j] = check_difficulte_typeRobot(this.robot, carte.getDifficulte(i,j));
            }
        }
        this.map = new CarteDeTerrain(matrice);
        List<Arete> adjacentsFinaux = new LinkedList<>();
        for(int i = 0 ; i < this.map.getLargeur(); i++){
            for(int j = 0 ; j < this.map.getHauteur(); j++){
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
                adjacentsFinaux.add(new Arete(base, adjacent, this.map.getCarte()[adjacent.getY()][adjacent.getX()]));
           }               
        }
        return adjacentsFinaux;
    }
    
    private boolean checkAdjacentExiste(Position pos){
        int x = pos.getX();
        int y = pos.getY();
        if(x < 0 || x > (this.map.getLargeur())-1)
            return false;
        if(y < 0 || y > (this.map.getHauteur())-1)
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