package Model;

import static java.util.Collections.synchronizedList;
import java.util.LinkedList;
import java.util.List;

/**
 * L'archive des résultats d'une simulation. Composée d'une liste des archives
 * de chaque tour de la simulation. La liste interne est synchronisée.
 */
public class ArchiveSimulation {

    final List<ArchiveTourSimulation> tours;

    /**
     * Constructeur d'une archive vide.
     */
    public ArchiveSimulation() {
        tours = synchronizedList(new LinkedList<ArchiveTourSimulation>());
    }

    /**
     * Ajoute une archive de tour à l'archive de la simulation
     *
     * @param tour L'archive du tour de la simulation à ajouter.
     */
    public void addTour(ArchiveTourSimulation tour) {
        tours.add(tour);
    }

    /**
     * Affiche sous forme de texte dans la console tous les tours de la
     * simulation
     */
    public void afficher() {
        for (ArchiveTourSimulation tour : tours) {
            System.out.println(tour);
        }
    }

    /**
     * Retourne une liste synchronisée des archives des tour de la simulation.
     *
     * @return Une liste synchronisée des archives des tour de la simulation.
     */
    public List<ArchiveTourSimulation> getArchive() {
        return tours;
    }
}
