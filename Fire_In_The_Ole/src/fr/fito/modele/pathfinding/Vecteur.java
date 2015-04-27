/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.modele.pathfinding;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author arthur
 */
public class Vecteur implements Comparable<Vecteur>{
    public int difficulte = Integer.MAX_VALUE;
    public Position pos;
    public Vecteur precedent = null;
    public Map<Vecteur, Integer> voisins = new HashMap<>();

    public Vecteur(Position pos){
        this.pos = pos;
    }
    
    @Override
    public int compareTo(Vecteur o) {
        return Integer.compare(difficulte, o.difficulte);
    }
    
    @Override
    public boolean equals(Object o){
        Vecteur other = (Vecteur) o;
        return (this.pos.getX() == other.pos.getX() && this.pos.getY() == other.pos.getY());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.difficulte;
        hash = 83 * hash + Objects.hashCode(this.pos);
        return hash;
    }
    
    @Override
    public String toString(){
        String result = "";
        result += pos;
        //result += " Prec : " + precedent;
        result += " Voisins : " + voisins.size();
        result += " Difficulte : " + difficulte + "\n";
        return result;
    }
}
