/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue.accueil.config;

import fr.fito.modele.CarteDeTerrain;
import fr.fito.modele.parametrage.InitialisationIncendie;
import fr.fito.modele.parametrage.InitialisationRobot;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author S219
 */
class EcouteurAjouterIncendie implements ActionListener {

    private List<InitialisationIncendie> incendies;
    private List<InitialisationRobot> robots;
    private Dimension clickLocation;
    private PanelAffichageBmp main;
    private CarteDeTerrain map;
    public EcouteurAjouterIncendie(PanelAffichageBmp main, List<InitialisationIncendie> incendies, Dimension clickLocation, CarteDeTerrain map, List<InitialisationRobot> robots) {
        this.incendies = incendies;
        this.clickLocation = clickLocation;
        this.main = main;
        this.map = map;
        this.robots = robots;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = clickLocation.width/30;
        int y = clickLocation.height/30;
        if(x > this.map.getHauteur()-1|| y > this.map.getLargeur()-1){
            JOptionPane.showMessageDialog(this.main, "On ne peut pas ajouter cet incendie\nLes coordonnées en dehors de la carte !", "Mauvaises coordonnées", JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(this.coordAvailable(x,y)){
                this.incendies.add(new InitialisationIncendie(x, y)); 
                this.main.repaint(); 
            }else{
                JOptionPane.showMessageDialog(this.main, "Il existe déjà un robot ou un feu aux coordonnées (" + x + ", " + y +")", "Coordonnées déjà utilisées", JOptionPane.ERROR_MESSAGE);
            }           
        }       
    }
    
    private boolean coordAvailable(int x, int y){
        if(this.robots.size() > 0){
           for(InitialisationRobot robot : this.robots){
                if(robot.getX_depart()== x && robot.getY_depart()== y){
                    return false;
                }
            } 
        }  
        if(this.incendies.size() > 0){
            for(InitialisationIncendie incendie : this.incendies){
                if(incendie.getX_depart() == x && incendie.getY_depart() == y){
                    return false;
                }
            }
        }
        return true;
    }
    
}
