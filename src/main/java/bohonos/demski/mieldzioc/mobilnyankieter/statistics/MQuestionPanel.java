/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.MultipleChoiceQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.statistics.*;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.util.List;
import javax.swing.JCheckBox;
/**
 *
 * @author Delirus
 */
public class MQuestionPanel extends QuestionPanel {
    
     public int HEIGHT = 35;

    private List<String> answers;
    private MultipleChoiceQuestion multipleChoiceQuestion;
    
    public MQuestionPanel(Question question, StatisticsQuestionsFrame sqframe, List<Survey> surveys, int numberOfQuestion){
    super(question, sqframe, surveys, numberOfQuestion);
     this.multipleChoiceQuestion = (MultipleChoiceQuestion) question;
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
