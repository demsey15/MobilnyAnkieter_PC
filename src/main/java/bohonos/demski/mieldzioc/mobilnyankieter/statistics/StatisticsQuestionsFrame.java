/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Delirus
 */
public class StatisticsQuestionsFrame extends JFrame implements ActionListener{
    //private ApplicationLogic applicationLogic;
    private int questionPanelPosition = 0;
    private JPanel panel;
    private Container con;
    private JButton close;
    private List<Survey> surveys;
   // private JScrollPane listScroller;
    //private JList list;
    //private List<String> selectedInterviewer;
    private JPanel questionPanel;
    private JScrollPane scrollPane;
    
     public StatisticsQuestionsFrame(List<Survey> surveys){
        super("Statystyki ankiety");
        //applicationLogic = ApplicationLogic.getInstance();
        this.surveys = surveys;
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
        
        setSize(800,600);
        setLocation(200,250);
        setResizable(false);
        
        questionPanel = new JPanel();
        questionPanel.setLayout(null);
        scrollPane = new JScrollPane(questionPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 50, 800, 500);
        
        this.add(scrollPane);
	setVisible(true);
        refreshViewOfInterviewers();
        
    }
     
     public void refreshViewOfInterviewers(){
        questionPanelPosition = 0;
        questionPanel.removeAll();
        //List<Interviewer> listInterviewers = applicationLogic.getInterviewers();       
        for(int i=0;i<surveys.get(0).questionListSize();i++){
            QuestionPanel qPanel = new QuestionPanel(surveys.get(0).getQuestion(i), this, surveys, i);
            qPanel.setBounds(0, questionPanelPosition, 780, 80);
            questionPanelPosition+=80;
            questionPanel.setPreferredSize(new Dimension(780,questionPanelPosition));
            questionPanel.add(qPanel);           
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==close){
            dispose();
            //System.exit(0);
        }
    }
    
}
