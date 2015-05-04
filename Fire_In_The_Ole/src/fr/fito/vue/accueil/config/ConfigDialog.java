/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue.accueil.config;

import fr.fito.modele.CarteDeTerrain;
import fr.fito.modele.parametrage.InitialisationIncendie;
import fr.fito.modele.parametrage.InitialisationRobot;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author arthur
 */
public class ConfigDialog extends JFrame{
    
    private JLabel filename;
    private JButton chargerBitmap;
    private JButton lancer;
    private JButton fermer;
    private List<InitialisationRobot> robots;
    private List<InitialisationIncendie> incendies;
    private CarteDeTerrain carte;
    
    public ConfigDialog(){
        super();
        this.initComponents();
        this.setSize(new Dimension(450,200));
        this.setBounds(500, 400, 450, 200);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    private void initComponents(){
        this.robots = new LinkedList<>();
        this.getContentPane().setLayout(new GridLayout(3, 1, 5, 5));
        JPanel top = new JPanel(new GridLayout(1, 3, 5, 5));
        JPanel bottom = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel center = new JPanel(new BorderLayout());
        
        chargerBitmap = new JButton("Charger une carte");
        chargerBitmap.addActionListener(new BitmapChargerListener(this));
        
        top.add(chargerBitmap);
        
        this.getContentPane().add(top);
    }
    
    public List<InitialisationRobot> getRobots(){
        return this.robots;
    }
    
    public CarteDeTerrain getCarte(){
        return this.carte;
    }
    
    public void setMap(CarteDeTerrain map){
        this.carte = map;
    }
    
    public void setLabel(String label){
        this.filename.setText(label);
        this.revalidate();
        this.repaint();
    }

    private static class ActionListenerImpl implements ActionListener {

        private ConfigDialog main;
        
        public ActionListenerImpl(ConfigDialog main) {
            this.main = main;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.main.dispose();
        }
    }
    
}
