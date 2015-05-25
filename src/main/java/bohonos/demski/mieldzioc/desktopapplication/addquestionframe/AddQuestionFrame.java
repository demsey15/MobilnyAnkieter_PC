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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
/**
 *
 * @author Andrzej
 */
public class AddQuestionFrame extends JFrame implements ActionListener {
    
    protected Boolean obligatory;
    protected String question = "";
    protected String pictureUrl = "";
    protected String hint = "";
    protected String errorMessage = "";
    
    protected ApplicationLogic applicationLogic;
    protected CreatorFrame creatorFrame;
    protected JButton addButton, cancelButton;
    protected JTextField questionField, hintField, errorMessageField;
    protected JLabel questionLabel, hintLabel, errorMessageLabel;
    protected ButtonGroup buttonGroup;
    protected JRadioButton obligatoryButton;
    
    public AddQuestionFrame(ApplicationLogic appLogic, CreatorFrame crFrame) {
        super("Dodaj pytanie");
        
        applicationLogic = appLogic;
        creatorFrame = crFrame;
        
        setSize(500, 300);
        setLocation(400,300);
        setResizable(false);
        this.setLayout(null);
        
        questionLabel = new JLabel("pytanie: ");
        questionLabel.setBounds(20, 10, 170, 25);
        this.add(questionLabel);
         
        questionField = new JTextField();
        questionField.setBounds(180, 10, 300, 25);
        this.add(questionField);
        
        hintLabel = new JLabel("wskazówka: ");
        hintLabel.setBounds(20, 50, 170, 25);
        this.add(hintLabel);

        hintField = new JTextField();
        hintField.setBounds(180, 50, 300, 25);
        this.add(hintField);
        
        errorMessageLabel = new JLabel("informacja o b³êdzie: ");
        errorMessageLabel.setBounds(20, 90, 170, 25);
        this.add(errorMessageLabel);

        errorMessageField = new JTextField();
        errorMessageField.setBounds(180, 90, 300, 25);
        this.add(errorMessageField);
        
        addButton = new JButton("Dodaj");
        addButton.setBounds(260, 200, 120, 40);
        this.add(addButton);
        addButton.addActionListener(this);
        
        cancelButton = new JButton("Anuluj");
        cancelButton.setBounds(100, 200, 120, 40);
        this.add(cancelButton);
        cancelButton.addActionListener(this);
        
        setVisible(true);
    }
    
    /**
     * checks, if all conditions are satisfied (has to be overwrite)
     * @return true, iff conditions are satisfied
     */
    protected Boolean questionConditions() {
        return true;
    }

    public void actionPerformed(ActionEvent ae) {
        
        Object source = ae.getSource();
        
        if (source==cancelButton) {
            dispose();
        }
        
        if (source==addButton) {
            if (questionConditions()==true) {
                // to do
                dispose();
            }
        }
    }
    
}
