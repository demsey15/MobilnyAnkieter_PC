/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.ScaleQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author Andrzej
 */
public class ScaleQuestionPanel extends QuestionPanel {
    
    public int HEIGHT = 70;
    private int currentXPosition = 30;
    
    private ScaleQuestion scaleQuestion;
    private int minValue, maxValue; 
    private String minLabel, maxLabel;
    
    private JLabel minValueLabel, maxValueLabel;
    private ButtonGroup buttonGroup;
    
    public ScaleQuestionPanel(Survey survey, ScaleQuestion scaleQuestion, SurveyPanel surveyPanel) throws IOException, ParseException {
        
        super(survey, scaleQuestion, surveyPanel);
        
        this.scaleQuestion = scaleQuestion;
        minValue = this.scaleQuestion.getMinValue();
        maxValue = this.scaleQuestion.getMaxValue();
        minLabel = this.scaleQuestion.getMinLabel();
        maxLabel = this.scaleQuestion.getMaxLabel();
        
        buttonGroup = new ButtonGroup();
        
        if (minLabel!=null && minLabel.equals("")==false) {
            minValueLabel = new JLabel(minLabel);
            int length = minLabel.length() * 10;
            minValueLabel.setBounds(currentXPosition, 35, length, 20);
            this.add(minValueLabel);
            currentXPosition = currentXPosition + length + 10;
        }
        else {
            currentXPosition = currentXPosition + 10;
        }
        
        for (int i=minValue; i<=maxValue; i++) {
            JRadioButton radioButton = new JRadioButton(Integer.toString(i));
            radioButton.setBounds(currentXPosition, 35, 40, 20);
            currentXPosition = currentXPosition + 50;
            buttonGroup.add(radioButton);
            radioButton.setEnabled(false);
            this.add(radioButton);
        }
        
        if (maxLabel!=null && maxLabel.equals("")==false) {
            maxValueLabel = new JLabel(maxLabel);
            int length = maxLabel.length() * 10;
            maxValueLabel.setBounds(currentXPosition, 35, length, 20);
            this.add(maxValueLabel);
            currentXPosition = currentXPosition + length + 10;
        }
        else {
            currentXPosition = currentXPosition + 10;
        }
        
    }
    
}
