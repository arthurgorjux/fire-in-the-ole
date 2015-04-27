package fr.fito.modele.pathfinding;

/**
 * Interface des pathfinders. Il s'agit de classe permetant de trouver le plus court chemin entre deux positions.
 */
public interface PathFinder {
    /**
     * Retourne le plus court chemin permettant d'aller de la position de départ à la position d'arrivée.
     * @param depart La position de départ.
     * @param cible La position ciblée.
     * @return 
     */
    public Chemin getCheminLePlusCourt(Position depart, Position cible);   
}
