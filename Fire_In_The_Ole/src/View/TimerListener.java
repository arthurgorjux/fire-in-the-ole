/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ArchiveSimulation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author arthur
 */
public class TimerListener implements ActionListener{
    
    private Main window;
    private SimulationPanel panel;
    
    public TimerListener(Main window, SimulationPanel panel){
        this.window = window;
        this.panel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!window.getSimulation().estTerminee()){
            window.getSimulation().mettreAJour();
            window.setMap(window.getSimulation().archiverTour());
        }else{
            Timer t = (Timer) e.getSource();
            t.stop();
            System.out.println("Fin");
            ArchiveSimulation archive = this.window.getSimulation().getArchiveResultat();
            archive.afficher();
        }
    }    
}
