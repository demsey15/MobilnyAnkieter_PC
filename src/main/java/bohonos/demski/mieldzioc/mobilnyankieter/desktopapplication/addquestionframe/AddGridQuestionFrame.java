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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.CreatorFrame;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.GridQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

/**
 *
 * @author Delirus
 */
public class AddGridQuestionFrame extends AddQuestionFrame {
    
    private final int RC_BUTTONS_X_POSITION = 450;
    private final int RC_BUTTONS_WIDTH = 130;
    private final int RC_BUTTONS_HEIGHT = 40;
    private final int LIST_X_POSITION = 220;
    private final int LIST_WIDTH = 220;
    private final int LIST_HEIGHT = 120;
    private final int BUTTONS_Y_POSITION = 470;
    
    private int CURRENT_BUTTON_Y_POSITION;
    
    private List<String> rows, columns;
    private List<String> selectedRows, selectedColumns;
    
    private JLabel rowLabel, columnLabel;
    private JList rowList, columnList;
    private DefaultListModel rowListItems, columnListItems;
    
    public AddGridQuestionFrame(Survey survey, CreatorFrame crFrame) {
        
        super(survey, crFrame);
        
        rows = new ArrayList<String>();
        columns = new ArrayList<String>();
        
        this.setSize(600, 550);
        
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
        
        addColumnButton.setBounds(RC_BUTTONS_X_POSITION, CURRENT_Y_POSITION, RC_BUTTONS_WIDTH, RC_BUTTONS_HEIGHT);
        this.add(addColumnButton);
        
        CURRENT_BUTTON_Y_POSITION = CURRENT_Y_POSITION + FIELDS_HEIGHT + SPACE_HEIGHT + SPACE_HEIGHT;
        
        deleteColumnButton.setBounds(RC_BUTTONS_X_POSITION, CURRENT_BUTTON_Y_POSITION, RC_BUTTONS_WIDTH, RC_BUTTONS_HEIGHT);
        this.add(deleteColumnButton);

        addButton.setBounds(ADD_BUTTON_X_POSITION, BUTTONS_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);

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
    protected Question createQuestion() {
        GridQuestion gridQuestion = new GridQuestion(questionField.getText(), obligatoryBox.isSelected(), hintField.getText(), columns, rows);
        survey.addQuestion(gridQuestion);
        creatorFrame.addGridQuestionPanel(gridQuestion);
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
