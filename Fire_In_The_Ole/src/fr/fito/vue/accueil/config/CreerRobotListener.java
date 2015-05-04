/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue.accueil.config;

import fr.fito.vue.accueil.config.ConfigDialog;
import fr.fito.modele.Robot;
import fr.fito.modele.parametrage.InitialisationRobot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author arthur
 */
class CreerRobotListener implements ActionListener {

    private ConfigDialog windowMain;
    private List<InitialisationRobot> robots;
    public CreerRobotListener(ConfigDialog window, List<InitialisationRobot> robots) {
        windowMain = window;
        this.robots = robots;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new CreateRobotDialog(this.robots, this.windowMain);
    }
    
}
