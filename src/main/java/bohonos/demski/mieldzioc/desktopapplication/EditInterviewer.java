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

/**
 *
 * @author Delirus
 */
public class EditInterviewer extends JFrame implements ActionListener{
    private Interviewer interviewer;
    private JTextField jname, jsurname, jid;
    private JFormattedTextField jdate;
    private JLabel nameLabel, surnameLabel, idLabel, dateLabel;
    private Container addcon;
    private JButton anul, editinterv;
    
    public EditInterviewer(Interviewer interviewer){
        super("Edycja ankietera");
        //applicationLogic = ApplicationLogic.getInstance();
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
        jname = new JTextField(interviewer.getName());
        jsurname = new JTextField(interviewer.getSurname());
        nameLabel = new JLabel("Imiê: ");
        surnameLabel = new JLabel("Nazwisko: ");
        jid = new JTextField(interviewer.getId());
        idLabel = new JLabel("ID: ");
        DateFormat format = new SimpleDateFormat("dd--MM--yyyy");
        jdate = new JFormattedTextField(format);
        dateLabel = new JLabel("Data zatrudnienia: ");
        anul = new JButton("Anuluj");
        editinterv= new JButton("Edytuj ankietera");
             
        nameLabel.setBounds(350, 0, 50, 40);
        jname.setBounds(400, 0, 100, 40);
        surnameLabel.setBounds(320, 50, 75, 40);
        jsurname.setBounds(400, 50, 100, 40);
        jid.setBounds(400, 100, 100, 40);
        idLabel.setBounds(360, 100, 40, 40);
        jdate.setBounds(400, 150, 100, 50);
        dateLabel.setBounds(275, 150, 125, 50);
        anul.setBounds(100, 400, 100, 50);
        editinterv.setBounds(600, 400, 150, 50);
        
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
        inputPanel.add(editinterv);
        
        addcon = this.getContentPane();
        addcon.add(inputPanel);
        
        editinterv.addActionListener(this);
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
        if(source == editinterv){
            //JOptionPane optionPane;
            Date now;
            GregorianCalendar cal = new GregorianCalendar();
            now=(Date) jdate.getValue();
            cal.setTime(now);
            //System.out.println("data: "+cal.getTime().getTime());
            String myid = jid.getText();
            interviewer.editeName(jname.getText());
            interviewer.editeSurname(jsurname.getText());
            interviewer.editeId(myid);
            interviewer.editeHireDay(cal);               
            JOptionPane.showMessageDialog(this, "Zedytowano ankietera");
           
                
            dispose();
        }
    }
}