/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication.questionpanel;

import bohonos.demski.mieldzioc.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.desktopapplication.questionpanel.QuestionPanel;
import bohonos.demski.mieldzioc.questions.MultipleChoiceQuestion;
import bohonos.demski.mieldzioc.survey.Survey;

/**
 *
 * @author Andrzej
 */
public class MultipleChoiceQuestionPanel extends QuestionPanel {

    public MultipleChoiceQuestionPanel(Survey survey, MultipleChoiceQuestion multipleChoiceQuestion) {
        
        super(survey, multipleChoiceQuestion);
        
        HEIGHT = 120;
    }
    
}
