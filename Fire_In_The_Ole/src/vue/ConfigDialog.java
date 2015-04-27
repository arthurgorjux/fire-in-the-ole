/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import modele.CarteDeTerrain;
import modele.Robot;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

/**
 *
 * @author arthur
 */
public class ConfigDialog extends JFrame{
    
    private JLabel filename;
    private JButton creerRobots;
    private JButton chargerBitmap;
    private JButton lancer;
    private JButton fermer;
    private LinkedList<Robot> robots;
    private CarteDeTerrain carte;
    
    public ConfigDialog(){
        super();
        this.initComponents();
        this.setSize(new Dimension(450,200));
        this.setBounds(500, 400, 450, 200);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initComponents(){
        this.robots = new LinkedList<>();
        this.getContentPane().setLayout(new GridLayout(3, 1, 5, 5));
        JPanel top = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel bottom = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel center = new JPanel(new BorderLayout());
        
        filename = new JLabel("");
        creerRobots = new JButton("Ajouter des robots");
        creerRobots.addActionListener(new CreerRobotListener(this, robots));
        
        chargerBitmap = new JButton("Charger une carte");
        chargerBitmap.addActionListener(new BitmapChargerListener(this));
        
        lancer = new JButton("Lancer");
        lancer.addActionListener(new LancerProgListener(this));
        
        fermer = new JButton("Fermer");
        fermer.addActionListener(new ActionListenerImpl(this));
        
        top.add(chargerBitmap);
        top.add(creerRobots);
        center.add(filename, BorderLayout.CENTER);
        bottom.add(lancer);
        bottom.add(fermer);
        
        this.getContentPane().add(top);
        this.getContentPane().add(center);
        this.getContentPane().add(bottom);
    }
    
    public LinkedList<Robot> getRobots(){
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
