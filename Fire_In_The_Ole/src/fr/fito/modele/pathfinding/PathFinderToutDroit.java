package fr.fito.modele.pathfinding;

import fr.fito.modele.CarteDeTerrain;
import fr.fito.modele.Robot;
import fr.fito.modele.Simulation;
import java.util.*;

/**
 *
 * @author S219
 */
public class PathFinderToutDroit implements PathFinder{
    private Simulation simu;
    
    public PathFinderToutDroit(Simulation simulation) {
        this.simu = simulation;
    }
    
    @Override
    public Chemin getCheminLePlusCourt(Position robot, Position feu) {
        CarteDeTerrain carte = new CarteDeTerrain();
        List<Position> chemin = new ArrayList<>();
        chemin.add(robot);
        int robot_temp_x = robot.getX();
        int robot_temp_y = robot.getY();
        int dest_x = feu.getX();
        int dest_y = feu.getY();
        // TODO
        /*
        List robots = simu.getRobots();
        List incendies = simu.getIncendies();
        */
                
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
        return new Chemin(chemin);        
    }
}
