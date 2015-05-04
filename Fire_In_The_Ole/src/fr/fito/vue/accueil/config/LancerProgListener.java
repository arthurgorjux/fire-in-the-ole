/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue.accueil.config;

import fr.fito.vue.accueil.config.ConfigDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author arthur
 */
class LancerProgListener implements ActionListener {

    private FenetreCreationParametre main;
    
    public LancerProgListener(FenetreCreationParametre main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.lancerSimulation();
        main.dispose();
    }
    
}
