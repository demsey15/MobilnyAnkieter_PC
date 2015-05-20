/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import bohonos.demski.mieldzioc.survey.*;
import bohonos.demski.mieldzioc.interviewer.*;
import bohonos.demski.mieldzioc.questions.*;
import java.util.GregorianCalendar;
/**
 *
 * @author Andrzej
 */
public class ApplicationLogic {
    
    private SurveyHandler surveyHandler;
    private Interviewer loggedInterviewer;
    private InterviewersRepository intervierwsRepository;
    
    /**
     * adds new survey template to survey handler
     * @return id of new survey template
     */
    public String newSurvey() {
        String idOfSurvey = surveyHandler.addNewSurveyTemplate(new Survey(loggedInterviewer));
        return idOfSurvey;
    }
    
    /**
     * returns title of survey template with given id
     * @param idOfSurvey id of survey template, we are interested in
     * @return title of survey template
     */
    public String getSurveyTitle(String idOfSurvey) {
        return surveyHandler.getSurvey(idOfSurvey).getTitle();
    }
    
    /**
     * sets title of survey with given id
     * @param idOfSurvey id of survey template
     * @param title title of survey template
     * @throws CloneNotSupportedException 
     */
    public void setSurveyTitle(String idOfSurvey, String title) throws CloneNotSupportedException {
        Survey survey = surveyHandler.provideSurvey(idOfSurvey);
        survey.setTitle(title);
    }
    
    /**
     * returns description of survey template with given id
     * @param idOfSurvey id of survey template
     * @return description of survey template
     */
    public String getSurveyDescription(String idOfSurvey) {
        return surveyHandler.getSurvey(idOfSurvey).getDescription();
    }
    
    public ApplicationLogic() {
        surveyHandler = new SurveyHandler(0);
        loggedInterviewer = new Interviewer("Imiê", "Nazwisko", "PESEL000000", new GregorianCalendar()); //to do
        intervierwsRepository = new InterviewersRepository();
    }
    public boolean addInterviewer(Interviewer interv){
        return intervierwsRepository.addInterviewer(interv);
    }
}
