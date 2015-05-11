package fr.fito.controleur;

import fr.fito.modele.Robot;
import fr.fito.modele.Simulation;
import fr.fito.modele.archivage.ArchiveTourSimulation;
import fr.fito.stats.StatistiqueTour;
import java.util.List;

/**
 * Thread permettant de calculer la simulation
 * @author Lucas
 */
public class ThreadCalcul extends Thread {
    private final Simulation simulation;
    
    /**
     * Créer le threade en lui passant la simulation à manipuler/
     * @param simulation La simulation à manipuler.
     */
    public ThreadCalcul(Simulation simulation) {
        this.simulation = simulation;
    }
    
    @Override
    public void run (){
        //Tant que la simulation n'est pas terminée on calcule les nouveaux tours...
        long startTime;
        long endTime;
        int nb_tour = 0;
        while (!simulation.estTerminee()) {
            nb_tour +=1;
            startTime = System.currentTimeMillis();
            simulation.mettreAJour();
            endTime = System.currentTimeMillis();
//            System.out.println("Temps calcul d'un tour = "+(endTime-startTime)+" ms"); //nécessaire pour les tests de performances
            ArchiveTourSimulation tour = simulation.archiverTour();
            this.simulation.getStat().addArchive(nb_tour, new StatistiqueTour(tour, endTime-startTime));
        }
        System.out.println("Nbr tour total : "+nb_tour);
    }
    
}
