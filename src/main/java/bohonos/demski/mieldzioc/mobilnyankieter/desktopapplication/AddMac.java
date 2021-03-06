/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import bohonos.demski.mieldzioc.mobilnyankieter.common.Pair;
import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author Delirus
 */
public class AddMac extends JFrame implements ActionListener{

    //private JFormattedTextField jdate;
    private final JButton anul, save;
    private final ArrayList<String> newMacs;
    private final JLabel adress;
    private final Container addcon;
    private final JTextField newMac;
    //private JList workOutTime;
    private final DefaultListModel listModel;
    
    
     public AddMac(ArrayList<String> newMacs, DefaultListModel listModel){
        super("Dodaj adres Mac");
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
    
        this.newMacs = newMacs;
        this.listModel = listModel;
        //this.workOutTime = workOutTime;
        setSize(300,200);
        setLocation(400,400);
        setResizable(false);
        
        adress = new JLabel("Adres Mac:");
        //DateFormat format = new SimpleDateFormat("dd--MM--yyyy");
        //jdate = new JFormattedTextField(format);
        anul = new JButton("Anuluj");
        save = new JButton("Zapisz");
        newMac = new JTextField();
        //rel.setBounds(20, 10, 150, 40);
        //jdate.setBounds(180, 10, 100, 40);
        adress.setBounds(20, 20, 100, 40);
        anul.setBounds(20, 100, 100, 50);
        save.setBounds(180, 100, 100, 50);
        newMac.setBounds(140, 20, 140, 40);
        //jdate.setValue(new Date());
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        //inputPanel.add(rel);
        //inputPanel.add(jdate);
        inputPanel.add(adress);
        inputPanel.add(newMac);
        inputPanel.add(anul);
        inputPanel.add(save);
        
        addcon = this.getContentPane();
        addcon.add(inputPanel);
        
        anul.addActionListener(this);
        save.addActionListener(this);
        setVisible(true);
     }
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source==anul){
            dispose();
        }
        if(source==save){
            String a = new String();
            a = newMac.getText();
            newMacs.add(a);
            listModel.addElement(a);
            dispose();
        }
    }
    
}
