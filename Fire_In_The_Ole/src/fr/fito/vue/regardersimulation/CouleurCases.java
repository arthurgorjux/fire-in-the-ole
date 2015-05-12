
package fr.fito.vue.regardersimulation;

import fr.fito.modele.ConstantesTypesTerrain;
import java.awt.Color;

/**
 *
 * @author arthur
 * Classe statique pour gérer la couleur des cases de la carte
 */
public class CouleurCases {
    
    private static final int RED_COLOR = 6;
    private static final int BLUE_COLOR = 4;
    
    /**
     * 
     * @param difficulte
     * @return la couleur correspondante à la difficulté
     */
    public static Color getColorByDifficulte(int difficulte) {
        if(difficulte >= ConstantesTypesTerrain.CHEMIN_MIN && difficulte <= ConstantesTypesTerrain.CHEMIN_MAX){
            return new Color(RED_COLOR,255,BLUE_COLOR);
        }else if(difficulte >= ConstantesTypesTerrain.PLAINE_MIN && difficulte <= ConstantesTypesTerrain.PLAINE_MAX){
            return new Color(RED_COLOR,213,BLUE_COLOR);
        }else if(difficulte >= ConstantesTypesTerrain.TERRAIN_ACCIDENTE_MIN && difficulte <= ConstantesTypesTerrain.TERRAIN_ACCIDENTE_MAX){
            return new Color(RED_COLOR,171,BLUE_COLOR);
        }else if(difficulte >= ConstantesTypesTerrain.FORET_MIN && difficulte <= ConstantesTypesTerrain.FORET_MAX){
            return new Color(RED_COLOR,128,BLUE_COLOR);
        }else if(difficulte >= ConstantesTypesTerrain.ROCHER_MIN && difficulte <= ConstantesTypesTerrain.ROCHER_MAX){
            return new Color(4,85,32);
        }else{
            return new Color(0,50,0);
        }
    }
}
