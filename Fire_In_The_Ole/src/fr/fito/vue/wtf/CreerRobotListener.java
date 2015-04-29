/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue.wtf;

import fr.fito.modele.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author arthur
 */
class CreerRobotListener implements ActionListener {

    private ConfigDialog windowMain;
    private List<Robot> robots;
    public CreerRobotListener(ConfigDialog window, List<Robot> robots) {
        windowMain = window;
        this.robots = robots;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new CreateRobotDialog(this.robots, this.windowMain);
    }
    
}
