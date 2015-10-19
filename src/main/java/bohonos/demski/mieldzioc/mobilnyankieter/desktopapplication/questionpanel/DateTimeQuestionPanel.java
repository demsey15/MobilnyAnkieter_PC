/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel;

import javax.swing.JTextField;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.editquestionframe.EditDateTimeQuestionFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.QuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

/**
 *
 * @author Andrzej
 */
public class DateTimeQuestionPanel extends QuestionPanel {
    
    public static int HEIGHT = 70;
    
    private JTextField answerField;
    
    public DateTimeQuestionPanel(Survey survey, DateTimeQuestion dataTimeQuestion, SurveyPanel surveyPanel) {
        
        super(survey, dataTimeQuestion, surveyPanel);
        
        answerField = new JTextField();
        answerField.setEditable(false);
        answerField.setBounds(20, 30, 80, 20);
        this.add(answerField);
    }
    
}
