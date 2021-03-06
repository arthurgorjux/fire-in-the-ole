/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.pathfinding;

import fr.fito.modele.pathfinding.PathfinderAstar;
import fr.fito.modele.pathfinding.Position;
import fr.fito.modele.pathfinding.Chemin;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author S219
 */
public class PathfinderAstarTest {
    
    public PathfinderAstarTest() {
    }
    
    /**
     * Test de chemin nul (depart = destination)
     */
    /**
    @Test
    public void testCalculerCheminNul() {
        System.out.println("calculerChemin nul (a reflechir)");
        int[][] matrice = {
		{10,10,10,20},
                {10,60,60,10},
                {10,60,60,10},
                {10,10,20,10},
		};
        Position depart = new Position(0, 0);
        Position fin = new Position(3, 3);
        PathFinderDijkstra instance = new PathFinderDijkstra(null);
        
        // Chemin determiné à la main
        List<Position> etapesChemin = new LinkedList();
        etapesChemin.add(new Position(0, 0));
        etapesChemin.add(new Position(0, 0));
        Chemin expResult = new Chemin(etapesChemin);
        
        Chemin result = instance.calculerChemin(matrice, depart, fin);
        assertEquals(expResult, result);
    }
    */
    
    /**
     * Test de chemin simple
     */
    @Test
    public void testCalculerCheminSimple() {
        System.out.println("calculerChemin simple");
        int[][] matrice = {
		{10,20,10,30},
                {10,60,60,20},
                {10,60,60,10},
                {10,10,10,10},
		};
        Position depart = new Position(0, 0);
        Position fin = new Position(3, 3);
        PathfinderAstar instance = new PathfinderAstar(null);
        
        // Chemin determiné à la main
        List<Position> etapesChemin = new ArrayList<>();
        etapesChemin.add(new Position(0, 0));
        etapesChemin.add(new Position(1, 0));
        etapesChemin.add(new Position(2, 0));
        etapesChemin.add(new Position(3, 0));
        etapesChemin.add(new Position(3, 1));
        etapesChemin.add(new Position(3, 2));
        etapesChemin.add(new Position(3, 3));
        Chemin expResult = new Chemin(etapesChemin);
        
        Chemin result = instance.calculerChemin(matrice, depart, fin);
        
        System.out.println("Chemin final : " + result);
        assertEquals(expResult.getEtapes(), result.getEtapes());
    }
    
}
