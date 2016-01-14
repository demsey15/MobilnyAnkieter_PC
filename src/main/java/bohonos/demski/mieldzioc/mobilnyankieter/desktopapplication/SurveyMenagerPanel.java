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
import files.FileProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
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
    private JButton activeButton, disactiveButton, saveToFileButton;
    private CreatorFrame creatorFrame;
    
    public SurveyMenagerPanel (Survey survey, SurveyMenagerFrame surveyMenagerFrame, CreatorFrame creatorFrame) throws IOException, ParseException {
        
        applicationLogic = ApplicationLogic.getInstance();
        this.survey = survey;
        this.surveyMenagerFrame = surveyMenagerFrame;
        this.creatorFrame = creatorFrame;
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        idLabel = new JLabel(survey.getIdOfSurveys());
        idLabel.setBounds(5, 15, 130, 20);
        this.add(idLabel);
        
        titleLabel = new JLabel(survey.getTitle());
        titleLabel.setBounds(140, 15, 150, 20);
        this.add(titleLabel);
        
        descriptionLabel = new JLabel(survey.getDescription());
        descriptionLabel.setBounds(295, 15, 200, 20);
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
        statusLabel.setBounds(500, 15, 120, 20);
        this.add(statusLabel);
        
        activeButton = new JButton("Aktywuj");
        activeButton.setBounds(625, 10, 120, 30);
        activeButton.addActionListener(this);
        
        disactiveButton = new JButton("Zawieœ");
        disactiveButton.setBounds(625, 10, 120, 30);
        disactiveButton.addActionListener(this);
        
        saveToFileButton = new JButton("Zapisz do pliku");
        saveToFileButton.setBounds(625, 10, 120, 30);
        saveToFileButton.addActionListener(this);
        
        switch (status) {
            case SurveyHandler.IN_PROGRESS : this.add(activeButton);
                                             break;
            case SurveyHandler.ACTIVE : this.add(saveToFileButton);
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
            this.add(saveToFileButton);
            statusLabel.setText("aktywna");
            System.out.println("ankieta: " + (new JsonSurveySerializator()).serializeSurvey(survey));
            //TODO udostêpnienie ankiety
            GregorianCalendar date = new GregorianCalendar();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SSS");
            fmt.setCalendar(date);
            String dateFormatted = fmt.format(date.getTime());
            String templatePath = "C:" + File.separator + "ankieter" + File.separator + "activeTemplates" + File.separator + dateFormatted + " " + survey.getTitle() + ".json";
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
            try {
                creatorFrame.disableSurveyEdition(survey.getIdOfSurveys());
            } catch (IOException ex) {
                Logger.getLogger(SurveyMenagerPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(SurveyMenagerPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (source == disactiveButton) {
            applicationLogic.getSurveyHandler().setSurveyStatus(survey, SurveyHandler.INACTIVE);
            this.remove(disactiveButton);
            this.add(activeButton);
            statusLabel.setText("nieaktywna");
            SwingUtilities.updateComponentTreeUI(this);
        }
        
        if (source == saveToFileButton) {
            FileProvider fileProvider = new FileProvider();
            fileProvider.makeHtml(survey);
        }
    }
    
}
