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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
/**
 *
 * @author Andrzej
 */
public class CreatorFrame extends JFrame implements ActionListener {
    
    public CreatorFrame(){
        super("Kreator ankiet");
        /*addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we){
		dispose();
				//System.exit(0);
            }
	});*/
        
        setSize(800, 600);
	setLocation(300,200);
        setResizable(false);
        
        JMenu menuSurvey = new JMenu("Ankieta");
        
        JMenuItem itemNewSurvey = new JMenuItem("Stwórz now¹ ankietê");
        JMenuItem itemCopyOldSurvey = new JMenuItem("Swórz now¹ na podstawie...");
        JMenuItem itemEditSurvey = new JMenuItem("Edytuj ankietê"); 
        
        menuSurvey.add(itemNewSurvey);
        menuSurvey.add(itemCopyOldSurvey);
        menuSurvey.add(itemEditSurvey);
        
        JMenu menuQuestion = new JMenu("Dodaj Pytanie");

        JMenuItem itemDataTimeQuestion = new JMenuItem("Pytanie o datê");
        JMenuItem itemGridQuestion = new JMenuItem("Pytanie typu GrigQuestion");
        JMenuItem itemMultipleChioceQuestion = new JMenuItem("Pytanie wielokrotnego wyboru");
        JMenuItem itemOneChoiceQuestion = new JMenuItem("Pytanie jednokrotnego wyboru");
        JMenuItem itemScaleQuestion = new JMenuItem("Pytanie ze skal¹");
        JMenuItem itemTextQuestion = new JMenuItem("Pytanie tekstowe");
        
        menuQuestion.add(itemDataTimeQuestion);
        menuQuestion.add(itemGridQuestion);
        menuQuestion.add(itemMultipleChioceQuestion);
        menuQuestion.add(itemOneChoiceQuestion);
        menuQuestion.add(itemScaleQuestion);
        menuQuestion.add(itemTextQuestion);
        
        JMenuBar menuBar = new JMenuBar();
        
        menuBar.add(menuSurvey);
        menuBar.add(menuQuestion);
        setJMenuBar(menuBar);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
