package fr.fito.modele.pathfinding;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Un chemin est une suite de positions successives à occuper pour aller d'un point à un autre.
 */
public class Chemin {
    private final List<Position> etapes;
    
    public Chemin(List<Position> etapes) {
        this.etapes = new LinkedList<>();    
        this.etapes.addAll(etapes);
    }
    
    /**
     * Retourne la position suivant celle passée en paramètre dans le chemin.
     * @param etapeCourante La position dont on veut la suivante.
     * @return La position suivant celle passée en paramètre dans le chemin.
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

    /**
     * Retourne si l'objet en pramètre est bien un chemin comprenant exactement les mêmes étapes.
     * @param o L'objet en pramètre.
     * @return true si l'objet en pramètre est bien un chemin comprenant exactement les mêmes étapes.
     */
    @Override
    public boolean equals(Object o){
        // Vérifie la classe
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Chemin autreChemin = (Chemin) o;
        
        // Vérifie les tailles
        if (this.etapes.size() != autreChemin.etapes.size()) {
            return false;
        }
        
        // Vérifie que toutes les étapes sont identiques
        for (int i = 0; i < this.etapes.size(); i++) {
            if ( !(this.etapes.get(i).equals(autreChemin.etapes.get(i)))  ) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.etapes);
        return hash;
    }
}
