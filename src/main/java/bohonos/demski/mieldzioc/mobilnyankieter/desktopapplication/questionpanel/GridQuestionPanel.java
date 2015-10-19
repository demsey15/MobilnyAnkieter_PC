/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel;

import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.SurveyPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.questionpanel.QuestionPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.GridQuestion;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

/**
 *
 * @author Andrzej
 */
public class GridQuestionPanel extends QuestionPanel {
    
    public int HEIGHT = 60;
    
    private int CURRENT_BOX_X_POSITION;
    private int CURRET_COLUMN_X_POSITION = 110;
    private int COLUMN_WIDTH = 70;
    private int COLUMN_X_DISTANCE = 80;
    
    private GridQuestion gridQuestion;
    private List<String> columns, rows;
    private int columsNumber;

    public GridQuestionPanel(Survey survey, GridQuestion gridQuestion, SurveyPanel surveyPanel) {
        
        super(survey, gridQuestion, surveyPanel);
        
        this.gridQuestion = gridQuestion;
        columns = this.gridQuestion.getColumnLabels();
        rows = this.gridQuestion.getRowLabels();
        columsNumber = columns.size();
        for (String column : columns) {
            JLabel columnLabel = new JLabel(column);
            columnLabel.setBounds(CURRET_COLUMN_X_POSITION, 35, COLUMN_WIDTH, 20);
            CURRET_COLUMN_X_POSITION = CURRET_COLUMN_X_POSITION + COLUMN_X_DISTANCE;
            this.add(columnLabel);
        }
        for (String row : rows) {
            CURRENT_BOX_X_POSITION = 120;
            JLabel rowLabel = new JLabel(row);
            rowLabel.setBounds(25, HEIGHT, 90, 20);
            this.add(rowLabel);
            for (int i=0; i<columsNumber; i++) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setBounds(CURRENT_BOX_X_POSITION, HEIGHT, 20, 20);
                checkBox.setEnabled(false);
                CURRENT_BOX_X_POSITION = CURRENT_BOX_X_POSITION + COLUMN_X_DISTANCE;
                this.add(checkBox);
            }
            HEIGHT = HEIGHT + 25;
        }
        
        
    }
    
}
