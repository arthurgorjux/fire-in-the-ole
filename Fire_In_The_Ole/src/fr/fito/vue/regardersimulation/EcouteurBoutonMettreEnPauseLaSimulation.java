package fr.fito.vue.regardersimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Ecouteur du bouton Stop.
 * @author arthur
 */
class EcouteurBoutonMettreEnPauseLaSimulation implements ActionListener {
    private PanelPilotageSimulation panel;
    
    public EcouteurBoutonMettreEnPauseLaSimulation(PanelPilotageSimulation panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.mettreEnPauseLaSimulation();
    }
    
}
