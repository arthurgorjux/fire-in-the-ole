package fr.fito.vue.regardersimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Ecouteur du bouton Stop.
 * @author arthur
 */
class EcouteurBoutonGestionPauseSimulation implements ActionListener {

    private FenetreRegarderSimulation window;
    private PanelPilotageSimulation panel;
    public EcouteurBoutonGestionPauseSimulation(FenetreRegarderSimulation window, PanelPilotageSimulation panel) {
        this.window = window;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.checkState();
        System.out.println("Simulation en pause...");
        this.panel.getTimer().stop();
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
