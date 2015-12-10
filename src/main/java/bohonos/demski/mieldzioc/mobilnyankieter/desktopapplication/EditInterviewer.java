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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Delirus
 */
public class EditInterviewer extends JFrame implements ActionListener{
    private Interviewer interviewer;
    private JTextField jname, jsurname, jid;
    //private JFormattedTextField jdate;
    private JLabel nameLabel, surnameLabel, idLabel, opis; //dateLabel, active
    private Container addcon;
    private JButton anul, editinterv, dodajMac, usunMac;// setOutWork, relieve, restore;
    private MenagerInterviewersFrame menager;
    private JList devices;//workOutTime;
    private DefaultListModel listModel;
    private List<String> selectedMacs;
    private ArrayList<String> newListOfMacs;
    
    public EditInterviewer(Interviewer interviewer, MenagerInterviewersFrame menager) throws CloneNotSupportedException{
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
        newListOfMacs = new ArrayList<String>();
        setSize(800,600);
        setLocation(300,350);
        setResizable(false);
        jname = new JTextField(interviewer.getName());
        jsurname = new JTextField(interviewer.getSurname());
        nameLabel = new JLabel("Imiê: ");
        surnameLabel = new JLabel("Nazwisko: ");
        jid = new JTextField(interviewer.getId());
        idLabel = new JLabel("ID: ");
        //active = new JLabel();
        opis = new JLabel("Lista urz¹dzeñ: ");
        DateFormat format = new SimpleDateFormat("dd--MM--yyyy");
        //jdate = new JFormattedTextField(format);
        //dateLabel = new JLabel("Data zatrudnienia: ");
        anul = new JButton("Anuluj");
        editinterv= new JButton("Zapisz");
        dodajMac = new JButton("Dodaj urz¹dzenie");
        usunMac = new JButton("Usuñ urz¹dzenie");
        
        listModel = new DefaultListModel();
        devices = new JList(listModel);
        
        
        nameLabel.setBounds(450, 0, 50, 40);
        jname.setBounds(500, 0, 100, 40);
        surnameLabel.setBounds(420, 50, 75, 40);
        jsurname.setBounds(500, 50, 100, 40);
        jid.setBounds(500, 100, 100, 40);
        idLabel.setBounds(460, 100, 40, 40);
        anul.setBounds(100, 500, 100, 50);
        editinterv.setBounds(600, 500, 150, 50);
        dodajMac.setBounds(350, 200, 150, 50);
        usunMac.setBounds(350, 350, 150, 50);;
        devices.setBounds(50, 150, 250, 300);
        opis.setBounds(50, 100, 200, 50);
        
        ListSelectionListener listListener = new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                selectedMacs = devices.getSelectedValuesList();
            }
        };
        
        JPanel inputPanel = new JPanel();
	inputPanel.setLayout(null);
	inputPanel.add(nameLabel);
	inputPanel.add(jname);
        inputPanel.add(surnameLabel);
	inputPanel.add(jsurname);
        inputPanel.add(jid);
        inputPanel.add(idLabel);

        inputPanel.add(anul);
        inputPanel.add(editinterv);
        inputPanel.add(dodajMac);
        inputPanel.add(usunMac);
        inputPanel.add(devices);
        inputPanel.add(opis);
        
        addcon = this.getContentPane();
        addcon.add(inputPanel);
        
        editinterv.addActionListener(this);
        anul.addActionListener(this);
        dodajMac.addActionListener(this);
        usunMac.addActionListener(this);
        devices.addListSelectionListener(listListener);
        for( String device : interviewer.getMacAdresses()){
            listModel.addElement(device);
            newListOfMacs.add(device);
        }
	setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==anul){
            dispose();
            //System.exit(0);
        }
        if(source == editinterv){
            
            String myid = jid.getText();
         
            interviewer.editeName(jname.getText());
            interviewer.editeSurname(jsurname.getText());
            interviewer.editeId(myid);
            interviewer.getMacAdresses().clear();
            interviewer.setMacAdresses(newListOfMacs);
            System.out.println("Imiê zedytowanego ankietera: " + interviewer.getName());
            //interviewer.editeHireDay(cal);               
            //JOptionPane.showMessageDialog(this, "Zedytowano ankietera");
            System.out.println(interviewer.interviewerToString());
            try {
                System.out.println("po konwersji: " + (Interviewer.stringToInterviewer(interviewer.interviewerToString())).interviewerToString());
            } catch (ParseException ex) {
                Logger.getLogger(EditInterviewer.class.getName()).log(Level.SEVERE, null, ex);
            }
            menager.refreshViewOfInterviewers();
            dispose();
        }
        
        if(source == dodajMac){
            AddMac addMac = new AddMac(newListOfMacs, listModel);
        }
        
        if(source == usunMac){
            newListOfMacs.remove(selectedMacs.get(0));
            listModel.removeElement(selectedMacs.get(0));
        }
        

    }
}