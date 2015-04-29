package fr.fito.vue.regardersimulation;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author arthur
 */
public class PanelPilotageSimulation extends JPanel{
    
    private int etat;
    private final int START = 1;
    private final int STOP = 0;
    private final JButton start;
    private final JButton stop;
    private final JButton reset;
    private final FenetreRegarderSimulation window;
    private Timer timer;
    
    public PanelPilotageSimulation(FenetreRegarderSimulation window) throws IOException{
        super();
        this.etat = STOP;
        this.timer = null;
        this.window = window;
        Image imgStart = ImageIO.read(getClass().getResource("/IMG/play.png"));
        start = new JButton(new ImageIcon(imgStart));
        Image imgStop = ImageIO.read(getClass().getResource("/IMG/pause.png"));
        stop = new JButton(new ImageIcon(imgStop));
        Image imgReset = ImageIO.read(getClass().getResource("/IMG/reset.png"));
        reset = new JButton(new ImageIcon(imgReset));
        stop.setEnabled(false);
        reset.setEnabled(false);
        start.addActionListener(new EcouteurBoutonDemarrerSimulation(this.window, this));
        this.add(start);
        stop.addActionListener(new EcouteurBoutonGestionPauseSimulation(this.window, this));
        this.add(stop);
        reset.addActionListener(new EcouteurBoutonRelancerSimulation(this.window,this));
        this.add(reset);
    }
    
    public void setEtat(int etat){
        this.etat = etat;
    }
    
    public Timer getTimer(){
        return this.timer;
    }
    
    public JButton getStart(){
        return this.start;
    }
    
    public JButton getStop(){
        return this.stop;
    }
    
    public JButton getReset(){
        return this.reset;
    }

    void setTimer(Timer timer) {
        this.timer = timer;
    }
}
