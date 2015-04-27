package modele;

import controleur.FauxSimulateurPourTestConsole;
import modele.stockage.InitialisationIncendie;
import modele.stockage.InitialisationRobot;
import modele.stockage.JeuDeParametres;
import java.util.LinkedList;
import java.util.List;

/**
 * Un lanceurs de test en mode console pour une simulation.
 */
public class LanceurVersionConsolePourTests {
    private FauxSimulateurPourTestConsole simulateur;

    /**
     * Constructeur.
     */
    public LanceurVersionConsolePourTests() {
        simulateur = new FauxSimulateurPourTestConsole();
    }

    /**
     * Génère un jeu de paramètres simple pour tester l'execution d'une simulation.
     * @return un jeu de paramètres simple pour tester l'execution d'une simulation.
     */
    private JeuDeParametres genererJeuParametresTest() {
        List<InitialisationRobot> robots;
        List<InitialisationIncendie> incendies;
        // Robots
        robots = new LinkedList<>();
        robots.add(new InitialisationRobot(1, 1, TypeRobot.PATTE));
        robots.add(new InitialisationRobot(2, 2, TypeRobot.PATTE));
        robots.add(new InitialisationRobot(4, 5, TypeRobot.PATTE));
        robots.add(new InitialisationRobot(7, 5, TypeRobot.PATTE));
        // Incendies
        incendies = new LinkedList<>();
        incendies.add(new InitialisationIncendie(2, 3));
        incendies.add(new InitialisationIncendie(0, 0));
        incendies.add(new InitialisationIncendie(2, 5));

        return new JeuDeParametres("", SensVent.EST, incendies, robots);
    }

    /**
     * Execute le lanceur et donc affiche une simulation dans la console.
     */
    public void executer() {
        System.out.println("Génération de la simulation...");
        simulateur.jouerSimulation(genererJeuParametresTest());
        System.out.println("Affichage de la simulation :");
        simulateur.rejouerSimulation();
        System.out.println("fin !");
    }

    /**
     * Point d'entrée possible de l'application.
     * @param args
     */
    public static void main(String[] args) {
        LanceurVersionConsolePourTests lanceur;
        lanceur = new LanceurVersionConsolePourTests();
        lanceur.executer();
    }

}
