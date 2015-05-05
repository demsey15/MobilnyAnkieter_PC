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
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
