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
public class AddTextQuestionFrame extends JFrame implements ActionListener {
    private ApplicationLogic applicationLogic;
    private CreatorFrame creatorFrame;
    
    public AddTextQuestionFrame(ApplicationLogic appLogic, CreatorFrame crFrame) {
        super("Dodaj pytanie typu Text");
        
        applicationLogic = appLogic;
        creatorFrame = crFrame;
        
        setSize(300, 300);
        setLocation(400,300);
        setResizable(false);
        this.setLayout(null);
        
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
