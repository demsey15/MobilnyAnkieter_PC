/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bohonos.demski.mieldzioc.mobilnyankieter.serialization.jsonserialization.JsonSurveySerializator;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.SurveyHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrzej
 */
public class SurveyMenagerPanel extends JPanel implements ActionListener {

    public static final int HEIGHT = 50;
    public static final int WIDTH = 780;
    
    private ApplicationLogic applicationLogic;
    private Survey survey;
    private SurveyMenagerFrame surveyMenagerFrame;
    private String statusString;
    private int status;
    
    private JLabel idLabel, titleLabel, descriptionLabel, statusLabel;
    private JButton activeButton, disactiveButton;
    
    public SurveyMenagerPanel (Survey survey, SurveyMenagerFrame surveyMenagerFrame) throws IOException, ParseException {
        
        applicationLogic = ApplicationLogic.getInstance();
        this.survey = survey;
        this.surveyMenagerFrame = surveyMenagerFrame;
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        idLabel = new JLabel(survey.getIdOfSurveys());
        idLabel.setBounds(5, 15, 130, 20);
        this.add(idLabel);
        
        titleLabel = new JLabel(survey.getTitle());
        titleLabel.setBounds(140, 15, 160, 20);
        this.add(titleLabel);
        
        descriptionLabel = new JLabel(survey.getDescription());
        descriptionLabel.setBounds(305, 15, 250, 20);
        this.add(descriptionLabel);
        
        status = applicationLogic.getSurveyHandler().getSurveyStatus(survey);
        switch (status) {
            case SurveyHandler.IN_PROGRESS : statusString = "w przygotowaniu";
                                             break;
            case SurveyHandler.ACTIVE : statusString = "aktywna";
                                        break;
            case SurveyHandler.INACTIVE : statusString = "nieaktywna";
                                          break;
        }
        
        statusLabel = new JLabel(statusString);
        statusLabel.setBounds(560, 15, 120, 20);
        this.add(statusLabel);
        
        activeButton = new JButton("Aktywuj");
        activeButton.setBounds(685, 10, 85, 30);
        activeButton.addActionListener(this);
        
        disactiveButton = new JButton("Zawieœ");
        disactiveButton.setBounds(685, 10, 85, 30);
        disactiveButton.addActionListener(this);
        
        switch (status) {
            case SurveyHandler.IN_PROGRESS : this.add(activeButton);
                                             break;
            case SurveyHandler.ACTIVE : this.add(disactiveButton);
                                        break;
            case SurveyHandler.INACTIVE : this.add(activeButton);
                                          break;
        }
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        
        Object source = ae.getSource();
        
        if (source == activeButton) {
            applicationLogic.getSurveyHandler().setSurveyStatus(survey, SurveyHandler.ACTIVE);
            this.remove(activeButton);
            this.add(disactiveButton);
            statusLabel.setText("aktywna");
            System.out.println("ankieta: " + (new JsonSurveySerializator()).serializeSurvey(survey));
            //TODO udostêpnienie ankiety
            String templatePath = "C:" + File.separator + "ankieter" + File.separator + "activeTemplates" + File.separator + survey.getIdOfSurveys() + ".json";
            PrintWriter writer;
            try {
                writer = new PrintWriter(templatePath, "UTF-8");
                writer.println((new JsonSurveySerializator()).serializeSurvey(survey));
                writer.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SurveyMenagerPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SurveyMenagerPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            SwingUtilities.updateComponentTreeUI(this);
        }
        
        if (source == disactiveButton) {
            applicationLogic.getSurveyHandler().setSurveyStatus(survey, SurveyHandler.INACTIVE);
            this.remove(disactiveButton);
            this.add(activeButton);
            statusLabel.setText("nieaktywna");
            SwingUtilities.updateComponentTreeUI(this);
        }
    }
    
}
