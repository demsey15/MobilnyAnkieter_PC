/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Delirus
 */
public class InterviewerFrame extends JFrame implements ActionListener {
    
    private JButton addinterv, listinterv, close;
    private JPanel panel;
    private Container con;
    private ApplicationLogic appsLogic;
    public InterviewerFrame(ApplicationLogic appsLogic){
        super("Menad¿er ankieterów");
        this.appsLogic=appsLogic;
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				//System.exit(0);
			}
		});
  
                setSize(800, 600);
		setLocation(200,250);
                setResizable(false);
                panel = new JPanel();   
                addinterv = new JButton("Dodaj ankietera");
                listinterv = new JButton("Lista ankieterów");
                close = new JButton("Zamknij");
		con = this.getContentPane();
                con.add(panel);
            
               
                addinterv.setBounds(getWidth()/2 - 100, getHeight()/2 - 200, 200, 50);
                listinterv.setBounds(300, 225, 200, 50);
                close.setBounds(300, 350, 200, 50);
                panel.setLayout(null); 
                panel.add(addinterv); 
                panel.add(listinterv);
                panel.add(close);
               
                addinterv.addActionListener(this);
                listinterv.addActionListener(this);
                close.addActionListener(this);
               
                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
 
                
    }
    
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==close){
            dispose();
            //System.exit(0);
        }
        if(source == addinterv){
            EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
                            AddInterviewer addinterviewer= new AddInterviewer(appsLogic);
                        }
		});
        }
    }
}
