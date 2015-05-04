/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue.accueil.config;

import fr.fito.vue.accueil.config.CreateRobotDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author arthur
 */
class AnnulerCreateRobotListener implements ActionListener {

    CreateRobotDialog window;
    
    public AnnulerCreateRobotListener(CreateRobotDialog aThis) {
        this.window = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.window.dispose();
    }
    
}
