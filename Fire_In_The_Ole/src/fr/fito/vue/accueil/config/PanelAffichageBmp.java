/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue.accueil.config;

import fr.fito.modele.CarteDeTerrain;
import fr.fito.modele.ConstantesTypesTerrain;
import fr.fito.modele.MappingTypeRobot;
import fr.fito.modele.TypeRobot;
import fr.fito.modele.archivage.EtatEntite;
import fr.fito.modele.parametrage.InitialisationIncendie;
import fr.fito.modele.parametrage.InitialisationRobot;
import fr.fito.vue.regardersimulation.CouleurCases;
import fr.fito.vue.regardersimulation.PanelAffichageCarte;
import static fr.fito.vue.regardersimulation.PanelAffichageCarte.PREFERRED_GRID_SIZE_PIXELS;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author arthur
 */
public class PanelAffichageBmp extends JPanel{
    private CarteDeTerrain map;
    private JButton lancer;
    private JButton fermer;
    private List<InitialisationRobot> robots;
    private List<InitialisationIncendie> incendies;
    public static final int PREFERRED_GRID_SIZE_PIXELS = 10;
    private JPopupMenu clickDroit = new JPopupMenu();
    private FenetreCreationParametre main;
    
    public PanelAffichageBmp(CarteDeTerrain map, FenetreCreationParametre main) {
        super();
        this.main = main;
        this.setPreferredSize(new Dimension(map.getLargeur()*10, map.getHauteur()*10));
        robots = new ArrayList<>();
        incendies = new ArrayList<>();
        final Dimension clickLocation = new Dimension();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clickLocation.setSize(e.getX(), e.getY());
            }
        });
        this.map = map;
        for(TypeRobot type : TypeRobot.values()){
            JMenuItem item = new JMenuItem("Ajouter un robot " + type.toString());
            item.addActionListener(new EcouteurAjouterRobot(this,this.robots, clickLocation, type, map, this.incendies));
            this.clickDroit.add(item);
        }
        JMenuItem itemIncendie = new JMenuItem("Ajouter un incendie");
        itemIncendie.addActionListener(new EcouteurAjouterIncendie(this,this.incendies, clickLocation,map, this.robots));
        this.clickDroit.add(itemIncendie);       
        this.setComponentPopupMenu(clickDroit);
        int preferredWidth = this.map.getLargeur()* PREFERRED_GRID_SIZE_PIXELS;
        int preferredHeight = this.map.getHauteur() * PREFERRED_GRID_SIZE_PIXELS;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.clearRect(0, 0, getWidth(), getHeight());
        
        int rectWidth = PREFERRED_GRID_SIZE_PIXELS * 3;
        int rectHeight = PREFERRED_GRID_SIZE_PIXELS * 3;
        
        for (int i = 0; i < this.map.getHauteur()-1; i++) {
            for (int j = 0; j < this.map.getLargeur()-1; j++) {
                int [][] carte = this.map.getCarte();
                Color color = CouleurCases.getColorByDifficulte(carte[i][j]);
                // Upper left corner of this terrain rect
                int x = i * rectWidth;
                int y = j * rectHeight;
                g2d.setColor(color);
                g2d.fill3DRect(x, y, rectWidth, rectHeight, true);
                
            }
        }
        for(InitialisationRobot robot : this.robots){
            try {
                Color colorEntite = null;             
                Image imgRobot = ImageIO.read(getClass().getResource(MappingTypeRobot.getPicture(robot.getType().toString())));

                int widthEntite = PREFERRED_GRID_SIZE_PIXELS * 3;
                int heightEntite = PREFERRED_GRID_SIZE_PIXELS * 3;
                int x = robot.getX_depart()* rectWidth;
                int y = robot.getY_depart() * rectHeight;
                /*if(colorEntite != null)
                    g2d.setColor(colorEntite*/

                g2d.drawImage(imgRobot, x, y, widthEntite, heightEntite, this);
                //g2d.fillRect(x, y, widthEntite, heightEntite);
            } catch (IOException ex) {
                Logger.getLogger(PanelAffichageCarte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(InitialisationIncendie incendie : this.incendies){
            try {
                Color colorEntite = null;             
                Image imgFeu = ImageIO.read(getClass().getResource("/IMG/feu_2.png"));

                int widthEntite = PREFERRED_GRID_SIZE_PIXELS * 3;
                int heightEntite = PREFERRED_GRID_SIZE_PIXELS * 3;
                int x = incendie.getX_depart()* rectWidth;
                int y = incendie.getY_depart() * rectHeight;
                /*if(colorEntite != null)
                    g2d.setColor(colorEntite*/

                g2d.drawImage(imgFeu, x, y, widthEntite, heightEntite, this);
                //g2d.fillRect(x, y, widthEntite, heightEntite);
            } catch (IOException ex) {
                Logger.getLogger(PanelAffichageCarte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(this.robots.size() > 0 && this.incendies.size() > 0){
            this.main.ready(this.robots, this.incendies);
        }
    }
}
