/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import bohonos.demski.mieldzioc.survey.*;
import bohonos.demski.mieldzioc.interviewer.*;
import bohonos.demski.mieldzioc.questions.*;
import bohonos.demski.mieldzioc.networkConnection.ServerConnectionFacade;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Andrzej
 */
public class ApplicationLogic {
    
    private SurveyHandler surveyHandler;
    private Interviewer loggedInterviewer;
    private InterviewersRepository intervierwsRepository;
    private static ApplicationLogic instance;
    private ServerConnectionFacade serverConnectionFacade;
    private String host = "localhost";
    private SurveysRepository surveysRepository;
    
    private ApplicationLogic() {
        surveyHandler = new SurveyHandler(0);
        loggedInterviewer = new Interviewer("Imiê", "Nazwisko", "PESEL000000", new GregorianCalendar()); //to do
        intervierwsRepository = new InterviewersRepository();
        serverConnectionFacade = new ServerConnectionFacade(host);
        surveysRepository = new SurveysRepository();
    }
    
    public static ApplicationLogic getInstance() {
        if (instance == null) {
            instance = new ApplicationLogic();
        }
        return instance;
    }
    
    public SurveyHandler getSurveyHandler() {
        return surveyHandler;
    }
    
    /**
     * adds new survey template to survey handler
     * @return id of new survey template
     */
    public String newSurvey() {
        String idOfSurvey = surveyHandler.addNewSurveyTemplate(new Survey(loggedInterviewer));
        return idOfSurvey;
    }
    
    /**
     * adds newSurvey, based on existing survey
     * @param idOfSurvey id of existing survey template
     * @return if of new survey template
     * @throws CloneNotSupportedException 
     */
    public String copySurvey(String idOfSurvey) throws CloneNotSupportedException {
        return surveyHandler.copyOldAndCreateNewSurvey(idOfSurvey, loggedInterviewer.getId());
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
    
    /**
     * adds interviewer to repository
     * @param interv interviewer to add
     * @return true, if interviewer was added, or false, if such interviewer already exists
     */

    public boolean addInterviewer(Interviewer interv){
        return intervierwsRepository.addInterviewer(interv);
    }
    
    public List<Interviewer> getInterviewers(){
        return intervierwsRepository.getAllInterviewers();
    }
    
    public Interviewer getInterviewer(String idi){
        return intervierwsRepository.getInterviewer(idi);
    }
    
    public SurveysRepository getSurveysRepository(){
        return surveysRepository;
    }
}
