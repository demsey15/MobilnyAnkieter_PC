/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.addquestionframe;

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

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.CreatorFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.OneChoiceQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.io.IOException;

/**
 *
 * @author Delirus
 */
public class AddOneChoiceQuestionFrame extends AddQuestionFrame {
    
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
    
    public AddOneChoiceQuestionFrame(Survey survey, CreatorFrame crFrame) throws IOException {
        
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
        OneChoiceQuestion oneChoiceQuestion = new OneChoiceQuestion(questionField.getText(), obligatoryBox.isSelected(), hintField.getText(), false, answers);
        survey.addQuestion(oneChoiceQuestion);
        creatorFrame.addOneChoiceQuestionPanel(oneChoiceQuestion);
        return null;
    }
    
    @Override
    protected Boolean questionConditions() {
        if (questionField.getText().equals("")==false && answers.isEmpty()==false) {
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