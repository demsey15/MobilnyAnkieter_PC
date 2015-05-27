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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Andrzej
 */
public class AddAnswerFrame extends JFrame implements ActionListener {
    
    private final int CANCEL_BUTTON_X_POSITION = 60;
    private final int ADD_BUTTON_X_POSITION = 240;
    private final int CANCEL_BUTTON_Y_POSITION = 110;
    private final int ADD_BUTTON_Y_POSITION = 110;
    private final int BUTTON_WIDTH = 120;
    private final int BUTTON_HEIGHT = 40;
    private final int LABEL_X_POSITION = 20;
    private final int LABEL_Y_POSITION = 40;
    private final int LABEL_WIDTH = 60;
    private final int LABEL_HEIGHT = 25;
    private final int FIELD_X_POSITION = 100;
    private final int FIELD_Y_POSITION = 40;
    private final int FIELD_WIDTH = 280;
    private final int FIELD_HEIGHT = 25;
    
    private String answer;
    
    private ApplicationLogic applicationLogic;
    private AddQuestionFrame addQuestionFrame;
    
    private JButton addButton, cancelButton;
    private JTextField answerField;
    private JLabel answerLabel;
    
    
    public AddAnswerFrame(ApplicationLogic appLogic, AddQuestionFrame addQuestFrame) {
        
        super("Dodaj odpowiedü");
        
        applicationLogic = appLogic;
        addQuestionFrame = addQuestFrame;
        
        setSize(400, 200);
        setLocation(600,400);
        setResizable(false);
        this.setLayout(null);
        
        answerLabel = new JLabel("pytanie: ");
        answerLabel.setBounds(LABEL_X_POSITION, LABEL_Y_POSITION, LABEL_WIDTH, LABEL_HEIGHT);
        this.add(answerLabel);
         
        answerField = new JTextField();
        answerField.setBounds(FIELD_X_POSITION, FIELD_Y_POSITION, FIELD_WIDTH, FIELD_HEIGHT);
        this.add(answerField);
        
        addButton = new JButton("Dodaj");
        addButton.setBounds(ADD_BUTTON_X_POSITION, ADD_BUTTON_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(addButton);
        addButton.addActionListener(this);
        
        cancelButton = new JButton("Anuluj");
        cancelButton.setBounds(CANCEL_BUTTON_X_POSITION, CANCEL_BUTTON_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(cancelButton);
        cancelButton.addActionListener(this);
        
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        
        Object source = ae.getSource();
        
        if (source == cancelButton) {
            dispose();
        }
        
        if (source == addButton) {
            //to do
            dispose();
        }
        
    }
    

}
