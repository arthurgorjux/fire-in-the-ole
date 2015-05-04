/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue.accueil.config;

import fr.fito.modele.CarteDeTerrain;
import fr.fito.modele.TypeRobot;
import fr.fito.modele.parametrage.InitialisationIncendie;
import fr.fito.modele.parametrage.InitialisationRobot;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author S219
 */
class EcouteurAjouterRobot implements ActionListener {

    private Dimension clickPosition;
    private List<InitialisationRobot> robots;
    private List<InitialisationIncendie> incendies;
    private TypeRobot typeRobot;
    private PanelAffichageBmp main;
    private CarteDeTerrain map;
    public EcouteurAjouterRobot(PanelAffichageBmp main, List<InitialisationRobot> robots, Dimension mousePosition, TypeRobot typeRobot, CarteDeTerrain map, List<InitialisationIncendie> incendies) {
        this.clickPosition = mousePosition;
        this.robots = robots;
        this.typeRobot = typeRobot;
        this.main = main;
        this.map = map;
        this.incendies = incendies;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = clickPosition.width/30;
        int y = clickPosition.height/30;
        if(x > this.map.getLargeur() || y > this.map.getHauteur()){
            JOptionPane.showMessageDialog(this.main, "On ne peut pas ajouter ce robot\nLes coordonnées sont en dehors de la carte !", "Mauvaises coordonnées", JOptionPane.ERROR_MESSAGE);
        }else{
            if(this.coordAvailable(x,y)){
                this.robots.add(new InitialisationRobot(x, y, typeRobot));
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
