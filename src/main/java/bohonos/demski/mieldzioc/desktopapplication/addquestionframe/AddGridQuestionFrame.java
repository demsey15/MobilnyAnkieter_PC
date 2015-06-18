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
import javax.swing.JFrame;

/**
 *
 * @author Delirus
 */
public class AddGridQuestionFrame extends AddQuestionFrame {
    
    
    public AddGridQuestionFrame(Survey survey, CreatorFrame crFrame) {
        
        super(survey, crFrame);
        
        
    }
    
    /**
     * adds new row to the list
     * @param row label of new row
     */
    public void addRow(String row) {
        
    }
    
    /**
     * deletes choosen row from the list
     */
    public void deleteRow() {
        
    }
    
    /**
     * adds new column to the list
     * @param column label of new column
     */
    public void addColumn(String column) {
        
    }
    
    /**
     * deletes choosen column from the list
     */
    public void deleteColumn() {
        
    }
    
    @Override
    protected Question createQuestion() {
        return null; //to do
    }
    
    @Override
    protected Boolean questionConditions() {
        return true;
    }

}
