/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

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
    
    public CreatorFrame(ApplicationLogic appLogic){
        super("Kreator ankiet");
        applicationLogic = appLogic;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we){
		dispose();
            }
	});
        
        setSize(800, 600);
	setLocation(300,200);
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
        SurveyPanel surveyPanel = new SurveyPanel(applicationLogic, id);
        if (tabbedPane.indexOfTab(id) == -1) {
            tabbedPane.addTab(id, surveyPanel);
        }
        /*int index = tabbedPane.indexOfTab(id);
        JPanel panelTab = new JPanel(new GridBagLayout());
        panelTab.setOpaque(false);
        JLabel labelTitle = new JLabel(id);
        JButton closeButton = new JButton("x");
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        
        panelTab.add(labelTitle, gbc);
        
        gbc.gridx++;
        gbc.weightx = 0;
        panelTab.add(closeButton, gbc);
        
        tabbedPane.setTabComponentAt(index, panelTab);
        
        
        closeButton.addActionListener(this);
        
        */
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == itemNewSurvey) {
            CreatingSurveyFrame creatingSurveyFrame = new CreatingSurveyFrame(applicationLogic, this);
            //String idOfSurvey = applicationLogic.newSurvey();
            //SurveyPanel surveyPanel = new SurveyPanel(applicationLogic, idOfSurvey);
            //tabbedPane.addTab("id ankiety: " + idOfSurvey, surveyPanel);
        }
        
        if (source == itemCopyOldSurvey) {
            CopingSurveyFrame copingSurveyFrame = new CopingSurveyFrame(applicationLogic, this);
        }
        
        if (source == itemEditSurvey) {
            LoadingSurveyFrame loadingSurveyFrame = new LoadingSurveyFrame(applicationLogic, this);
        }
        
        if (source == itemDataTimeQuestion) {
            AddDataTimeQuestionFrame addDataTimeQuestionFrame = new AddDataTimeQuestionFrame(applicationLogic, this);
        }
        
        if (source == itemGridQuestion) {
            
        }
        
        if (source == itemMultipleChioceQuestion) {
            
        }
        
        if (source == itemOneChoiceQuestion) {
            
        }
        
        if (source == itemScaleQuestion) {
            
        }
        
        if (source == itemTextQuestion) {
            
        }
        
        //Component selectedTab = tabbedPane.getSelectedComponent();
        //int selectedTab = tabbedPane.indexOfTab(getName());
        //if(selectedTab>=0){
        //if (selectedTab != null) {
        //   tabbedPane.remove(selectedTab);
        //}
        
        /*JButton btn = (JButton) ae.getSource();
        String s1 = btn.getActionCommand();
        for (int counter = 1; counter < tabbedPane.getTabCount(); counter++) {
            JPanel pnl = (JPanel) tabbedPane.getTabComponentAt(counter);
            btn = (JButton) pnl.getComponent(0);
            String s2 = btn.getActionCommand();
            if (s1.equals(s2)) {
                tabbedPane.removeTabAt(counter);
                break;
            }
        }*/
        
    }
    
}
