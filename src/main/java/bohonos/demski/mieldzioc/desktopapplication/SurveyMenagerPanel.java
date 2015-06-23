/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import bohonos.demski.mieldzioc.survey.Survey;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Andrzej
 */
public class SurveyMenagerPanel extends JPanel implements ActionListener {

    public static final int HEIGHT = 50;
    public static final int WIDTH = 780;
    
    private Survey survey;
    private SurveyMenagerFrame surveyMenagerFrame;
    
    public SurveyMenagerPanel (Survey survey, SurveyMenagerFrame surveyMenagerFrame) {
        
        this.survey = survey;
        this.surveyMenagerFrame = surveyMenagerFrame;
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    }
    
    public void actionPerformed(ActionEvent ae) {
        
    }
    
}
