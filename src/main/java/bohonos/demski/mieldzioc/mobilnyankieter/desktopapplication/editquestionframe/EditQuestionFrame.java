/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.editquestionframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.CreatorFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

/**
 *
 * @author Andrzej
 */
public abstract class EditQuestionFrame extends JFrame implements ActionListener {
    
    protected final int LABELS_WIDTH = 200;
    protected final int LABELS_HEIGHT = 25;
    protected final int LABELS_X_POSITION = 20;
    protected final int FIELDS_WIDTH = 300;
    protected final int FIELDS_HEIGHT = 25;
    protected final int FIELDS_X_POSITION = 220;
    protected final int SPACE_HEIGHT = 15;
    protected final int CANCEL_BUTTON_X_POSITION = 140;
    protected final int ADD_BUTTON_X_POSITION = 300;
    protected final int BUTTON_WIDTH = 120;
    protected final int BUTTON_HEIGHT = 40;
    
    protected int CURRENT_Y_POSITION = 10;
    
    protected Boolean obligatory;
    //protected String question = "";
    protected String pictureUrl = "";
    protected String hint = "";
    protected String errorMessage = "";
    protected Question question;
    
    protected ApplicationLogic applicationLogic;
    protected SurveyPanel surveyPanel;
    protected JButton saveButton, cancelButton, addAnswerButton, deleteAnswerButton;
    protected JButton addRowButton, deleteRowButton, addColumnButton, deleteColumnButton;
    protected JTextField questionField, hintField, errorMessageField;
    protected JLabel questionLabel, hintLabel, errorMessageLabel;
    protected JCheckBox obligatoryBox;
    
    public EditQuestionFrame(Question question, SurveyPanel surveyPanel) {
        super("Edytuj pytanie");
        
        applicationLogic = ApplicationLogic.getInstance();
        this.surveyPanel = surveyPanel;
        this.question = question;
        
        setSize(600, 300);
        setLocation(400,150);
        setResizable(false);
        this.setLayout(null);
        
        questionLabel = new JLabel("pytanie: ");
        questionLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(questionLabel);
         
        questionField = new JTextField();
        questionField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(questionField);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        hintLabel = new JLabel("wskazówka: ");
        hintLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(hintLabel);

        hintField = new JTextField(question.getHint());
        hintField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(hintField);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        errorMessageLabel = new JLabel("informacja o b³êdzie: ");
        errorMessageLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(errorMessageLabel);

        errorMessageField = new JTextField("");
        errorMessageField.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(errorMessageField);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        obligatoryBox = new JCheckBox("pytanie obowi¹zkowe");
        obligatoryBox.setSelected(question.isObligatory());
        obligatoryBox.setBounds(FIELDS_X_POSITION, CURRENT_Y_POSITION, FIELDS_WIDTH, FIELDS_HEIGHT);
        this.add(obligatoryBox);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT;
        
        saveButton = new JButton("Zapisz");
        saveButton.setBounds(ADD_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(saveButton);
        saveButton.addActionListener(this);
        
        cancelButton = new JButton("Anuluj");
        cancelButton.setBounds(CANCEL_BUTTON_X_POSITION, CURRENT_Y_POSITION + SPACE_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(cancelButton);
        cancelButton.addActionListener(this);
        
        addAnswerButton = new JButton("Dodaj odpowiedŸ");
        addAnswerButton.addActionListener(this);
        
        deleteAnswerButton = new JButton("Usuñ odpowiedŸ");
        deleteAnswerButton.addActionListener(this);
        
        addRowButton = new JButton("Dodaj wiersz");
        addRowButton.addActionListener(this);
               
        deleteRowButton = new JButton("Usuñ wiersz");
        deleteRowButton.addActionListener(this);
        
        addColumnButton = new JButton("Dodaj kolumnê");
        addColumnButton.addActionListener(this);
               
        deleteColumnButton = new JButton("Usuñ kolumnê");
        deleteColumnButton.addActionListener(this);
        
        setVisible(true);
    }
    
    /**
     * saves changes
     * @return new question or null, if question was not created
     */
    protected abstract Question saveQuestion();
    
    /**
     * checks, if all conditions are satisfied (has to be overwriten)
     * @return true, iff conditions are satisfied
     */
    protected Boolean questionConditions() {
        return true;
    }
    
    /**
     * adds answer to MultipleChoiceQuestion and OneChoiceQuestion (has to be overwriten)
     * @param answer answer to add
     */
    protected void addAnswer(String answer) {
        
    }
    
    /**
     * removes chosen answer from the list (has to be overwriten)
     */
    protected void deleteAnswer() {
        
    }
    
    /**
     * adds new row to the list (has to be overwriten)
     * @param Row label of new row
     */
    protected void addRow(String Row) {
        
    }
    
    /**
     * deletes choosen row from the list (has to be overwriten)
     */
    protected void deleteRow() {
        
    }
    
    /**
     * adds new column to the list (has to be overwriten)
     * @param column label of new column
     */
    protected void addColumn(String column) {
        
    }
    
    /**
     * deletes choosen column from the list (has to be overwriten)
     */
    protected void deleteColumn() {
        
    }

    public void actionPerformed(ActionEvent ae) {
        
        Object source = ae.getSource();
        
        if (source == cancelButton) {
            dispose();
        }
        
        if (source == saveButton) {
            if (questionConditions()==true) {
                saveQuestion();
                dispose();
            }
        }
        
        if (source == addAnswerButton) {
            AddAnswerFrame addAnswerFrame = new AddAnswerFrame(this);
        }
        
        if (source == deleteAnswerButton) {
            this.deleteAnswer();
        }
        
        if (source == addRowButton) {
            AddRowFrame addRowFrame = new AddRowFrame(this);
        }
        
        if (source == deleteRowButton) {
            this.deleteRow();
        }
        
        if (source == addColumnButton) {
            AddColumnFrame addColumnFrame = new AddColumnFrame(this);
        }
        
        if (source == deleteColumnButton) {
            this.deleteColumn();
        }
    }
    
}
