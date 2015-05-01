package fr.fito.vue.regardersimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ecouteur du bouton mettre en pause la simulation.
 * @author arthur
 */
public class EcouteurBoutonMettreEnPauseLaSimulation implements ActionListener {
    private final PanelPilotageSimulation panel;
    
    /**
     * Construit l'écouteur en lui fournissant une référence vers le panneau qui le contient.
     * @param panel Le panneau qui le contient.
     */
    public EcouteurBoutonMettreEnPauseLaSimulation(PanelPilotageSimulation panel) {
        this.panel = panel;
    }

    /**
     * Lors du clic sur le bouton, demande à la fenêtre de mettre en pause la simulation.
     * @param e L'évènement qui a déclanchée l'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.mettreEnPauseLaSimulation();
    }
    
}
