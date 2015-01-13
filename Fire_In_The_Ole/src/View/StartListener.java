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
class StartListener implements ActionListener {

    private Main window;
    public StartListener(Main window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Génération de la simulation...");
        window.getSimulation().mettreAJour();
        window.setMap(window.getSimulation().archiverTour());
        Timer timer = new Timer(1000, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                window.getSimulation().mettreAJour();
                window.setMap(window.getSimulation().archiverTour());
            }
        });
        if(!window.getSimulation().estTerminee()){
            timer.start(); 
        }else{
            timer.stop();
            System.out.println("Fin");
            ArchiveSimulation archive = this.window.getSimulation().getArchiveResultat();
            archive.afficher();
        }
    }
    
}
