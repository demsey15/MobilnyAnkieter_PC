/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import bohonos.demski.mieldzioc.desktopapplication.questionpanel.DateTimeQuestionPanel;
import bohonos.demski.mieldzioc.desktopapplication.questionpanel.QuestionPanel;
import bohonos.demski.mieldzioc.questions.DateTimeQuestion;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author Andrzej
 */
public class SurveyPanel extends JPanel implements ActionListener {
    
    private ApplicationLogic applicationLogic;
    private String idOfSurvey;
    private JButton saveButton;
    private JLabel titleLabel, descriptionLabel, title, description;
    private JPanel questionsPanel;
    private JScrollPane scrollPane;
    private Container container;
    private JTextPane exempleTextPane; //tests only
    
    private CreatingSurveyFrame creatingSurveyFrame;
    
    public SurveyPanel(String id) {
        
        applicationLogic = ApplicationLogic.getInstance();
        idOfSurvey = id;
        //creatingSurveyFrame = new CreatingSurveyFrame(applicationLogic, id);
        //saveButton = creatingSurveyFrame.addActionListenerSave(this);
        this.setLayout(null);
        
        titleLabel = new JLabel("tytu³ :   " + applicationLogic.getSurveyTitle(idOfSurvey));
        titleLabel.setBounds(20, 20, 300, 30);
        this.add(titleLabel);
        
        descriptionLabel = new JLabel("opis :   "+ applicationLogic.getSurveyDescription(idOfSurvey));
        descriptionLabel.setBounds(20, 70, 300, 30);
        this.add(descriptionLabel);
        
        questionsPanel = new JPanel();
        questionsPanel.setLayout(new FlowLayout());
        scrollPane = new JScrollPane(questionsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(20, 120, 660, 350);
        
        this.add(scrollPane);
        
        
    }
    
    public void addDateTimeQuestion(DateTimeQuestion dateTimeQuestion) {
        questionsPanel.add(new DateTimeQuestionPanel(dateTimeQuestion));
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == saveButton) {
            //String tit;
            //do
            //        {
            //            tit = creatorLogic.getSurveyTitle(idOfSurvey);
            //        }
            //while (tit==null);
            //title = new JLabel(creatorLogic.getSurveyTitle(idOfSurvey));
            //title.setBounds(80, 20, 200, 30);
            //this.add(title);
            //description = new JLabel(creatorLogic.getSurveyDescription(idOfSurvey));
            //description.setBounds(80, 70, 200, 30);
            //this.add(description);
        }

    }
    
}
