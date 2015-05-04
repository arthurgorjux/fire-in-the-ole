package fr.fito.vue.accueil;

import fr.fito.modele.CarteDeTerrain;
import fr.fito.modele.SensVent;
import fr.fito.modele.Simulation;
import fr.fito.modele.TypeRobot;
import fr.fito.modele.parametrage.InitialisationIncendie;
import fr.fito.modele.parametrage.InitialisationRobot;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import fr.fito.modele.parametrage.JeuDeParametres;
import fr.fito.vue.regardersimulation.FenetreRegarderSimulation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Fenêtre de l'écran d'acceuil.
 * @author arthur
 */
public class FenetreAccueil extends JFrame{
    private final JLabel labelComboJeuxDeParametres = new JLabel("Jeu de paramètres : ");
    private JComboBox<String> comboJeuxDeParametres;
    private Map<String, JeuDeParametres> hashmapJeuxDeParametres = new HashMap<>();
    private final JButton boutonLancerSimulation = new JButton("Lancer");
    
    /**
     * Construit une fenêtre de l'écran principal.
     * @throws IOException 
     */
    public FenetreAccueil() throws IOException{
        super();
        this.setSize(new Dimension(450,200));
        this.setBounds(500, 400, 450, 200);
        this.setResizable(false);
        this.getContentPane().setLayout(new GridLayout(2, 1, 5, 5));
        JPanel top = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel bottom = new JPanel(new GridLayout(1, 1, 5, 5));
        top.add(labelComboJeuxDeParametres);
        this.majComboParametresSimulation();
        top.add(comboJeuxDeParametres);
        
        boutonLancerSimulation.addActionListener(new EcouteurBoutonLancerSimulation(this));
        bottom.add(boutonLancerSimulation);
        this.getContentPane().add(top);
        this.getContentPane().add(bottom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    /**
     * Retourne la combobox
     * @return l'objet JComboBox
     */
    public JComboBox<String> getComboBox(){
        return this.comboJeuxDeParametres;
    }
    
    /**
     * Lance une simulation dans une nouvelle fenêtre et ferme celle-ci.
     */
    public void lancerLaSimulation() {
        String nameParam = (String) this.comboJeuxDeParametres.getSelectedItem();
        JeuDeParametres params = this.hashmapJeuxDeParametres.get(nameParam);
        try {
            FenetreRegarderSimulation main = new FenetreRegarderSimulation(new Simulation(params), new CarteDeTerrain(params.getFichier_carte()));
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(EcouteurBoutonLancerSimulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Met à jour le contenu de la combobox des jeux de paramètres.
     */
    private void majComboParametresSimulation(){
        String[] listJeux = new String[10];
        //System.out.println(this.getClass().getResource("/IMG/test_1.bmp").getPath());
        JeuDeParametres jeu1 = new JeuDeParametres(this.getClass().getResource("/IMG/test_1.bmp").getPath(), SensVent.EST, this.getListeDesIncendies(), this.getListeDesRobots());
        listJeux[0] = "Jeu 1";
        listJeux[1] = "Personnaliser ...";
        hashmapJeuxDeParametres.put("Jeu 1", jeu1);
        comboJeuxDeParametres = new JComboBox<>(listJeux);
    }
    
    /**
     * Retourne la liste des incendies à placer dans la simulation.
     * @return La liste des incendies à placer dans la simulation.
     */
    private List<InitialisationIncendie> getListeDesIncendies(){
        List<InitialisationIncendie> feux = new ArrayList<>();
        //feux.add(new InitialisationIncendie(10, 10));
        feux.add(new InitialisationIncendie(1, 2));
        //feux.add(new InitialisationIncendie(3, 5));
        feux.add(new InitialisationIncendie(4, 4));
        feux.add(new InitialisationIncendie(10, 1));
        feux.add(new InitialisationIncendie(5, 6));
        return feux;
    }
    
    /**
     * Retourne la liste des robots à placer dans la simulation.
     * @return La liste des robots à placer dans la simulation.
     */
    private List<InitialisationRobot> getListeDesRobots(){
        List<InitialisationRobot> robots = new ArrayList<>();
        robots.add(new InitialisationRobot(0, 0, TypeRobot.CHENILLE));
        //robots.add(new InitialisationRobot(8, 3, TypeRobot.CHENILLE));
        robots.add(new InitialisationRobot(10, 0, TypeRobot.ROUE));
        //robots.add(new InitialisationRobot(7, 9, TypeRobot.CHENILLE));
        robots.add(new InitialisationRobot(0, 6, TypeRobot.PATTE));
        robots.add(new InitialisationRobot(6, 5, TypeRobot.JETPACK));
        return robots;
    }
}
