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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    AddInterviewer(MenagerInterviewersFrame menager) throws IOException, ParseException{
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
        setSize(400,360);
        setLocation(300,150);
        setResizable(false);
        jname = new JTextField();
        jsurname = new JTextField();
        nameLabel = new JLabel("Imiê: ");
        surnameLabel = new JLabel("Nazwisko: ");
        jid = new JTextField();
        idLabel = new JLabel("ID: ");
        DateFormat format = new SimpleDateFormat("dd--MM--yyyy");
        jdate = new JFormattedTextField(format);
        dateLabel = new JLabel("Data dodania: ");
        anul = new JButton("Anuluj");
        createinterv= new JButton("Dodaj ankietera");
             
        nameLabel.setBounds(100, 30, 50, 30);
        jname.setBounds(150, 30, 150, 30);
        surnameLabel.setBounds(70, 80, 75, 30);
        jsurname.setBounds(150, 80, 150, 30);
        jid.setBounds(150, 130, 150, 30);
        idLabel.setBounds(110, 130, 40, 30);
        jdate.setBounds(150, 180, 150, 30);
        dateLabel.setBounds(50, 180, 125, 30);
        anul.setBounds(30, 260, 150, 50);
        createinterv.setBounds(220, 260, 150, 50);
        
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
            Interviewer newinterv;
            try {
                newinterv = new Interviewer(jname.getText(),jsurname.getText(),myid, cal);
                boolean addInterviewer = applicationLogic.addInterviewer(newinterv);
            } catch (ParseException ex) {
                Logger.getLogger(AddInterviewer.class.getName()).log(Level.SEVERE, null, ex);
            }
            //to bêdzie musia³o byæ gdzie indziej, jeden obiekt tylko tej klasy zostanie stworzony
            //if(applicationLogic.addInterviewer(newinterv))
            //    JOptionPane.showMessageDialog(this, "Dodano ankietera");
            //else
            //    JOptionPane.showMessageDialog(this, "Nie dodano ankietera");
            
            menager.refreshViewOfInterviewers();
            dispose();
        }
        
    }
}
