/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.editquestionframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.CreatorFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.GridQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Delirus
 */
public class EditGridQuestionFrame extends EditQuestionFrame {
    
    private final int RC_BUTTONS_X_POSITION = 450;
    private final int RC_BUTTONS_WIDTH = 130;
    private final int RC_BUTTONS_HEIGHT = 40;
    private final int LIST_X_POSITION = 220;
    private final int LIST_WIDTH = 220;
    private final int LIST_HEIGHT = 120;
    private final int BUTTONS_Y_POSITION = 470;
    
    private int CURRENT_BUTTON_Y_POSITION;
    
    private List<String> rows, columns, oldRows, oldColumns;
    private List<String> selectedRows, selectedColumns;
    private GridQuestion gridQuestion;
    
    private JLabel rowLabel, columnLabel;
    private JList rowList, columnList;
    private DefaultListModel rowListItems, columnListItems;
    
    public EditGridQuestionFrame(GridQuestion gridQuestion, SurveyPanel surveyPanel) throws IOException, ParseException {
        
        super(gridQuestion, surveyPanel);
        this.gridQuestion = gridQuestion;
        
        oldColumns = this.gridQuestion.getColumnLabels();
        oldRows = this.gridQuestion.getRowLabels();
        
        rows = new ArrayList<String>();
        columns = new ArrayList<String>();
        
        for (String column : oldColumns) {
            columns.add(column);
        }
        for (String row : oldRows) {
            rows.add(row);
        }
        
        this.setSize(600, 550);
        
        questionField.setText(question.getQuestion());
        hintField.setText(question.getHint());
        //errorMessageField.setText("");
        
        rowLabel = new JLabel("wiersze: ");
        rowLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(rowLabel);
        
        rowListItems = new DefaultListModel();
        rowList = new JList(rowListItems);
        rowList.setBounds(LIST_X_POSITION, CURRENT_Y_POSITION, LIST_WIDTH, LIST_HEIGHT);
        ListSelectionListener lslRows = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectedRows = rowList.getSelectedValuesList();
            }
        };
        this.add(rowList);
        rowList.addListSelectionListener(lslRows);
        for (String row : rows) {
            rowListItems.addElement(row);
        }
        
        addRowButton.setBounds(RC_BUTTONS_X_POSITION, CURRENT_Y_POSITION, RC_BUTTONS_WIDTH, RC_BUTTONS_HEIGHT);
        this.add(addRowButton);
        
        CURRENT_BUTTON_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT + SPACE_HEIGHT;
        
        deleteRowButton.setBounds(RC_BUTTONS_X_POSITION, CURRENT_BUTTON_Y_POSITION, RC_BUTTONS_WIDTH, RC_BUTTONS_HEIGHT);
        this.add(deleteRowButton);
        
        CURRENT_Y_POSITION = CURRENT_Y_POSITION + LIST_HEIGHT + SPACE_HEIGHT;
        
        columnLabel = new JLabel("kolumny: ");
        columnLabel.setBounds(LABELS_X_POSITION, CURRENT_Y_POSITION, LABELS_WIDTH, LABELS_HEIGHT);
        this.add(columnLabel);
        
        columnListItems = new DefaultListModel();
        columnList = new JList(columnListItems);
        columnList.setBounds(LIST_X_POSITION, CURRENT_Y_POSITION, LIST_WIDTH, LIST_HEIGHT);
        ListSelectionListener lslColumns = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectedColumns = columnList.getSelectedValuesList();
            }
        };
        this.add(columnList);
        columnList.addListSelectionListener(lslColumns);
        for (String column : columns) {
            columnListItems.addElement(column);
        }
        
        addColumnButton.setBounds(RC_BUTTONS_X_POSITION, CURRENT_Y_POSITION, RC_BUTTONS_WIDTH, RC_BUTTONS_HEIGHT);
        this.add(addColumnButton);
        
        CURRENT_BUTTON_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT + SPACE_HEIGHT;
        
        deleteColumnButton.setBounds(RC_BUTTONS_X_POSITION, CURRENT_BUTTON_Y_POSITION, RC_BUTTONS_WIDTH, RC_BUTTONS_HEIGHT);
        this.add(deleteColumnButton);

        saveButton.setBounds(ADD_BUTTON_X_POSITION, BUTTONS_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);

        cancelButton.setBounds(CANCEL_BUTTON_X_POSITION, BUTTONS_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
        
    }
    
    @Override
    protected void addRow(String row) {
        rows.add(row);
        rowListItems.addElement(row);
    }
    
    @Override
    protected void deleteRow() {
        rows.remove(selectedRows.get(0));
        rowListItems.removeElement(selectedRows.get(0));
    }
    
    @Override
    protected void addColumn(String column) {
        columns.add(column);
        columnListItems.addElement(column);
    }
    
    @Override
    protected void deleteColumn() {
        columns.remove(selectedColumns.get(0));
        columnListItems.removeElement(selectedColumns.get(0));
    }
    
    @Override
    protected Question saveQuestion() {
        question.setQuestion(questionField.getText());
        question.setHint(hintField.getText());
        //question.setErrorMessage(errorMessageField.getText());
        question.setObligatory(obligatoryBox.isSelected());
        gridQuestion.getColumnLabels().clear();
        gridQuestion.getRowLabels().clear();
        gridQuestion.setColumnLabels(columns);
        gridQuestion.setRowLabels(rows);
        try {
            surveyPanel.refreshQuestionList();
        } catch (IOException ex) {
            Logger.getLogger(EditGridQuestionFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EditGridQuestionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    protected Boolean questionConditions() {
        if (questionField.getText().equals("")==false && rows.isEmpty()==false && columns.isEmpty()==false) {
            return true;
        }
        else {
            return false;
        }
    }

}