package Model;

import Model.pathfinding.Position;

/**
 * Cette classe est une représentation des entitées de la simulation à un moment donné.
 * @author S219
 */
public class EtatEntite {
    private final String nom;
    private final String type;
    private final Position position;

    /**
     * Retourne la position x de l'entité.
     * @return  La position x de l'entité.
     */
    public int getX() {
        return position.getX();
    }

    /**
     * Retourne la position y de l'entité.
     * @return La position Y de l'entité.
     */
    public int getY() {
        return position.getY();
    }

    /**
     * Retourne la position de l'entité.
     * @return La position de l'entité.
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Retourne le nom de l'entité.
     * @return Le nom de l'entité.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le type du robot.
     * @return Le type du robot.
     */
    public String getType() {
        return type;
    }

    /**
     * Constructeur à partir de la position, du nom et du type de l'entité.
     * @param position La Position de l'entité.
     * @param nom Le nom de l'entité.
     * @param type Le Type de l'entité.
     */
    public EtatEntite(Position position, String nom, String type) {
        this.position = position;
        this.nom = nom;
        this.type = type;
    }

    /**
     * Constructeur à partir des coordonés, x et y, du nom et du type de l'entité.
     * @param x La coordoné x de l'entité.
     * @param y La coordoné y de l'entité.
     * @param nom Le nom de l'entité.
     * @param type Le Type de l'entité.
     */
    public EtatEntite(int x, int y, String nom, String type) {
        this(new Position(x, y), nom, type);
    }

    @Override
    /**
     * Debugage log
     */
    public String toString() {
        return " " + getX() + "   " + getY() + "   " + nom + " de type " + type;
    }

}
