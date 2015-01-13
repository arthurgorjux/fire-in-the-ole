/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.pathfinding;

import Model.CarteDeTerrain;
import Model.Robot;
import Model.Simulation;
import java.util.*;

/**
 *
 * @author S219
 */
public class PathFinderToutDroit {
    public ArrayList getCheminLePlusCourt(Position robot, Position feu, Simulation simu) {
        CarteDeTerrain carte = new CarteDeTerrain();
        ArrayList chemin = new ArrayList();
        chemin.add(robot);
        int robot_temp_x = robot.getX();
        int robot_temp_y = robot.getY();
        int dest_x = feu.getX();
        int dest_y = feu.getY();
        List robots = simu.getRobots();
        List incendies = simu.getIncendies();
        
        while (robot_temp_x != dest_x || robot_temp_y != dest_y){
            if (robot_temp_x < dest_x){
//                for (Iterator it = robots.iterator(); it.hasNext();) {
//                    Robot robot_carte = (Robot) it.next();
//                    if (robot_carte.getPosition().getX() == robot_temp_x + 1){
//                        if (robot_temp_y < dest_y){
//                            robot_temp_y += 1;
//                            chemin.add(new Position(robot_temp_x, robot_temp_y));
//                        }
//                        else if (robot_temp_y > dest_y){
//                            robot_temp_y -= 1;
//                            chemin.add(new Position(robot_temp_x, robot_temp_y));
//                        } 
//                    }            
//                }
                robot_temp_x += 1;
                chemin.add(new Position(robot_temp_x, robot_temp_y));                
            }
            else if (robot_temp_x > dest_x){
                robot_temp_x -= 1;
                chemin.add(new Position(robot_temp_x, robot_temp_y));
            }
            else if (robot_temp_y < dest_y){
                robot_temp_y += 1;
                chemin.add(new Position(robot_temp_x, robot_temp_y));
            }
            else if (robot_temp_y > dest_y){
                robot_temp_y -= 1;
                chemin.add(new Position(robot_temp_x, robot_temp_y));
            }    
        }
        return chemin;        
    }
    
    public static void main(String args[]) {
        PathFinderToutDroit toutdroit = new PathFinderToutDroit();
        Position robot = new Position(2, 0);
        Position feu = new Position(2, 3);
        Simulation simu = new Simulation();
        ArrayList resultat = toutdroit.getCheminLePlusCourt(robot, feu, simu);
        for (Iterator it = resultat.iterator(); it.hasNext();) {
            Position pos = (Position) it.next();
            System.out.println (pos.getX() + " " + pos.getY());
        }
            
        //System.out.println(rez);
    }
}
