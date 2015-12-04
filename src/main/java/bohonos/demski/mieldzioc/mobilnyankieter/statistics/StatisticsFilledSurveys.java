/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;

/**
 *
 * @author Delirus
 */
public class StatisticsFilledSurveys extends JFrame implements ActionListener{

      private ApplicationLogic applicationLogic;
    private String idInterviewer, idSurveys;
    private JLabel ja, jm;
    private float mean;
    private int amount;
    private JPanel panel;
    private JButton close;
    private Container con;
    
    public StatisticsFilledSurveys( ApplicationLogic applicationLogic, Object s, Object repo){
         super("Statystyki wype³nianej ankiety");
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
        
        
        SurveysStatisticsProvider surveysStatisticsProvider = new SurveysStatisticsProvider();
      
        //mean = ;
        //amount = surveysStatisticsProvider.getNumberOfFilledSurveys(applicationLogic.getServerConnectionFacade().getFilledSurveys(idSurveys, "admin", "admin".toCharArray()));
        ja = new JLabel("Liczba zebranych ankiet: "+amount);
        jm = new JLabel("Œrednia liczba wype³nianych ankiet na dzieñ: "+mean);
        close = new JButton("Zamknij");
        jm.setBounds(10, 20, 280, 30);
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
