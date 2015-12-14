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
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Delirus
 */
public class StatisticsFrame extends JFrame implements ActionListener{

    private ApplicationLogic applicationLogic;
    private JButton statResultSurvey, statFillSurvey, close, statisticsInterviewer, rankInterviewers;
    private JComboBox idInterviewer, idSurvey;
    private JLabel jInterviewer, jSurvey;
    private JPanel panel;
    private Container con;
    private JList listOfInterviewers;
    private DefaultListModel listModel;
    private List<Interviewer> selectedInterviewers;
    private List<Interviewer> interviewers;
    
    public StatisticsFrame(final ApplicationLogic applicationLogic){
        super("Wyniki ankiet i statystyki");
         addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
         this.applicationLogic = applicationLogic;
         setSize(800, 600);
	setLocation(100,80);
        setResizable(false);
        panel = new JPanel();   
        statResultSurvey = new JButton("Statystyki wyników ankiety");
        statFillSurvey = new JButton("Statystyki wype³niania ankiety");
        close = new JButton("Zamknij");
        statisticsInterviewer = new JButton("Statystyki ankietera");
        rankInterviewers = new JButton("Ranking ankieterów");
        con = this.getContentPane();
        con.add(panel);
        
        //Dodanie elementów do comboboxów        
       // ServerConnectionFacade serverConnectionFacade = applicationLogic.getServerConnectionFacade();
        //Vector comboBoxItems1=new Vector();
        /*for(Interviewer interviewer : applicationLogic.getServerConnectionFacade().getAllInterviewers("admin", "admin".toCharArray())){
            comboBoxItems1.add(interviewer.getId());
        }*/
        //DefaultComboBoxModel model1 = new DefaultComboBoxModel(comboBoxItems1);
        Vector comboBoxItems2=new Vector();
        
        for(String entrSurv : applicationLogic.getSurveyHandler().getStatusSurveysId(1).keySet()){
            comboBoxItems2.add(entrSurv);
        }
                
        DefaultComboBoxModel model2 = new DefaultComboBoxModel(comboBoxItems2);
        //idInterviewer = new JComboBox(model1);
        idSurvey = new JComboBox(model2);
        //jInterviewer = new JLabel("ID Ankietera: ");
        jSurvey = new JLabel("ID Ankiety: ");
        
        listModel = new DefaultListModel();
        listOfInterviewers = new JList(listModel);
        
        //idInterviewer.setBounds(250, 30, 200, 30);
        idSurvey.setBounds(250, 30, 200, 30);
        //jInterviewer.setBounds(150, 20, 100, 50);
        jSurvey.setBounds(150,20,100, 50);
        rankInterviewers.setBounds(450, 195, 200, 50);
        statisticsInterviewer.setBounds(450, 270, 200, 50);
        statResultSurvey.setBounds(450, 345, 200, 50);
        statFillSurvey.setBounds(450, 420, 200, 50);
        close.setBounds(450, 495, 200, 50);
        listOfInterviewers.setBounds(100, 150, 300, 400);
        
        ListSelectionListener listListener = new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                selectedInterviewers = listOfInterviewers.getSelectedValuesList();
            }
        };
        idSurvey.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e){
                String selectedIdSurvey = (String) idSurvey.getSelectedItem();
                List<String> macs = applicationLogic.getSurveysRepository().getMacsOfSurvey(selectedIdSurvey);
                interviewers = applicationLogic.getInterviewersRepository().getSelectedInterviewers(macs);
                for( Interviewer interviewer : interviewers){               
                    listModel.addElement(interviewer.getName()+" "+interviewer.getSurname()+" "+interviewer.getId());
                }
        
            }
        });
        panel.setLayout(null); 
                 
        //panel.add(idInterviewer);
        panel.add(idSurvey);
        //panel.add(jInterviewer);
        panel.add(jSurvey);
        panel.add(rankInterviewers);
        panel.add(statisticsInterviewer);
        panel.add(statFillSurvey);
        panel.add(statResultSurvey);
        panel.add(listOfInterviewers);
        panel.add(close);
        
        rankInterviewers.addActionListener(this);
        statisticsInterviewer.addActionListener(this);
        statFillSurvey.addActionListener(this);
        statResultSurvey.addActionListener(this);
        close.addActionListener(this);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       if(source==close){
            dispose();
        }
       
       if(source == rankInterviewers){
           
       }
       
       if(source == statisticsInterviewer){
           Object selected = idInterviewer.getSelectedItem();
           Object repo = idSurvey.getSelectedItem();
           StatisticsInterviewer si = new StatisticsInterviewer(applicationLogic, selected, repo);
       }
       
       if(source == statFillSurvey){
           Object repo = idSurvey.getSelectedItem();
           Object selected = idInterviewer.getSelectedItem();
           StatisticsFilledSurveys statisticsFilledSurveys = new StatisticsFilledSurveys(applicationLogic, selected, repo);
       }
       
       if(source == statResultSurvey){
           
       }
    }
    
}
