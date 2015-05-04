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
    private TypeRobot typeRobot;
    private PanelAffichageBmp main;
    private CarteDeTerrain map;
    public EcouteurAjouterRobot(PanelAffichageBmp main, List<InitialisationRobot> robots, Dimension mousePosition, TypeRobot typeRobot, CarteDeTerrain map) {
        this.clickPosition = mousePosition;
        this.robots = robots;
        this.typeRobot = typeRobot;
        this.main = main;
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = clickPosition.width/30;
        int y = clickPosition.height/30;
        if(x > this.map.getLargeur() || y > this.map.getHauteur()){
            JOptionPane.showMessageDialog(this.main, "On ne peut pas ajouter ce robot\nLes coordonnées sont en dehors de la carte !", "Mauvaises coordonnées", JOptionPane.ERROR_MESSAGE);
        }else{
           this.robots.add(new InitialisationRobot(x, y, typeRobot));
           this.main.repaint(); 
        }         
    }    
}
