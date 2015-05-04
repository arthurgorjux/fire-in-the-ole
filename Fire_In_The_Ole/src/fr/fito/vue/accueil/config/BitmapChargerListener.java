/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue.accueil.config;

import fr.fito.vue.accueil.config.ConfigDialog;
import fr.fito.utilitaire.BitmapLoader;
import fr.fito.modele.CarteDeTerrain;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author arthur
 */
public class BitmapChargerListener implements ActionListener{
    
    private ConfigDialog window;

    public BitmapChargerListener(ConfigDialog window) {
        this.window = window;
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
            try {
                BitmapLoader bmp = new BitmapLoader();
                CarteDeTerrain map = new CarteDeTerrain(bmp.lireFichierBitmap(dir + "/" + filename));
                JFrame frame = new JFrame();
                frame.setContentPane(new PanelAffichageBmp(map));
                frame.setPreferredSize(new Dimension(500, 500));
                frame.setBounds(500, 400, 1200, 550);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.window.dispose();
                //this.window.setMap(map);
                //this.window.setLabel("Carte choisie : " + filename);
            } catch (IOException ex) {
                Logger.getLogger(BitmapChargerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      }
      if (rVal == JFileChooser.CANCEL_OPTION) {
      }
    }
    
}
