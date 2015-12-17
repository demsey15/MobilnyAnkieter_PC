/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;

import bohonos.demski.mieldzioc.mobilnyankieter.statistics.DTQuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.GridQuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.MultipleChoiceQuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.OneChoiceQuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.ScaleQuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.TextQuestionPanel;
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
import bohonos.demski.mieldzioc.mobilnyankieter.questions.*;
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
    
     public StatisticsQuestionsFrame(List<Survey> surveys) throws IOException, ParseException{
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
     
     public void refreshViewOfInterviewers() throws IOException, ParseException{
        questionPanelPosition = 0;
        questionPanel.removeAll();

       for(int i=0;i<surveys.get(0).questionListSize();i++){
            Question question = surveys.get(0).getQuestion(i);
            int type = question.getQuestionType();
            switch (type) {
                case Question.DATE_QUESTION: {
                    addDateTimeQuestion((DateTimeQuestion)question, i);
                    break;
                }
                case Question.TIME_QUESTION: {
                    addDateTimeQuestion((DateTimeQuestion)question,i);
                    break;
                }
                case Question.TEXT_QUESTION: {
                    addTextQuestion((TextQuestion)question,i);
                    break;
                }
                case Question.SCALE_QUESTION: {
                    addScaleQuestion((ScaleQuestion)question,i);
                    break;
                }
                case Question.GRID_QUESTION: {
                    addGridQuestion((GridQuestion)question,i);
                    break;
                }
                case Question.MULTIPLE_CHOICE_QUESTION: {
                    addMultipleChoiceQuestion((MultipleChoiceQuestion)question,i);
                    break;
                }
                case Question.ONE_CHOICE_QUESTION: {
                    addOneChoiceQuestion((OneChoiceQuestion)question,i);
                    break;
                }
            }
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
    
     public void addDateTimeQuestion(DateTimeQuestion dateTimeQuestion, int numberOfQuestion) throws IOException, ParseException {
        DTQuestionPanel dateTimeQuestionPanel = new DTQuestionPanel(dateTimeQuestion, this, surveys, numberOfQuestion);
        dateTimeQuestionPanel.setBounds(0, questionPanelPosition, 780, 80);
        questionPanelPosition = questionPanelPosition + 70;
        questionPanel.setPreferredSize(new Dimension(780, questionPanelPosition));
        questionPanel.add(dateTimeQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    /**
     * adds new TestQuestion to panel
     * @param textQuestion new question to add
     */
    public void addTextQuestion(TextQuestion textQuestion, int i) throws IOException, ParseException {
        TQuestionPanel textQuestionPanel = new TQuestionPanel( textQuestion, this,surveys,i);
        textQuestionPanel.setBounds(0, questionPanelPosition, 780, 70);
        questionPanelPosition = questionPanelPosition + 70;
        questionPanel.setPreferredSize(new Dimension(780, questionPanelPosition));
        questionPanel.add(textQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    /**
     * adds new ScaleQuestion to panel
     * @param scaleQuestion new question to add
     */
    public void addScaleQuestion(ScaleQuestion scaleQuestion, int i) throws IOException, ParseException {
        SQuestionPanel scaleQuestionPanel = new SQuestionPanel(scaleQuestion, this, surveys, i);
        scaleQuestionPanel.setBounds(0, questionPanelPosition, 780, scaleQuestionPanel.HEIGHT);
        questionPanelPosition = questionPanelPosition + scaleQuestionPanel.HEIGHT;
        questionPanel.setPreferredSize(new Dimension(780, questionPanelPosition));
        questionPanel.add(scaleQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    /**
     * adds new OneChoiceQuestion to panel
     * @param oneChoiceQuestion OneChoiceQuestion
     */
    public void addOneChoiceQuestion(OneChoiceQuestion oneChoiceQuestion, int i) throws IOException, ParseException {
        OQuestionPanel oneChoiceQuestionPanel = new OQuestionPanel(oneChoiceQuestion, this,surveys,i);
        oneChoiceQuestionPanel.setBounds(0, questionPanelPosition, 780, oneChoiceQuestionPanel.HEIGHT);
        questionPanelPosition = questionPanelPosition + oneChoiceQuestionPanel.HEIGHT;
        questionPanel.setPreferredSize(new Dimension(780, questionPanelPosition));
        questionPanel.add(oneChoiceQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this); //added
    }
    
    /**
     * adds new MultipleChoiceQuestion to panel
     * @param multipleChoiceQuestion OneChoiceQuestion
     */
    public void addMultipleChoiceQuestion(MultipleChoiceQuestion multipleChoiceQuestion, int i) throws IOException, ParseException {
        MQuestionPanel multipleChoiceQuestionPanel = new MQuestionPanel(multipleChoiceQuestion, this, surveys, i);
        multipleChoiceQuestionPanel.setBounds(0, questionPanelPosition, 780, multipleChoiceQuestionPanel.HEIGHT);
        questionPanelPosition = questionPanelPosition + multipleChoiceQuestionPanel.HEIGHT;
        questionPanel.setPreferredSize(new Dimension(780, questionPanelPosition));
        questionPanel.add(multipleChoiceQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    /**
     * adds new GridQuestion to panel
     * @param gridQuestion OneChoiceQuestion
     */
    public void addGridQuestion(GridQuestion gridQuestion, int numberOfQuestion) throws IOException, ParseException {
        GQuestionPanel gridQuestionPanel = new GQuestionPanel(gridQuestion, this, surveys, numberOfQuestion);
        gridQuestionPanel.setBounds(0, questionPanelPosition, 780, gridQuestionPanel.HEIGHT);
        questionPanelPosition = questionPanelPosition + gridQuestionPanel.HEIGHT;
        questionPanel.setPreferredSize(new Dimension(780, questionPanelPosition));
        questionPanel.add(gridQuestionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
}
