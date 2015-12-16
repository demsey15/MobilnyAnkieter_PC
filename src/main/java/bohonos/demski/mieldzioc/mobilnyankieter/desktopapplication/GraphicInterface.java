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

import bohonos.demski.mieldzioc.mobilnyankieter.statistics.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Delirus
 */
public class GraphicInterface extends JFrame implements ActionListener{

    private JButton interv, creator, statisticsButton;
    private JPanel panel;
   // private GridLayout gridLayout ;
    private Container con;
    private final ApplicationLogic applicationLogic;
    
    public GraphicInterface() throws IOException, ParseException{
        
        super("Bezpieczny ankieter");
        applicationLogic = ApplicationLogic.getInstance();
        addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
                            System.out.println("Zamykamy aplikacjê.");
                            try {
                                applicationLogic.getInterviewersRepository().saveRepository();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (UnsupportedEncodingException ex) {
                                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                applicationLogic.getSurveyHandler().saveSurveyTemplates();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (UnsupportedEncodingException ex) {
                                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                            }
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
                statisticsButton = new JButton("Wyniki ankiet");
		con = this.getContentPane();
                //con.setLayout(new BorderLayout());
                con.add(panel);

                creator.setBounds(200, 75, 200, 50);
                interv.setBounds(200, 175, 200, 50);
                statisticsButton.setBounds(200, 275, 200, 50);
                
                panel.setLayout(null); 
                 
                panel.add(creator);
                panel.add(statisticsButton);
                panel.add(interv);
               
                creator.addActionListener(this);;
                statisticsButton.addActionListener(this);
                interv.addActionListener(this);
               
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
                //pack();
                
    }

  //  @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == interv){
            EventQueue.invokeLater(new Runnable() {
		//	@Override
			public void run() {
                            try {
                                MenagerInterviewersFrame menageinterv= new MenagerInterviewersFrame();
                            } catch (IOException ex) {
                                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
		});
        }
        if(source == creator){
            EventQueue.invokeLater(new Runnable() {
              //  @Override
                public void run() {
                    try {
                        CreatorFrame creator = new CreatorFrame();
                    } catch (IOException ex) {
                        Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(GraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
