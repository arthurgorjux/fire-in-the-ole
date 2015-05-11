/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.stats;
import fr.fito.modele.archivage.ArchiveTourSimulation;
import fr.fito.modele.archivage.EtatEntite;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public class StatistiqueTour {
    ArchiveTourSimulation tour;
    private Long time;
    private int numTour;

    public int getNumTour() {
        return numTour;
    }
    
    public StatistiqueTour(ArchiveTourSimulation tour, Long time){
        this.tour = tour;
        this.time = time;
    }
    
    public void setNum(int numTour){
        this.numTour = numTour;
    }
    
    @Override
    public String toString(){
        String result = "";
        result += this.getInfosTour();
        result += this.getStatutRobots();
        result += this.getAvancement();
        result += "\n\n\n";
       return result; 
    }
    
    private String getInfosTour(){
        String result = "";
        result += "========== INFOS TOUR #" + this.numTour + "==========\n";
        int nbRobots = 0, nbIncendies = 0;
        for(EtatEntite entite : this.tour.getEtatsEntite()){
            if(entite.getType() != "incendie"){
                nbRobots++;
            }else{
                nbIncendies++;
            }
        }
        result += "Nombre de robots : " + nbRobots + "\n";
        result += "Nombre d'incendies : " + nbIncendies;
        result += "\n";
        return result;
    }
    
    private String getStatutRobots(){
        String result = "";
        result += "========== STATUT DES ROBOTS ==========\n";
        for(EtatEntite entite : this.tour.getEtatsEntite()){
            result += (entite.getType()!= "incendie") ? entite.getNom() +  " est en " + entite.getEtat().toString() + "\n" : "";             
        }
        return result;
    }
    
    private String getAvancement(){
        String result = "";
        result += "========== DURÉE ==========\n";
        result += "Durée du tour : " + this.time + " ms";
        return result;
    }
    
    public Long getTime(){
        return this.time;
    }
}
