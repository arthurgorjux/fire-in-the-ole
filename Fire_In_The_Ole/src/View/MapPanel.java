/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CarteDeTerrain;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author S219
 */
public class MapPanel extends javax.swing.JPanel{

    private CarteDeTerrain map;
    public static final int NUM_ROWS = 4;
    public static final int NUM_COLS = 4;
    public static final int PREFERRED_GRID_SIZE_PIXELS = 10;
    
    MapPanel(CarteDeTerrain map) {
        this.map = map;
        
        // Set size map
        int preferredWidth = NUM_COLS * PREFERRED_GRID_SIZE_PIXELS;
        int preferredHeight = NUM_ROWS * PREFERRED_GRID_SIZE_PIXELS;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.clearRect(0, 0, getWidth(), getHeight());
        
        int rectWidth = NUM_COLS * 15;
        int rectHeight = NUM_ROWS * 15;
        
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
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
    }
    
}
