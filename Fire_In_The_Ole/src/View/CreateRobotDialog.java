/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Robot;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author arthur
 */
public class CreateRobotDialog extends JDialog{
    
    private JTextField robotName;
    private JComboBox<String> robotType;
    private JButton ajouterRobot;
    private JButton annuler;
    public List<Robot> robots;
    private JSpinner coordX;
    private JSpinner coordY;
    private ConfigDialog main;
    
    public CreateRobotDialog(List<Robot> robots, ConfigDialog main){
        super();
        this.robots = robots;
        this.main = main;
        this.setModal(true);
        this.init();
        this.setSize(new Dimension(300,150));
        this.setBounds(500, 400, 300, 150);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void init() {
        this.getContentPane().setLayout(new GridLayout(5, 2, 5, 5));
        String[] listData = {"Robot à pattes", "Robot à roues" ,"Robot à chenilles", "Robot à jet-pack"};
        this.robotName = new JTextField();
        this.robotName.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                robotName.setBorder(new LineBorder(Color.BLACK, 1));
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        this.robotType = new JComboBox<>(listData);
        SpinnerNumberModel spinnerModelX = new SpinnerNumberModel(0,0,30,1);
        SpinnerNumberModel spinnerModelY = new SpinnerNumberModel(0,0,30,1);
        this.coordX = new JSpinner(spinnerModelX);
        this.coordY = new JSpinner(spinnerModelY);
        this.ajouterRobot = new JButton("Ajouter");
        this.ajouterRobot.addActionListener(new AjouterRobotListener(this)); // TODO : passer surement la liste des robots pour ajouter le robot
        this.annuler = new JButton("Annuler");
        this.annuler.addActionListener(new AnnulerCreateRobotListener(this));
        this.add(new JLabel("Nom du robot : "));
        this.add(this.robotName);
        this.add(new JLabel("Type du robot : "));
        this.add(this.robotType);
        this.add(new JLabel("Abscisse : "));
        this.add(this.coordX);
        this.add(new JLabel("Ordonnée : "));
        this.add(this.coordY);
        this.add(this.ajouterRobot);
        this.add(this.annuler);
    }
    
    public JTextField getNameRobot(){
        return this.robotName;
    }
    
    public JComboBox<String> getTypeRobot(){
        return this.robotType;
    }
    
    public JSpinner getCoordX(){
        return this.coordX;
    }
    
    public JSpinner getCoordY(){
        return this.coordY;
    }
    
    public ConfigDialog getMainWindow(){
        return this.main;
    }
    
}
