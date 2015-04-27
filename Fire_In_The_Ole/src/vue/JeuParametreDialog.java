package vue;

import modele.SensVent;
import modele.TypeRobot;
import modele.stockage.InitialisationIncendie;
import modele.stockage.InitialisationRobot;
import modele.stockage.JeuDeParametres;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import modele.stockage.JeuDeParametres;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author arthur
 */
public class JeuParametreDialog extends JFrame{
    private JLabel labelParam = new JLabel("Jeu de paramètres : ");
    private JComboBox<String> listParam;
    public Map<String, JeuDeParametres> mapParam = new HashMap<>();
    private JButton lancer = new JButton("Lancer");
    
    public JeuParametreDialog() throws IOException{
        super();
        this.setSize(new Dimension(450,200));
        this.setBounds(500, 400, 450, 200);
        this.setResizable(false);
        this.getContentPane().setLayout(new GridLayout(2, 1, 5, 5));
        JPanel top = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel bottom = new JPanel(new GridLayout(1, 1, 5, 5));
        top.add(labelParam);
        this.majList();
        top.add(listParam);
        
        lancer.addActionListener(new LancerParamListener(this, listParam));
        bottom.add(lancer);
        this.getContentPane().add(top);
        this.getContentPane().add(bottom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void majList(){
        String[] listJeux = new String[1];
        System.out.println(this.getClass().getResource("/IMG/test_1.bmp").getPath());
        JeuDeParametres jeu1 = new JeuDeParametres(this.getClass().getResource("/IMG/test_1.bmp").getPath(), SensVent.EST, this.majFeux(), this.majRobots());
        listJeux[0] = "Jeu 1";
        mapParam.put("Jeu 1", jeu1);
        listParam = new JComboBox<>(listJeux);
    }
    
    private List<InitialisationIncendie> majFeux(){
        List<InitialisationIncendie> feux = new ArrayList<>();
        feux.add(new InitialisationIncendie(10, 10));
        feux.add(new InitialisationIncendie(1, 2));
        feux.add(new InitialisationIncendie(3, 5));
        feux.add(new InitialisationIncendie(4, 4));
        feux.add(new InitialisationIncendie(10, 1));
        return feux;
    }
    
    private List<InitialisationRobot> majRobots(){
        List<InitialisationRobot> robots = new ArrayList<>();
        robots.add(new InitialisationRobot(0, 0, TypeRobot.CHENILLE));
        robots.add(new InitialisationRobot(8, 3, TypeRobot.CHENILLE));
        robots.add(new InitialisationRobot(10, 0, TypeRobot.CHENILLE));
        robots.add(new InitialisationRobot(7, 9, TypeRobot.CHENILLE));
        robots.add(new InitialisationRobot(0, 6, TypeRobot.CHENILLE));
        return robots;
    }
}
