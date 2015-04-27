/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Simulation;

/**
 * Thread permettant de calculer la simulation
 * @author Lucas
 */
public class ThreadCalcul extends Thread {
    Simulation simulation;
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
            System.out.println("Temps calcul d'un tour = "+(endTime-startTime)); //nécessaire pour les tests de performances
            simulation.archiverTour();
        }
        System.out.println("Nbr tour total : "+nb_tour);
    }
    
}
