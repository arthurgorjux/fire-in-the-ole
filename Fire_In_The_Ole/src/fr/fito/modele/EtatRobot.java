package fr.fito.modele;

/**
 * Les différents etats possibles d'un robot.
 */
public enum EtatRobot {

    /**
     * Le robot est à l'arrêt et ne bouge plus.
     */
    ARRET,

    /**
     * Le robot est en train de se déplacer vers un autre emplacement.
     */
    DEPLACEMENT,

    /**
     * Le robot est en train d'éteindre un feu.
     */
    EXTINCTION;
}
