package Model;

import java.util.List;

/**
 * L'archive du résultat d'un tour de simulation. Composée d'une liste des états
 * des entitées à la fin d'un tour d'une simulation. Immutable
 *
 * @author gael
 *
 */
public final class ArchiveTourSimulation {

    private final EtatEntite[] etatsEntite;

    /**
     * Constructeur utilisant en paramètre une liste d'EtatEntite.
     *
     * @param etatsEntite Une liste d'EtatEntite.
     */
    public ArchiveTourSimulation(List<EtatEntite> etatsEntite) {
        this.etatsEntite = etatsEntite.toArray(new EtatEntite[etatsEntite.size()]);
    }

    /**
     * Retourne une copie de la liste des EtatEntite.
     *
     * @return Une copie de la liste des EtatEntite.
     */
    public EtatEntite[] getEtatsEntite() {
        return etatsEntite.clone();
    }

    @Override
    public String toString() {
        String chaineTour = "==========================================| Nouveau Tour |===========================================================\n";
        for (EtatEntite etatEntite : etatsEntite) {
            chaineTour += etatEntite + "\n";
        }
        return chaineTour;
    }
}
