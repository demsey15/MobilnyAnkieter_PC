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
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 *
 * @author Delirus
 */
public class SQuestionStatistics extends JFrame implements ActionListener{
    private JPanel panel;
    //private JButton close;
    private Container con;
    private List<Survey> surveys;
    private int numberOfQuestion;
    private QuestionStatisticsProvider qsp;
    private JLabel  meanValue, maxValue, minValue, mode, median, sigma, percentage,j6,j7;
    //private JTextArea questionName;
    
    public SQuestionStatistics(List<Survey> surveys,int numberOfQuestion){
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
        setSize(400, 400);
	setLocation(300,300);
        setResizable(false);
        panel = new JPanel();
        con = this.getContentPane();
        con.add(panel);
       // close = new JButton("Zamknij");
        
        String pytanie = surveys.get(0).getQuestion(numberOfQuestion).getQuestion();
        //questionName = new JTextArea("Pytanie: " + pytanie);
        //questionName.setLineWrap(true);
        meanValue = new JLabel("Œrednia: "+ qsp.getMean(surveys, numberOfQuestion));
        maxValue = new JLabel("Wartoœæ maksymalna: "+ qsp.getMaxValue(surveys, numberOfQuestion));
        minValue = new JLabel("Wartoœæ minimalna: "+ qsp.getMinValue(surveys, numberOfQuestion));
        mode = new JLabel("Moda: "+ qsp.getMode(surveys, numberOfQuestion));
        median = new JLabel("Mediana: "+ qsp.getMedian(surveys, numberOfQuestion));
        sigma = new JLabel("Odchylenie standardowe: "+ qsp.getStandardDeviation(surveys, numberOfQuestion));
        j6 = new JLabel("Liczba wype³nionych ankiet: " + surveys.size());
        j7 = new JLabel("Liczba odpowiedzi na to pytanie to: " + qsp.getNumberUsersAnswers(surveys, numberOfQuestion));
        //percentage
        
        //questionName.setBounds(20, 20, 300, 60);
        meanValue.setBounds(20, 200, 300, 30);
        minValue.setBounds(20, 80, 300, 30);
        maxValue.setBounds(20, 110, 300, 30);
        mode.setBounds(20, 170, 300, 30);
        median.setBounds(20, 140, 300, 30);
        sigma.setBounds(20, 230, 300, 30);
        j6.setBounds(100, 20, 300, 30);
        j7.setBounds(100, 50, 300, 30);
        
        //close.setBounds(150, 300, 100, 50);
        
        panel.setLayout(null); 
        
       //panel.add(questionName);
        panel.add(meanValue);
        panel.add(minValue);
        panel.add(maxValue);
        panel.add(mode);
        panel.add(median);
        panel.add(sigma);
        //panel.add(close);
        panel.add(j6);
        panel.add(j7);
        
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

