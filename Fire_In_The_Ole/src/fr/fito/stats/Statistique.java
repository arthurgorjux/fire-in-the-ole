/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.stats;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nicolas
 */
public class Statistique {
    
    private String name;
    private List<StatistiqueTour> archive;
    public Statistique(){
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd=HH_mm_ss");
        this.name = "Statistiques_FITO_" + format.format(new Date());
        this.archive = new ArrayList<>();
    }
    
    public void addArchive(Integer numTour, StatistiqueTour tour){
        tour.setNum(numTour);
        this.archive.add(tour);
    }
    
    /**
     * Sauvegarde les statistiques dans un fichier texte
     */
    public void persistance(){
        final File rep = new File(System.getProperty("user.home") + "/resultatsSimulation");
        final String chemin = rep.getPath() + "/"  + this.name + ".txt";
        rep.mkdir();
        final File fichier = new File(chemin);
        try{
            final FileWriter writer = new FileWriter(fichier, true);
            Long timeFinal = new Long(0);
            int nbTour = 0;
            for(StatistiqueTour tour : this.archive){
                writer.write(tour.toString());
                timeFinal += tour.getTime();
                nbTour++;
            }
            writer.write(this.getStatFinale(timeFinal, nbTour));
            writer.close();
        }catch (Exception e){
            System.out.println("Impossible d'écrire les statistiques");
        }
    }
    
    private String getStatFinale(Long timeFinal, int NbTour){
        String result = "";
        result += "========== STATISTIQUES FINALES ========\n";
        result += "Temps total de la simulation : " + timeFinal + " ms\n";
        result += "Nombre de tours total : " + NbTour + "\n";
        result += "Temps moyen de la simulaiton : " + timeFinal/NbTour + " ms";
        return result;
    }
}
