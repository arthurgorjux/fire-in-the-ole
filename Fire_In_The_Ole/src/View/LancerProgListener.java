/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author arthur
 */
class LancerProgListener implements ActionListener {

    private ConfigDialog main;
    
    public LancerProgListener(ConfigDialog aThis) {
        this.main = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        try {
            if(this.main.getRobots().size() > 0 && this.main.getCarte() != null){
                new Main(this.main.getRobots(), this.main.getCarte());
            }else{
                JOptionPane.showMessageDialog(this.main, "Carte et/ou robots vide(s)", "Il faut choisir une carte et créer au moins un robot", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(LancerProgListener.class.getName()).log(Level.SEVERE, null, ex);
        }
                */
    }
    
}
