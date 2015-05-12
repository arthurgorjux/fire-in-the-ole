package fr.fito.vue.regardersimulation;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author arthur
 */
public class PanelPilotageSimulation extends JPanel{
    
    private int etat;
    private final int INIT = 0;
    private final int START = 1;
    private final int PAUSE = 2;
    private final JButton start;
    private final JButton stop;
    private final JButton reset;
    private final FenetreRegarderSimulation window;
    
    public PanelPilotageSimulation(FenetreRegarderSimulation window) throws IOException{
        super();
        this.window = window;
        
        Image imgStart = ImageIO.read(getClass().getResource("/IMG/play.png"));
        start = new JButton(new ImageIcon(imgStart));
        start.addActionListener(new EcouteurBoutonDemarrerSimulation( this));
        this.add(start);
        
        Image imgStop = ImageIO.read(getClass().getResource("/IMG/pause.png"));
        stop = new JButton(new ImageIcon(imgStop));
        stop.addActionListener(new EcouteurBoutonMettreEnPauseLaSimulation(this));
        this.add(stop);
        
        Image imgReset = ImageIO.read(getClass().getResource("/IMG/reset.png"));
        reset = new JButton(new ImageIcon(imgReset));
        reset.addActionListener(new EcouteurBoutonRelancerSimulation(this));
        this.add(reset);
        
        stop.setEnabled(false);
        this.etat = INIT;
    }
    
    public void passerEnEtatSimulationEnCours() {
        etat = START;
        start.setEnabled(false);
        stop.setEnabled(true);
        reset.setEnabled(false);
    }
    
    public void passerEnEtatSimulationEnPause() {
        etat = PAUSE;
        stop.setEnabled(false);
        start.setEnabled(true);
    }
    
    public void mettreEnPauseLaSimulation() {
        passerEnEtatSimulationEnPause();
        window.mettreEnPauseLaSimulation();
    }
    
    /**
     * Relance le déroulement de la simulation.
     */
    public void relancerLaSimulation() {
        passerEnEtatSimulationEnCours();
        window.relancerLaSimulation();
    }

    public void demarrerSimulation() {
        passerEnEtatSimulationEnCours();
        window.demarrerSimulation();
    }
    
    public void reinitialiserLesBoutons() {
        stop.setEnabled(false);
        start.setEnabled(true);
        reset.setEnabled(false);
    }

    void resetSimulation() {
        this.reinitialiserLesBoutons();
        this.etat = INIT;
        window.resetSimulation();
    }
    
    public int getEtat() {
        return etat;
    }

    public void terminerSimulation() {
        stop.setEnabled(false);
        start.setEnabled(false);
        reset.setEnabled(true);
    }
    
}
