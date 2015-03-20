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
        while (!simulation.estTerminee()) {
            simulation.mettreAJour();
            simulation.archiverTour();
        }
    }
    
}
