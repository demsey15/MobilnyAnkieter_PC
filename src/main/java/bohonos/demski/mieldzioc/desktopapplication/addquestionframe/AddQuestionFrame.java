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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Andrzej
 */
public class AddQuestionFrame extends JFrame implements ActionListener {
    
    protected final int LABELS_WIDTH = 200;
    protected final int LABELS_HEIGHT = 25;
    protected final int LABELS_X_POSITION = 20;
    protected final int FIELDS_WIDTH = 300;
    protected final int FIELDS_HEIGHT = 25;
    protected final int FIELDS_X_POSITION = 220;
    protected final int SPACE_HEIGHT = 15;
    protected final int CANCEL_BUTTON_X_POSITION = 140;
    protected final int ADD_BUTTON_X_POSITION = 300;
    protected final int BUTTON_WIDTH = 120;
    protected final int BUTTON_HEIGHT = 40;
    
    protected int CURRENT_Y_POSITION = 10;
    
    protected Boolean obligatory;
    protected String question = "";
    protected String pictureUrl = "";
    protected String hint = "";
    protected String errorMessage = "";
    
    protected ApplicationLogic applicationLogic;
    protected CreatorFrame creatorFrame;
    protected JButton addButton, cancelButton, addAnswerButton, deleteAnswerButton;
    protected JTextField questionField, hintField, errorMessageField;
    protected JLabel questionLabel, hintLabel, errorMessageLabel;
    protected JCheckBox obligatoryBox;
    
    public AddQuestionFrame(ApplicationLogic appLogic, CreatorFrame crFrame) {
        super("Dodaj pytanie");
        
        applicationLogic = appLogic;
        creatorFrame = crFrame;
        
        setSize(600, 300);
        setLocation(400,300);
        setResizable(false);
        this.setLayout(null);
        
        questionLabel = new JLabel("pytanie: ");
        questionLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(questionLabel);
         
        questionField = new JTextField();
        questionField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(questionField);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        hintLabel = new JLabel("wskazówka: ");
        hintLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(hintLabel);

        hintField = new JTextField();
        hintField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(hintField);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        errorMessageLabel = new JLabel("informacja o b³êdzie: ");
        errorMessageLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(errorMessageLabel);

        errorMessageField = new JTextField();
        errorMessageField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(errorMessageField);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        obligatoryBox = new JCheckBox("pytanie obowi¹zkowe");
        obligatoryBox.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(obligatoryBox);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        addButton = new JButton("Dodaj");
        addButton.setBounds(ADD_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(addButton);
        addButton.addActionListener(this);
        
        cancelButton = new JButton("Anuluj");
        cancelButton.setBounds(CANCEL_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(cancelButton);
        cancelButton.addActionListener(this);
        
        addAnswerButton = new JButton("Dodaj odpowiedŸ");
        addAnswerButton.addActionListener(this);
        
        deleteAnswerButton = new JButton("Usuñ odpowiedŸ");
        deleteAnswerButton.addActionListener(this);
        
        setVisible(true);
    }
    
    /**
     * checks, if all conditions are satisfied (has to be overwriten)
     * @return true, iff conditions are satisfied
     */
    protected Boolean questionConditions() {
        return true;
    }
    
    /**
     * adds answer to MultipleChoiceQuestion and OneChoiceQuestion (has to be overwriten)
     * @param answer answer to add
     */
    protected void addAnswer(String answer) {
        
    }
    
    /**
     * removes chosen answer from the list (has to be overwriten)
     */
    protected void deleteAnswer() {
        
    }

    public void actionPerformed(ActionEvent ae) {
        
        Object source = ae.getSource();
        
        if (source == cancelButton) {
            dispose();
        }
        
        if (source == addButton) {
            if (questionConditions()==true) {
                // to do
                dispose();
            }
        }
        
        if (source == addAnswerButton) {
            AddAnswerFrame addAnswerFrame = new AddAnswerFrame(applicationLogic, this);
        }
        
        if (source == deleteAnswerButton) {
            this.deleteAnswer();
        }
    }
    
}
