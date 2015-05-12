/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue.regardersimulation;

import fr.fito.modele.CarteDeTerrain;
import fr.fito.modele.ConstantesTypesTerrain;
import fr.fito.modele.MappingTypeRobot;
import fr.fito.modele.Robot;
import fr.fito.modele.Simulation;
import fr.fito.modele.archivage.EtatEntite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author S219
 */
public class PanelAffichageCarte extends javax.swing.JPanel{

    private final CarteDeTerrain map;
    public static final int PREFERRED_GRID_SIZE_PIXELS = 10;
    private EtatEntite[] etatEntites;
    private Simulation simulation;
    private boolean isReset = false;
    
    PanelAffichageCarte(CarteDeTerrain map, Simulation simu) {
        this.map = map;
        this.simulation = simu;
        //this.setBackground(new Color(144,238,144));
        // Set size map
        int preferredWidth = this.map.getLargeur()* PREFERRED_GRID_SIZE_PIXELS;
        int preferredHeight = this.map.getHauteur() * PREFERRED_GRID_SIZE_PIXELS;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
    }
    
    public void enableReset(){
        this.isReset = true;
    }
    
    public void disableReset(){
        this.isReset = false;
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
        if(!this.isReset){
            if(this.etatEntites != null){
                for(EtatEntite entite : this.etatEntites){
                    try {
                        Image img = null;
                        Color colorEntite = null;
                        switch(entite.getType()){
                            default:                            
                                Image imgRobot = ImageIO.read(getClass().getResource(MappingTypeRobot.getPicture(entite.getType())));
                                img = imgRobot;
                                break;
                            case "incendie":
                                //colorEntite = new Color(255, 0, 0);
                                Image imgFeu = ImageIO.read(getClass().getResource("/IMG/feu_2.png"));
                                img = imgFeu;
                                break;
                        }
                        int widthEntite = PREFERRED_GRID_SIZE_PIXELS * 3;
                        int heightEntite = PREFERRED_GRID_SIZE_PIXELS * 3;
                        int x = entite.getPosition().getX() * rectWidth;
                        int y = entite.getPosition().getY() * rectHeight;
                        /*if(colorEntite != null)
                            g2d.setColor(colorEntite*/

                        g2d.drawImage(img, x, y, widthEntite, heightEntite, this);
                        //g2d.fillRect(x, y, widthEntite, heightEntite);
                    } catch (IOException ex) {
                        Logger.getLogger(PanelAffichageCarte.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public void setEtatsEntites(EtatEntite[] etatsEntite) {
        this.etatEntites = etatsEntite;
    }  
}
