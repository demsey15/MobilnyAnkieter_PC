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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Andrzej
 */
public class CreatingSurveyFrame extends JFrame implements ActionListener {
    
    private ApplicationLogic applicationLogic;
    private CreatorFrame creatorFrame;
    private String idOfSurvey;
    private JButton saveButton, cancelButton;
    private JTextField titleField, descriptionField, summaryField;
    private JLabel titleLabel, descriptionLabel, summaryLabel;
    
    public CreatingSurveyFrame(CreatorFrame crFrame) throws IOException, ParseException {
        
        super("nowa ankieta");
        
        applicationLogic = ApplicationLogic.getInstance();
        creatorFrame = crFrame;
        //idOfSurvey = id;
        
        setSize(400, 300);
        setLocation(400,300);
        setResizable(false);
        this.setLayout(null);
        
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
        
        saveButton = new JButton("Zapisz");
        saveButton.setBounds(210, 200, 100, 40);
        this.add(saveButton);
        saveButton.addActionListener(this);
        
        cancelButton = new JButton("Anuluj");
        cancelButton.setBounds(90, 200, 100, 40);
        this.add(cancelButton);
        cancelButton.addActionListener(this);
        
        setVisible(true);
        
    }
    
    public JButton addActionListenerSave(ActionListener al) {
        saveButton.addActionListener(al);
        return saveButton;
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == saveButton) {
            if (titleField.getText().equals("")==false) {
                String idOfSurvey = applicationLogic.newSurvey();
                applicationLogic.setSurveyTitle(idOfSurvey, titleField.getText());
                applicationLogic.setSurveyDescription(idOfSurvey, descriptionField.getText());
                applicationLogic.setSurveySummary(idOfSurvey, summaryField.getText());
                try {
                    creatorFrame.addSurveyPanel(idOfSurvey);
                } catch (IOException ex) {
                    Logger.getLogger(CreatingSurveyFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(CreatingSurveyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            }
        }
        if (source == cancelButton) {
            dispose();
        }
    }
    
}