/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;

import bohonos.demski.mieldzioc.mobilnyankieter.common.Pair;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Delirus
 */
public class RankInterviewer extends JFrame implements ActionListener{

    private JLabel j1, j2,j3,j4,j5;
    private JPanel panel;
    private JButton close;
    private Container con;
    private List<Survey> surveys;
    private InterviewerStatisticsProvider stats;
    private List<Pair<String, Integer>> map;
    private List<Pair<Interviewer, Integer>> ranking;
    List<Interviewer> interviewers;
    
    public RankInterviewer(List<Survey> surveys, List<Interviewer> interviewers){
    super("Ranking ankieterów");
         addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
			}
		});
        
        this.interviewers=interviewers;
        this.surveys = surveys;
        stats = new InterviewerStatisticsProvider();
        setSize(400, 350);
	setLocation(300,300);
        setResizable(false);
        panel = new JPanel();
        con = this.getContentPane();
        con.add(panel);
        
        map = stats.getNumbersOfFilledSurveysByMac(surveys);
        ranking = stats.getNumberOfFilledSurveysPerInterviewer(map, interviewers);
        ranking.sort(new Comparator<Pair<Interviewer, Integer>>() {
        public int compare(Pair<Interviewer, Integer> o1, Pair<Interviewer, Integer> o2) {
            if (o1.getSecond() > o2.getSecond()) {
                return 1;
            } else if (o1.getSecond().equals(o2.getSecond())) {
                return 0; // You can change this to make it then look at the
                          //words alphabetical order
            } else {
                return -1;
            }
        }
    });
       
        /*
        InterviewerStatisticsProvider interviewerStatisticsProvider = new InterviewerStatisticsProvider();
        Interviewer interviewer = applicationLogic.getServerConnectionFacade().getInterviewer(idInterviewer, "admin", "admin".toCharArray());
        mean = interviewerStatisticsProvider.getMeanFilledSurveysOnADay(applicationLogic.getServerConnectionFacade().getFilledSurveys(idSurveys, "admin", "admin".toCharArray()), interviewer);
        amount = interviewerStatisticsProvider.getAmountOfFilledSurveys(applicationLogic.getServerConnectionFacade().getFilledSurveys(idSurveys, "admin", "admin".toCharArray()));
        */
        j1 = new JLabel("1. ");
        j2 = new JLabel("2. ");
        j3 = new JLabel("3. ");
        j4 = new JLabel("4. ");
        j5 = new JLabel("5. ");
        

            if(0<ranking.size()){
                j1.setText("1. " + ranking.get(0).getFirst().getName() + " "+ranking.get(0).getFirst().getSurname()+" "+ranking.get(0).getFirst().getId()+ " - "+ranking.get(0).getSecond());
            }

            if(1<ranking.size()){
                j2.setText("1. " + ranking.get(1).getFirst().getName() + " "+ranking.get(1).getFirst().getSurname()+" "+ranking.get(1).getFirst().getId()+ " - "+ranking.get(1).getSecond());
            }
            if(2<ranking.size()){
                j3.setText("1. " + ranking.get(2).getFirst().getName() + " "+ranking.get(2).getFirst().getSurname()+" "+ranking.get(2).getFirst().getId()+ " - "+ranking.get(2).getSecond());
            }
            if(3<ranking.size()){
                j4.setText("1. " + ranking.get(3).getFirst().getName() + " "+ranking.get(3).getFirst().getSurname()+" "+ranking.get(3).getFirst().getId()+ " - "+ranking.get(3).getSecond());
            }
            if(4<ranking.size()){
                j5.setText("1. " + ranking.get(4).getFirst().getName() + " "+ranking.get(4).getFirst().getSurname()+" "+ranking.get(4).getFirst().getId()+ " - "+ranking.get(4).getSecond());
            }
    
        close = new JButton("Zamknij");
        j1.setBounds(100, 20, 300, 30);
        j2.setBounds(100, 70, 300, 30);
        j3.setBounds(100, 120, 300, 30);
        j4.setBounds(100, 170, 300, 30);
        j5.setBounds(100, 220, 300, 30);
        
        close.setBounds(125, 270, 150, 50);
        
        panel.setLayout(null); 
                 
        panel.add(j1);
        panel.add(j2);
        panel.add(j3);
        panel.add(j4);
        panel.add(j5);
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
