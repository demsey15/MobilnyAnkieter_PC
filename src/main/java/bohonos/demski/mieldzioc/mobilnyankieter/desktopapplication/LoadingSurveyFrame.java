/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Andrzej
 */
public class LoadingSurveyFrame extends JFrame implements ActionListener{
    private JButton editButton, cancelButton;
    private ApplicationLogic applicationLogic;
    private CreatorFrame creatorFrame;
    private String[] surveysList;
    private DefaultListModel surveysItems;
    private JList list;
    private List<String> selectedSurveysList;
    
    public LoadingSurveyFrame(CreatorFrame creatorFrame) throws IOException, ParseException{
        super("otw�rz ankiet�");
        applicationLogic = ApplicationLogic.getInstance();
        this.creatorFrame = creatorFrame;
        
        surveysList = applicationLogic.getSurveysList();
        surveysItems = new DefaultListModel();
        list = new JList(surveysItems);
        for (int iterator = 0; iterator < surveysList.length; iterator++) {
            surveysItems.addElement(surveysList[iterator] + "  " + applicationLogic.getSurveyTitle(surveysList[iterator]));
        }
        list.setBounds(40, 10, 320, 170);
        ListSelectionListener lsl = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectedSurveysList = list.getSelectedValuesList();
            }
        };
        this.add(list);
        list.addListSelectionListener(lsl);
        
        setSize(400, 300);
        setLocation(400,300);
        setResizable(false);
        this.setLayout(null);
        
        editButton = new JButton("Otw�rz");
        editButton.setBounds(210, 200, 100, 40);
        this.add(editButton);
        editButton.addActionListener(this);
        
        cancelButton = new JButton("Anuluj");
        cancelButton.setBounds(90, 200, 100, 40);
        this.add(cancelButton);
        cancelButton.addActionListener(this);
        
        
        
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == cancelButton) {
            dispose();
        }
        
        if (source == editButton) {
            for (int iterator = 0; iterator < selectedSurveysList.size(); iterator++) {
                String str = selectedSurveysList.get(iterator);
                String[] splited = str.split("\\s+");
                try {
                    creatorFrame.addSurveyPanel(splited[0]);
                } catch (IOException ex) {
                    Logger.getLogger(LoadingSurveyFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(LoadingSurveyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dispose();
        }
        
    }
    
}
