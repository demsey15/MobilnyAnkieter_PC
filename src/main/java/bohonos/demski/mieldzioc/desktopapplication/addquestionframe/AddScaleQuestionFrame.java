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
public class AddScaleQuestionFrame extends AddQuestionFrame {
    
    
    public AddScaleQuestionFrame(Survey survey, CreatorFrame crFrame) {
        
        super(survey, crFrame);
        
        
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
