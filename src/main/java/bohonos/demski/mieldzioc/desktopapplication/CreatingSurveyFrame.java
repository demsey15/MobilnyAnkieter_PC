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
 * @author Andrzej
 */
public class CreatingSurveyFrame extends JFrame implements ActionListener {
    
    private CreatorLogic creatorLogic;
    private String idOfSurvey;
    
    public CreatingSurveyFrame(CreatorLogic cl, String id) {
        
        super("nowa ankieta");
        
        creatorLogic = cl;
        idOfSurvey = id;
        
        setSize(200, 150);
        setLocation(400,300);
        setResizable(false);
        
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent ae) {
        
    }
    
}