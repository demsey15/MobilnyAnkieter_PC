/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;

import bohonos.demski.mieldzioc.mobilnyankieter.common.Pair;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Delirus
 */
public class GQuestionStatistics extends JFrame implements ActionListener{
    private JPanel panel;
    //private JButton close;
    private Container con;
    private List<Survey> surveys;
    private int numberOfQuestion;
    private QuestionStatisticsProvider qsp;
    private JLabel questionName;
    private JLabel j1, j2,j3,j4,j5, j6, j7;
    private List<Pair<String, Integer>> ranking;
    
    private String getStringWithoutSpecialCharacters(String s){
        String w ="";
        
        for(int i=0; i<s.length();i++){
            if(s.charAt(i)!='#'&&s.charAt(i)!='^'){
                w+=s.charAt(i);
            }
        }
        return w;
    }
    
    public GQuestionStatistics(List<Survey> surveys,int numberOfQuestion){
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
        ranking = qsp.getTheMostFrequentAnswers(surveys, numberOfQuestion);
        setSize(400, 350);
	setLocation(300,300);
        setResizable(false);
        panel = new JPanel();
        con = this.getContentPane();
        con.add(panel);
        
        j1 = new JLabel("1. ");
        j2 = new JLabel("2. ");
        j3 = new JLabel("3. ");
        j4 = new JLabel("4. ");
        j5 = new JLabel("5. ");
        j6 = new JLabel("Liczba wype³nionych ankiet: " + surveys.size());
        j7 = new JLabel("Liczba odpowiedzi na to pytanie to: " + qsp.getNumberUsersAnswers(surveys, numberOfQuestion));
        
            if(0<ranking.size()){
                j1.setText("1. " + getStringWithoutSpecialCharacters(ranking.get(0).getFirst()) +  " - "+ranking.get(0).getSecond());
            }

            if(1<ranking.size()){
                j2.setText("2. " + getStringWithoutSpecialCharacters(ranking.get(1).getFirst()) +  " - "+ranking.get(1).getSecond());
            }
            if(2<ranking.size()){
                j3.setText("3. " + getStringWithoutSpecialCharacters(ranking.get(2).getFirst()) + " - "+ranking.get(2).getSecond());
            }
            if(3<ranking.size()){
                j4.setText("4. " + getStringWithoutSpecialCharacters(ranking.get(3).getFirst()) +" - "+ranking.get(3).getSecond());
            }
            if(4<ranking.size()){
                j5.setText("5. " + getStringWithoutSpecialCharacters(ranking.get(4).getFirst()) +  " - "+ranking.get(4).getSecond());
            }
        
        //close = new JButton("Zamknij");
        //close.setBounds(150, 300, 100, 50);
        j1.setBounds(100, 90, 300, 30);
        j2.setBounds(100, 130, 300, 30);
        j3.setBounds(100, 170, 300, 30);
        j4.setBounds(100, 210, 300, 30);
        j5.setBounds(100, 250, 300, 30);
        j6.setBounds(100, 20, 300, 30);
        j7.setBounds(100, 50, 300, 30);
        
        panel.setLayout(null); 
        
        //panel.add(close);
        panel.add(j1);
        panel.add(j2);
        panel.add(j3);
        panel.add(j4);
        panel.add(j5);
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

