/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.pathfinding;

import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author S219
 */
public class PathFinderDijkstraTest {
    
    public PathFinderDijkstraTest() {
    }

    /**
     * Test de chemin nul (depart = destination)
     */
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
    
    /**
     * Test de chemin simple
     */
    @Test
    public void testCalculerCheminSimple() {
        System.out.println("calculerChemin simple");
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
        etapesChemin.add(new Position(0, 1));
        etapesChemin.add(new Position(0, 2));
        etapesChemin.add(new Position(0, 3));
        etapesChemin.add(new Position(1, 3));
        etapesChemin.add(new Position(2, 3));
        etapesChemin.add(new Position(3, 3));
        System.out.print(etapesChemin);
        Chemin expResult = new Chemin(etapesChemin);
        
        Chemin result = instance.calculerChemin(matrice, depart, fin);
        assertEquals(expResult, result);
    }
    
}