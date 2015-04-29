package fr.fito.vue.regardersimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Nicolas
 */
public class EcouteurBoutonRelancerSimulation implements ActionListener{

    private FenetreRegarderSimulation window;
    private PanelPilotageSimulation panel;

    public EcouteurBoutonRelancerSimulation(FenetreRegarderSimulation window, PanelPilotageSimulation panel) {
        this.window = window;this.panel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // ré-initialisation timer
        this.panel.getTimer().stop();
        this.panel.setTimer(new Timer(1000, new TimerListener(window, panel)));
        
        
    }
    
}
