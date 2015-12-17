/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.OneChoiceQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.statistics.*;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
/**
 *
 * @author Delirus
 */
public class OQuestionPanel extends QuestionPanel{
    
     public int HEIGHT = 35;
    private int currentYPosition = 35;
    private List<String> answers;
    
    private OneChoiceQuestion oneChoiceQuestion;
    private ButtonGroup buttonGroup;
    
    public OQuestionPanel(Question question, StatisticsQuestionsFrame sqframe, List<Survey> surveys, int numberOfQuestion){
        super(question, sqframe, surveys, numberOfQuestion);
        this.oneChoiceQuestion = (OneChoiceQuestion) question;
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
