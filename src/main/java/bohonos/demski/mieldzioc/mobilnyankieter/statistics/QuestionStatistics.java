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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Delirus
 */
public class QuestionStatistics extends JFrame implements ActionListener{
    private JPanel panel;
    private JButton close;
    private Container con;
    private List<Survey> surveys;
    private int numberOfQuestion;
    private QuestionStatisticsProvider qsp;
    private JLabel questionName;
    
    
    public QuestionStatistics(List<Survey> surveys,int numberOfQuestion){
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
        close = new JButton("Zamknij");
        close.setBounds(150, 300, 100, 50);
        
        panel.setLayout(null); 
        
        panel.add(close);
        
        close.addActionListener(this);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       if(source==close){
            dispose();
        }
    }
    
}
