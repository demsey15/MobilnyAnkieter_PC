/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import bohonos.demski.mieldzioc.desktopapplication.questionpanel.DateTimeQuestionPanel;
import bohonos.demski.mieldzioc.desktopapplication.questionpanel.GridQuestionPanel;
import bohonos.demski.mieldzioc.desktopapplication.questionpanel.MultipleChoiceQuestionPanel;
import bohonos.demski.mieldzioc.desktopapplication.questionpanel.OneChoiceQuestionPanel;
import bohonos.demski.mieldzioc.desktopapplication.questionpanel.QuestionPanel;
import bohonos.demski.mieldzioc.desktopapplication.questionpanel.ScaleQuestionPanel;
import bohonos.demski.mieldzioc.desktopapplication.questionpanel.TextQuestionPanel;
import bohonos.demski.mieldzioc.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.questions.GridQuestion;
import bohonos.demski.mieldzioc.questions.MultipleChoiceQuestion;
import bohonos.demski.mieldzioc.questions.OneChoiceQuestion;
import bohonos.demski.mieldzioc.questions.ScaleQuestion;
import bohonos.demski.mieldzioc.questions.TextQuestion;
import bohonos.demski.mieldzioc.survey.Survey;
import java.awt.Container;
import java.awt.Dimension;
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
    
    private int questionsPosition = 0;
    
    private ApplicationLogic applicationLogic;
    private String idOfSurvey;
    private JButton saveButton;
    private JLabel titleLabel, descriptionLabel, title, description;
    private JPanel questionsPanel;
    private JScrollPane scrollPane;
    private Container container;
    private JTextPane exempleTextPane; //tests only
    
    private CreatingSurveyFrame creatingSurveyFrame;
    private Survey survey;
    
    public SurveyPanel(String id) {
        
        applicationLogic = ApplicationLogic.getInstance();
        idOfSurvey = id;
        survey = applicationLogic.getSurveyHandler().getSurvey(idOfSurvey);
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
        questionsPanel.setLayout(null);
        scrollPane = new JScrollPane(questionsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 120, 660, 350);
        
        this.add(scrollPane);
        
        
    }
    
    /**
     * adds new DateTimeQuestion to panel
     * @param dateTimeQuestion new question to add
     */
    public void addDateTimeQuestion(DateTimeQuestion dateTimeQuestion) {
        DateTimeQuestionPanel dateTimeQuestionPanel = new DateTimeQuestionPanel(survey, dateTimeQuestion);
        dateTimeQuestionPanel.setBounds(0, questionsPosition, 640, 50);
        questionsPosition = questionsPosition + 50;
        questionsPanel.setPreferredSize(new Dimension(640, questionsPosition));
        questionsPanel.add(dateTimeQuestionPanel);
    }
    
    /**
     * adds new TestQuestion to panel
     * @param textQuestion new question to add
     */
    public void addTextQuestion(TextQuestion textQuestion) {
        TextQuestionPanel textQuestionPanel = new TextQuestionPanel(survey, textQuestion);
        textQuestionPanel.setBounds(0, questionsPosition, 640, 50);
        questionsPosition = questionsPosition + 50;
        questionsPanel.setPreferredSize(new Dimension(640, questionsPosition));
        questionsPanel.add(textQuestionPanel);
    }
    
    /**
     * adds new ScaleQuestion to panel
     * @param scaleQuestion new question to add
     */
    public void addScaleQuestion(ScaleQuestion scaleQuestion) {
        ScaleQuestionPanel scaleQuestionPanel = new ScaleQuestionPanel(survey, scaleQuestion);
        scaleQuestionPanel.setBounds(0, questionsPosition, 640, 50);
        questionsPosition = questionsPosition + 50;
        questionsPanel.setPreferredSize(new Dimension(640, questionsPosition));
        questionsPanel.add(scaleQuestionPanel);
    }
    
    /**
     * adds new OneChoiceQuestion to panel
     * @param oneChoiceQuestion OneChoiceQuestion
     */
    public void addOneChoiceQuestion(OneChoiceQuestion oneChoiceQuestion) {
        OneChoiceQuestionPanel oneChoiceQuestionPanel = new OneChoiceQuestionPanel(survey, oneChoiceQuestion);
        oneChoiceQuestionPanel.setBounds(0, questionsPosition, 640, 80);
        questionsPosition = questionsPosition + 80;
        questionsPanel.setPreferredSize(new Dimension(640, questionsPosition));
        questionsPanel.add(oneChoiceQuestionPanel);
    }
    
    /**
     * adds new MultipleChoiceQuestion to panel
     * @param multipleChoiceQuestion OneChoiceQuestion
     */
    public void addMultipleChoiceQuestion(MultipleChoiceQuestion multipleChoiceQuestion) {
        MultipleChoiceQuestionPanel multipleChoiceQuestionPanel = new MultipleChoiceQuestionPanel(survey, multipleChoiceQuestion);
        multipleChoiceQuestionPanel.setBounds(0, questionsPosition, 640, 80);
        questionsPosition = questionsPosition + 80;
        questionsPanel.setPreferredSize(new Dimension(640, questionsPosition));
        questionsPanel.add(multipleChoiceQuestionPanel);
    }
    
    /**
     * adds new GridQuestion to panel
     * @param gridQuestion OneChoiceQuestion
     */
    public void addGridQuestion(GridQuestion gridQuestion) {
        GridQuestionPanel gridQuestionPanel = new GridQuestionPanel(survey, gridQuestion);
        gridQuestionPanel.setBounds(0, questionsPosition, 640, 100);
        questionsPosition = questionsPosition + 100;
        questionsPanel.setPreferredSize(new Dimension(640, questionsPosition));
        questionsPanel.add(gridQuestionPanel);
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
