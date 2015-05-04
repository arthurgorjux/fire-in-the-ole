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
    private Dimension clickLocation;
    private PanelAffichageBmp main;
    private CarteDeTerrain map;
    public EcouteurAjouterIncendie(PanelAffichageBmp main, List<InitialisationIncendie> incendies, Dimension clickLocation, CarteDeTerrain map) {
        this.incendies = incendies;
        this.clickLocation = clickLocation;
        this.main = main;
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = clickLocation.width/30;
        int y = clickLocation.height/30;
        if(x > this.map.getLargeur() || y > this.map.getHauteur()){
            JOptionPane.showMessageDialog(this.main, "On ne peut pas ajouter cet incendie\nLes coordonnées en dehors de la carte !", "Mauvaises coordonnées", JOptionPane.ERROR_MESSAGE);
        }else{
           this.incendies.add(new InitialisationIncendie(x, y));  
           this.main.repaint(); 
        }        
    }
    
}
