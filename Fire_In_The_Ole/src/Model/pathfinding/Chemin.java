/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.pathfinding;

import java.util.LinkedList;
import java.util.List;

/**
 * Immutable
 * une suite de positions successives
 * @author S219
 */
public class Chemin {
    private final List<Position> etapes;
    
    public Chemin(List<Position> etapes) {
        this.etapes = new LinkedList<>();    
        this.etapes.addAll(etapes);
    }
    
    /**
     * Donne la position suivant celle passée en paramètre dans le chemin
     * @param etapeCourante
     * @return 
     */
    public Position getPositionSuivante(Position etapeCourante) {
       int indiceEtapeCourante ;
       
       if (etapes.contains(etapeCourante)) {
           indiceEtapeCourante = etapes.indexOf(etapeCourante);
           return etapes.get(indiceEtapeCourante+1); //TODO verifier que ca retourne bien la position suivante dans la liste
       } else {
           throw new Error("getPositionSuivante de Chemin marche mal");
       }
  
    }
    
    
}
