package fr.fito.modele;

/**
 * Interface utilisée pour l'implémentation du patron observateur.
 * @author gael
 */
public interface Observateur {
    /**
     * Notifie l'observateur d'un évènement important.
     */
    public void prevenir();
}
