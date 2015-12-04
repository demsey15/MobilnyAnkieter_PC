/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.addquestionframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bohonos.demski.mieldzioc.mobilnyankieter.constraints.IConstraint;
import bohonos.demski.mieldzioc.mobilnyankieter.constraints.TextConstraint;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.CreatorFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.TextQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

/**
 *
 * @author Delirus
 */
public class AddTextQuestionFrame extends AddQuestionFrame {
    
    private final int NUMBER_FIELDS_WIDTH = 60;

    private int maxLength;
    private int minLength;
    
    private JLabel minLengthLabel, maxLengthLabel;
    private JTextField minLengthField, maxLengthField;
    
    public AddTextQuestionFrame(Survey survey, CreatorFrame crFrame) {
        
        super(survey, crFrame);
        
        this.setSize(600, 400);

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
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;

        addButton.setBounds(ADD_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

        cancelButton.setBounds(CANCEL_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

    }
    
    @Override
    protected Question createQuestion() {
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
        TextQuestion textQuestion = new TextQuestion(questionField.getText(), obligatoryBox.isSelected(), hintField.getText(), constraint);
        survey.addQuestion(textQuestion);
        creatorFrame.addTextQuestionPanel(textQuestion);
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
