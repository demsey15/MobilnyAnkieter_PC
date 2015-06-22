/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import bohonos.demski.mieldzioc.interviewer.Interviewer;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javafx.scene.control.CheckBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Delirus
 */
public class SetPrivileges extends JFrame implements ActionListener{
    //private MenagerInterviewersFrame menager;
    private Interviewer interviewer;
    private ApplicationLogic applicationLogic;
    private JButton save, anul;
    private Container addcon;
    private JCheckBox checkBox;
    
    public SetPrivileges(Interviewer interviewer){
       super("Uprawnienia");
        applicationLogic = ApplicationLogic.getInstance();
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
        this.interviewer=interviewer;
        //this.menager = menager;      
        setSize(800,600);
        setLocation(300,350);
        setResizable(false);
        
        checkBox = new JCheckBox("Mo¿liwoœæ tworzenia w³asnych ankiet");
        anul = new JButton("Anuluj");
        save= new JButton("Zapisz");
        
        anul.setBounds(100, 400, 100, 50);
        save.setBounds(600, 400, 150, 50);
        checkBox.setBounds(350,40,150,50);
        checkBox.setSelected(interviewer.getInterviewerPrivileges());
        
        JPanel inputPanel = new JPanel();
	inputPanel.setLayout(null);
        inputPanel.add(anul);
        inputPanel.add(save);
        inputPanel.add(checkBox);
        
        
        addcon = this.getContentPane();
        addcon.add(inputPanel);
        
        save.addActionListener(this);
        anul.addActionListener(this);
        
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       if(source == save){
           interviewer.setInterviewerPrivileges(checkBox.isSelected());
           
           dispose();
       }
       if(source == anul){
           dispose();
       }
    }
}
