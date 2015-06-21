/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 *
 * @author Delirus
 */
public class GraphicInterface extends JFrame implements ActionListener{

    private JButton interv, creator, close;
    private JPanel panel;
   // private GridLayout gridLayout ;
    private Container con;
    private final ApplicationLogic applicationLogic;
    
    public GraphicInterface(){
        super("Bezpieczny ankieter");
        applicationLogic = ApplicationLogic.getInstance();
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				System.exit(0);
			}
		});
                
                //gridLayout = new GridLayout(5,1,20,10);
             
                //setLayout(gridLayout);
                setSize(800, 600);
		setLocation(100,150);
                setResizable(false);
                panel = new JPanel();   
                interv = new JButton("Zarz¹dzanie ankieterami");
                creator = new JButton("Kreator ankiet");
                close = new JButton("Zamknij");
		con = this.getContentPane();
                //con.setLayout(new BorderLayout());
                con.add(panel);
                //add(interv);
                //add(creator);
                //add(close);
               
                interv.setBounds(getWidth()/2 - 100, getHeight()/2 - 200, 200, 50);
                creator.setBounds(300, 225, 200, 50);
                close.setBounds(300, 350, 200, 50);
                panel.setLayout(null); 
                panel.add(interv); 
                panel.add(creator);
                panel.add(close);
               
                interv.addActionListener(this);
                creator.addActionListener(this);
                close.addActionListener(this);
               
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
                //pack();
                
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==close){
            dispose();
            System.exit(0);
        }
        if(source == interv){
            EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
                            MenagerInterviewersFrame menageinterv= new MenagerInterviewersFrame();
                        }
		});
        }
        if(source == creator){
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    CreatorFrame creator = new CreatorFrame();
                }
            });
        }
    }

}
