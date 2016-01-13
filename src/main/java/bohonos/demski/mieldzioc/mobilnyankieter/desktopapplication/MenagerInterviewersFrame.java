/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Delirus
 */
public class MenagerInterviewersFrame extends JFrame implements ActionListener{
    private ApplicationLogic applicationLogic;
    private int interviewerPanelPosition = 0;
    private JPanel panel;
    private Container con;
    private JButton close;
    private List<Interviewer> allInterviewers;
   // private JScrollPane listScroller;
    //private JList list;
    private List<String> selectedInterviewer;
    private JPanel interviewersPanel;
    private JScrollPane scrollPane;
    private JMenu menuInterviewers;
    
    private JMenuItem itemAddInterviewer, itemlistWorksInterviewers;
    //private JMenuItem itemRelieveInterviewer;
    //private JMenuItem itemEditingInterviewer; 

    
    private CloseButtonTabbedPane tabbedPane;
    
    /*  public DefaultListModel getNamesInterviewers(){
        DefaultListModel listModel = new DefaultListModel();
      for(int i =0; i<allInterviewers.size();i++){
          Object text = allInterviewers.get(i).getName() +" "+ allInterviewers.get(i).getSurname() +" "+ allInterviewers.get(i).getId();
          listModel.addElement(text);
          //listModel.addElement("John Smith");
      }
      return listModel;
  }*/
    
    public MenagerInterviewersFrame() throws IOException, ParseException{
        super("Menad¿er ankieterów");
        applicationLogic = ApplicationLogic.getInstance();
        allInterviewers=applicationLogic.getInterviewers();
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
        
        setSize(650,500);
        setLocation(200,150);
        setResizable(false);
        
        //menuInterviewers = new JMenu("Ankieterzy");
        
        itemAddInterviewer = new JMenuItem("Dodaj ankietera");
        //itemlistWorksInterviewers = new JMenuItem("Pracuj¹cy ankieterzy");
        //itemRelieveInterviewer = new JMenuItem("Zwolnij ankietera");
        //itemEditingInterviewer = new JMenuItem("Edytuj ankietera");
        
        //menuInterviewers.add(itemAddInterviewer);
        //menuInterviewers.add(itemlistWorksInterviewers);  
        //menuInterviewers.add(itemEditingInterviewer);
        
        itemAddInterviewer.addActionListener(this);
        //itemlistWorksInterviewers.addActionListener(this);
        //itemEditingInterviewer.addActionListener(this);
        
        
        JMenuBar menuBar = new JMenuBar();
        
        menuBar.add(itemAddInterviewer);
        setJMenuBar(menuBar);
        
        //tabbedPane = new CloseButtonTabbedPane();
        //add(tabbedPane);   
        
        
        /*panel = new JPanel();
        close = new JButton("Zamknij");
        con = this.getContentPane();
        con.add(panel);
                
        close.setBounds(300, 500, 200, 50);
        panel.setLayout(null); 
        panel.add(close);
        
        close.addActionListener(this);
        
        /*list = new JList(getNamesInterviewers());
        list.setSelectedIndex(1);
        list.setBounds(40, 10, 40, 170);
        ListSelectionListener lsl = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectedInterviewer = list.getSelectedValuesList();
            }
        };
        this.add(list);
        listScroller = new JScrollPane();
        listScroller.setPreferredSize(new Dimension(250, 80));
        listScroller.setSize(100, 200);
        //System.out.println(list.getSelectedValue());
        con.add(listScroller);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        
        interviewersPanel = new JPanel();
        interviewersPanel.setLayout(null);
        scrollPane = new JScrollPane(interviewersPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 50, 800, 500);
        
        this.add(scrollPane);
	setVisible(true);
        refreshViewOfInterviewers();
        
    }

    public void refreshViewOfInterviewers(){
        interviewerPanelPosition = 0;
        interviewersPanel.removeAll();
        //List<Interviewer> listInterviewers = applicationLogic.getInterviewers();       
        for(Interviewer interviewer : applicationLogic.getInterviewers()){
            InterviewerPanel iPanel = new InterviewerPanel(interviewer, this);
            iPanel.setBounds(0, interviewerPanelPosition, 780, 70);
            interviewerPanelPosition+=70;
            interviewersPanel.setPreferredSize(new Dimension(780,interviewerPanelPosition));
            interviewersPanel.add(iPanel);           
        }
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==close){
            dispose();
            //System.exit(0);
        }
        if (source == itemAddInterviewer) {
            try {
                //EventQueue.invokeLater(new Runnable() {
                //	@Override
                //	public void run() {
                AddInterviewer addInterviewer = new AddInterviewer(this);
                //  }
                //});
                //refreshViewOfInterviewers();
            } catch (IOException ex) {
                Logger.getLogger(MenagerInterviewersFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(MenagerInterviewersFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(source == itemlistWorksInterviewers){
            try {
                WorksInterviewers worksInterviewers = new WorksInterviewers();
            } catch (IOException ex) {
                Logger.getLogger(MenagerInterviewersFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(MenagerInterviewersFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
    }
}
