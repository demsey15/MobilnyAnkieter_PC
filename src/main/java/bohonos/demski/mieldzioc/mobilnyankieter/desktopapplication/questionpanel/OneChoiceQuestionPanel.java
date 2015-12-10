/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.OneChoiceQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author Andrzej
 */
public class OneChoiceQuestionPanel extends QuestionPanel {
    
    public int HEIGHT = 35;
    private int currentYPosition = 35;
    private List<String> answers;
    
    private OneChoiceQuestion oneChoiceQuestion;
    private ButtonGroup buttonGroup;

    public OneChoiceQuestionPanel(Survey survey, OneChoiceQuestion oneChoiceQuestion, SurveyPanel surveyPanel) throws IOException, ParseException {
        
        super(survey, oneChoiceQuestion, surveyPanel);
        
        this.oneChoiceQuestion = oneChoiceQuestion;
        answers = this.oneChoiceQuestion.getAnswersAsStringList();
        
        buttonGroup = new ButtonGroup();
        
        for (String answer : answers) {
            JRadioButton radioButton = new JRadioButton(answer);
            radioButton.setBounds(30, currentYPosition, 250, 20);
            currentYPosition = currentYPosition + 25;
            buttonGroup.add(radioButton);
            radioButton.setEnabled(false);
            this.add(radioButton);
        }
        HEIGHT = currentYPosition + 10;
    }
    
}
