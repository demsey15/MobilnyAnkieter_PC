/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.CheckBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;
import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.InterviewerSurveyPrivileges;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.io.IOException;

/**
 *
 * @author Delirus
 */
public class SetPrivileges extends JFrame implements ActionListener{
    //private MenagerInterviewersFrame menager;
    private Interviewer interviewer;
    private ApplicationLogic applicationLogic;
    private JButton save, anul;
    private Container addcon;
    private JCheckBox checkBox;
    private JPanel privilegesPanel, inputPanel;
    private JScrollPane scrollPane;
    private JLabel editinig, filling, editningwithout, fillingStatisitcis;
    private int privilegesPanelPosition=0;
    
    public SetPrivileges(Interviewer interviewer) throws IOException{
       super("Uprawnienia");
        applicationLogic = ApplicationLogic.getInstance();
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
        this.interviewer=interviewer;
        //this.menager = menager;      
        setSize(800,600);
        setLocation(300,350);
        setResizable(false);
        
        checkBox = new JCheckBox("Mo¿liwoœæ tworzenia w³asnych ankiet");
        anul = new JButton("Anuluj");
        save= new JButton("Zapisz");
        
        anul.setBounds(400, 40, 100, 50);
        save.setBounds(580, 40, 150, 50);
        checkBox.setBounds(50,40,350,50);
        checkBox.setSelected(interviewer.getInterviewerPrivileges());
        
        editinig = new JLabel("Edytowanie");
        filling = new JLabel("Wype³nianie");
        editningwithout = new JLabel("Samodzielne edytowanie");
        fillingStatisitcis = new JLabel("Wype³nianie statystyk");
        
        editinig.setBounds(250, 100, 100, 30);
        filling.setBounds(350, 100, 100, 30);
        editningwithout.setBounds(450, 100, 150, 30);
        fillingStatisitcis.setBounds(600, 100, 150, 30);
        
        inputPanel = new JPanel();
	inputPanel.setLayout(null);
        inputPanel.add(anul);
        inputPanel.add(save);
        inputPanel.add(editinig);
        inputPanel.add(filling);
        inputPanel.add(editningwithout);
        inputPanel.add(fillingStatisitcis);
        inputPanel.add(checkBox);
        inputPanel.setBounds(0, 0, 780, 150);
        
        //addcon = this.getContentPane();
        //addcon.add(inputPanel);
        
        save.addActionListener(this);
        anul.addActionListener(this);
        
        privilegesPanel = new JPanel();
        privilegesPanel.setLayout(null);
        //privilegesPanel.add(inputPanel);
        scrollPane = new JScrollPane(privilegesPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 50, 800, 450);
        
        this.add(inputPanel);
        this.add(scrollPane);
        setVisible(true);
        refreshViewOfPrivileges();
    }

    private void refreshViewOfPrivileges(){
        privilegesPanelPosition = 150;
        privilegesPanel.removeAll();
        //privilegesPanel.add(inputPanel);
        //List<Interviewer> listInterviewers = applicationLogic.getInterviewers();       
        //Pamiêtaj, ¿e ma byæ tu 2 zamiast 0
        for(Map.Entry<String, Survey> entrSurv : applicationLogic.getSurveyHandler().getStatusSurveysId(1).entrySet()){
            //System.out.println("Nazwa ankiety: " + entrSurv.getKey());
            if(!interviewer.getIntervSurveyPrivileges().containsKey(entrSurv.getKey())){
                interviewer.setPrivilegesForInterviewer(entrSurv.getKey(), new InterviewerSurveyPrivileges(false, false, false, false));
            }
            
        }
        for(Map.Entry<String, InterviewerSurveyPrivileges> entr : interviewer.getIntervSurveyPrivileges().entrySet())
        {
            //Map.Entry<String,InterviewerSurveyPrivileges> entr = new AbstractMap.SimpleEntry<String, InterviewerSurveyPrivileges>(entrSurv.getKey(), new InterviewerSurveyPrivileges(false, false, false, false));
            PrivilegesPanel pPanel = new PrivilegesPanel(entr, this);
            pPanel.setBounds(0, privilegesPanelPosition, 780, 70);
            privilegesPanelPosition+=70;
            privilegesPanel.setPreferredSize(new Dimension(780,privilegesPanelPosition));
            privilegesPanel.add(pPanel);           
        }
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       if(source == save){
           interviewer.setInterviewerPrivileges(checkBox.isSelected());
           for(Component c : privilegesPanel.getComponents())
        {
            if(c instanceof PrivilegesPanel){
                //PrivilegesPanel privPanel = (PriviligesPanel) c;
                String s = ((PrivilegesPanel)c).getIdSurvey();
                InterviewerSurveyPrivileges interPriv = ((PrivilegesPanel)c).getInterviewerSurveyPrivileges();
                interviewer.setPrivilegesForInterviewer(s, interPriv);
                //interviewer.getIntervSurveyPrivileges().get(((PrivilegesPanel)c).getIdSurvey()) ;//= ((PrivilegesPanel)c).getInterviewerSurveyPrivileges();
            }
              
        }
           dispose();
       }
       if(source == anul){
           dispose();
       }
    }
}
