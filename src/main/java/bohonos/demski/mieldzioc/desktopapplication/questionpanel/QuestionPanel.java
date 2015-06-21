/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication.questionpanel;

import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.survey.Survey;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Andrzej
 */
public class QuestionPanel extends JPanel implements ActionListener {
    
    public static int HEIGHT = 50;
    public static final int WIDTH = 640;
    
    private ApplicationLogic applicationLogic;
    
    private JButton questionUp;
    private JButton questionDown;
    private JButton questionDelete;
    protected JLabel questionLabel;
    
    public QuestionPanel(Survey survey, Question question) {
        
        applicationLogic = ApplicationLogic.getInstance();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        questionLabel = new JLabel(question.getQuestion());
        questionLabel.setBounds(20, 20, 300, 30);
        this.add(questionLabel);
        
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
