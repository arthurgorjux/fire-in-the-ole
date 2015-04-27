/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import modele.CarteDeTerrain;
import modele.Simulation;
import modele.stockage.JeuDeParametres;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author arthur
 */
class LancerParamListener implements ActionListener {

    public JeuParametreDialog main;
    public JComboBox<String> combo;
    public LancerParamListener(JeuParametreDialog main, JComboBox<String> listParam) {
        this.main = main;
        this.combo = listParam;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nameParam = (String) this.combo.getSelectedItem();
        JeuDeParametres params = this.main.mapParam.get(nameParam);
        try {
            Main main = new Main(new Simulation(params), new CarteDeTerrain(params.getFichier_carte()));
            this.main.dispose();
        } catch (IOException ex) {
            Logger.getLogger(LancerParamListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
