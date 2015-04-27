package Model;
import Controller.*;
import Model.stockage.InitialisationIncendie;
import Model.stockage.InitialisationRobot;
import Model.stockage.JeuDeParametres;
import java.util.LinkedList;
import java.util.List;

/**
 * Un lanceur de test pour simulation
 * @author S219
 *
 */
public class LanceurVersionConsolePourTests {
    private FauxSimulateurPourTestConsole simulateur;
    
    public LanceurVersionConsolePourTests() {
        simulateur = new FauxSimulateurPourTestConsole();
    }
        
    /**
     * Génère un jeu de paramètres simple pour tester l'execution d'une simu
     * @return un jeu de paramètres simple pour tester l'execution d'une simu
     */
    private JeuDeParametres genererJeuParametresTest() {
        List<InitialisationRobot> robots;
	List<InitialisationIncendie> incendies;
        // Robots
        robots = new LinkedList<>();
        robots.add(new InitialisationRobot(1, 1,"typerobotbidon"));
        robots.add(new InitialisationRobot(2, 2,"typerobotbidon"));
        robots.add(new InitialisationRobot(4, 5,"typerobotbidon"));
        robots.add(new InitialisationRobot(7, 5,"typerobotbidon"));
        // Incendies
        incendies = new LinkedList<>();
        incendies.add(new InitialisationIncendie(2,3));
        incendies.add(new InitialisationIncendie(0,0));
        incendies.add(new InitialisationIncendie(2,5));
        
        return new JeuDeParametres("", SensVent.EST, incendies, robots);
    }
    
    /**
     * Execute le lanceur et donc affiche une simulation
     */
    public void executer() {
	System.out.println("Génération de la simulation...");
        simulateur.jouerSimulation(new CarteDeTerrain(), genererJeuParametresTest());
        System.out.println("Affichage de la simulation :");
        simulateur.rejouerSimulation();
        System.out.println("fin !");
    }
    
    /**
     * Point d'entrée
     * @param args 
     */
    public static void main(String[] args) {
        LanceurVersionConsolePourTests lanceur = new LanceurVersionConsolePourTests();
    }

}
