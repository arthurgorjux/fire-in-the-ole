/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics;
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
public class SimulationPanel extends JPanel{
    
    private int etat;
    private int START = 1;
    private int STOP = 0;
    private JButton start;
    private JButton stop;
    private JButton reset;
    private Main window;
    private Timer timer;
    
    public SimulationPanel(Main window) throws IOException{
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
        start.addActionListener(new StartListener(this.window, this));
        this.add(start);
        stop.addActionListener(new StopListener(this.window, this));
        this.add(stop);
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
