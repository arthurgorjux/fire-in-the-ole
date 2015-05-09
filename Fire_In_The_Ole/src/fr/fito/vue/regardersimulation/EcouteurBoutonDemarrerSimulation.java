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
        //etat 0 = non démarrée - etat 1 = simu en cours -etat 2 = simu en pause
        System.out.println("ETAT SIMU = "+this.panel.getEtat());
        if (panel.getEtat() == 0)
            panel.demarrerSimulation();
        else if (panel.getEtat() == 2)
            panel.relancerLaSimulation();
        else {
            System.err.println("Lancement de la simulation alors que la simulation est déjà lancée...");
            System.exit(1);
        }
    }
    
}
