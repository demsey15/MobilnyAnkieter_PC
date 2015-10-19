/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;

/**
 *
 * @author Delirus
 */
public class WorksInterviewers extends JFrame implements ActionListener{

    private ApplicationLogic applicationLogic;
    private JList jlist;
    private DefaultListModel listModel;
    private JPanel panel;
    private JScrollPane scrollPane;
    
    public WorksInterviewers(){
        super("Lista aktywnych ankieterów");
        applicationLogic = ApplicationLogic.getInstance(); 
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
        listModel = new DefaultListModel();
        for(Interviewer interviewer : applicationLogic.getInterviewers()){
            if(interviewer.isActive()){
                listModel.addElement(interviewer.getName()+" "+ interviewer.getSurname()+ " " +interviewer.getId());
            }
        }
        jlist=new JList(listModel);
        //panel = new JPanel();
        //panel.setLayout(null);
        setSize(400,600);
        setLocation(350,350);
        setResizable(false);
        
        scrollPane = new JScrollPane(jlist);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 50, 800, 500);
        
        this.add(scrollPane);
	setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
