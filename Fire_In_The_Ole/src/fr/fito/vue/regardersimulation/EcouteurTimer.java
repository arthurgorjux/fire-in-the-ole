package fr.fito.vue.regardersimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author arthur
 */
public class EcouteurTimer implements ActionListener{
    
    private final FenetreRegarderSimulation fenetre;
    
     /**
     * Construit l'écouteur en lui fournissant une référence vers la fenêtre qui le contient.
     * @param fenetre La fenêtre qui le contient.
     */
    public EcouteurTimer(FenetreRegarderSimulation fenetre){
        this.fenetre = fenetre;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        fenetre.majAffichageSimulation();
    }    
}
