/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ThreadCalcul;
import Model.ArchiveSimulation;
import Model.Simulation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author arthur
 */
class StartListener implements ActionListener {

    private Main window;
    private SimulationPanel panel;
    public StartListener(Main window, SimulationPanel panel) {
        this.window = window;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.checkState();
        //window.getSimulation().mettreAJour();
        //window.setMap(window.getSimulation().archiverTour());
        System.out.println("ARCHIVE==========");
        //System.out.println(window.getSimulation().archiverTour());
        Timer timer;
        if(this.panel.getTimer()!= null){
            System.out.println("Reprise de la simulation...");
            timer = this.panel.getTimer();
        }else{
            System.out.println("Début de la simulation...");
            timer = new Timer(1000, new TimerListener(window, panel));
            this.panel.setTimer(timer);
            
            //on lance le thread calcul
            ThreadCalcul calcul = new ThreadCalcul(window.getSimulation());
            calcul.start();
            //this.window.getRobotsPanel().setList(window.getSimulation().archiverTour().getEtatsEntite());
        }        
        timer.start();   
    }
    
    public void checkState(){
        this.panel.setEtat(1);
        this.panel.getStart().setEnabled(false);
        this.panel.getStop().setEnabled(true);
    }
    
}
