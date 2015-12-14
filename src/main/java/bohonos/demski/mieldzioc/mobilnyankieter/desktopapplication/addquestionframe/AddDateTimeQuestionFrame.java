/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.addquestionframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.CreatorFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Delirus
 */
public class AddDateTimeQuestionFrame extends AddQuestionFrame {
    
    private Boolean onlyDate;
    private Boolean onlyTime;
    
    private JCheckBox onlyDateBox;
    private JCheckBox onlyTimeBox;
    
    public AddDateTimeQuestionFrame(Survey survey, CreatorFrame crFrame) throws IOException, ParseException {
        
        super(survey, crFrame);
        
        this.setSize(600, 400);
        
        onlyDateBox = new JCheckBox("tylko data");
        onlyDateBox.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(onlyDateBox);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        onlyTimeBox = new JCheckBox("tylko czas");
        onlyTimeBox.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(onlyTimeBox);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        addButton.setBounds(ADD_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

        cancelButton.setBounds(CANCEL_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        
    }
    
    @Override
    protected Question createQuestion() {
        DateTimeQuestion dateTimeQuestion = new DateTimeQuestion(questionField.getText(), obligatoryBox.isSelected(), hintField.getText(), onlyTimeBox.isSelected(), onlyDateBox.isSelected());
        survey.addQuestion(dateTimeQuestion);
        try {
            creatorFrame.addDateTimeQuestionPanel(dateTimeQuestion);
        } catch (IOException ex) {
            Logger.getLogger(AddDateTimeQuestionFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddDateTimeQuestionFrame.class.getName()).log(Level.SEVERE, null, ex);
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
