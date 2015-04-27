package fr.fito.controleur;

import fr.fito.modele.archivage.ArchiveTourSimulation;
import fr.fito.modele.Simulation;
import fr.fito.vue.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread permettant d'afficher la simulation
 * @author Lucas
 */
public class ThreadAffichage extends Thread {
    Simulation simulation;
    int delay;
    private boolean affichageTermine;
    private int compteur;
    Main window;
    
    public ThreadAffichage(Simulation simulation, int delay, Main window) {
        this.simulation = simulation;
        this.delay = delay;
        this.window = window;
        affichageTermine = false;
        compteur = 0;
    }

    
    @Override
    public void run () {
        
        //en fonction du timer on affiche les tours d'une simulation...
        while (!affichageTermine) {
            
            if (compteur < simulation.getArchiveResultat().getArchive().size()) {
                // on regarde la taille de l'arraylist pr voir si on peut get l'objet
                // ex : si compteur = 2 et qu'on a calculÃ© 3 tours on get tours[2] qui existe bien (car size = 3 donc 2<3)
                // si compteur = 2 et qu'on a calculÃ© 2 tours, on a size = 2 et tours[2] n'existe pas (cpt = size dans ce cas)
                // si compteur = 2 et qu'on a calculÃ© 1 tour, on a size = 1 et tours[2] n'existe pas (cpt > size)
                ArchiveTourSimulation tour = simulation.getArchiveResultat().getArchive().get(compteur);
                window.setMap(tour);
                compteur++;
                try {
                    this.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadAffichage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (!simulation.estTerminee()) {
                //si le calcul simu n'est pas terminÃ©e, c'est que le tour suivant n'est pas encore disponible. on sleep l'affichage
                System.out.println("Tour pas encore dispo... on sleep");
                try {
                    this.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadAffichage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                //si le calcul est terminÃ© et qu'on arrive pas a get : on a fini l'affichage
                System.out.println("Affichage terminÃ©");
                affichageTermine = true;
            }
        }
    }
    
    public boolean estTermine(){
        return affichageTermine;
    }
    
}
