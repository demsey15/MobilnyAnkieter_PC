/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;


import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

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
/**
 *
 * @author Delirus
 */
public class QuestionPanel extends JPanel implements ActionListener{
    private Question question;
    private StatisticsQuestionsFrame sqframe;
    public static int HEIGHT = 80;
    public static final int WIDTH = 780;
    private JLabel questionLabel, answersLabel;
    private JButton check;
    private int numberOfQuestion;
    private List<Survey> surveys;
    
    public QuestionPanel(Question question, StatisticsQuestionsFrame sqframe, List<Survey> surveys, int numberOfQuestion){
        this.question = question;
        this.sqframe = sqframe;
        this.surveys = surveys;
        this.numberOfQuestion = numberOfQuestion;
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        
        questionLabel = new JLabel(question.getQuestion());
        //System.out.println(interviewer.getName()+" "+ interviewer.getSurname()+" "+interviewer.getId());
        questionLabel.setBounds(20, 0, 400, 40);
        
        //answersLabel = new JLabel(this.getAnswersAsString(question.getAnswersAsStringList()));
        //answersLabel.setBounds(20, 40, 400, 40);
        this.add(questionLabel);
        //this.add(answersLabel);
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        check = new JButton("Statystyki odpowiedzi");
        //privileges = new JButton("Uprawnienia");
        check.setBounds(550, 20, 200, 40);
        //privileges.setBounds(600, 15, 150, 40);
        this.add(check);
        //this.add(privileges);
        check.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==check){
            final int type = question.getQuestionType();
            EventQueue.invokeLater(new Runnable() {
		//	@Override
			public void run() {           
                           //     QuestionStatistics show = new QuestionStatistics(surveys, numberOfQuestion);
                           switch (type) {
                               case Question.DATE_QUESTION: {
                    DTQuestionStatistics dt = new DTQuestionStatistics(surveys, numberOfQuestion);
                    break;
                }
                case Question.TIME_QUESTION: {
                     DTQuestionStatistics dt = new DTQuestionStatistics(surveys, numberOfQuestion);
                    break;
                }
                case Question.TEXT_QUESTION: {
                    TQuestionStatistics t = new TQuestionStatistics(surveys, numberOfQuestion);
                    break;
                }
                case Question.SCALE_QUESTION: {
                    SQuestionStatistics st = new SQuestionStatistics(surveys, numberOfQuestion);
                    break;
                }
                case Question.GRID_QUESTION: {
                    GQuestionStatistics gt = new GQuestionStatistics(surveys, numberOfQuestion);
                    break;
                }
                case Question.MULTIPLE_CHOICE_QUESTION: {
                    MQuestionStatistics mt = new MQuestionStatistics(surveys, numberOfQuestion);
                    break;
                }
                case Question.ONE_CHOICE_QUESTION: {
                    OQuestionStatistics ot = new OQuestionStatistics(surveys, numberOfQuestion);
                    break;
                }
                           }
                        }
		});
        }
    }
    private String getAnswersAsString(List<String> list){
        String s="";
        for(String w : list){
            s += w+"  ";
        }
        System.out.println("String laczony: "+s);
        return s;
    }
}
