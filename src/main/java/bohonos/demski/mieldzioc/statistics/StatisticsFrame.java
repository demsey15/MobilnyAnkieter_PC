/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.statistics;

import bohonos.demski.mieldzioc.desktopapplication.ApplicationLogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Delirus
 */
public class StatisticsFrame extends JFrame implements ActionListener{

    private ApplicationLogic applicationLogic;
    private JButton statResultSurvey, statFillSurvey, close, statisticsInterviewer, rankInterviewers;
    private JTextField idInterviwer, idSurvey;
    private JLabel jInterviewer, jSurvey;
    
    public StatisticsFrame(ApplicationLogic applicationLogic1){
        super("Wyniki ankiet i statystyki");
         addWindowListener(new WindowAdapter() {
                        @Override
			public void windowClosing(WindowEvent we){
				dispose();
				System.exit(0);
			}
		});
         
         
    }
    
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
