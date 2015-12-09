/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.DateTimeQuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.GridQuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.MultipleChoiceQuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.OneChoiceQuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.QuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.ScaleQuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.TextQuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.GridQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.MultipleChoiceQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.OneChoiceQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.ScaleQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.TextQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.io.IOException;

/**
 *
 * @author Andrzej
 */
public class SurveyPanel extends JPanel implements ActionListener {
    
    private int questionsPosition = 0;
    
    private Boolean titleSaved, descriptionSaved;
    private ApplicationLogic applicationLogic;
    private String idOfSurvey;
    private JButton changeTitleButton, changeDescriptionButton;
    private JLabel titleLabel, descriptionLabel, title, description;
    private JPanel questionsPanel;
    private JScrollPane scrollPane;
    private Container container;
    private JTextField titleField, descriptionField;
    
    private CreatingSurveyFrame creatingSurveyFrame;
    private Survey survey;
    
    public SurveyPanel(String id) throws IOException {
        
        applicationLogic = ApplicationLogic.getInstance();
        idOfSurvey = id;
        survey = applicationLogic.getSurveyHandler().getSurvey(idOfSurvey);
        //creatingSurveyFrame = new CreatingSurveyFrame(applicationLogic, id);
        //saveButton = creatingSurveyFrame.addActionListenerSave(this);
        this.setLayout(null);
        
        titleLabel = new JLabel("tytu³ : ");
        titleLabel.setBounds(20, 20, 80, 30);
        this.add(titleLabel);
        
        title = new JLabel(applicationLogic.getSurveyTitle(idOfSurvey));
        title.setBounds(110, 20, 200, 30);
        this.add(title);
        titleSaved = true;
        
        titleField = new JTextField();
        titleField.setBounds(110, 20, 200, 30);
        
        descriptionLabel = new JLabel("opis : ");
        descriptionLabel.setBounds(20, 70, 80, 30);
        this.add(descriptionLabel);
        
        description = new JLabel(applicationLogic.getSurveyDescription(idOfSurvey));
        description.setBounds(110, 70, 200, 30);
        this.add(description);
        descriptionSaved = true;
        
        descriptionField = new JTextField();
        descriptionField.setBounds(110, 70, 200, 30);
        
        changeTitleButton = new JButton("Zmieñ tytu³");
        changeTitleButton.setBounds(350, 20, 120, 30);
        changeTitleButton.addActionListener(this);
        this.add(changeTitleButton);
        
        changeDescriptionButton = new JButton("Zmieñ opis");
        changeDescriptionButton.setBounds(350, 70, 120, 30);
        changeDescriptionButton.addActionListener(this);
        this.add(changeDescriptionButton);
        
        questionsPanel = new JPanel();
        questionsPanel.setLayout(null);
        scrollPane = new JScrollPane(questionsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 120, 660, 350);
        
        this.add(scrollPane);
        
        this.refreshQuestionList();//
    }
    
    /**
     * adds new DateTimeQuestion to panel
     * @param dateTimeQuestion new question to add
     */
    public void addDateTimeQuestion(DateTimeQuestion dateTimeQuestion) throws IOException {
        DateTimeQuestionPanel dateTimeQuestionPanel = new DateTimeQuestionPanel(survey, dateTimeQuestion, this);
        dateTimeQuestionPanel.setBounds(0, questionsPosition, 642, 70);
        questionsPosition = questionsPosition + 70;
        questionsPanel.setPreferredSize(new Dimension(642, questionsPosition));
        questionsPanel.add(dateTimeQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    /**
     * adds new TestQuestion to panel
     * @param textQuestion new question to add
     */
    public void addTextQuestion(TextQuestion textQuestion) throws IOException {
        TextQuestionPanel textQuestionPanel = new TextQuestionPanel(survey, textQuestion, this);
        textQuestionPanel.setBounds(0, questionsPosition, 642, 70);
        questionsPosition = questionsPosition + 70;
        questionsPanel.setPreferredSize(new Dimension(642, questionsPosition));
        questionsPanel.add(textQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    /**
     * adds new ScaleQuestion to panel
     * @param scaleQuestion new question to add
     */
    public void addScaleQuestion(ScaleQuestion scaleQuestion) throws IOException {
        ScaleQuestionPanel scaleQuestionPanel = new ScaleQuestionPanel(survey, scaleQuestion, this);
        scaleQuestionPanel.setBounds(0, questionsPosition, 642, scaleQuestionPanel.HEIGHT);
        questionsPosition = questionsPosition + scaleQuestionPanel.HEIGHT;
        questionsPanel.setPreferredSize(new Dimension(642, questionsPosition));
        questionsPanel.add(scaleQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    /**
     * adds new OneChoiceQuestion to panel
     * @param oneChoiceQuestion OneChoiceQuestion
     */
    public void addOneChoiceQuestion(OneChoiceQuestion oneChoiceQuestion) throws IOException {
        OneChoiceQuestionPanel oneChoiceQuestionPanel = new OneChoiceQuestionPanel(survey, oneChoiceQuestion, this);
        oneChoiceQuestionPanel.setBounds(0, questionsPosition, 642, oneChoiceQuestionPanel.HEIGHT);
        questionsPosition = questionsPosition + oneChoiceQuestionPanel.HEIGHT;
        questionsPanel.setPreferredSize(new Dimension(642, questionsPosition));
        questionsPanel.add(oneChoiceQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this); //added
    }
    
    /**
     * adds new MultipleChoiceQuestion to panel
     * @param multipleChoiceQuestion OneChoiceQuestion
     */
    public void addMultipleChoiceQuestion(MultipleChoiceQuestion multipleChoiceQuestion) throws IOException {
        MultipleChoiceQuestionPanel multipleChoiceQuestionPanel = new MultipleChoiceQuestionPanel(survey, multipleChoiceQuestion, this);
        multipleChoiceQuestionPanel.setBounds(0, questionsPosition, 642, multipleChoiceQuestionPanel.HEIGHT);
        questionsPosition = questionsPosition + multipleChoiceQuestionPanel.HEIGHT;
        questionsPanel.setPreferredSize(new Dimension(642, questionsPosition));
        questionsPanel.add(multipleChoiceQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    /**
     * adds new GridQuestion to panel
     * @param gridQuestion OneChoiceQuestion
     */
    public void addGridQuestion(GridQuestion gridQuestion) throws IOException {
        GridQuestionPanel gridQuestionPanel = new GridQuestionPanel(survey, gridQuestion, this);
        gridQuestionPanel.setBounds(0, questionsPosition, 642, gridQuestionPanel.HEIGHT);
        questionsPosition = questionsPosition + gridQuestionPanel.HEIGHT;
        questionsPanel.setPreferredSize(new Dimension(642, questionsPosition));
        questionsPanel.add(gridQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    /**
     * refreshes all QuestionPanels in survey panel
     */
    public void refreshQuestionList() throws IOException {
        questionsPanel.removeAll();
        questionsPosition = 0;
        int size = survey.questionListSize();
        for (int i=0; i<size; i++) {
            Question question = survey.getQuestion(i);
            int type = question.getQuestionType();
            switch (type) {
                case Question.DATE_QUESTION: {
                    addDateTimeQuestion((DateTimeQuestion)question);
                    break;
                }
                case Question.TIME_QUESTION: {
                    addDateTimeQuestion((DateTimeQuestion)question);
                    break;
                }
                case Question.TEXT_QUESTION: {
                    addTextQuestion((TextQuestion)question);
                    break;
                }
                case Question.SCALE_QUESTION: {
                    addScaleQuestion((ScaleQuestion)question);
                    break;
                }
                case Question.GRID_QUESTION: {
                    addGridQuestion((GridQuestion)question);
                    break;
                }
                case Question.MULTIPLE_CHOICE_QUESTION: {
                    addMultipleChoiceQuestion((MultipleChoiceQuestion)question);
                    break;
                }
                case Question.ONE_CHOICE_QUESTION: {
                    addOneChoiceQuestion((OneChoiceQuestion)question);
                    break;
                }
            }
        }
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == changeTitleButton) {
            if (titleSaved) {
                titleField.setText(survey.getTitle());
                this.remove(title);
                this.add(titleField);
                titleSaved = false;
                SwingUtilities.updateComponentTreeUI(this);
            }
            else {
                if (titleField.getText().equals("")==false) {
                    survey.setTitle(titleField.getText());
                    title.setText(survey.getTitle());
                    this.remove(titleField);
                    this.add(title);
                    titleSaved = true;
                    SwingUtilities.updateComponentTreeUI(this);
                }
            }
        }
        
        if (source == changeDescriptionButton) {
            if (descriptionSaved) {
                descriptionField.setText(survey.getDescription());
                this.remove(description);
                this.add(descriptionField);
                descriptionSaved = false;
                SwingUtilities.updateComponentTreeUI(this);
            }
            else {
                survey.setDescription(descriptionField.getText());
                description.setText(survey.getDescription());
                this.remove(descriptionField);
                this.add(description);
                descriptionSaved = true;
                SwingUtilities.updateComponentTreeUI(this);
            }
        }

    }
    
}
