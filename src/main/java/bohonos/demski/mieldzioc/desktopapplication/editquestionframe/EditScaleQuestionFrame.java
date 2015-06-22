/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication.editquestionframe;

import bohonos.demski.mieldzioc.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.desktopapplication.CreatorFrame;
import bohonos.demski.mieldzioc.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.questions.ScaleQuestion;
import bohonos.demski.mieldzioc.survey.Survey;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Delirus
 */
public class EditScaleQuestionFrame extends EditQuestionFrame {
    
    private final int NUMBER_FIELDS_WIDTH = 60;
    private final int TEXT_FIELDS_WIDTH = 300;
    
    private int minValue, maxValue;
    private String minLabel, maxLabel;
    private ScaleQuestion scaleQuestion;
    
    private JLabel minLabelLabel, maxLabelLabel, minValueLabel, maxValueLabel;
    private JTextField minLabelField, maxLabelField, minValueField, maxValueField;
    
    public EditScaleQuestionFrame(ScaleQuestion scaleQuestion, SurveyPanel surveyPanel) {
        
        super(scaleQuestion, surveyPanel);
        this.scaleQuestion = scaleQuestion;
        
        this.setSize(600, 500);
        
        questionField.setText(question.getQuestion());
        hintField.setText(question.getHint());
        errorMessageField.setText(question.getErrorMessage());
        
        minLabelLabel = new JLabel("etylieta wartoœci minimalnej: ");
        minLabelLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(minLabelLabel);

        minLabelField = new JTextField();
        minLabelField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, TEXT_FIELDS_WIDTH, FIELDS_HEIGHT);
        minLabelField.setText(scaleQuestion.getMinLabel());
        this.add(minLabelField);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        minValueLabel = new JLabel("wartoœæ minimalna: ");
        minValueLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(minValueLabel);

        minValueField = new JTextField();
        minValueField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, NUMBER_FIELDS_WIDTH, FIELDS_HEIGHT);
        minValueField.setText(Integer.toString(scaleQuestion.getMinValue()));
        this.add(minValueField);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        maxLabelLabel = new JLabel("etykieta wartoœci maksymalnej: ");
        maxLabelLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(maxLabelLabel);

        maxLabelField = new JTextField();
        maxLabelField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, TEXT_FIELDS_WIDTH, FIELDS_HEIGHT);
        maxLabelField.setText(scaleQuestion.getMaxLabel());
        this.add(maxLabelField);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        maxValueLabel = new JLabel("wartoœci maksymalna: ");
        maxValueLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(maxValueLabel);

        maxValueField = new JTextField();
        maxValueField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, NUMBER_FIELDS_WIDTH, FIELDS_HEIGHT);
        maxValueField.setText(Integer.toString(scaleQuestion.getMaxValue()));
        this.add(maxValueField);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        saveButton.setBounds(ADD_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

        cancelButton.setBounds(CANCEL_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        
    }
    
    @Override
    protected Question saveQuestion() {
        question.setQuestion(questionField.getText());
        question.setHint(hintField.getText());
        question.setErrorMessage(errorMessageField.getText());
        scaleQuestion.setMinLabel(minLabelField.getText());
        scaleQuestion.setMaxLabel(maxLabelField.getText());
        scaleQuestion.setMinValue(Integer.valueOf(minValueField.getText()));
        scaleQuestion.setMaxValue(Integer.valueOf(maxValueField.getText()));
        surveyPanel.refreshQuestionList();
        return null;
    }
    
    @Override
    protected Boolean questionConditions() {
        if (questionField.getText().equals("")==false && minValueField.getText().equals("")==false && maxValueField.getText().equals("")==false) {
            return true;
        }
        else {
            return false;
        }
    }

}
