package fr.fito.vue.accueil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ecouteur du bouton lancer la simulation.
 * @author arthur
 */
class EcouteurBoutonLancerSimulation implements ActionListener {
    public FenetreAccueil fenetre;

    public EcouteurBoutonLancerSimulation(FenetreAccueil fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fenetre.lancerLaSimulation();
    }
    
}
