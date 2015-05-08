package fr.fito.vue.regardersimulation;

import fr.fito.controleur.ThreadCalcul;
import fr.fito.modele.archivage.ArchiveTourSimulation;
import fr.fito.modele.CarteDeTerrain;
import fr.fito.modele.archivage.EtatEntite;
import fr.fito.modele.Robot;
import fr.fito.modele.Simulation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author S219
 */
public class FenetreRegarderSimulation extends JFrame{
    
    private final CarteDeTerrain map;
    public PanelAffichageCarte mapPanel;
    private final Simulation simulation;
    private javax.swing.JPanel infos;
    private PanelPilotageSimulation simulateur;
    private javax.swing.JPanel stats;
    private EtatEntite[] etatsEntite;
    private List<Robot> robots;
    private final JPanel layout = new JPanel();
    public JPanel layout_north;
    private final JPanel layout_south;
    private Timer timer;
    private int compteur;
    private boolean affichageTermine;
    
    public FenetreRegarderSimulation(Simulation simu, CarteDeTerrain map) throws IOException {
        compteur = 0;
        affichageTermine = false;
        this.layout_north = new JPanel();
        this.layout_south = new JPanel();
        this.simulation = simu;
        this.map = map;
        this.timer = null;
        this.initComponents();        
        this.setLocationRelativeTo(null);
        this.setSize(500, 500);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void setMap(ArchiveTourSimulation tour){
        this.etatsEntite = tour.getEtatsEntite();
        mapPanel.setEtatsEntites(tour.getEtatsEntite());
        mapPanel.repaint();
    }
    
    public EtatEntite[] getEtatsEntites(){
        return this.etatsEntite;
    }
    
    public CarteDeTerrain getMap(){
        return this.map;
    }
    
    public Simulation getSimulation(){
        return this.simulation;
    }

    private void initComponents() throws IOException {
        layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));
        layout_north.setLayout(new BoxLayout(layout_north, BoxLayout.X_AXIS));
        layout_south.setLayout(new GridLayout(1, 2));

        mapPanel = new PanelAffichageCarte(map, simulation);
        mapPanel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        mapPanel.setPreferredSize(new Dimension(300,300));  
        
        infos = new javax.swing.JPanel();
        infos.setPreferredSize(new Dimension(layout_south.getWidth(), layout_south.getHeight()));
        stats = new javax.swing.JPanel();
        stats.setPreferredSize(new Dimension(infos.getWidth()*(2/3), infos.getHeight()));
        simulateur = new PanelPilotageSimulation(this);
        simulateur.setPreferredSize(new Dimension(infos.getWidth()*(1/3), infos.getHeight()));

        
        simulateur.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        stats.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        layout_north.add(mapPanel);
        
        layout_south.add(simulateur);
        layout_south.add(stats);
        layout.add(layout_north);
        layout.add(layout_south);
        this.setPreferredSize(new Dimension(layout.getWidth(), layout.getHeight()));
        this.setContentPane(layout);
    }
    
    public List<Robot> getRobotsList(){
        return this.robots;
    }
    
    /**
     * Lance le déroulement de la simulation à l'écran.
     */
    public void demarrerSimulation() {
        Timer timerTemporaire;
        
        if(timer != null){
            System.out.println("Reprise de la simulation...");
            this.relancerLaSimulation();
        }else{
            /** on récupère le nombre de robot, le nombre d'incendies et la taille de la map
            * Si trop de paramètres, calcul avant et affichage après
            * Sinon, calcul et affichage en même temps
            */
            // Récupération de la taille des paramètres
            int nb_params = 0;
            int taille_carte = 0;
            nb_params += simulation.getRobots().size();
            nb_params += simulation.getIncendies().size();
            taille_carte += simulation.getCarte().getHauteur()*simulation.getCarte().getLargeur();
            
            ThreadCalcul calcul = new ThreadCalcul(simulation);
            timerTemporaire = new Timer(1000, new EcouteurTimer(this));
            
            if (nb_params > 50 || taille_carte > 2500) {
                //TODO modifier limite en fonction des tests de performances
                //simulation trop importante, on calcule tout avant de lancer l'affichage
                calcul.start();
                System.out.println("Début du calcul...");
                while(calcul.isAlive() || !simulation.estTerminee()){ try {
                    //check thread alive ou simu terminée ?
                    //on attend encore 1 tour de timer
                    Thread.sleep(timer.getDelay());
                    System.out.println("Calcul toujours en cours...");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EcouteurBoutonDemarrerSimulation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //simulation terminée, on lance le timer de l'affichage
                System.out.println("Calcul terminé. Lancement du timer pour l'affichage");
                timer.start();
            } else {
                //cas ou on réalise le calcul et l'affichage en même temps
                System.out.println("Début de la simulation (calcul et affichage en parrallèle)");
                timer = timerTemporaire;
                //on lance le thread calcul
                calcul.start();
                timer.start(); 
            }
        }  
    }

    /**
     * Reprends le déroulement de l'affichage de la simulation.
     */
    public void relancerLaSimulation() {
        timer.start();
    }

    /**
     * Interrompt le déroulement de l'affichage de la simulation.
     */
    public void mettreEnPauseLaSimulation() {
        System.out.println("Simulation en pause...");
        timer.stop();
    }

    /**
     * Réinitialise le pannel de boutons de pilotage de la simulation.
     */
    void reinitialiserBoutonsPilotage() {
        simulateur.reinitialiserLesBoutons();
    }

    /**
     * Met à jour l'image de la simulation.
     */
    void majAffichageSimulation() {
        if (compteur < simulation.getArchiveResultat().getArchive().size()) {
            // on regarde la taille de l'arraylist pr voir si on peut get l'objet
            // ex : si compteur = 2 et qu'on a calculé 3 tours on get tours[2] qui existe bien (car size = 3 donc 2<3)
            // si compteur = 2 et qu'on a calculé 2 tours, on a size = 2 et tours[2] n'existe pas (cpt = size dans ce cas)
            // si compteur = 2 et qu'on a calculé 1 tour, on a size = 1 et tours[2] n'existe pas (cpt > size)
            setMap(simulation.getArchiveResultat().getArchive().get(compteur));
            compteur++;
        }
        else if (!simulation.estTerminee()) {
            //si le calcul simu n'est pas terminée, c'est que le tour suivant n'est pas encore disponible. on sleep l'affichage
            int new_delay = (int)(timer.getDelay()*1.2);
            System.out.println("Tour pas encore dispo... Augmentation du timer de 20% : "+new_delay);
            timer.setDelay(new_delay);
        }
        else {
            //si le calcul est terminé et qu'on arrive pas a get : on a fini l'affichage
            System.out.println("Affichage terminé");
            affichageTermine = true;
        }
            
        if (affichageTermine) {
            timer.stop();
            reinitialiserBoutonsPilotage();
            System.out.println("Fin");
        }
    }

    void resetSimulation() {
        //reset du timer puis des paramètres de la simulation
        timer.setDelay(timer.getInitialDelay());
        timer.stop();
        simulation.resetSimulation();
    }
}
