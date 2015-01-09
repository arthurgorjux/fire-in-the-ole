package fr.gfg.fireintheole;

import java.util.LinkedList;
import java.util.List;

public class ArchiveSimulation {
	 List<ArchiveTourSimulation> tours;
	 
	 public ArchiveSimulation() {
		 tours = new LinkedList<ArchiveTourSimulation>();
	 }
	 
	 public void addTour(ArchiveTourSimulation tour) {
		 tours.add(tour);
	 }
	 
	 public void afficher() {
		 for (ArchiveTourSimulation tour : tours) {
			 System.out.println(tour);
		 }
	 }
}
