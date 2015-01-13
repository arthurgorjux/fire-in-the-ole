/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ArchiveSimulation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author arthur
 */
class StopListener implements ActionListener {

    private Main window;
    private SimulationPanel panel;
    public StopListener(Main window, SimulationPanel panel) {
        this.window = window;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.checkState();
        /*System.out.println("Génération de la simulation...");
        window.getSimulation().mettreAJour();
        window.setMap(window.getSimulation().archiverTour());
        Timer timer = new Timer(1000, new TimerListener(window));
        timer.start(); */
    }
    
    public void checkState(){
        this.panel.setEtat(0);
        this.panel.getStop().setEnabled(false);
        this.panel.getStart().setEnabled(true);
    }
    
}
