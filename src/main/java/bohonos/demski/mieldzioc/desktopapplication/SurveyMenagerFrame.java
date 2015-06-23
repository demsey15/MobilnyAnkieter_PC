/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import bohonos.demski.mieldzioc.survey.Survey;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Andrzej
 */
public class SurveyMenagerFrame extends JFrame implements ActionListener {

    private ApplicationLogic applicationLogic;
    private int surveyPanelPosition;
    
    private JPanel surveysPanel;
    private JScrollPane scrollPane;
    
    public SurveyMenagerFrame() {
        
        super("Zarz¹dzanie ankietami");
        applicationLogic = ApplicationLogic.getInstance();
        setSize(800,600);
        setLocation(120,110);
        setResizable(false);
        
        surveysPanel = new JPanel();
        surveysPanel.setLayout(null);
        scrollPane = new JScrollPane(surveysPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 50, 800, 500);
        this.add(scrollPane);
        
        refreshViewOfSurveys();
        
        setVisible(true);
    }
    
    public void refreshViewOfSurveys(){
        surveyPanelPosition = 0;
        surveysPanel.removeAll();     
        for(String idOfSurvey : applicationLogic.getSurveyHandler().getSetOfIds()){
            SurveyMenagerPanel surveyMenagerPanel = new SurveyMenagerPanel(applicationLogic.getSurveyHandler().getSurvey(idOfSurvey), this);
            surveyMenagerPanel.setBounds(0, surveyPanelPosition, SurveyMenagerPanel.WIDTH, SurveyMenagerPanel.HEIGHT);
            surveyPanelPosition = surveyPanelPosition + SurveyMenagerPanel.HEIGHT;
            surveysPanel.setPreferredSize(new Dimension(780,surveyPanelPosition));
            surveysPanel.add(surveyMenagerPanel);           
        }
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    public void actionPerformed(ActionEvent ae) {
        
    }
    
}
