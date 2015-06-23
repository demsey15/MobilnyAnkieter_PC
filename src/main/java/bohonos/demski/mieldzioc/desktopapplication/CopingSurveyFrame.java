/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Andrzej
 */
public class CopingSurveyFrame extends JFrame implements ActionListener{
    private JButton copyButton, cancelButton;
    private ApplicationLogic applicationLogic;
    private CreatorFrame creatorFrame;
    private String[] surveysList;
    private DefaultListModel surveysItems;
    private JList list;
    private List<String> selectedSurveysList;
    private JTextField titleField, descriptionField;
    private JLabel titleLabel, descriptionLabel;
    
    public CopingSurveyFrame(CreatorFrame creatorFrame){
        super("nowa ankieta na podstawie...");
        applicationLogic = ApplicationLogic.getInstance();
        this.creatorFrame = creatorFrame;
        
        titleLabel = new JLabel("tytu³: ");
        titleLabel.setBounds(20, 20, 70, 30);
        this.add(titleLabel);
        
        descriptionLabel = new JLabel("opis: ");
        descriptionLabel.setBounds(20, 70, 70, 30);
        this.add(descriptionLabel);
        
        titleField = new JTextField();
        titleField.setBounds(80, 20, 200, 30);
        this.add(titleField);
        
        descriptionField = new JTextField();
        descriptionField.setBounds(80, 70, 200, 30);
        this.add(descriptionField);
        
        surveysList = applicationLogic.getAllSurveysList();
        surveysItems = new DefaultListModel();
        list = new JList(surveysItems);
        for (int iterator = 0; iterator < surveysList.length; iterator++) {
            surveysItems.addElement(surveysList[iterator] + "  " + applicationLogic.getSurveyTitle(surveysList[iterator]));
        }
        list.setBounds(40, 110, 220, 170);
        ListSelectionListener lsl = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectedSurveysList = list.getSelectedValuesList();
            }
        };
        this.add(list);
        list.addListSelectionListener(lsl);
        
        setSize(300, 400);
        setLocation(400,300);
        setResizable(false);
        this.setLayout(null);
        
        copyButton = new JButton("Zapisz");
        copyButton.setBounds(160, 300, 100, 40);
        this.add(copyButton);
        copyButton.addActionListener(this);
        
        cancelButton = new JButton("Anuluj");
        cancelButton.setBounds(40, 300, 100, 40);
        this.add(cancelButton);
        cancelButton.addActionListener(this);
        
        
        
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == cancelButton) {
            dispose();
        }
        
        if (source == copyButton && titleField.getText().equals("")==false) {
            String str = selectedSurveysList.get(0);
            String[] splited = str.split("\\s+");
            creatorFrame.addSurveyPanel(splited[0]);
            try {
                String idOfSurvey = applicationLogic.copySurvey(splited[0]);
                applicationLogic.setSurveyTitle(idOfSurvey, titleField.getText());
                applicationLogic.setSurveyDescription(idOfSurvey, descriptionField.getText());
                creatorFrame.addSurveyPanel(idOfSurvey);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(CopingSurveyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            dispose();
        }
        
    }
    
}
