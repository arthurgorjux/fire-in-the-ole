/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Simulateur;
import Model.CarteDeTerrain;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author S219
 */
public class Main extends JFrame{
    
    CarteDeTerrain map = new CarteDeTerrain();
    private MapPanel mapPanel;
    
    private javax.swing.JButton start;
    private javax.swing.JButton creerRobot;
    //private javax.swing.JLabel jLabel1;
    /*private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;*/
    private javax.swing.JPanel listeRobots;
    private javax.swing.JPanel infos;
    private javax.swing.JPanel simulateur;
    private javax.swing.JPanel stats;
    
    public Main() {
        this.initComponents();        
        //this.setContentPane(mapPanel);
        this.setLocationRelativeTo(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private void initComponents() {
        JPanel layout = new JPanel();
        layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));
        JPanel layout_north = new JPanel();
        layout_north.setLayout(new BoxLayout(layout_north, BoxLayout.X_AXIS));
        
        JPanel layout_south = new JPanel();
        layout_south.setLayout(new GridLayout(1, 2));
        
        start = new JButton("Start");
        creerRobot = new JButton("Creer");
        
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Simulateur simulateur = new Simulateur();
                simulateur.jouerSimulation(new CarteDeTerrain());
                simulateur.rejouerSimulation(); 
            }
        });
        
        mapPanel = new MapPanel(map);
        mapPanel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        mapPanel.setPreferredSize(new Dimension(300,300));     
        
        infos = new javax.swing.JPanel();
        infos.setPreferredSize(new Dimension(layout_south.getWidth(), layout_south.getHeight()));
        listeRobots = new javax.swing.JPanel();
        creerRobot = new javax.swing.JButton("Creer");
        stats = new javax.swing.JPanel();
        stats.setPreferredSize(new Dimension(infos.getWidth()*(2/3), infos.getHeight()));
        simulateur = new javax.swing.JPanel();
        simulateur.setPreferredSize(new Dimension(infos.getWidth()*(1/3), infos.getHeight()));
        start = new javax.swing.JButton("Start");

        
        simulateur.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        stats.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        listeRobots.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        simulateur.add(start);
        listeRobots.add(creerRobot);
        layout_north.add(mapPanel);
        layout_north.add(listeRobots);
        
        layout_south.add(simulateur);
        layout_south.add(stats);
        layout.add(layout_north);
        layout.add(layout_south);
        this.setPreferredSize(new Dimension(layout.getWidth(), layout.getHeight()));
        this.setContentPane(layout);
    }
}
