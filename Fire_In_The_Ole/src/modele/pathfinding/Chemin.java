/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.pathfinding;

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
       Position positionSuivante;
       
       if (etapes.contains(etapeCourante)) {
           indiceEtapeCourante = etapes.indexOf(etapeCourante);
           // gerer le cas ou il n'y a pas de position suivante
           if (indiceEtapeCourante+1 < etapes.size()) {
                positionSuivante = etapes.get(indiceEtapeCourante+1); //TODO verifier que ca retourne bien la position suivante dans la liste
           } else {
               positionSuivante = etapeCourante;
           }
           return positionSuivante;
       } else {
           throw new Error("getPositionSuivante de Chemin marche mal");
       }
  
    }
   
    public List<Position> getEtapes(){
        return this.etapes;
    }

    @Override
    public boolean equals(Object o){
        Chemin other = (Chemin) o;
        boolean result = false;
        for(Position etape : this.etapes){
            if(other.etapes.contains(etape)){
                result = true;
            }else{
                result = false;
            }
        }
        return result;
    }
}
