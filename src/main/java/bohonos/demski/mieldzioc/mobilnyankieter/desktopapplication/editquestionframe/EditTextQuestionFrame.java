/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.editquestionframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bohonos.demski.mieldzioc.mobilnyankieter.constraints.IConstraint;
import bohonos.demski.mieldzioc.mobilnyankieter.constraints.TextConstraint;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.CreatorFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.TextQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Delirus
 */
public class EditTextQuestionFrame extends EditQuestionFrame {
    
    private final int NUMBER_FIELDS_WIDTH = 60;

    private int maxLength;
    private int minLength;
    private TextQuestion textQuestion;
    private TextConstraint constraint;
    
    private JLabel minLengthLabel, maxLengthLabel;
    private JTextField minLengthField, maxLengthField;
    
    public EditTextQuestionFrame(TextQuestion textQuestion, SurveyPanel surveyPanel) throws IOException, ParseException {
        
        super(textQuestion, surveyPanel);
        
        this.setSize(600, 400);
        
        questionField.setText(question.getQuestion());
        hintField.setText(question.getHint());
        //errorMessageField.setText("");

        this.textQuestion = textQuestion;
        
        minLengthLabel = new JLabel("minimalna d³ugoœæ odpowiedzi: ");
        minLengthLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(minLengthLabel);

        minLengthField = new JTextField();
        minLengthField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, NUMBER_FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(minLengthField);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        maxLengthLabel = new JLabel("maksymalna d³ugoœæ odpowiedzi: ");
        maxLengthLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(maxLengthLabel);

        maxLengthField = new JTextField();
        maxLengthField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, NUMBER_FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(maxLengthField);
        
        
        if (textQuestion.getConstraint() != null) {
            constraint = (TextConstraint)textQuestion.getConstraint();
            if (constraint.getMinLength() != null) {
                minLength = constraint.getMinLength();
                if (minLength != 0) {
                    minLengthField.setText(Integer.toString(minLength));
                }
            }
            if (constraint.getMaxLength() != null) {
                maxLength = constraint.getMaxLength();
                if (maxLength != 0) {
                    maxLengthField.setText(Integer.toString(maxLength));
                }
            }
        }
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;

        saveButton.setBounds(ADD_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

        cancelButton.setBounds(CANCEL_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

    }
    
    @Override
    protected Question saveQuestion() {
        TextConstraint constraint = null;
        int min, max;
        if (minLengthField.getText().equals("")) {
            if (maxLengthField.getText().equals("")) {
                constraint = new TextConstraint(null, null, null);
            }
            else {
                constraint = new TextConstraint(null, Integer.valueOf(maxLengthField.getText()), null);
            }
        }
        else {
            if (maxLengthField.getText().equals("")) {
                constraint = new TextConstraint(Integer.valueOf(minLengthField.getText()), null, null);
            }
            else {
                constraint = new TextConstraint(Integer.valueOf(minLengthField.getText()), Integer.valueOf(maxLengthField.getText()), null);
            }
        }
        question.setQuestion(questionField.getText());
        question.setHint(hintField.getText());
        //question.setErrorMessage(errorMessageField.getText());
        question.setObligatory(obligatoryBox.isSelected());
        textQuestion.setConstraint(constraint);
        try {
            surveyPanel.refreshQuestionList();
        } catch (IOException ex) {
            Logger.getLogger(EditTextQuestionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
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
