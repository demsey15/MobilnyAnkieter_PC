/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import bohonos.demski.mieldzioc.common.Pair;
import bohonos.demski.mieldzioc.interviewer.Interviewer;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Delirus
 */
public class RestoreInterviewer extends JFrame implements ActionListener{

    private JFormattedTextField jdate;
    private JButton anul, save;
    private Interviewer interviewer;
    private JLabel rel, active;
    private Container addcon;
    //private JList workOutTime;
    private DefaultListModel listModel;
    
    
     public RestoreInterviewer(Interviewer interviewer, JLabel j, DefaultListModel listModel){
        super("Przywróc do pracy ankietera");
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
        this.active = j;
        this.interviewer = interviewer;
        this.listModel = listModel;
        //this.workOutTime = workOutTime;
        setSize(300,200);
        setLocation(400,400);
        setResizable(false);
        
        rel = new JLabel("Data przywrócenia: ");
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
            if(cal.compareTo(interviewer.getRelieveDay())>=0){
                interviewer.setOutOfWorkTime(interviewer.getRelieveDay(), cal);
                int y1 = interviewer.getRelieveDay().get(Calendar.YEAR);//.toString();
                int m1 = interviewer.getRelieveDay().get(Calendar.MONTH);
                int d1 = interviewer.getRelieveDay().get(Calendar.DAY_OF_MONTH);
                int y2 = cal.get(Calendar.YEAR);
                int m2 = cal.get(Calendar.MONTH);
                int d2 = cal.get(Calendar.DAY_OF_MONTH);
                String s = Integer.toString(d1) +"."+ Integer.toString(m1+1)+"."+Integer.toString(y1)+ " - "+Integer.toString(d2) +"."+ Integer.toString(m2+1)+"."+Integer.toString(y2);
                interviewer.setRelieveDay(null);
                JOptionPane.showMessageDialog(this, "Przywrócono ankietera");
                active.setText("Ankieter jest aktywny");  
                listModel.addElement(s);
                //JList newJlist = new JList(getTimeOutWork(interviewer));
                //workOutTime = newJlist;
                //SwingUtilities.updateComponentTreeUI(this);
                //System.out.println("dzien zwolnienia ankietera "+ interviewer.getRelieveDay().getTime());
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(this, "Data nieprawid³owa");
            }
        }
    }
    
}
