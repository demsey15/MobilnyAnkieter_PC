/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Delirus
 */
public class AddInterviewer extends JFrame implements ActionListener{
    
    private JTextField jname, jsurname;
    private JLabel nameLabel, surnameLabel;
    private Container addcon;
    private JButton anul, createinterv; 
    AddInterviewer(){
        super("Dodanie ankietera");
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
        setSize(800,600);
        setLocation(300,350);
        setResizable(false);
        jname = new JTextField();
        jsurname = new JTextField();
        nameLabel = new JLabel("Imiê: ");
        surnameLabel = new JLabel("Nazwisko: ");
        anul = new JButton("Anuluj");
        createinterv= new JButton("Dodaj ankietera");
                
        nameLabel.setBounds(350, 0, 50, 40);
        jname.setBounds(400, 0, 100, 40);
        surnameLabel.setBounds(320, 50, 75, 40);
        jsurname.setBounds(400, 50, 100, 40);
        anul.setBounds(100, 400, 100, 50);
        createinterv.setBounds(600, 400, 150, 50);
        
        JPanel inputPanel = new JPanel();
	inputPanel.setLayout(null);
	inputPanel.add(nameLabel);
	inputPanel.add(jname);
        inputPanel.add(surnameLabel);
	inputPanel.add(jsurname);
        inputPanel.add(anul);
        inputPanel.add(createinterv);
        
        addcon = this.getContentPane();
        addcon.add(inputPanel);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==anul){
            dispose();
            //System.exit(0);
        }
    }
}
