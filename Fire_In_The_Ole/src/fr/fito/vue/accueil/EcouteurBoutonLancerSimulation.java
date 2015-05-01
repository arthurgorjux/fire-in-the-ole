package fr.fito.vue.accueil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ecouteur du bouton lancer la simulation.
 * @author arthur
 */
public class EcouteurBoutonLancerSimulation implements ActionListener {
    private final FenetreAccueil fenetre;

    /**
     * Construit l'écouteur en lui passant une référence vers la fenêtre qui l'utilise.
     * @param fenetre La fenêtre parent.
     */
    public EcouteurBoutonLancerSimulation(FenetreAccueil fenetre) {
        this.fenetre = fenetre;
    }

    /**
     * Lors du clic sur le bouton, demande à la fenêtre de lancer la simulation.
     * @param e L'évènement qui a déclanchée l'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        fenetre.lancerLaSimulation();
    }
    
}
