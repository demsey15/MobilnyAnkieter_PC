/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.editquestionframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.CreatorFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

/**
 *
 * @author Delirus
 */
public class EditDateTimeQuestionFrame extends EditQuestionFrame {
    
    private Boolean onlyDate;
    private Boolean onlyTime;
    private DateTimeQuestion dateTimeQuestion;
    
    private JCheckBox onlyDateBox;
    private JCheckBox onlyTimeBox;
    
    public EditDateTimeQuestionFrame(DateTimeQuestion dateTimeQuestion, SurveyPanel surveyPanel) {
        
        super(dateTimeQuestion, surveyPanel);
        this.dateTimeQuestion = dateTimeQuestion;
        
        this.setSize(600, 400);
        
        questionField.setText(question.getQuestion());
        hintField.setText(dateTimeQuestion.getHint());
        errorMessageField.setText(dateTimeQuestion.getErrorMessage());
        
        onlyDateBox = new JCheckBox("tylko data");
        onlyDateBox.setSelected(dateTimeQuestion.isOnlyDate());
        onlyDateBox.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(onlyDateBox);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        onlyTimeBox = new JCheckBox("tylko czas");
        onlyTimeBox.setSelected(dateTimeQuestion.isOnlyTime());
        onlyTimeBox.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(onlyTimeBox);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        saveButton.setBounds(ADD_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

        cancelButton.setBounds(CANCEL_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        
    }
    
    @Override
    protected Question saveQuestion() {
        question.setQuestion(questionField.getText());
        question.setHint(hintField.getText());
        question.setErrorMessage(errorMessageField.getText());
        question.setObligatory(obligatoryBox.isSelected());
        dateTimeQuestion.setOnlyDate(onlyDateBox.isSelected());
        dateTimeQuestion.setOnlyTime(onlyTimeBox.isSelected());
        surveyPanel.refreshQuestionList();
        return null;
    }

    
    @Override
    protected Boolean questionConditions() {
        if (questionField.getText().equals("")==false) {
            return true;
        }
        else {
            return false;
        }
    }
    
}
