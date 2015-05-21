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
import java.util.Map;
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
     */
    public void setSurveyTitle(String idOfSurvey, String title) {
        Survey survey = surveyHandler.getSurvey(idOfSurvey);
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
    
    /**
     * sets description of survey with given id
     * @param idOfSurvey id of survey template
     * @param description description of survey template
     */
    public void setSurveyDescription(String idOfSurvey, String description) {
        Survey survey = surveyHandler.getSurvey(idOfSurvey);
        survey.setDescription(description);
    }
    
    /**
     * sets currently logged interviewer
     * @param interviewer logged interviewer
     */
    public void setLoggedInterviever(Interviewer interviewer) {
        loggedInterviewer = interviewer;
    } 
    
    /**
     * returns list of surveys templates with status "in progress"
     * @return String array
     */
    public String[] getSurveysList() {
        Map<String,Survey> surveys = surveyHandler.getStatusSurveysId(0);
        String[] surveysList = new String[surveys.size()];
        int iterator = 0;
        for (Map.Entry<String,Survey> entry : surveys.entrySet()) {
            surveysList[iterator] = entry.getKey();
            iterator++;
        }
        return surveysList;
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
