/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication.questionpanel;

import bohonos.demski.mieldzioc.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.desktopapplication.questionpanel.QuestionPanel;
import bohonos.demski.mieldzioc.questions.GridQuestion;
import bohonos.demski.mieldzioc.survey.Survey;

/**
 *
 * @author Andrzej
 */
public class GridQuestionPanel extends QuestionPanel {

    public GridQuestionPanel(Survey survey, GridQuestion gridQuestionPanel) {
        super(survey, gridQuestionPanel);
        
        HEIGHT = 100;
    }
    
}
