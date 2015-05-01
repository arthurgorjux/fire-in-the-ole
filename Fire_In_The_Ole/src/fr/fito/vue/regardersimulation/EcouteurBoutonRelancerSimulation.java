package fr.fito.vue.regardersimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ecouteur du bouton relancer la simulation.
 * @author Nicolas
 */
public class EcouteurBoutonRelancerSimulation implements ActionListener{
    private PanelPilotageSimulation panel;

     /**
     * Construit l'écouteur en lui fournissant une référence vers le panneau qui le contient.
     * @param panel Le panneau qui le contient.
     */
    public EcouteurBoutonRelancerSimulation(PanelPilotageSimulation panel) {
        this.panel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.relancerLaSimulation();
    }
    
}
