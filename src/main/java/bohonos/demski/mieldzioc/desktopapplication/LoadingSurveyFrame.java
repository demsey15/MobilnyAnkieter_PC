/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Andrzej
 */
public class LoadingSurveyFrame extends JFrame implements ActionListener{
    private JButton chooseButton, cancelButton;
    private ApplicationLogic applicationLogic;
    private CreatorFrame creatorFrame;
    
    public LoadingSurveyFrame(ApplicationLogic applicationLogic, CreatorFrame creatorFrame){
        super("Edycja ankiety");
        this.applicationLogic = applicationLogic;
        this.creatorFrame = creatorFrame;
        
        setSize(300, 300);
        setLocation(400,300);
        setResizable(false);
        this.setLayout(null);
        
        chooseButton = new JButton("Zapisz");
        chooseButton.setBounds(160, 200, 100, 40);
        this.add(chooseButton);
        chooseButton.addActionListener(this);
        
        cancelButton = new JButton("Anuluj");
        cancelButton.setBounds(40, 200, 100, 40);
        this.add(cancelButton);
        cancelButton.addActionListener(this);
        
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == cancelButton) {
            dispose();
        }
    }
    
}
