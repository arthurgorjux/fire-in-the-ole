/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Nicolas
 */
public class ResetSimuListener implements ActionListener{

    private Main window;
    private SimulationPanel panel;

    public ResetSimuListener(Main window, SimulationPanel panel) {
        this.window = window;this.panel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // ré-initialisation timer
        this.panel.getTimer().stop();
        this.panel.setTimer(new Timer(1000, new TimerListener(window, panel)));
        
        
    }
    
}
