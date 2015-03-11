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
    
    public LanceurVersionConsolePourTests() {
        List<InitialisationRobot> robots;
	List<InitialisationIncendie> incendies;
        
        
        
        robots = new LinkedList<>();
        incendies = new LinkedList<>();

        robots.add(new InitialisationRobot(1, 1,"typerobotbidon"));
        robots.add(new InitialisationRobot(2, 2,"typerobotbidon"));
        robots.add(new InitialisationRobot(4, 5,"typerobotbidon"));
        robots.add(new InitialisationRobot(7, 5,"typerobotbidon"));
        
        incendies.add(new InitialisationIncendie(2,3));
        incendies.add(new InitialisationIncendie(0,0));
        incendies.add(new InitialisationIncendie(2,5));
        
        JeuDeParametres parametres = new JeuDeParametres("", SensVent.EST, incendies, robots);
        
	FauxSimulateurPourTestConsole simulateur = new FauxSimulateurPourTestConsole();
	System.out.println("Génération de la simulation...");
        simulateur.jouerSimulation(new CarteDeTerrain(), parametres);
        System.out.println("Affichage de la simulation :");
        simulateur.rejouerSimulation();
        System.out.println("fin !");
    }
        
    
    public static void main(String[] args) {
        LanceurVersionConsolePourTests lanceur = new LanceurVersionConsolePourTests();
    }

}
