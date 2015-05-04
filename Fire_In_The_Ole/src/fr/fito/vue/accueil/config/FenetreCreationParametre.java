/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue.accueil.config;

import fr.fito.modele.CarteDeTerrain;
import fr.fito.modele.SensVent;
import fr.fito.modele.Simulation;
import fr.fito.modele.parametrage.InitialisationIncendie;
import fr.fito.modele.parametrage.InitialisationRobot;
import fr.fito.modele.parametrage.JeuDeParametres;
import fr.fito.vue.accueil.EcouteurBoutonLancerSimulation;
import fr.fito.vue.regardersimulation.FenetreRegarderSimulation;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author arthur
 */
public class FenetreCreationParametre extends JFrame{
    private PanelAffichageBmp panelCarte;
    private JButton valider;
    private JButton fermer;
    private CarteDeTerrain map;
    private List<InitialisationRobot> robots;
    private List<InitialisationIncendie> incendies;

    public FenetreCreationParametre(CarteDeTerrain map){
        super();
        this.map = map;
        this.panelCarte = new PanelAffichageBmp(this.map, this);
        this.valider = new JButton("Valider");
        this.valider.setEnabled(false);
        this.fermer = new JButton("Fermer");
        this.fermer.addActionListener(new ActionListenerImpl(this));
        this.getContentPane().setLayout(new BorderLayout());
        JPanel top = new JPanel();
        top.add(this.panelCarte);
        this.add(this.panelCarte, BorderLayout.CENTER);
        
        JPanel bottom = new JPanel(new GridLayout(1, 2, 5, 5));
        bottom.add(valider);
        bottom.add(fermer);
        
        this.add(bottom, BorderLayout.SOUTH);
        
        //this.setPreferredSize(new Dimension(this.panelCarte.getWidth(), 500));
        this.setBounds(500, 400, 1200, 550);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void ready(List<InitialisationRobot> robots, List<InitialisationIncendie> incendies) {
        this.robots = robots;
        this.incendies = incendies;
        this.valider.setEnabled(true);
        this.valider.addActionListener(new LancerProgListener(this));
    }
    
    public void lancerSimulation(){
        try {
            JeuDeParametres params = new JeuDeParametres(this.map.getChemin(), SensVent.EST, incendies, robots);
            FenetreRegarderSimulation main = new FenetreRegarderSimulation(new Simulation(params), this.map);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(EcouteurBoutonLancerSimulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<InitialisationRobot> getRobots(){
        return this.robots;
    }
    
    public List<InitialisationIncendie> getIncendies(){
        return this.incendies;
    }
    
    public CarteDeTerrain getMap(){
        return this.map;
    }

    private static class ActionListenerImpl implements ActionListener {

        FenetreCreationParametre main;
        public ActionListenerImpl(FenetreCreationParametre aThis) {
            this.main = aThis;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.main.dispose();
        }
    }
    
    
}
