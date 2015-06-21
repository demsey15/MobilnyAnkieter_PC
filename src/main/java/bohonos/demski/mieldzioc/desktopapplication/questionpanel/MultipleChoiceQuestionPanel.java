/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication.questionpanel;

import bohonos.demski.mieldzioc.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.desktopapplication.questionpanel.QuestionPanel;
import bohonos.demski.mieldzioc.questions.MultipleChoiceQuestion;
import bohonos.demski.mieldzioc.survey.Survey;
import java.util.List;
import javax.swing.JCheckBox;

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
