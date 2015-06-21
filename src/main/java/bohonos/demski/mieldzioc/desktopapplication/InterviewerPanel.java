/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import static bohonos.demski.mieldzioc.desktopapplication.questionpanel.QuestionPanel.HEIGHT;
import static bohonos.demski.mieldzioc.desktopapplication.questionpanel.QuestionPanel.WIDTH;
import bohonos.demski.mieldzioc.interviewer.Interviewer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Delirus
 */
public class InterviewerPanel extends JPanel implements ActionListener{
    private Interviewer interviewer;
    public static int HEIGHT = 70;
    public static final int WIDTH = 780;
    private JButton editInterv, privileges;
    //private ApplicationLogic applicationLogic;
    
    private JLabel nameLabel, surnameLabel, idLabel;
    private MenagerInterviewersFrame menager;
    
    public InterviewerPanel(Interviewer interviewer, MenagerInterviewersFrame meanger){
        this.interviewer=interviewer;
        
        //applicationLogic = ApplicationLogic.getInstance();
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);

        nameLabel = new JLabel(interviewer.getName());
        //System.out.println(interviewer.getName()+" "+ interviewer.getSurname()+" "+interviewer.getId());
        nameLabel.setBounds(20, 15, 80, 40);
        surnameLabel = new JLabel(interviewer.getSurname());
        surnameLabel.setBounds(120, 15, 80, 40);
        idLabel = new JLabel(interviewer.getId());
        idLabel.setBounds(220, 15, 80, 40);
        this.add(surnameLabel);
        this.add(idLabel);
        this.add(nameLabel);
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        //menager.refreshViewOfInterviewers();
        editInterv = new JButton("Edycja ankietera");
        privileges = new JButton("Uprawnienia");
        editInterv.setBounds(400, 15, 150, 40);
        privileges.setBounds(600, 15, 150, 40);
        this.add(editInterv);
        this.add(privileges);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
       
    }
}
