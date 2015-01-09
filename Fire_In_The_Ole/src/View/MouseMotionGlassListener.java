/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class MouseMotionGlassListener extends MouseAdapter{
  private MyGlassPane myGlass;
   
  public MouseMotionGlassListener(MyGlassPane glass){
    myGlass = glass;
  }
   
  /**
  * Méthode fonctionnant sur le même principe que la classe précédente
  * mais cette fois sur l'action de déplacement
  */
  public void mouseDragged(MouseEvent event) {
    //Vous connaissez maintenant…
    Component c = event.getComponent();
    Point p = (Point) event.getPoint().clone();
    SwingUtilities.convertPointToScreen(p, c);
    SwingUtilities.convertPointFromScreen(p, myGlass);
    myGlass.setLocation(p);
    myGlass.repaint();
  }
}
