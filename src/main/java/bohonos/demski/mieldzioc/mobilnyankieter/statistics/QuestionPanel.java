/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;

import static bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.InterviewerPanel.HEIGHT;
import static bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.InterviewerPanel.WIDTH;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        
        answersLabel = new JLabel(this.getAnswersAsString(question.getAnswersAsStringList()));
        answersLabel.setBounds(20, 40, 400, 40);
        this.add(questionLabel);
        this.add(answersLabel);
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
