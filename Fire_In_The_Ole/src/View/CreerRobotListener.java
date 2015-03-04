/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author arthur
 */
class CreerRobotListener implements ActionListener {

    private Main windowMain;
    private RobotsPanel panel;
    private List<Robot> robots;
    public CreerRobotListener(Main window, RobotsPanel aThis, List<Robot> robots) {
        windowMain = window;
        panel = aThis;
        this.robots = robots;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new CreateRobotDialog(this.robots, this.windowMain);
    }
    
}
