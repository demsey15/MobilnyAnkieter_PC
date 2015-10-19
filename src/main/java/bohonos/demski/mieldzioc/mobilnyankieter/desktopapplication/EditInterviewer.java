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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import bohonos.demski.mieldzioc.mobilnyankieter.common.Pair;
import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;
import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.InterviewerSurveyPrivileges;

/**
 *
 * @author Delirus
 */
public class EditInterviewer extends JFrame implements ActionListener{
    private Interviewer interviewer;
    private JTextField jname, jsurname, jid;
    private JFormattedTextField jdate;
    private JLabel nameLabel, surnameLabel, idLabel, dateLabel, active, opis;
    private Container addcon;
    private JButton anul, editinterv, setOutWork, relieve, restore;
    private MenagerInterviewersFrame menager;
    private JList workOutTime;
    private DefaultListModel listModel;
    
    public EditInterviewer(Interviewer interviewer, MenagerInterviewersFrame menager){
        super("Edycja ankietera");
        //applicationLogic = ApplicationLogic.getInstance();
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
        this.interviewer = interviewer;
        this.menager = menager;
        setSize(800,600);
        setLocation(300,350);
        setResizable(false);
        jname = new JTextField(interviewer.getName());
        jsurname = new JTextField(interviewer.getSurname());
        nameLabel = new JLabel("Imiê: ");
        surnameLabel = new JLabel("Nazwisko: ");
        jid = new JTextField(interviewer.getId());
        idLabel = new JLabel("ID: ");
        active = new JLabel();
        if(interviewer.isActive()){
            active = new JLabel("Ankieter jest aktywny");
        }
        else{
            active = new JLabel("Ankieter jest nieaktywny");
        }
        opis = new JLabel("Czas niepracowania ankietera");
        DateFormat format = new SimpleDateFormat("dd--MM--yyyy");
        jdate = new JFormattedTextField(format);
        dateLabel = new JLabel("Data zatrudnienia: ");
        anul = new JButton("Anuluj");
        editinterv= new JButton("Zapisz");
        relieve = new JButton("Zwolnij");
        restore = new JButton("Przywróæ do pracy");
        setOutWork = new JButton("Przerwy w zatrudnieniu");
        
        listModel = new DefaultListModel();
        getTimeOutWork(interviewer);
        workOutTime = new JList(listModel);
        
        nameLabel.setBounds(450, 0, 50, 40);
        jname.setBounds(500, 0, 100, 40);
        surnameLabel.setBounds(420, 50, 75, 40);
        jsurname.setBounds(500, 50, 100, 40);
        jid.setBounds(500, 100, 100, 40);
        idLabel.setBounds(460, 100, 40, 40);
        jdate.setBounds(500, 150, 100, 50);
        dateLabel.setBounds(375, 150, 125, 50);
        anul.setBounds(100, 500, 100, 50);
        editinterv.setBounds(600, 500, 150, 50);
        relieve.setBounds(350, 350, 150, 50);
        restore.setBounds(550, 350, 150, 50);
        setOutWork.setBounds(425, 250, 200, 50);
        workOutTime.setBounds(50, 150, 250, 300);
        //GregorianCalendar now = new GregorianCalendar();
        active.setBounds(50, 25, 150, 50);
        opis.setBounds(50, 100, 200, 50);
       // int y1 = interviewer.getHiredDay().get(Calendar.YEAR);
        ////int m1 = interviewer.getHiredDay().get(Calendar.MONTH);
        //int d1 = interviewer.getHiredDay().get(Calendar.DAY_OF_MONTH);
        //System.out.println(d1+" "+m1+" "+y1);
        jdate.setValue(interviewer.getHiredDay().getTime());
        
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
        inputPanel.add(relieve);
        inputPanel.add(restore);
        inputPanel.add(setOutWork);
        inputPanel.add(workOutTime);
        inputPanel.add(active);
        inputPanel.add(opis);
        
        addcon = this.getContentPane();
        addcon.add(inputPanel);
        
        editinterv.addActionListener(this);
        anul.addActionListener(this);
        relieve.addActionListener(this);
        restore.addActionListener(this);
        setOutWork.addActionListener(this);
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
    }
    
    public void getTimeOutWork(Interviewer interviewer){
        String[] lista = new String[interviewer.getOutOfWorkTime().size()];
        //int i=0;
        for(Pair<GregorianCalendar,GregorianCalendar> pair : interviewer.getOutOfWorkTime())
        {
            int y1 = pair.getFirst().get(Calendar.YEAR);//.toString();
            int m1 = pair.getFirst().get(Calendar.MONTH);
            int d1 = pair.getFirst().get(Calendar.DAY_OF_MONTH);
            int y2 = pair.getSecond().get(Calendar.YEAR);
            int m2 = pair.getSecond().get(Calendar.MONTH);
            int d2 = pair.getSecond().get(Calendar.DAY_OF_MONTH);
            String s = Integer.toString(d1) +"."+ Integer.toString(m1+1)+"."+Integer.toString(y1)+ " - "+Integer.toString(d2) +"."+ Integer.toString(m2+1)+"."+Integer.toString(y2);
            listModel.addElement(s);
            //lista[i]=s;
            //i++;
        }
        //return lista;
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
           // System.out.println(jname.getText());
           // System.out.println(jsurname.getText());
            //System.out.println(jid.getText());
           // System.out.println(jdate.getText());
         
            interviewer.editeName(jname.getText());
            interviewer.editeSurname(jsurname.getText());
            interviewer.editeId(myid);
            interviewer.editeHireDay(cal);               
            JOptionPane.showMessageDialog(this, "Zedytowano ankietera");
           
            menager.refreshViewOfInterviewers();
            dispose();
        }
        
        if(source == relieve){
            if(interviewer.isActive()){
                RelieveInterviewer rel = new RelieveInterviewer(interviewer, active);
            }
        }
        
        if(source == restore)
        {
            if(!interviewer.isActive()){
                RestoreInterviewer rel = new RestoreInterviewer(interviewer, active, listModel);
            }
        }
        
        if(source == setOutWork){
            OutOfWorkInterviewer outOfWorkInterviewer = new OutOfWorkInterviewer(interviewer, listModel);
        }
    }
}