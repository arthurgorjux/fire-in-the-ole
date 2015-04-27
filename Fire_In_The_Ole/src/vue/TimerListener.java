/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author arthur
 */
public class TimerListener implements ActionListener{
    
    private Main window;
    private SimulationPanel panel;
    private int compteur;
    private boolean affichageTermine;
    
    public TimerListener(Main window, SimulationPanel panel){
        this.window = window;
        this.panel = panel;
        compteur = 0;
        affichageTermine = false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Timer t = (Timer) e.getSource();
        
        if (compteur < window.getSimulation().getArchiveResultat().getArchive().size()) {
            // on regarde la taille de l'arraylist pr voir si on peut get l'objet
            // ex : si compteur = 2 et qu'on a calculé 3 tours on get tours[2] qui existe bien (car size = 3 donc 2<3)
            // si compteur = 2 et qu'on a calculé 2 tours, on a size = 2 et tours[2] n'existe pas (cpt = size dans ce cas)
            // si compteur = 2 et qu'on a calculé 1 tour, on a size = 1 et tours[2] n'existe pas (cpt > size)
            window.setMap(window.getSimulation().getArchiveResultat().getArchive().get(compteur));
            compteur++;
        }
        else if (!window.getSimulation().estTerminee()) {
            //si le calcul simu n'est pas terminée, c'est que le tour suivant n'est pas encore disponible. on sleep l'affichage
            int new_delay = (int)(t.getDelay()*1.2);
            System.out.println("Tour pas encore dispo... Augmentation du timer de 20% : "+new_delay);
            t.setDelay(new_delay);
        }
        else {
            //si le calcul est terminé et qu'on arrive pas a get : on a fini l'affichage
            System.out.println("Affichage terminé");
            affichageTermine = true;
        }
            
        if (affichageTermine) {
            //si affichage terminé on stop le timer et on re-set les boutons
            t.stop();
            panel.getStop().setEnabled(false);
            panel.getStart().setEnabled(true);
            panel.getReset().setEnabled(true);
            System.out.println("Fin");
        }
    }    
}
