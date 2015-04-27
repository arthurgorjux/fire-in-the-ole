/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.modele.pathfinding;

/**
 *
 * @author arthur
 */
public class Arete {
    public Position p1, p2;
    public int difficulte;
    
    public Arete(Position p1, Position p2, int difficulte){
        this.p1 = p1;
        this.p2 = p2;
        this.difficulte = difficulte;
    }
    
    @Override
    public String toString(){
        String result = "";
        result += "[P1 : " + p1 + "\n";
        result += "P2 : " + p2 + "\n";
        result += "Difficulte : " + difficulte + "]";
        return result;
    }
}
