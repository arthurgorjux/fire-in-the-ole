
package fr.fito.vue.regardersimulation;

import fr.fito.modele.ConstantesTypesTerrain;
import java.awt.Color;

/**
 *
 * @author arthur
 * Classe statique pour gérer la couleur des cases de la carte
 */
public class CouleurCases {
    
    private static final int RED_COLOR = 139;
    private static final int BLUE_COLOR = 19;
    
    /**
     * 
     * @param difficulte
     * @return la couleur correspondante à la difficulté
     */
    public static Color getColorByDifficulte(int difficulte) {
        if(difficulte >= ConstantesTypesTerrain.CHEMIN_MIN && difficulte <= ConstantesTypesTerrain.CHEMIN_MAX){
            return new Color(RED_COLOR,ConstantesTypesTerrain.CHEMIN_MAX,BLUE_COLOR);
        }else if(difficulte >= ConstantesTypesTerrain.PLAINE_MIN && difficulte <= ConstantesTypesTerrain.PLAINE_MAX){
            return new Color(RED_COLOR,ConstantesTypesTerrain.PLAINE_MAX,BLUE_COLOR);
        }else if(difficulte >= ConstantesTypesTerrain.FORET_MIN && difficulte <= ConstantesTypesTerrain.FORET_MAX){
            return new Color(RED_COLOR,ConstantesTypesTerrain.FORET_MAX,BLUE_COLOR);
        }else if(difficulte >= ConstantesTypesTerrain.ROCHER_MIN && difficulte <= ConstantesTypesTerrain.ROCHER_MAX){
            return new Color(RED_COLOR,ConstantesTypesTerrain.ROCHER_MAX,BLUE_COLOR);
        }else{
            return new Color(RED_COLOR,ConstantesTypesTerrain.MONTAGNE_MAX,BLUE_COLOR);
        }
    }
}
