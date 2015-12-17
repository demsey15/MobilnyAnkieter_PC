/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
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
    private JTextField titleField, descriptionField, summaryField;
    private JLabel titleLabel, descriptionLabel, summaryLabel;
    
    public CopingSurveyFrame(CreatorFrame creatorFrame) throws IOException, ParseException{
        super("nowa ankieta na podstawie...");
        applicationLogic = ApplicationLogic.getInstance();
        this.creatorFrame = creatorFrame;
        
        titleLabel = new JLabel("tytu³: ");
        titleLabel.setBounds(20, 20, 100, 30);
        this.add(titleLabel);
        
        descriptionLabel = new JLabel("opis: ");
        descriptionLabel.setBounds(20, 65, 100, 30);
        this.add(descriptionLabel);
        
        summaryLabel = new JLabel("podsumowanie: ");
        summaryLabel.setBounds(20, 110, 100, 30);
        this.add(summaryLabel);
        
        titleField = new JTextField();
        titleField.setBounds(120, 20, 250, 30);
        this.add(titleField);
        
        descriptionField = new JTextField();
        descriptionField.setBounds(120, 65, 250, 30);
        this.add(descriptionField);
        
        summaryField = new JTextField();
        summaryField.setBounds(120, 110, 250, 30);
        this.add(summaryField);
        
        surveysList = applicationLogic.getAllSurveysList();
        surveysItems = new DefaultListModel();
        list = new JList(surveysItems);
        for (int iterator = 0; iterator < surveysList.length; iterator++) {
            surveysItems.addElement(surveysList[iterator] + "  " + applicationLogic.getSurveyTitle(surveysList[iterator]));
        }
        list.setBounds(40, 150, 220, 170);
        ListSelectionListener lsl = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectedSurveysList = list.getSelectedValuesList();
            }
        };
        this.add(list);
        list.addListSelectionListener(lsl);
        
        setSize(400, 420);
        setLocation(400,280);
        setResizable(false);
        this.setLayout(null);
        
        copyButton = new JButton("Zapisz");
        copyButton.setBounds(210, 330, 100, 40);
        this.add(copyButton);
        copyButton.addActionListener(this);
        
        cancelButton = new JButton("Anuluj");
        cancelButton.setBounds(90, 330, 100, 40);
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
            try {
                creatorFrame.addSurveyPanel(splited[0]);
            } catch (IOException ex) {
                Logger.getLogger(CopingSurveyFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CopingSurveyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                String idOfSurvey = applicationLogic.copySurvey(splited[0]);
                applicationLogic.setSurveyTitle(idOfSurvey, titleField.getText());
                applicationLogic.setSurveyDescription(idOfSurvey, descriptionField.getText());
                applicationLogic.setSurveySummary(idOfSurvey, summaryField.getText());
                creatorFrame.addSurveyPanel(idOfSurvey);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(CopingSurveyFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CopingSurveyFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CopingSurveyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            dispose();
        }
        
    }
    
}
