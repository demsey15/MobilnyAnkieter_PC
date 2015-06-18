/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication.addquestionframe;

import bohonos.demski.mieldzioc.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.desktopapplication.CreatorFrame;
import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.survey.Survey;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Delirus
 */
public class AddMultipleChoiceQuestionFrame extends AddQuestionFrame {
    
    private final int ANSWER_BUTTONS_X_POSITION = 450;
    private final int ANSWER_BUTTONS_WIDTH = 130;
    private final int ANSWER_BUTTONS_HEIGHT = 40;
    private final int LIST_X_POSITION = 220;
    private final int LIST_WIDTH = 220;
    private final int LIST_HEIGHT = 200;
    private final int BUTTONS_Y_POSITION = 400;
    
    private List<String> answers;
    private List<String> selectedAnswers;
    
    private JLabel answerLabel;
    private JList answerList;
    private DefaultListModel answerListItems;
    
    public AddMultipleChoiceQuestionFrame(Survey survey, CreatorFrame crFrame) {
        
        super(survey, crFrame);
        
        answers = new ArrayList<String>();
        
        this.setSize(600, 500);
        
        answerLabel = new JLabel("mo¿liwe odpowiedzi: ");
        answerLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(answerLabel);
        
        answerListItems = new DefaultListModel();
        answerList = new JList(answerListItems);
        answerList.setBounds(LIST_X_POSITION, CURRENT_Y_POSITION, LIST_WIDTH, LIST_HEIGHT);
        ListSelectionListener lsl = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectedAnswers = answerList.getSelectedValuesList();
            }
        };
        this.add(answerList);
        answerList.addListSelectionListener(lsl);
        
        addAnswerButton.setBounds(ANSWER_BUTTONS_X_POSITION, CURRENT_Y_POSITION, ANSWER_BUTTONS_WIDTH, ANSWER_BUTTONS_HEIGHT);
        this.add(addAnswerButton);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT + SPACE_HEIGHT;
        
        deleteAnswerButton.setBounds(ANSWER_BUTTONS_X_POSITION, CURRENT_Y_POSITION, ANSWER_BUTTONS_WIDTH, ANSWER_BUTTONS_HEIGHT);
        this.add(deleteAnswerButton);

        addButton.setBounds(ADD_BUTTON_X_POSITION, BUTTONS_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);

        cancelButton.setBounds(CANCEL_BUTTON_X_POSITION, BUTTONS_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
        
    }
    
    @Override
    protected Question createQuestion() {
        return null; //to do
    }
    
    @Override
    protected Boolean questionConditions() {
        if (question.equals("")==false && answers.isEmpty()==false) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected void addAnswer(String answer) {
        answers.add(answer);
        answerListItems.addElement(answer);
    }
    
    @Override
    protected void deleteAnswer() {
            answers.remove(selectedAnswers.get(0));
            answerListItems.removeElement(selectedAnswers.get(0));
    }
    
}
