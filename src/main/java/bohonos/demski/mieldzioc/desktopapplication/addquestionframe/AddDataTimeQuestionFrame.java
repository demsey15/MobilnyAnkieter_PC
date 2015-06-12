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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Delirus
 */
public class AddDataTimeQuestionFrame extends AddQuestionFrame {
    
    private Boolean onlyDate;
    private Boolean onlyTime;
    
    private JCheckBox onlyDateBox;
    private JCheckBox onlyTimeBox;
    
    public AddDataTimeQuestionFrame(ApplicationLogic appLogic, CreatorFrame crFrame) {
        
        super(appLogic, crFrame);
        
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
    protected Boolean questionConditions() {
        return true;
    }
    
}
