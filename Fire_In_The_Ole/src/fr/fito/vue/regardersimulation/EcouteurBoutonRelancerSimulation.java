package fr.fito.vue.regardersimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Nicolas
 */
public class EcouteurBoutonRelancerSimulation implements ActionListener{
    private PanelPilotageSimulation panel;

    public EcouteurBoutonRelancerSimulation(PanelPilotageSimulation panel) {
        this.panel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.relancerLaSimulation();
    }
    
}
