/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CarteDeTerrain;
import Model.EtatEntite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author S219
 */
public class MapPanel extends javax.swing.JPanel{

    private CarteDeTerrain map;
    public static final int PREFERRED_GRID_SIZE_PIXELS = 7;
    private EtatEntite[] etatEntites;
    
    MapPanel(CarteDeTerrain map) {
        this.map = map;
        
        // Set size map
        int preferredWidth = this.map.getLargeur()* PREFERRED_GRID_SIZE_PIXELS;
        int preferredHeight = this.map.getHauteur() * PREFERRED_GRID_SIZE_PIXELS;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.clearRect(0, 0, getWidth(), getHeight());
        
        int rectWidth = this.map.getLargeur() * 10;
        int rectHeight = this.map.getHauteur() * 10;
        
        for (int i = 0; i < this.map.getHauteur()-1; i++) {
            for (int j = 0; j < this.map.getLargeur()-1; j++) {
                Color color = new Color(102,153,0);
                int [][] carte = this.map.getCarte();
                switch(carte[i][j]){
                    case 0: color = new Color(144,238,144);
                     break;
                    case 2: color = new Color(50,205,50);
                     break;
                    case 40: color = new Color(139,69,19);
                     break;
                   }
                // Upper left corner of this terrain rect
                int x = i * rectWidth;
                int y = j * rectHeight;
                g2d.setColor(color);
                g2d.fillRect(x, y, rectWidth, rectHeight);
                
            }
        } 
        if(this.etatEntites != null){
            for(EtatEntite entite : this.etatEntites){
                try {
                    Color colorEntite = new Color(0, 0, 0);
                    Image img = null;
                    switch(entite.getType()){
                        case "typeRobot":
                            Image imgRobot = ImageIO.read(getClass().getResource("/IMG/robot.png"));
                            img = imgRobot;
                            break;
                        case "incendie":
                            colorEntite = new Color(255, 0, 0);
                            Image imgFeu = ImageIO.read(getClass().getResource("/IMG/feu_2.png"));
                            img = imgFeu;
                            break;
                    }
                    int widthEntite = this.map.getLargeur()* 10;
                    int heightEntite = this.map.getHauteur() * 10;
                    int x = entite.getPosition().getX() * rectWidth;
                    int y = entite.getPosition().getY() * rectHeight;
                    g2d.setColor(colorEntite);
                    
                    g2d.drawImage(img, x, y, widthEntite, heightEntite, this);
                    //g2d.fillRect(x, y, widthEntite, heightEntite);
                } catch (IOException ex) {
                    Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void setEtatsEntites(EtatEntite[] etatsEntite) {
        this.etatEntites = etatsEntite;
    }    
}
