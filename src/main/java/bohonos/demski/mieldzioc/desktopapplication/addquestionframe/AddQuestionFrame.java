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
        
        setSize(300, 300);
        setLocation(400,300);
        setResizable(false);
        this.setLayout(null);
        
        addButton = new JButton("Dodaj");
        addButton.setBounds(160, 200, 100, 40);
        this.add(addButton);
        addButton.addActionListener(this);
        
        cancelButton = new JButton("Anuluj");
        cancelButton.setBounds(40, 200, 100, 40);
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
                
            }
        }
    }
    
}
