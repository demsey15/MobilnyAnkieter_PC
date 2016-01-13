/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bohonos.demski.mieldzioc.mobilnyankieter.statistics;

import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Math.round;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Delirus
 */
public class OQuestionStatistics extends JFrame implements ActionListener{
    private JPanel panel;
    //private JButton close;
    private Container con;
    private List<Survey> surveys;
    private int numberOfQuestion;
    private QuestionStatisticsProvider qsp;
    private JLabel questionName, amountOfAnswers, amountOfSurveys;
    private JLabel biglabel;// = new JLabel("<html><p>A lot of text to be wrapped</p></html>");
    
    public OQuestionStatistics(List<Survey> surveys,int numberOfQuestion){
         super("Statystyki pytania");
         addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
        this.surveys = surveys;
        this.numberOfQuestion = numberOfQuestion;
        
        qsp = new QuestionStatisticsProvider();
        
        String text = "";
        List<Float> procOfAnwers = qsp.getPrecentageOfChoosedOptions(surveys, numberOfQuestion);
        List<String> listOfAnswers = surveys.get(0).getQuestion(numberOfQuestion).getAnswersAsStringList();
        for(int i=0; i <listOfAnswers.size();i++){
            float procent = round(procOfAnwers.get(i)); 
            
            text += listOfAnswers.get(i)+"      " + procOfAnwers.get(i).toString() + "%"+"<br>";
        }
        
        biglabel = new JLabel("<html>" + text + "<html>");
        amountOfSurveys = new JLabel("Liczba wype³nionych ankiet: " + surveys.size());
        amountOfAnswers = new JLabel("Liczba odpowiedzi na to pytanie to: " + qsp.getNumberUsersAnswers(surveys, numberOfQuestion));
        
        setSize(400, 350);
	setLocation(300,300);
        setResizable(false);
        panel = new JPanel();
        con = this.getContentPane();
        con.add(panel);
        //close = new JButton("Zamknij");
        //close.setBounds(150, 300, 100, 50);
        biglabel.setBounds(20, 100, 320, 200);
        amountOfAnswers.setBounds(20,70,300,50);
        amountOfSurveys.setBounds(20,20,300,50);
        panel.setLayout(null); 
        
        //panel.add(close);
        panel.add(biglabel);
        panel.add(amountOfAnswers);
        panel.add(amountOfSurveys);
        //close.addActionListener(this);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       /*if(source==close){
            dispose();
        }*/
    }
    
}
