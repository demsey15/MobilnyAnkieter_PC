/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

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

import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;

/**
 *
 * @author Delirus
 */
public class RelieveInterviewer extends JFrame implements ActionListener{
    private JFormattedTextField jdate;
    private JButton anul, save;
    private Interviewer interviewer;
    private JLabel rel, active;
    private Container addcon;
    
    public RelieveInterviewer(Interviewer interviewer, JLabel j){
        super("Pozbaw pracy ankietera");
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
        active = j;
        this.interviewer = interviewer;
        
        setSize(300,200);
        setLocation(400,400);
        setResizable(false);
        
        rel = new JLabel("Data zwolnienia: ");
        DateFormat format = new SimpleDateFormat("dd--MM--yyyy");
        jdate = new JFormattedTextField(format);
        anul = new JButton("Anuluj");
        save = new JButton("Zapisz");
        
        rel.setBounds(20, 10, 150, 40);
        jdate.setBounds(180, 10, 100, 40);
        anul.setBounds(20, 100, 100, 50);
        save.setBounds(180, 100, 100, 50);
        jdate.setValue(new Date());
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.add(rel);
        inputPanel.add(jdate);
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
            Date now;
            GregorianCalendar cal = new GregorianCalendar();
            now=(Date) jdate.getValue();
            cal.setTime(now);
            interviewer.setRelieveDay(cal);
            JOptionPane.showMessageDialog(this, "Zwolniono ankietera");
            active.setText("Ankieter jest nieaktywny");
            //System.out.println("dzien zwolnienia ankietera "+ interviewer.getRelieveDay().getTime());
            dispose();
        }
    }
    
}
