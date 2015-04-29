package fr.fito.vue.regardersimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ecouteur du bouton start.
 * @author arthur
 */
class EcouteurBoutonDemarrerSimulation implements ActionListener {
    private final PanelPilotageSimulation panel;
    
    public EcouteurBoutonDemarrerSimulation(PanelPilotageSimulation panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.demarrerSimulation();     
    }
    
}
