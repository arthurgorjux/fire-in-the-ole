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
    private Long timeFinal;
    private int nbFeuxPropages;
    private int nbTourTotal;
    private String chemin;
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
    public void persistance(int nbIncendiesPropages){
        final File rep = new File(System.getProperty("user.home") + "/resultatsSimulation");
        final String chemin = rep.getPath() + "/"  + this.name + ".txt";
        this.chemin = chemin;
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
            writer.write(this.getStatFinale(timeFinal, nbTour, nbIncendiesPropages));
            writer.close();
        }catch (Exception e){
            System.out.println("Impossible d'écrire les statistiques");
        }
    }
    
    public String getChemin(){
        return this.chemin;
    }
    
    @Override
    public String toString(){
       return this.getStatFinale(timeFinal, nbTourTotal, nbFeuxPropages) + "\n" + "Le fichier complet est disponible : " + this.chemin;
    }
    
    private String getStatFinale(Long timeFinal, int nbTour, int nbIncendiesPropages){
        this.timeFinal = timeFinal;
        this.nbTourTotal = nbTour;
        this.nbFeuxPropages = nbIncendiesPropages;
        String result = "";
        result += "========== STATISTIQUES FINALES ========\n";
        result += (nbIncendiesPropages != 0) ? "Nombre d'incendies qui se sont propagés : " + nbIncendiesPropages : "Aucun incendie ne s'est propagé";
        result += "\nTemps total de la simulation : " + timeFinal + " ms\n";
        result += "Nombre de tours total : " + nbTour + "\n";
        result += "Temps moyen par tour : " + timeFinal/nbTour + " ms\n";
        result += "\n\t\t\t\t\t\t\t\t\t\tGénéré automatiquement par FITO (Atchy-Dalama, Fantinel, Fenet-Garde, Gorjux, Yong)";
        return result;
    }
}
