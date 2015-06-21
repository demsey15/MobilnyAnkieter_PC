/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication.questionpanel;

import bohonos.demski.mieldzioc.desktopapplication.ApplicationLogic;
import bohonos.demski.mieldzioc.desktopapplication.questionpanel.QuestionPanel;
import bohonos.demski.mieldzioc.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.survey.Survey;

/**
 *
 * @author Andrzej
 */
public class DateTimeQuestionPanel extends QuestionPanel {

    public static int HEIGHT = 50;
    
    public DateTimeQuestionPanel(Survey survey, DateTimeQuestion dataTimeQuestion) {
        super(survey, dataTimeQuestion);
    }
    
}
