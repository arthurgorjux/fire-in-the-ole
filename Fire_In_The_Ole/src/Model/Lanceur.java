package Model;
import Controller.*;
import View.Main;

/**
 * Un lanceur de test pour simulation
 * @author S219
 *
 */
public class Lanceur {

	public static void main(String[] args) {          
		Simulateur simulateur = new Simulateur();
		System.out.println("Génération de la simulation...");
                simulateur.jouerSimulation(new CarteDeTerrain());
                System.out.println("Affichage de la simulation :");
                simulateur.rejouerSimulation();
                System.out.println("fin !");
	}

}
