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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bohonos.demski.mieldzioc.mobilnyankieter.common.Pair;
import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;

/**
 *
 * @author Delirus
 */
public class OutOfWorkInterviewer extends JFrame implements ActionListener{

    private JFormattedTextField jdate1, jdate2;
    private JButton anul, save;
    private Interviewer interviewer;
    private JLabel first, second;
    private Container addcon;
    private DefaultListModel listModel;
    
    public OutOfWorkInterviewer(Interviewer interviewer, DefaultListModel listModel){
        super("Ustaw okres przerwy w zatrudnieniu");
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});

        this.interviewer = interviewer;
        this.listModel = listModel;
        //this.workOutTime = workOutTime;
        setSize(400,250);
        setLocation(400,300);
        setResizable(false);
        
        first = new JLabel("Pocz¹tek: ");
        second = new JLabel("Koniec: ");
        DateFormat format = new SimpleDateFormat("dd--MM--yyyy");
        jdate1 = new JFormattedTextField(format);
        jdate2 = new JFormattedTextField(format);
        anul = new JButton("Anuluj");
        save = new JButton("Dodaj");
        
        first.setBounds(20, 10, 150, 40);
        second.setBounds(20, 80, 150, 40);
        jdate1.setBounds(180, 10, 100, 40);
        jdate2.setBounds(180, 80, 100, 40);
        anul.setBounds(20, 150, 100, 50);
        save.setBounds(180, 150, 100, 50);
        
        jdate1.setValue(new Date());
        jdate2.setValue(new Date());
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.add(first);
        inputPanel.add(second);
        inputPanel.add(jdate1);
        inputPanel.add(jdate2);
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
            Date now1;
            GregorianCalendar cal1 = new GregorianCalendar();
            now1=(Date) jdate1.getValue();
            cal1.setTime(now1);
            Date now2;
            GregorianCalendar cal2 = new GregorianCalendar();
            now2=(Date) jdate2.getValue();
            cal2.setTime(now2);
            boolean go = true;
            if(cal1.compareTo(cal2)<=0){
                for(Pair<GregorianCalendar,GregorianCalendar> pair : interviewer.getOutOfWorkTime()){
                    if(cal1.compareTo(pair.getSecond())<=0 && pair.getFirst().compareTo(cal2)>=0){
                        go=true;
                    }
                    else{
                        go=false;
                        break;
                    }
                }
                if(go){
                    interviewer.setOutOfWorkTime(cal1, cal2);
                    int y1 = cal1.get(Calendar.YEAR);
                    int m1 = cal1.get(Calendar.MONTH);
                    int d1 = cal1.get(Calendar.DAY_OF_MONTH);
                    int y2 = cal2.get(Calendar.YEAR);
                    int m2 = cal2.get(Calendar.MONTH);
                    int d2 = cal2.get(Calendar.DAY_OF_MONTH);
                    String s = Integer.toString(d1) +"."+ Integer.toString(m1+1)+"."+Integer.toString(y1)+ " - "+Integer.toString(d2) +"."+ Integer.toString(m2+1)+"."+Integer.toString(y2);
                    listModel.addElement(s);
                    JOptionPane.showMessageDialog(this, "Dodano okres");
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Podano b³êdny okres okres");
                }
            }
        }
    }
    
}
