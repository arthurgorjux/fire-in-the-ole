/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Simulateur;
import Model.ArchiveTourSimulation;
import Model.CarteDeTerrain;
import Model.EtatEntite;
import Model.Robot;
import Model.Simulation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author S219
 */
public class Main extends JFrame{
    
    private CarteDeTerrain map;
    public MapPanel mapPanel;
    private Simulation simulation;
    
    private javax.swing.JButton start;
    private javax.swing.JButton creerRobot;
    //private javax.swing.JLabel jLabel1;
    /*private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;*/
    private RobotsPanel listeRobots;
    private javax.swing.JPanel infos;
    private javax.swing.JPanel simulateur;
    private javax.swing.JPanel stats;
    private EtatEntite[] etatsEntite;
    private List<Robot> robots;
    private JPanel layout = new JPanel();
    public JPanel layout_north = new JPanel();
    private JPanel layout_south = new JPanel();
    private JMenuBar menu = new JMenuBar();
    private JMenu fichier = new JMenu("Fichier");
    private JMenuItem bitmapCharger = new JMenuItem("Charger map");
    
    public Main(LinkedList<Robot> robots, CarteDeTerrain map) throws IOException {
        //this.simulation = new Simulation();
        this.robots = robots;
        this.map = map;
        this.initComponents();        
        //this.setContentPane(mapPanel);
        this.setLocationRelativeTo(null);
        this.setSize(500, 500);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }*/
    
    public void setMap(ArchiveTourSimulation tour){
        this.etatsEntite = tour.getEtatsEntite();
        mapPanel.setEtatsEntites(tour.getEtatsEntite());
        mapPanel.repaint();
    }
    
    public EtatEntite[] getEtatsEntites(){
        return this.etatsEntite;
    }
    
    public RobotsPanel getRobotsPanel(){
        return this.listeRobots;
    }
    
    public CarteDeTerrain getMap(){
        return this.map;
    }
    
    public Simulation getSimulation(){
        return this.simulation;
    }

    private void initComponents() throws IOException {
        
        /*this.bitmapCharger.addActionListener(new BitmapChargerListener(this, this.map));
        this.fichier.add(this.bitmapCharger);
        this.menu.add(this.fichier);
        this.setJMenuBar(this.menu);*/
        
        layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));
        layout_north.setLayout(new BoxLayout(layout_north, BoxLayout.X_AXIS));
        layout_south.setLayout(new GridLayout(1, 2));
        
        //start = new JButton("Start");
        //creerRobot = new JButton("Creer");
        
        //start.addActionListener(new StartListener(this));
        
        mapPanel = new MapPanel(map);
        mapPanel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        mapPanel.setPreferredSize(new Dimension(300,300));  
        
        infos = new javax.swing.JPanel();
        infos.setPreferredSize(new Dimension(layout_south.getWidth(), layout_south.getHeight()));
        listeRobots = new RobotsPanel(this, robots);
        //creerRobot = new javax.swing.JButton("Creer");
        stats = new javax.swing.JPanel();
        stats.setPreferredSize(new Dimension(infos.getWidth()*(2/3), infos.getHeight()));
        simulateur = new SimulationPanel(this);
        simulateur.setPreferredSize(new Dimension(infos.getWidth()*(1/3), infos.getHeight()));
        //start = new javax.swing.JButton("Start");

        
        simulateur.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        stats.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        listeRobots.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        listeRobots.setPreferredSize(new Dimension(200, 300));
        //simulateur.add(start);
        //listeRobots.add(creerRobot);
        layout_north.add(mapPanel);
        //layout_north.add(listeRobots);
        
        layout_south.add(simulateur);
        layout_south.add(stats);
        layout.add(layout_north);
        layout.add(layout_south);
        this.setPreferredSize(new Dimension(layout.getWidth(), layout.getHeight()));
        this.setContentPane(layout);
    }
    
    public List<Robot> getRobotsList(){
        return this.robots;
    }
    
    public void setSimulation(Simulation simu){
        this.simulation = simu;
    }
    
    void changeMap(CarteDeTerrain map) {
        this.map = map;
    }
}
