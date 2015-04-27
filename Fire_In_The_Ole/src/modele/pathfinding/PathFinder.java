/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.pathfinding;

/**
 * Interface des pathfinders.
 */
public interface PathFinder {
    public Chemin getCheminLePlusCourt(Position robot, Position feu);   
}
