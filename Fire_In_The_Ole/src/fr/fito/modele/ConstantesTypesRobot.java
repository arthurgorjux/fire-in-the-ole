/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.modele;

/**
 * Permet d'accéder à toutes les constantes liées au types de robot.
 */
public interface ConstantesTypesRobot {
    
    /**
     * La puissance d'extinction d'un robot de type patte.
     */
    static final int PUISSANCE_ROBOT_PATTE = 300;
    /**
     * La puissance d'extinction d'un robot de type roue.
     */
    static final int PUISSANCE_ROBOT_ROUE = 200;
    /**
     * La puissance d'extinction d'un robot de type chenille.
     */
    static final int PUISSANCE_ROBOT_CHENILLE = 200;
    /**
     * La puissance d'extinction d'un robot de type jet-pack.
     */
    static final int PUISSANCE_ROBOT_JETPACK = 50;
    
    static final int FACTEUR_PATTE_CHEMIN = 1;
    static final int FACTEUR_PATTE_PLAINE = 2;
    static final int FACTEUR_PATTE_TERRAIN_ACCIDENTE = 3;
    static final int FACTEUR_PATTE_FORET = 4;
    static final int FACTEUR_PATTE_ROCHER = 5;
    static final int FACTEUR_PATTE_MONTAGNE = 6;
    
    static final int FACTEUR_ROUE_CHEMIN = 1;
    static final int FACTEUR_ROUE_PLAINE = 1;
    static final int FACTEUR_ROUE_TERRAIN_ACCIDENTE = 2;
    static final int FACTEUR_ROUE_FORET = 2;
    static final int FACTEUR_ROUE_ROCHER = 3;
    static final int FACTEUR_ROUE_MONTAGNE = 4;
    
    static final int FACTEUR_CHENILLE_CHEMIN = 1;
    static final int FACTEUR_CHENILLE_PLAINE = 1;
    static final int FACTEUR_CHENILLE_TERRAIN_ACCIDENTE = 2;
    static final int FACTEUR_CHENILLE_FORET = 2;
    static final int FACTEUR_CHENILLE_ROCHER = 10;
    static final int FACTEUR_CHENILLE_MONTAGNE = 10;
    
    static final int FACTEUR_JETPACK_CHEMIN = 1;
    static final int FACTEUR_JETPACK_PLAINE = 1;
    static final int FACTEUR_JETPACK_TERRAIN_ACCIDENTE = 1;
    static final int FACTEUR_JETPACK_FORET = 1;
    static final int FACTEUR_JETPACK_ROCHER = 1;
    static final int FACTEUR_JETPACK_MONTAGNE = 1;
    
}
