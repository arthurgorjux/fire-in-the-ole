package fr.fito.vue.regardersimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ecouteur du bouton Stop.
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

    @Override
    /**
     * Met en pause la simulation lmors du clic sur le bouton.
     */
    public void actionPerformed(ActionEvent e) {
        panel.mettreEnPauseLaSimulation();
    }
    
}
