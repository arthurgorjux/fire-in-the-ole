/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.modele;

/**
 *
 * @author arthur
 */
public class MappingTypeRobot {
    /**
     * Retourne le chemin vers l'image du robot en fonction de son type.
     * @return chemin vers l'image.
     */
    
    public static String getPicture(String type){
        String path = "/IMG/";
        switch(type){
            case "PATTE" :
                path += "Robot_Patte.png";
                break;
            case "CHENILLE" :
                path += "Robot_Chenille.png";
                break;
            case "ROUE" :
                path += "Robot_Roue.png";
                break;
            case "JETPACK" :
                path += "Robot_Jetpack.png";
                break;
        }        
        return path;
    } 
}
