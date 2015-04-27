package fr.fito.modele;

/**
 * Cette interface décrit les interactions avec toutes les entitées participant à la simulation. Les incendies, les robots et le manager.
 * @author gael
 */
public interface Entite {

    /**
     * L'appel de cette méthode permet à l'entité d'agir pour un tour.
     */
    public void agir();
}
