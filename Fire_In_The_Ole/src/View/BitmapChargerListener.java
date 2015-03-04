/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.BitmapLoader;
import Model.CarteDeTerrain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author arthur
 */
public class BitmapChargerListener implements ActionListener{
    
    private Main window;
    private CarteDeTerrain map;

    public BitmapChargerListener(Main window, CarteDeTerrain map) {
        this.window = window;
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser c = new JFileChooser();
        String filename;
        String dir;
      // Demonstrate "Open" dialog:
      int rVal = c.showOpenDialog(this.window);
      if (rVal == JFileChooser.APPROVE_OPTION) {
        filename = c.getSelectedFile().getName();
        dir = c.getCurrentDirectory().toString();
        if(!filename.endsWith(".bmp")){
            JOptionPane.showMessageDialog(window, "L'extension du fichier doit être un .bmp", "Mauvaise extension", JOptionPane.ERROR_MESSAGE);
        }else{
            BitmapLoader bmp = new BitmapLoader(dir + "/" + filename);
            this.map = new CarteDeTerrain();
            this.map.setCarte(bmp.getCarte());
            this.window.changeMap(this.map);
            this.window.mapPanel = new MapPanel(map);
            this.window.mapPanel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
            this.window.mapPanel.setPreferredSize(new Dimension(300,300)); 
            this.window.layout_north.add(this.window.mapPanel);
            this.window.revalidate();
            this.window.repaint();
        }
      }
      if (rVal == JFileChooser.CANCEL_OPTION) {
      }
    }
    
}
