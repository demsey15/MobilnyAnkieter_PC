/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication.addquestionframe;

import bohonos.demski.mieldzioc.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.desktopapplication.CreatorFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
    
    public AddTextQuestionFrame(ApplicationLogic appLogic, CreatorFrame crFrame) {
        
        super(appLogic, crFrame);
        
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
    protected Boolean questionConditions() {
        return true;
    }

}
