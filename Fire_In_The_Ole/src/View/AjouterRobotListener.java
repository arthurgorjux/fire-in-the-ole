/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Robot;
import Model.Simulation;
import Model.pathfinding.PathFinderToutDroit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author arthur
 */
class AjouterRobotListener implements ActionListener {

    CreateRobotDialog window;
    
    public AjouterRobotListener(CreateRobotDialog aThis) {
        this.window = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.window.getNameRobot().getText().isEmpty()){
            this.window.getNameRobot().setBorder(new LineBorder(Color.RED, 3));
            JOptionPane.showMessageDialog(window, "Il y a un ou plusieurs champ(s) vide(s) !", "Champ(s) vide(s)", JOptionPane.ERROR_MESSAGE);
        }else{
            // Add robot dans la liste + fermer fenetre
            int x = (Integer) this.window.getCoordX().getValue();
            int y = (Integer) this.window.getCoordY().getValue();
            if(this.coordAvailable(x, y) == true){
                Simulation simuTmp = new Simulation();
                this.window.robots.add(new Robot(x, y, (String) this.window.getTypeRobot().getSelectedItem(), this.window.getTypeRobot().getSelectedIndex(), this.window.getNameRobot().getText(), new PathFinderToutDroit(simuTmp), simuTmp));
                JOptionPane.showMessageDialog(window, "Le robot " + this.window.getNameRobot().getText() + "\na été ajouté avec succès\naux coordonnées : " + x + ", " + y, "Robot ajouté ", JOptionPane.INFORMATION_MESSAGE);
                this.window.dispose();
            }else{
                JOptionPane.showMessageDialog(window, "Coordonnées déjà occupées", "Les coordonnées choisies sont déjà occupées !", JOptionPane.ERROR_MESSAGE);
            }            
        }
    }
    
    private boolean coordAvailable(int x, int y){
        if(this.window.robots.size() > 0){
           for(Robot robot : this.window.robots){
                if(robot.getPosition().getX() == x && robot.getPosition().getY() == y){
                    return false;
                }
            } 
        }        
        return true;
    }
    
}
