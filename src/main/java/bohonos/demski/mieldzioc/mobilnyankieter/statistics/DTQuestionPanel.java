/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;

import bohonos.demski.mieldzioc.mobilnyankieter.statistics.*;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JTextField;
/**
 *
 * @author Delirus
 */
public class DTQuestionPanel extends QuestionPanel{

     public static int HEIGHT = 80;
    
    private JTextField answerField;
    
    public DTQuestionPanel(Question question, StatisticsQuestionsFrame sqframe, List<Survey> surveys, int numberOfQuestion) {
        super(question, sqframe, surveys, numberOfQuestion);
        answerField = new JTextField();
        answerField.setEditable(false);
        answerField.setBounds(20, 30, 80, 20);
        this.add(answerField);
    }
    
}
