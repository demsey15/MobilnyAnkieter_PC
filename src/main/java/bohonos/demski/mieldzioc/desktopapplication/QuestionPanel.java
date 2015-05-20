/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 *
 * @author Andrzej
 */
public class QuestionPanel extends JPanel implements ActionListener {
    
    private ApplicationLogic applicationLogic;
    
    private JButton questionUp;
    private JButton questionDown;
    private JButton questionDelete;
    
    public QuestionPanel(ApplicationLogic appLogic) {
        
        applicationLogic = appLogic;
        
    }

    /**
     * adds questionUp button to ActionListener from other panel and returns this button
     * @param al given ActionListener
     * @return reference to questionUp button
     */
    public JButton addActionListenerQuestionUp(ActionListener al) {
        questionUp.addActionListener(al);
        return questionUp;
    }
    
    /**
     * adds questionDown button to ActionListener from other panel and returns this button
     * @param al given ActionListener
     * @return reference to questionDown button
     */
    public JButton addActionListenerQuestionDown(ActionListener al) {
        questionDown.addActionListener(al);
        return questionDown;
    }
    
    /**
     * adds questionDelete button to ActionListener from other panel and returns this button
     * @param al given ActionListener
     * @return reference to questionDelete button
     */
    public JButton addActionListenerQuestionDelete(ActionListener al) {
        questionDelete.addActionListener(al);
        return questionDelete;
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == questionUp) {
            
        }
        
        if (source == questionDown) {
            
        }
        
        if (source == questionDelete) {
            
        }
        
    }
    
}
