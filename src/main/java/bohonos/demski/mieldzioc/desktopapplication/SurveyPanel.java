/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 *
 * @author Andrzej
 */
public class SurveyPanel extends JPanel implements ActionListener {
    
    CreatorLogic creatorLogic;
    
    public SurveyPanel(CreatorLogic cl) {
        
        creatorLogic = cl;
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

    }
    
}
