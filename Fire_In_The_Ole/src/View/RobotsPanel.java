/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.EtatEntite;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author arthur
 */
public class RobotsPanel extends JPanel{
    
    private JButton creerRobot;
    private Main window;
    private JList robots = null;
    private boolean alreadyExist = false;
    
    public RobotsPanel(Main window) throws IOException{
        super();
        this.window = window;
        Image imgRobot = ImageIO.read(getClass().getResource("/IMG/plus.png"));
        creerRobot = new JButton(new ImageIcon(imgRobot));
        
        this.add(creerRobot);
    }
    
    public void setList(EtatEntite[] entites){
        ArrayList<EtatEntite> result = new ArrayList<>();
        for(EtatEntite entite : entites){
            if(entite.getType() == "typeRobot"){
                result.add(entite);
            }
        }
        
        if(!this.alreadyExist){        
            robots = new JList(result.toArray());
            this.add(robots);
        }else{
            robots.setListData(result.toArray());
        }
        this.alreadyExist = true;
    }
    
    public boolean alreadyExist(){
        return this.alreadyExist;
    }

    public JList getList() {
        return this.robots;
    }
}
