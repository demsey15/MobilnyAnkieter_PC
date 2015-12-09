/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

//import java.awt.BorderLayout;
import java.awt.Container;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.*;
import java.io.IOException;

/**
 *
 * @author Delirus
 */
public class AddInterviewer extends JFrame implements ActionListener{
    
    private JTextField jname, jsurname, jid;
    private JFormattedTextField jdate;
    private JLabel nameLabel, surnameLabel, idLabel, dateLabel;
    private Container addcon;
    private JButton anul, createinterv; 
    private ApplicationLogic applicationLogic;
    private MenagerInterviewersFrame menager;
    //private InterviewersRepository interrep = new InterviewersRepository(); //tymczasowo 
    AddInterviewer(MenagerInterviewersFrame menager) throws IOException{
        super("Dodanie ankietera");
        applicationLogic = ApplicationLogic.getInstance();
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
        this.menager = menager;
        setSize(800,600);
        setLocation(300,350);
        setResizable(false);
        jname = new JTextField();
        jsurname = new JTextField();
        nameLabel = new JLabel("Imiê: ");
        surnameLabel = new JLabel("Nazwisko: ");
        jid = new JTextField();
        idLabel = new JLabel("ID: ");
        DateFormat format = new SimpleDateFormat("dd--MM--yyyy");
        jdate = new JFormattedTextField(format);
        dateLabel = new JLabel("Data zatrudnienia: ");
        anul = new JButton("Anuluj");
        createinterv= new JButton("Dodaj ankietera");
             
        nameLabel.setBounds(350, 0, 50, 40);
        jname.setBounds(400, 0, 100, 40);
        surnameLabel.setBounds(320, 50, 75, 40);
        jsurname.setBounds(400, 50, 100, 40);
        jid.setBounds(400, 100, 100, 40);
        idLabel.setBounds(360, 100, 40, 40);
        jdate.setBounds(400, 150, 100, 50);
        dateLabel.setBounds(275, 150, 125, 50);
        anul.setBounds(100, 400, 100, 50);
        createinterv.setBounds(600, 400, 150, 50);
        
        //GregorianCalendar now = new GregorianCalendar();
        jdate.setValue(new Date());
        
        JPanel inputPanel = new JPanel();
	inputPanel.setLayout(null);
	inputPanel.add(nameLabel);
	inputPanel.add(jname);
        inputPanel.add(surnameLabel);
	inputPanel.add(jsurname);
        inputPanel.add(jid);
        inputPanel.add(idLabel);
        inputPanel.add(jdate);
        inputPanel.add(dateLabel);
        inputPanel.add(anul);
        inputPanel.add(createinterv);
        
        addcon = this.getContentPane();
        addcon.add(inputPanel);
        
        createinterv.addActionListener(this);
        anul.addActionListener(this);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==anul){
            dispose();
            //System.exit(0);
        }
        if(source == createinterv){
            //JOptionPane optionPane;
            Date now = new Date();
            GregorianCalendar cal = new GregorianCalendar();
            now=(Date) jdate.getValue();
            cal.setTime(now);
            //System.out.println("data: "+cal.getTime().getTime());
            String myid = jid.getText();
            Interviewer newinterv = new Interviewer(jname.getText(),jsurname.getText(),myid, cal);
             //to bêdzie musia³o byæ gdzie indziej, jeden obiekt tylko tej klasy zostanie stworzony
            if(applicationLogic.addInterviewer(newinterv))
                JOptionPane.showMessageDialog(this, "Dodano ankietera");
            else
                JOptionPane.showMessageDialog(this, "Nie dodano ankietera");
            menager.refreshViewOfInterviewers();
            dispose();
        }
        
    }
}
