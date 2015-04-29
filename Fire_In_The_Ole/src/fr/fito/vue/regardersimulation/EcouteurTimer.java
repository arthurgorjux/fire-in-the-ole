package fr.fito.vue.regardersimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author arthur
 */
public class EcouteurTimer implements ActionListener{
    
    private final FenetreRegarderSimulation window;
    
    public EcouteurTimer(FenetreRegarderSimulation window){
        this.window = window;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        window.majAffichageSimulation();
    }    
}
