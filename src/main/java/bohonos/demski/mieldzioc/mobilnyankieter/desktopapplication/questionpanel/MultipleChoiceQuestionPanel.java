/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel;

import java.util.List;
import javax.swing.JCheckBox;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.QuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.MultipleChoiceQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

/**
 *
 * @author Andrzej
 */
public class MultipleChoiceQuestionPanel extends QuestionPanel {
    
    public int HEIGHT = 35;

    private List<String> answers;
    private MultipleChoiceQuestion multipleChoiceQuestion;
    
    public MultipleChoiceQuestionPanel(Survey survey, MultipleChoiceQuestion multipleChoiceQuestion, SurveyPanel surveyPanel) {
        
        super(survey, multipleChoiceQuestion, surveyPanel);
        
        this.multipleChoiceQuestion = multipleChoiceQuestion;
        answers = this.multipleChoiceQuestion.getAnswersAsStringList();
        
        for (String answer : answers) {
            JCheckBox checkBox = new JCheckBox(answer);
            checkBox.setBounds(30, HEIGHT, 250, 20);
            HEIGHT = HEIGHT + 25;
            checkBox.setEnabled(false);
            this.add(checkBox);
        }
        
    }
    
}
