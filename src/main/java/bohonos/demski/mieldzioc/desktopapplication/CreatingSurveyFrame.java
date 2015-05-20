/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private String idOfSurvey;
    private JButton saveButton;
    private JTextField titleField, descriptionField;
    private JLabel titleLabel, descriptionLabel;
    
    public CreatingSurveyFrame(ApplicationLogic appLogic, String id) {
        
        super("nowa ankieta");
        
        applicationLogic = appLogic;
        idOfSurvey = id;
        
        setSize(300, 300);
        setLocation(400,300);
        setResizable(false);
        this.setLayout(null);
        
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
        
        saveButton = new JButton("Zapisz");
        saveButton.setBounds(100, 200, 100, 40);
        this.add(saveButton);
        saveButton.addActionListener(this);
        
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
                //to do
                dispose();
            }
        }
    }
    
}