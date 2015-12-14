/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Delirus
 */
public class ShowStatisticsFilledSurveys extends JFrame implements ActionListener{
    private JLabel j1, j2,j3,j4,j5;
    private JPanel panel;
    private JButton close;
    private Container con;
    private List<Survey> surveys;
    private SurveysStatisticsProvider stats;
 
    public ShowStatisticsFilledSurveys(List<Survey> surveys){
    super("Statystyki ankietera");
         addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
			}
		});
        
         this.surveys = surveys;
         stats = new SurveysStatisticsProvider();
         setSize(400, 400);
	setLocation(300,300);
        setResizable(false);
        panel = new JPanel();
         con = this.getContentPane();
        con.add(panel);
        
        /*
        InterviewerStatisticsProvider interviewerStatisticsProvider = new InterviewerStatisticsProvider();
        Interviewer interviewer = applicationLogic.getServerConnectionFacade().getInterviewer(idInterviewer, "admin", "admin".toCharArray());
        mean = interviewerStatisticsProvider.getMeanFilledSurveysOnADay(applicationLogic.getServerConnectionFacade().getFilledSurveys(idSurveys, "admin", "admin".toCharArray()), interviewer);
        amount = interviewerStatisticsProvider.getAmountOfFilledSurveys(applicationLogic.getServerConnectionFacade().getFilledSurveys(idSurveys, "admin", "admin".toCharArray()));
        */
        j1 = new JLabel("Liczba zebranych ankiet: "+ stats.getNumberOfFilledSurveys(surveys));
        j2 = new JLabel("Œrednia liczba zebranych ankiet na dzieñ: "+stats.getMeanFilledSurveysOnADay(surveys));
        j3 = new JLabel("Œredni czas wype³niania ankiety: "+stats.getMeanTimeOfInfillSurvey(surveys));
        
        
                
                
                
                
        close = new JButton("Zamknij");
        j1.setBounds(50, 20, 200, 30);
        j2.setBounds(10, 80, 280, 30);
        j3.setBounds(30, 140, 240, 30);
        close.setBounds(100, 200, 150, 50);
        
        panel.setLayout(null); 
                 
        panel.add(j1);
        panel.add(j2);
        panel.add(j3);
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