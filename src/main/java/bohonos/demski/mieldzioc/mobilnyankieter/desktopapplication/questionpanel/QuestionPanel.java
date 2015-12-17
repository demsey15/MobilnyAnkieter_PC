/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.editquestionframe.*;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.GridQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.MultipleChoiceQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.OneChoiceQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.ScaleQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.TextQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.SurveyHandler;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Andrzej
 */
public class QuestionPanel extends JPanel implements ActionListener {
    
    public static int HEIGHT = 50;
    public static final int WIDTH = 642;
    
    protected ApplicationLogic applicationLogic;
    protected Survey survey;
    protected SurveyPanel surveyPanel;
    protected Question question;
    
    private JButton questionUp;
    private JButton questionDown;
    private JButton questionDelete;
    private JButton questionEdit;
    protected JLabel questionLabel;
    
    public QuestionPanel(Survey survey, Question question, SurveyPanel surveyPanel) throws IOException, ParseException {
        
        applicationLogic = ApplicationLogic.getInstance();
        this.survey = survey;
        this.surveyPanel = surveyPanel;
        this.question = question;
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);

        questionLabel = new JLabel(question.getQuestion());
        questionLabel.setBounds(20, 5, 300, 20);
        this.add(questionLabel);
        
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        if (applicationLogic.getSurveyHandler().getSurveyStatus(survey.getIdOfSurveys())==SurveyHandler.IN_PROGRESS){
        questionEdit = new JButton("Edytuj");
        questionEdit.setBounds(450, 5, 80, 25);
        questionEdit.addActionListener(this);
        this.add(questionEdit);
        
        questionDelete = new JButton("Usuñ");
        questionDelete.setBounds(450, 35, 80, 25);
        questionDelete.addActionListener(this);
        this.add(questionDelete);
        
        questionUp = new JButton("W górê");
        questionUp.setBounds(540, 5, 80, 25);
        questionUp.addActionListener(this);
        this.add(questionUp);
        
        questionDown = new JButton("W dó³");
        questionDown.setBounds(540, 35, 80, 25);
        questionDown.addActionListener(this);
        this.add(questionDown);
        }
    }

    /**
     * adds questionUp button to ActionListener from other panel and returns this button
     * @param al given ActionListener
     * @return reference to questionUp button
     */
    public JButton addActionListenerQuestionUp(ActionListener al) {
        questionUp.addActionListener(al);
        return questionUp;
    }
    
    /**
     * adds questionDown button to ActionListener from other panel and returns this button
     * @param al given ActionListener
     * @return reference to questionDown button
     */
    public JButton addActionListenerQuestionDown(ActionListener al) {
        questionDown.addActionListener(al);
        return questionDown;
    }
    
    /**
     * adds questionDelete button to ActionListener from other panel and returns this button
     * @param al given ActionListener
     * @return reference to questionDelete button
     */
    public JButton addActionListenerQuestionDelete(ActionListener al) {
        questionDelete.addActionListener(al);
        return questionDelete;
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == questionUp) {
            int index = this.survey.indexOfQuestion(question);
            if (index != 0) {
                this.survey.removeQuestion(question);
                this.survey.addQuestion(index-1, question);
                try {
                    this.surveyPanel.refreshQuestionList();
                } catch (IOException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (source == questionDown) {
            int index = this.survey.indexOfQuestion(question);
            int size = this.survey.questionListSize();
            if (index < size-1) {
                this.survey.removeQuestion(question);
                if (index == size-2) {
                    this.survey.addQuestion(question);
                }
                else {
                    this.survey.addQuestion(index+1, question);
                }
                try {
                    this.surveyPanel.refreshQuestionList();
                } catch (IOException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (source == questionDelete) {
            this.survey.removeQuestion(question);
            try {
                this.surveyPanel.refreshQuestionList();
            } catch (IOException ex) {
                Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (source == questionEdit) {
            int type = question.getQuestionType();
            switch (type) {
                case Question.DATE_QUESTION: {
                try {
                    EditDateTimeQuestionFrame editDateTimeQuestionFrame = new EditDateTimeQuestionFrame((DateTimeQuestion)question, surveyPanel);
                } catch (IOException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                }
                case Question.TIME_QUESTION: {
                try {
                    EditDateTimeQuestionFrame editDateTimeQuestionFrame = new EditDateTimeQuestionFrame((DateTimeQuestion)question, surveyPanel);
                } catch (IOException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                }
                case Question.TEXT_QUESTION: {
                try {
                    EditTextQuestionFrame editTextQuestionFrame = new EditTextQuestionFrame((TextQuestion)question, surveyPanel);
                } catch (IOException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                }
                case Question.SCALE_QUESTION: {
                try {
                    EditScaleQuestionFrame editScaleQuestionFrame = new EditScaleQuestionFrame((ScaleQuestion)question, surveyPanel);
                } catch (IOException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                }
                case Question.GRID_QUESTION: {
                try {
                    EditGridQuestionFrame editGridQuestionFrame = new EditGridQuestionFrame((GridQuestion)question, surveyPanel);
                } catch (IOException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                }
                case Question.MULTIPLE_CHOICE_QUESTION: {
                try {
                    EditMultipleChoiceQuestionFrame editMultipleChoiceQuestionFrame = new EditMultipleChoiceQuestionFrame((MultipleChoiceQuestion)question, surveyPanel);
                } catch (IOException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                }
                case Question.ONE_CHOICE_QUESTION: {
                try {
                    EditOneChoiceQuestionFrame editOneChoiceQuestionFrame = new EditOneChoiceQuestionFrame((OneChoiceQuestion)question, surveyPanel);
                } catch (IOException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                }
            }
        }
        
    }
    
}
