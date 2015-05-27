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
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Delirus
 */
public class AddMultipleChoiceQuestionFrame extends AddQuestionFrame {
    
    private final int ANSWER_BUTTONS_X_POSITION = 450;
    private final int ANSWER_BUTTONS_WIDTH = 130;
    private final int ANSWER_BUTTONS_HEIGHT = 40;
    
    private List<String> answers;
    
    private JLabel answerLabel;
    
    public AddMultipleChoiceQuestionFrame(ApplicationLogic appLogic, CreatorFrame crFrame) {
        
        super(appLogic, crFrame);
        
        this.setSize(600, 500);
        
        answerLabel = new JLabel("mo¿liwe odpowiedzi: ");
        answerLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(answerLabel);
        
        addAnswerButton.setBounds(ANSWER_BUTTONS_X_POSITION, CURRENT_Y_POSITION, ANSWER_BUTTONS_WIDTH, ANSWER_BUTTONS_HEIGHT);
        this.add(addAnswerButton);
        
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;

        addButton.setBounds(ADD_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);

        cancelButton.setBounds(CANCEL_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        
    }
    
    @Override
    protected Boolean questionConditions() {
        return true;
    }

    @Override
    protected void addAnswer(String answer) {
        
    }
    
}
