/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

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

import bohonos.demski.mieldzioc.mobilnyankieter.networkConnection.ServerConnectionFacade;
import bohonos.demski.mieldzioc.mobilnyankieter.statistics.*;

/**
 *
 * @author Delirus
 */
public class GraphicInterface extends JFrame implements ActionListener{

    private JButton interv, creator, close, surveyButton, statisticsButton;
    private JPanel panel;
   // private GridLayout gridLayout ;
    private Container con;
    private final ApplicationLogic applicationLogic;
    private ServerConnectionFacade connectionFacade;
    
    public GraphicInterface(ServerConnectionFacade connectionFacade){
        
        super("Bezpieczny ankieter");
        this.connectionFacade = connectionFacade;
        applicationLogic = ApplicationLogic.getInstance();
        applicationLogic.setConnectionFacade(this.connectionFacade);
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				System.exit(0);
			}
		});
                
                //gridLayout = new GridLayout(5,1,20,10);
             
                //setLayout(gridLayout);
                setSize(600, 600);
		setLocation(100,80);
                setResizable(false);
                panel = new JPanel();   
                interv = new JButton("Zarz¹dzanie ankieterami");
                creator = new JButton("Kreator ankiet");
                close = new JButton("Zamknij");
                surveyButton = new JButton("Zarz¹dzanie ankietami");
                statisticsButton = new JButton("Wyniki ankiet");
		con = this.getContentPane();
                //con.setLayout(new BorderLayout());
                con.add(panel);

                creator.setBounds(200, 75, 200, 50);
                surveyButton.setBounds(200, 175, 200, 50);
                interv.setBounds(200, 275, 200, 50);
                statisticsButton.setBounds(200, 375, 200, 50);
                close.setBounds(200, 475, 200, 50);
                
                panel.setLayout(null); 
                 
                panel.add(creator);
                panel.add(surveyButton);
                panel.add(statisticsButton);
                panel.add(interv);
                panel.add(close);
               
                creator.addActionListener(this);
                surveyButton.addActionListener(this);
                statisticsButton.addActionListener(this);
                interv.addActionListener(this);
                close.addActionListener(this);
               
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
                //pack();
                
    }

  //  @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==close){
            dispose();
            System.exit(0);
        }
        if(source == interv){
            EventQueue.invokeLater(new Runnable() {
		//	@Override
			public void run() {
                            MenagerInterviewersFrame menageinterv= new MenagerInterviewersFrame();
                        }
		});
        }
        if(source == creator){
            EventQueue.invokeLater(new Runnable() {
              //  @Override
                public void run() {
                    CreatorFrame creator = new CreatorFrame();
                }
            });
        }
        if (source == surveyButton) {
            EventQueue.invokeLater(new Runnable() {
            //    @Override
                public void run() {
                    SurveyMenagerFrame surveyMenagerFrame = new SurveyMenagerFrame();
                }
            });
        }
        if(source == statisticsButton){
            EventQueue.invokeLater(new Runnable() {
              //  @Override
                public void run() {
                    StatisticsFrame statiticsFrame = new StatisticsFrame(applicationLogic);
                }
            });
        }
    }

}
