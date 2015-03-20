package Model;

import static java.util.Collections.synchronizedList;
import java.util.LinkedList;
import java.util.List;

public class ArchiveSimulation {
    final List<ArchiveTourSimulation> tours;
	 
     public ArchiveSimulation() {
         tours = synchronizedList(new LinkedList<ArchiveTourSimulation>());
    }
	
    /**
     * Ajoute une archive de tour à l'archive de la simulation
     * @param tour 
     */
    public void addTour(ArchiveTourSimulation tour) {
	 tours.add(tour);
    }
    
    /**
     * Affiche sous forme de texte dans la console tous les tours de la simulation
     */
    public void afficher() {
        for (ArchiveTourSimulation tour : tours) {
             System.out.println(tour);
	}
    }
    
    public List<ArchiveTourSimulation> getArchive(){
        return tours;
    }
}
