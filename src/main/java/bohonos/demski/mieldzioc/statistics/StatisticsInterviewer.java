/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.statistics;

import bohonos.demski.mieldzioc.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.interviewer.Interviewer;
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
public class StatisticsInterviewer extends JFrame implements ActionListener{

    private ApplicationLogic applicationLogic;
    private String idInterviewer, idSurveys;
    private JLabel ja, jm;
    private float mean;
    private int amount;
    private JPanel panel;
    private JButton close;
    private Container con;
    
    public StatisticsInterviewer( ApplicationLogic applicationLogic, Object s, Object repo){
         super("Statystyki ankietera");
         addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				System.exit(0);
			}
		});
         this.applicationLogic = applicationLogic;
         idInterviewer = (String) s;
         idSurveys = (String) repo;
         setSize(300, 200);
	setLocation(300,300);
        setResizable(false);
        panel = new JPanel();
         con = this.getContentPane();
        con.add(panel);
        
        
        InterviewerStatisticsProvider interviewerStatisticsProvider = new InterviewerStatisticsProvider();
        Interviewer interviewer = applicationLogic.getInterviewer(idInterviewer);
        mean = interviewerStatisticsProvider.getMeanFilledSurveysOnADay(applicationLogic.getSurveysRepository().getSurveys(idSurveys),interviewer);
        amount = interviewerStatisticsProvider.getAmountOfFilledSurveys(applicationLogic.getSurveysRepository().getSurveys(idSurveys));
        ja = new JLabel("Liczba zebranych ankiet: "+amount);
        jm = new JLabel("srednia liczba zebranych ankiet na dzieñ to: "+mean);
        close = new JButton("Zamknij");
        jm.setBounds(50, 20, 200, 30);
        ja.setBounds(50, 80, 200, 30);
        close.setBounds(100, 130, 100, 50);
        
        panel.setLayout(null); 
                 
        panel.add(jm);
        panel.add(ja);
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
