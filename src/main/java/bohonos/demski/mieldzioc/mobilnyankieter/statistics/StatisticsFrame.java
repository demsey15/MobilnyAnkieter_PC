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
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.GraphicInterface;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.MenagerInterviewersFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.awt.EventQueue;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private List<Survey> surveys;
    
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
        
        for(Entry<String, Long> entrSurv : applicationLogic.getSurveysRepository().getAllMaxNumbersOfSurveys().entrySet()){
            if(entrSurv.getValue()>0){
                comboBoxItems2.add(entrSurv.getKey());
            }
        }      
        DefaultComboBoxModel model2 = new DefaultComboBoxModel(comboBoxItems2);
        //idInterviewer = new JComboBox(model1);
        idSurvey = new JComboBox(model2);
        //jInterviewer = new JLabel("ID Ankietera: ");
        jSurvey = new JLabel("ID Ankiety: ");
        
        listModel = new DefaultListModel();
        listOfInterviewers = new JList(listModel);
        
        //idInterviewer.setBounds(250, 30, 200, 30);
        idSurvey.setBounds(250, 30, 350, 30);
        //jInterviewer.setBounds(150, 20, 100, 50);
        jSurvey.setBounds(150,20,100, 50);
        rankInterviewers.setBounds(450, 195, 200, 50);
        statisticsInterviewer.setBounds(450, 270, 200, 50);
        statResultSurvey.setBounds(450, 345, 200, 50);
        statFillSurvey.setBounds(450, 420, 200, 50);
        close.setBounds(450, 495, 200, 50);
        listOfInterviewers.setBounds(100, 100, 300, 450);
        
        ListSelectionListener listListener = new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                selectedInterviewers = new ArrayList<Interviewer>();
                for(int a=0; a < interviewers.size();a++){
                    if(listOfInterviewers.isSelectedIndex(a))
                        selectedInterviewers.add(interviewers.get(a));
                }
            }
        };
        
        idSurvey.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e){
                String selectedIdSurvey = (String) idSurvey.getSelectedItem();
                List<String> macs = applicationLogic.getSurveysRepository().getMacsOfSurvey(selectedIdSurvey);
                //System.out.println("Liczba maców: "+macs.size());
                interviewers = applicationLogic.getInterviewersRepository().getSelectedInterviewers(macs);
                //System.out.println("Liczba ankieterów z zadanych maców: " + interviewers.size());
                listModel.clear();
                for(Interviewer interviewer : interviewers){     
                    System.out.println("Lista: " + interviewer.getName()+" "+interviewer.getSurname()+" "+interviewer.getId());
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
        listOfInterviewers.addListSelectionListener(listListener);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       if(source==close){
            dispose();
        }
       
       if(source == rankInterviewers){
          if(selectedInterviewers.size()>0){
           surveys = applicationLogic.getSurveysRepository().getSurveysOfMacs((String) idSurvey.getSelectedItem(), applicationLogic.getInterviewersRepository().getMacAdress(selectedInterviewers));
           EventQueue.invokeLater(new Runnable() {
		//	@Override
			public void run() {
                            RankInterviewer show = new RankInterviewer(surveys, selectedInterviewers);             
                        }
		});
          }
       }
       
       /*if(source == statisticsInterviewer){
           Object selected = idInterviewer.getSelectedItem();
           Object repo = idSurvey.getSelectedItem();
           StatisticsInterviewer si = new StatisticsInterviewer(applicationLogic, selected, repo);
       }*/
       
       if(source == statFillSurvey){
           if(selectedInterviewers.size()>0){
                surveys = applicationLogic.getSurveysRepository().getSurveysOfMacs((String) idSurvey.getSelectedItem(), applicationLogic.getInterviewersRepository().getMacAdress(selectedInterviewers));
                System.out.println("Liczba ankiet:" + surveys.size());
                System.out.println("Lista adresow mac:" + applicationLogic.getInterviewersRepository().getMacAdress(selectedInterviewers));
                System.out.println("Nazwa ankiety: " + (String) idSurvey.getSelectedItem());
                EventQueue.invokeLater(new Runnable() {
		//	@Override
			public void run() {
                            ShowStatisticsFilledSurveys show = new ShowStatisticsFilledSurveys(surveys);             
                        }
		});
            }
       }
       
       if(source == statResultSurvey){
           surveys = applicationLogic.getSurveysRepository().getSurveysOfMacs((String) idSurvey.getSelectedItem(), applicationLogic.getInterviewersRepository().getMacAdress(selectedInterviewers));
           if(selectedInterviewers.size()>0){
           EventQueue.invokeLater(new Runnable() {
		//	@Override
			public void run() {           
                                StatisticsQuestionsFrame show = new StatisticsQuestionsFrame(surveys);
                           
                        }
		});
           }
       }
    
    }
}
