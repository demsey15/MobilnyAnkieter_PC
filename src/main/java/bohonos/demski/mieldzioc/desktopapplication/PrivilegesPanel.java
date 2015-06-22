/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import static bohonos.demski.mieldzioc.desktopapplication.InterviewerPanel.HEIGHT;
import static bohonos.demski.mieldzioc.desktopapplication.InterviewerPanel.WIDTH;
import bohonos.demski.mieldzioc.interviewer.Interviewer;
import bohonos.demski.mieldzioc.interviewer.InterviewerSurveyPrivileges;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Delirus
 */
public class PrivilegesPanel extends JPanel implements ActionListener{
    private Map.Entry<String,InterviewerSurveyPrivileges> entr;
    private SetPrivileges setPrivileges;
    public static int HEIGHT = 70;
    public static final int WIDTH = 780;
    private JLabel idLabel;
    //private boolean editing, filling, editingWithoutAdminAgreement, fillingStatistics;
    private JCheckBox cb1, cb2, cb3, cb4;
    
    public PrivilegesPanel(Map.Entry<String,InterviewerSurveyPrivileges> entr, SetPrivileges setPrivileges){
        this.entr = entr;
        this.setPrivileges = setPrivileges;
        
        addMouseListener(new MouseAdapter() { 
          public void mousePressed(MouseEvent me) { 
            //System.out.println(me); 
          } 
        }); 
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        cb1= new JCheckBox();
        cb2= new JCheckBox();
        cb3= new JCheckBox();
        cb4= new JCheckBox();
        idLabel = new JLabel(entr.getKey());
        idLabel.setBounds(20, 15, 150, 40);
        cb1.setBounds(270,20,30,20);
        cb2.setBounds(370,20,30,20);
        cb3.setBounds(510,20,30,20);
        cb4.setBounds(650,20,30,20);
        cb1.setSelected(entr.getValue().isEditing());
        cb2.setSelected(entr.getValue().isFilling());
        cb3.setSelected(entr.getValue().isEditingWithoutAdminAgreement());
        cb4.setSelected(entr.getValue().isFillingStatistics());
       /* cb1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            System.out.println("Clicked!");
        }
        });*/
        
        this.add(idLabel);
        this.add(cb1);
        this.add(cb2);
        this.add(cb3);
        this.add(cb4);
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    }

    public void actionPerformed(ActionEvent e) {
        
    }
}
