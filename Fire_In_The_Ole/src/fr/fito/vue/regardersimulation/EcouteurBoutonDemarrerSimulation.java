package fr.fito.vue.regardersimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ecouteur du bouton demarrer la simulation.
 * @author arthur
 */
public class EcouteurBoutonDemarrerSimulation implements ActionListener {
    private final PanelPilotageSimulation panel;
    
    /**
     * Construit un nouveau bouton avec une référence vers son apnneau parent.
     * @param panel Le panneau aprent.
     */
    public EcouteurBoutonDemarrerSimulation(PanelPilotageSimulation panel) {
        this.panel = panel;
    }

    /**
     * Lors du clic sur le bouton, demande à la fenêtre de démarrer la simulation.
     * @param e L'évènement qui a déclanchée l'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.demarrerSimulation();     
    }
    
}
