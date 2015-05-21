/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication.addquestionframe;

import bohonos.demski.mieldzioc.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.desktopapplication.CreatorFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Delirus
 */
public class AddDataTimeQuestionFrame extends JFrame implements ActionListener{
    
    private ApplicationLogic applicationLogic;
    private CreatorFrame creatorFrame;
    //private JTextField titleField, descriptionField;
    //private JLabel titleLabel, descriptionLabel;
    
    public AddDataTimeQuestionFrame(ApplicationLogic appLogic, CreatorFrame crFrame) {
        super("Dodaj pytanie typu DataTime");
        
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
