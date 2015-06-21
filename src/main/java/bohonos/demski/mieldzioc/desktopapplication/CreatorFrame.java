/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import bohonos.demski.mieldzioc.survey.Survey;
import bohonos.demski.mieldzioc.desktopapplication.addquestionframe.AddTextQuestionFrame;
import bohonos.demski.mieldzioc.desktopapplication.addquestionframe.AddScaleQuestionFrame;
import bohonos.demski.mieldzioc.desktopapplication.addquestionframe.AddOneChoiceQuestionFrame;
import bohonos.demski.mieldzioc.desktopapplication.addquestionframe.AddMultipleChoiceQuestionFrame;
import bohonos.demski.mieldzioc.desktopapplication.addquestionframe.AddGridQuestionFrame;
import bohonos.demski.mieldzioc.desktopapplication.addquestionframe.AddDateTimeQuestionFrame;
import bohonos.demski.mieldzioc.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.questions.ScaleQuestion;
import bohonos.demski.mieldzioc.questions.TextQuestion;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/**
 *
 * @author Andrzej
 */
public class CreatorFrame extends JFrame implements ActionListener {
    
    private JMenu menuSurvey;
    private JMenu menuQuestion;
    
    private JMenuItem itemNewSurvey;
    private JMenuItem itemCopyOldSurvey;
    private JMenuItem itemEditSurvey; 
    private JMenuItem itemDataTimeQuestion;
    private JMenuItem itemGridQuestion;
    private JMenuItem itemMultipleChioceQuestion;
    private JMenuItem itemOneChoiceQuestion;
    private JMenuItem itemScaleQuestion;
    private JMenuItem itemTextQuestion;
    
    private CloseButtonTabbedPane tabbedPane;
    
    private ApplicationLogic applicationLogic;
    
    public CreatorFrame(){
        super("Kreator ankiet");
        applicationLogic = ApplicationLogic.getInstance();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we){
		dispose();
            }
	});
        
        setSize(800, 600);
	setLocation(300,100);
        setResizable(false);
        
        menuSurvey = new JMenu("Ankieta");
        
        itemNewSurvey = new JMenuItem("Stwórz now¹ ankietê");
        itemCopyOldSurvey = new JMenuItem("Swórz now¹ na podstawie...");
        itemEditSurvey = new JMenuItem("Edytuj ankietê"); 
        
        menuSurvey.add(itemNewSurvey);
        menuSurvey.add(itemCopyOldSurvey);
        menuSurvey.add(itemEditSurvey);
        
        itemNewSurvey.addActionListener(this);
        itemCopyOldSurvey.addActionListener(this);
        itemEditSurvey.addActionListener(this);
        
        menuQuestion = new JMenu("Dodaj Pytanie");

        itemDataTimeQuestion = new JMenuItem("Pytanie o datê");
        itemGridQuestion = new JMenuItem("Pytanie typu GrigQuestion");
        itemMultipleChioceQuestion = new JMenuItem("Pytanie wielokrotnego wyboru");
        itemOneChoiceQuestion = new JMenuItem("Pytanie jednokrotnego wyboru");
        itemScaleQuestion = new JMenuItem("Pytanie ze skal¹");
        itemTextQuestion = new JMenuItem("Pytanie tekstowe");
        
        menuQuestion.add(itemDataTimeQuestion);
        menuQuestion.add(itemGridQuestion);
        menuQuestion.add(itemMultipleChioceQuestion);
        menuQuestion.add(itemOneChoiceQuestion);
        menuQuestion.add(itemScaleQuestion);
        menuQuestion.add(itemTextQuestion);
        
        itemDataTimeQuestion.addActionListener(this);
        itemGridQuestion.addActionListener(this);  
        itemMultipleChioceQuestion.addActionListener(this);
        itemOneChoiceQuestion.addActionListener(this);
        itemScaleQuestion.addActionListener(this);   
        itemTextQuestion.addActionListener(this);        
        
        JMenuBar menuBar = new JMenuBar();
        
        menuBar.add(menuSurvey);
        menuBar.add(menuQuestion);
        setJMenuBar(menuBar);
        
        tabbedPane = new CloseButtonTabbedPane();
        add(tabbedPane);   
        setVisible(true);
    }
    
    /**
     * adds new tab containing survey template with given id
     * @param id id of survey template
     */
    public void addSurveyPanel(String id) {
        SurveyPanel surveyPanel = new SurveyPanel(id);
        if (tabbedPane.indexOfTab(id) == -1) {
            tabbedPane.addTab(id, surveyPanel);
        }
    }
    
    /**
     * adds new DateTimeQuestionPanel to choosen tab
     * @param dateTimeQuestion new question to add
     */
    public void addDateTimeQuestionPanel(DateTimeQuestion dateTimeQuestion) {
        SurveyPanel surveyPanel = (SurveyPanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        surveyPanel.addDateTimeQuestion(dateTimeQuestion);
    }
    
    /**
     * adds new TextQuestionPanel to choosen tab
     * @param textQuestion new question to add
     */
    public void addTextQuestionPanel(TextQuestion textQuestion) {
        SurveyPanel surveyPanel = (SurveyPanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        surveyPanel.addTextQuestion(textQuestion);
    }
    
    /**
     * new ScaleQuestionPanel to choosen tab
     * @param scaleQuestion new question to add
     */
    public void addScaleQuestionPanel(ScaleQuestion scaleQuestion) {
        SurveyPanel surveyPanel = (SurveyPanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        surveyPanel.addScaleQuestion(scaleQuestion);
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == itemNewSurvey) {
            CreatingSurveyFrame creatingSurveyFrame = new CreatingSurveyFrame(this);
        }
        
        if (source == itemCopyOldSurvey) {
            CopingSurveyFrame copingSurveyFrame = new CopingSurveyFrame(this);
        }
        
        if (source == itemEditSurvey) {
            LoadingSurveyFrame loadingSurveyFrame = new LoadingSurveyFrame(this);
        }
        
        if (source == itemDataTimeQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                AddDateTimeQuestionFrame addDateTimeQuestionFrame = new AddDateTimeQuestionFrame(survey, this);
            }
        }
        
        if (source == itemGridQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                AddGridQuestionFrame addGridQuestionFrame = new AddGridQuestionFrame(survey, this);
            }
        }
        
        if (source == itemMultipleChioceQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                AddMultipleChoiceQuestionFrame addMultipleChoiceQuestionFrame = new AddMultipleChoiceQuestionFrame(survey, this);
            }
        }
        
        if (source == itemOneChoiceQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                AddOneChoiceQuestionFrame addOneChoiceQuestionFrame = new AddOneChoiceQuestionFrame(survey, this);
            }
        }
        
        if (source == itemScaleQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                AddScaleQuestionFrame addScaleQuestionFrame = new AddScaleQuestionFrame(survey, this);
            }
        }
        
        if (source == itemTextQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                AddTextQuestionFrame addTextQuestionFrame = new AddTextQuestionFrame(survey, this);
            }
        }

    }
    
}
