/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.*;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.*;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.*;
import java.io.IOException;
import java.text.ParseException;
/**
 *
 * @author Andrzej
 */
public class ApplicationLogic {
    
    private SurveyHandler surveyHandler;
    private Interviewer loggedInterviewer;
    private InterviewersRepository intervierwsRepository;
    private static ApplicationLogic instance;

    //private String host = "localhost";
    private SurveysRepository surveysRepository;
    
    private ApplicationLogic() throws IOException, ParseException {
        
        FileSystemCreator fileSystemCreator = new FileSystemCreator();
        surveyHandler = new SurveyHandler(0, 0);
        loggedInterviewer = new Interviewer("Imiê", "Nazwisko", "12345678911", new GregorianCalendar()); //to do
        intervierwsRepository = new InterviewersRepository();
        //serverConnectionFacade = new ServerConnectionFacade(host);
        surveysRepository = new SurveysRepository();
    }
    
    public static ApplicationLogic getInstance() throws IOException, ParseException {
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
        String idOfSurvey = surveyHandler.addNewSurveyTemplate(new Survey("desktopapplication"));
        return idOfSurvey;
    }
    
    /**
     * adds newSurvey, based on existing survey
     * @param idOfSurvey id of existing survey template
     * @return if of new survey template
     * @throws CloneNotSupportedException 
     */
    public String copySurvey(String idOfSurvey) throws CloneNotSupportedException {
        return surveyHandler.copyOldAndCreateNewSurvey(idOfSurvey, "desctopapplication");
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
    
    public String getSurveySummary(String idOfSurvey) {
        return surveyHandler.getSurvey(idOfSurvey).getSummary();
    }
    
    public void setSurveySummary(String idOfSurvey, String summary) {
        Survey survey = surveyHandler.getSurvey(idOfSurvey);
        survey.setSummary(summary);
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
     * returns list of all surveys
     * @return 
     */
    public String[] getAllSurveysList() {
        Map<String,Survey> inProgresSurveys = surveyHandler.getStatusSurveysId(0);
        Map<String,Survey> activeSurveys = surveyHandler.getStatusSurveysId(1);
        Map<String,Survey> disactiveSurveys = surveyHandler.getStatusSurveysId(2);
        System.out.println("liczba szablonow w wersji roboczej: " + inProgresSurveys.size());
        System.out.println("liczba szablonow aktywnych: " + activeSurveys.size());
        System.out.println("liczba szablonow nieaktywnych: " + disactiveSurveys.size());
        int size = inProgresSurveys.size() + activeSurveys.size() + disactiveSurveys.size();
        String[] surveysList = new String[size];
        int iterator = 0;
        for (Map.Entry<String,Survey> entry : inProgresSurveys.entrySet()) {
            surveysList[iterator] = entry.getKey();
            iterator++;
        }
        for (Map.Entry<String,Survey> entry : activeSurveys.entrySet()) {
            surveysList[iterator] = entry.getKey();
            iterator++;
        }
        for (Map.Entry<String,Survey> entry : disactiveSurveys.entrySet()) {
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
    
    public InterviewersRepository getInterviewersRepository(){
        return intervierwsRepository;
    }
}
