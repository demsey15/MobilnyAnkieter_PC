/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

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

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.addquestionframe.AddDateTimeQuestionFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.addquestionframe.AddGridQuestionFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.addquestionframe.AddMultipleChoiceQuestionFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.addquestionframe.AddOneChoiceQuestionFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.addquestionframe.AddScaleQuestionFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.addquestionframe.AddTextQuestionFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.GridQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.MultipleChoiceQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.OneChoiceQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.ScaleQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.TextQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Andrzej
 */
public class CreatorFrame extends JFrame implements ActionListener {
    
    private JMenu menuSurvey;
    private JMenu menuQuestion;
    private JMenuItem menuMenager;
    
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
    
    public CreatorFrame() throws IOException, ParseException{
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
        
        itemNewSurvey = new JMenuItem("Stw�rz now� ankiet�");
        itemCopyOldSurvey = new JMenuItem("Sw�rz now� na podstawie...");
        itemEditSurvey = new JMenuItem("Otw�rz ankiet�"); 
        
        menuSurvey.add(itemNewSurvey);
        menuSurvey.add(itemCopyOldSurvey);
        menuSurvey.add(itemEditSurvey);
        
        itemNewSurvey.addActionListener(this);
        itemCopyOldSurvey.addActionListener(this);
        itemEditSurvey.addActionListener(this);
        
        menuQuestion = new JMenu("Dodaj Pytanie");

        itemDataTimeQuestion = new JMenuItem("Pytanie o dat�");
        itemGridQuestion = new JMenuItem("Pytanie typu siatka");
        itemMultipleChioceQuestion = new JMenuItem("Pytanie wielokrotnego wyboru");
        itemOneChoiceQuestion = new JMenuItem("Pytanie jednokrotnego wyboru");
        itemScaleQuestion = new JMenuItem("Pytanie ze skal�");
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
        
        menuMenager = new JMenuItem("Zarz�dzanie ankietami");
        menuMenager.addActionListener(this);
        
        JMenuBar menuBar = new JMenuBar();
        
        menuBar.add(menuSurvey);
        menuBar.add(menuQuestion);
        menuBar.add(menuMenager);
        setJMenuBar(menuBar);
        
        tabbedPane = new CloseButtonTabbedPane();
        add(tabbedPane);   
        setVisible(true);
    }
    
    /**
     * adds new tab containing survey template with given id
     * @param id id of survey template
     */
    public void addSurveyPanel(String id) throws IOException, ParseException {
        SurveyPanel surveyPanel = new SurveyPanel(id);
        if (tabbedPane.indexOfTab(id) == -1) {
            tabbedPane.addTab(id, surveyPanel);
        }
    }
    
    /**
     * adds new DateTimeQuestionPanel to choosen tab
     * @param dateTimeQuestion new question to add
     */
    public void addDateTimeQuestionPanel(DateTimeQuestion dateTimeQuestion) throws IOException, ParseException {
        SurveyPanel surveyPanel = (SurveyPanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        surveyPanel.addDateTimeQuestion(dateTimeQuestion);
    }
    
    /**
     * adds new TextQuestionPanel to choosen tab
     * @param textQuestion new question to add
     */
    public void addTextQuestionPanel(TextQuestion textQuestion) throws IOException, ParseException {
        SurveyPanel surveyPanel = (SurveyPanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        surveyPanel.addTextQuestion(textQuestion);
    }
    
    /**
     * adds new ScaleQuestionPanel to choosen tab
     * @param scaleQuestion new question to add
     */
    public void addScaleQuestionPanel(ScaleQuestion scaleQuestion) throws IOException, ParseException {
        SurveyPanel surveyPanel = (SurveyPanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        surveyPanel.addScaleQuestion(scaleQuestion);
    }
    
    /**
     * adds new OneChoiceQuestionPanel to choosen tab
     * @param oneChoiceQuestion new question to add
     */
    public void addOneChoiceQuestionPanel(OneChoiceQuestion oneChoiceQuestion) throws IOException, ParseException {
        SurveyPanel surveyPanel = (SurveyPanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        surveyPanel.addOneChoiceQuestion(oneChoiceQuestion);
    }
    
    /**
     * adds new MultipleChoiceQuestionPanel to choosen tab
     * @param multipleChoiceQuestion new question to add
     */
    public void addMultipleChoiceQuestionPanel(MultipleChoiceQuestion multipleChoiceQuestion) throws IOException, ParseException {
        SurveyPanel surveyPanel = (SurveyPanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        surveyPanel.addMultipleChoiceQuestion(multipleChoiceQuestion);
    }
    
    /**
     * adds new GridQuestionPanel to choosen tab
     * @param gridQuestion new question to add
     */
    public void addGridQuestionPanel(GridQuestion gridQuestion) throws IOException, ParseException {
        SurveyPanel surveyPanel = (SurveyPanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        surveyPanel.addGridQuestion(gridQuestion);
    }
    
    /**
     * refreshes all QuestionPanels i choosen tab
     */
    public void refreshAllQuestionPanels() throws IOException, ParseException {
        SurveyPanel surveyPanel = (SurveyPanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        surveyPanel.refreshQuestionList();
    }
    
    public void disableSurveyEdition(String idOfSurvey) throws IOException, ParseException{
        if (tabbedPane.indexOfTab(idOfSurvey)!=-1){
            SurveyPanel surveyPanel = (SurveyPanel)tabbedPane.getComponentAt(tabbedPane.indexOfTab(idOfSurvey));
            surveyPanel.disableEdition();
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == itemNewSurvey) {
            try {
                CreatingSurveyFrame creatingSurveyFrame = new CreatingSurveyFrame(this);
            } catch (IOException ex) {
                Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (source == itemCopyOldSurvey) {
            try {
                CopingSurveyFrame copingSurveyFrame = new CopingSurveyFrame(this);
            } catch (IOException ex) {
                Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (source == itemEditSurvey) {
            try {
                LoadingSurveyFrame loadingSurveyFrame = new LoadingSurveyFrame(this);
            } catch (IOException ex) {
                Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (source == itemDataTimeQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                try {
                    AddDateTimeQuestionFrame addDateTimeQuestionFrame = new AddDateTimeQuestionFrame(survey, this);
                } catch (IOException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (source == itemGridQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                try {
                    AddGridQuestionFrame addGridQuestionFrame = new AddGridQuestionFrame(survey, this);
                } catch (IOException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (source == itemMultipleChioceQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                try {
                    AddMultipleChoiceQuestionFrame addMultipleChoiceQuestionFrame = new AddMultipleChoiceQuestionFrame(survey, this);
                } catch (IOException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (source == itemOneChoiceQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                try {
                    AddOneChoiceQuestionFrame addOneChoiceQuestionFrame = new AddOneChoiceQuestionFrame(survey, this);
                } catch (IOException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (source == itemScaleQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                try {
                    AddScaleQuestionFrame addScaleQuestionFrame = new AddScaleQuestionFrame(survey, this);
                } catch (IOException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (source == itemTextQuestion) {
            if (tabbedPane.getSelectedIndex()!=-1) {
                String tabTitle = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                Survey survey = applicationLogic.getSurveyHandler().getSurvey(tabTitle);
                try {
                    AddTextQuestionFrame addTextQuestionFrame = new AddTextQuestionFrame(survey, this);
                } catch (IOException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (source == menuMenager) { //zmiany!!
            //EventQueue.invokeLater(new Runnable() {
                //public void run() {
                    try {
                        SurveyMenagerFrame surveyMenagerFrame = new SurveyMenagerFrame(this);
                    } catch (IOException ex) {
                        Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(CreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //}
            //});
        }

    }
    
}
