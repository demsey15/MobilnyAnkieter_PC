/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import bohonos.demski.mieldzioc.interviewer.Interviewer;
import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

/**
 *
 * @author Delirus
 */
public class ListInterviewersFrame extends JFrame implements ActionListener{
    private ApplicationLogic appsLogic;
    private JPanel panel;
    private Container con;
    private JButton close;
    private List<Interviewer> allInterviewers;
    private JScrollPane listScroller;
    private JList list;
    
      public DefaultListModel getNamesInterviewers(){
        DefaultListModel listModel = new DefaultListModel();
      for(int i =0; i<allInterviewers.size();i++){
          Object text = allInterviewers.get(i).getName() +" "+ allInterviewers.get(i).getSurname() +" "+ allInterviewers.get(i).getId();
          listModel.addElement(text);
          //listModel.addElement("John Smith");
      }
      return listModel;
  }
    
    public ListInterviewersFrame(ApplicationLogic appsLogic){
        super("Lista ankieterów");
        this.appsLogic=appsLogic;
        allInterviewers=appsLogic.getInterviewers();
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
        
        panel = new JPanel();
        close = new JButton("Zamknij");
        con = this.getContentPane();
        con.add(panel);
                
        close.setBounds(300, 500, 200, 50);
        panel.setLayout(null); 
        panel.add(close);
        
        close.addActionListener(this);
        
        list = new JList(getNamesInterviewers());
        list.setSelectedIndex(1);
        listScroller = new JScrollPane(list);
        //listScroller.setPreferredSize(new Dimension(250, 80));
        listScroller.setSize(100, 200);
        //System.out.println(list.getSelectedValue());
        con.add(listScroller);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==close){
            dispose();
            //System.exit(0);
        }
    }
}
