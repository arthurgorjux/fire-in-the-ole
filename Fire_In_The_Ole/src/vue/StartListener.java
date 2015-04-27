/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.ThreadCalcul;
import modele.Simulation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author arthur
 */
class StartListener implements ActionListener {

    private Main window;
    private SimulationPanel panel;
    private Simulation simulation;
    public StartListener(Main window, SimulationPanel panel) {
        this.window = window;
        this.simulation = window.getSimulation();
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setState();

        Timer timer;
        if(this.panel.getTimer()!= null){
            System.out.println("Reprise de la simulation...");
            timer = this.panel.getTimer();
        }else{
            /** on récupère le nombre de robot, le nombre d'incendies et la taille de la map
            * Si trop de paramètres, calcul avant et affichage après
            * Sinon, calcul et affichage en même temps
            */
            
            // Récupération de la taille des paramètres
            int nb_params = 0;
            int taille_carte = 0;
            nb_params += simulation.getRobots().size();
            nb_params += simulation.getIncendies().size();
            taille_carte += simulation.getCarte().getHauteur()*simulation.getCarte().getLargeur();
            
            ThreadCalcul calcul = new ThreadCalcul(window.getSimulation());
            timer = new Timer(1000, new TimerListener(window, panel));
            
            if (nb_params > 50 || taille_carte > 2500) {
                //TODO modifier limite en fonction des tests de performances
                //simulation trop importante, on calcule tout avant de lancer l'affichage
                calcul.start();
                System.out.println("Début du calcul...");
                while(calcul.isAlive() || !simulation.estTerminee()){ try {
                    //check thread alive ou simu terminée ?
                    //on attend encore...
                    Thread.sleep(1000);
                    System.out.println("Calcul toujours en cours...");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StartListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //simulation terminée, on lance le timer de l'affichage
                System.out.println("Calcul terminé. Lancement du timer pour l'affichage");
                timer.start();
            }
            else {
                //cas ou on réalise le calcul et l'affichage en même temps
                System.out.println("Début de la simulation (calcul et affichage en parrallèle)");
                this.panel.setTimer(timer);
                //on lance le thread calcul
                calcul.start();
                timer.start(); 
            }
        }        
    }
    
    /**
     * Permet de désactiver le bouton start et activé le bouton stop
     */
    public void setState(){
        this.panel.setEtat(1);
        this.panel.getStart().setEnabled(false);
        this.panel.getStop().setEnabled(true);
    }
    
}
