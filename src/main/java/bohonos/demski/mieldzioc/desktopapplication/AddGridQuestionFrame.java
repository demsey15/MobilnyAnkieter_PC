/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Delirus
 */
public class AddGridQuestionFrame extends JFrame implements ActionListener {
     private ApplicationLogic applicationLogic;
    private CreatorFrame creatorFrame;
    
    public AddGridQuestionFrame(ApplicationLogic appLogic, CreatorFrame crFrame) {
        super("Dodaj pytanie typu Grid");
        
        applicationLogic = appLogic;
        creatorFrame = crFrame;
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
