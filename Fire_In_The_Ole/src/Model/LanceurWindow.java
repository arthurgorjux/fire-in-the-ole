package Model;
import View.Main;
import java.io.IOException;

/**
 * Un lanceur de test pour simulation
 * @author S219
 *
 */
public class LanceurWindow {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub            
                Main mainWindow = new Main(new Simulation());
		/*Simulateur simulateur = new Simulateur();
		System.out.println("Génération de la simulation...");
                simulateur.jouerSimulation(new CarteDeTerrain(), new Main());
                System.out.println("Affichage de la simulation :");
                simulateur.rejouerSimulation();
                System.out.println("fin !");*/
	}

}
