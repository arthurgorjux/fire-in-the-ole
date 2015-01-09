package fr.gfg.fireintheole;

import java.util.List;

/**
 * En th�orie immutable dans la mesure ou EtatEntite est �galement immutable
 * @author gael
 *
 */
public final class ArchiveTourSimulation {
	private final EtatEntite[] etatsEntite;
	
	public ArchiveTourSimulation(List<EtatEntite> etatsEntite) {
		this.etatsEntite = etatsEntite.toArray(new EtatEntite[etatsEntite.size()]);
	}
	
	public EtatEntite[] getEtatsEntite() {
		return etatsEntite.clone();
	}
	
	@Override
	public String toString() {
		String chaineTour = "";
		for (EtatEntite etatEntite : etatsEntite) {
			chaineTour += etatEntite;
		}
		return chaineTour;
	}
}
