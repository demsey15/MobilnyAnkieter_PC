/*
 * 
 */
package bohonos.demski.mieldzioc.mobilnyankieter.survey;

import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;
import bohonos.demski.mieldzioc.mobilnyankieter.serialization.jsonserialization.JsonSurveySerializator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
 *
 * @author Andrzej Bohonos
 */
public class SurveyHandler {
    
    public static final int IN_PROGRESS = 0;
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 2;
    public static final int NO_SURVEY = -1;
    
    public static final int LOCAL_ID_LENGTH = 6;
            
    private int maxSurveysId = 0;
    private Map<Survey, Integer> surveys = new HashMap<Survey, Integer>(); //ankieta, status
    private Map<String, Survey> surveysId = new HashMap<String, Survey>();
    
   
    
    
    /**
     * get new copy survey with given id
     * @param idOfSurveys survey id
     * @return copy of survey with given id
     * @throws java.lang.CloneNotSupportedException
     */
    public Survey provideSurvey(String idOfSurveys) throws CloneNotSupportedException
    {
        Survey survey = surveysId.get(idOfSurveys).clone();
        return survey;
    }
    
    /**
     * returns survey with given id
     * @param idOfSurveys survey id
     * @return reference to survey with given id
     */
    public Survey getSurvey(String idOfSurveys)
    {
        return surveysId.get(idOfSurveys);
    }
    
    public int getMaxSurveysId()
    {
        return maxSurveysId;
    }
    
    public void setMaxSurveysId(int id)
    {
        maxSurveysId = id;
    }
    
    /**
     * creates 6-char local id of survey
     * @param localId integer value to transform
     * @return local id
     */
    private String localIdToString(int localId)
    {
        String id = Integer.toString(localId);
        while (id.length()<LOCAL_ID_LENGTH)
        {
            id = "0" + id;
        }
        return id;
    }
    
    /**
     * add new survey template to map
     * @param survey survey to add
     * @return id of this survey template
     */
    public String addNewSurveyTemplate(Survey survey)
    {
        maxSurveysId++;
        //TODO ustali� spos�b nadawania id grupy ankiet String id = survey.getInterviewer().getId() + localIdToString(maxSurveysId);
        //TODO edit 2 (28.10.15 r. - dominik) zmienilem sposob nadawania idOfSurveys na idUrzadzenia (adres MAC karty sieciowej) + localIdToString(maxSurveysId) 
        String id = survey.getDeviceId() + localIdToString(maxSurveysId);
        survey.setIdOfSurveys(id);
        surveysId.put(id, survey);
        surveys.put(survey, IN_PROGRESS);     //default value
        return id;
    }
    
    /**
     * load survey to map
     * @param survey survey to load
     * @param status status of loading survey
     * @return true, if survey was added or false, if such survey already exists in map
     */
    public boolean loadSurveyTemplate(Survey survey, int status)
    {
        if (surveys.containsKey(survey))
            return false;
        else
        {
            surveys.put(survey, status);
            surveysId.put(survey.getIdOfSurveys(), survey);
            return true;
        }
    }
    
    /**
     * copy survey of given id
     * @param idOfSurveys survey id
     * @param interviewerId author of new survey template
     * @return id of new survey, if given id exists or "no survey" otherwise
     * @throws java.lang.CloneNotSupportedException
     */
    public String copyOldAndCreateNewSurvey(String idOfSurveys, String interviewerId) throws CloneNotSupportedException
    {
        if (surveysId.containsKey(idOfSurveys))
                {
                    maxSurveysId++;
                    String id = interviewerId + localIdToString(maxSurveysId);
                    Survey oldSurvey = surveysId.get(idOfSurveys);
                    Survey newSurvey = oldSurvey.clone();
                    newSurvey.setIdOfSurveys(id);
                    surveysId.put(id, newSurvey);
                    surveys.put(newSurvey, IN_PROGRESS);     //default value
                    return id;
                }
        else
        {
            return "no survey";
        }
    }
    
    /**
     * get status of particular survey
     * @param idOfSurveys id of survey
     * @return status of survey, if such survey exists or -1 otherwise
     */
    public int getSurveyStatus(String idOfSurveys)
    {
            if (surveysId.containsKey(idOfSurveys))
            {
                Survey survey = surveysId.get(idOfSurveys);
                return surveys.get(survey);
            }
            else
            {
                return NO_SURVEY;
            }
    }
    
    /**
     * status of particular survey
     * @param survey given survey
     * @return status of survey, if such survey exists or -1 otherwise
     */
    public int getSurveyStatus(Survey survey)
    {
        if (surveys.containsKey(survey))
        {
            return surveys.get(survey);
        }
        else
        {
            return NO_SURVEY;
        }
    }
    
    /**
     * change status of particular survey
     * @param idOfSurveys id of survey
     * @param status new status (-1 is forbidden)
     * @return true, iff action was successful
     */
    public boolean setSurveyStatus(String idOfSurveys, int status)
    {
            if (surveysId.containsKey(idOfSurveys) && status!=NO_SURVEY)
            {
                Survey survey = surveysId.get(idOfSurveys);
                surveys.put(survey, status);
                return true;
            }
            else
            {
                return false;
            }      
    }
    
    /**
     * change status of particular survey
     * @param survey given survey
     * @param status new status (-1 is forbidden)
     * @return true, iff action was successful
     */
    public boolean setSurveyStatus(Survey survey, int status)
    {
            if (surveys.containsKey(survey) && status!=NO_SURVEY)
            {
                surveys.put(survey, status);
                return true;
            }
            else
            {
                return false;
            }      
    }
    
    /**
     * returns map of surveys with given status
     * @param status given status
     * @return map of surveys and their status
     */
    public Map<Survey,Integer> getStatusSurveys(int status)
    {
        Map<Survey,Integer> statusSurveys = new HashMap<Survey,Integer>();
        for (Map.Entry<Survey, Integer> entry : surveys.entrySet()) {
            if (entry.getValue().equals(status)) {
                statusSurveys.put(entry.getKey(), entry.getValue());
            }
        }
        return statusSurveys;
    }
    
    /**
     * returns map of surveysId and surveys with given status
     * @param status given status
     * @return map of surveysId and surveys
     */
    public Map<String,Survey> getStatusSurveysId(int status)
    {
        Map<String, Survey> statusSurveysId = new HashMap<String, Survey>();
        for (Map.Entry<Survey, Integer> entry : surveys.entrySet()) {
            if (entry.getValue().equals(status)) {
                statusSurveysId.put(entry.getKey().getIdOfSurveys(), entry.getKey());
            }
        }
        return statusSurveysId;
    }    
    
    /**
     * returns set of surveys Ids in handler
     * @return set of Ids
     */
    public Set<String> getSetOfIds() {
        return surveysId.keySet();
    }
    
    /**
     * @author Dominik Demski
     * @param survey ankieta do usuniecia
     */
    public void deleteSurveyTemplate(Survey survey){
    	surveys.remove(survey);
    	surveysId.remove(survey.getIdOfSurveys());
    }

    public SurveyHandler(int maxSurveysId)
    {
        this.maxSurveysId = maxSurveysId;
        
    }
    
    public SurveyHandler(int param1, int param2) throws IOException {
        String maxIdPath = "C:" + File.separator + "ankieter" + File.separator + "maxId.txt";
        BufferedReader br = new BufferedReader(new FileReader(maxIdPath));
        try {
            //StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            if (line!=null) {
                this.maxSurveysId = Integer.parseInt(line);
            }
            else {
                this.maxSurveysId = 0;
            }
        } finally {
            br.close();
        }
        this.loadSurveysTemplates();
        System.out.println("liczba ankiet w handlerze: " + this.surveys.size());
    }
    
    public void saveSurveyTemplates() throws FileNotFoundException, UnsupportedEncodingException, IOException{
        for (String id : surveysId.keySet()){
            String shortId = id.replace(":", "");
            String templatePath = "C:" + File.separator + "ankieter" + File.separator + "templates" + File.separator + shortId + ".json";
            System.out.println("Id zapisywanego szablonu: " + id);
            File templateFile = new File(templatePath);
            templateFile.createNewFile();
            PrintWriter writer = new PrintWriter(templatePath, "UTF-8");
            writer.println((new JsonSurveySerializator()).serializeSurvey(getSurvey(id)));
            writer.close();
            String maxIdPath = "C:" + File.separator + "ankieter" + File.separator + "maxId.txt";
            PrintWriter maxIdWriter = new PrintWriter(maxIdPath, "UTF-8");
            maxIdWriter.println(this.getMaxSurveysId());
            maxIdWriter.close();
        }
    }
    
    public void loadSurveysTemplates() throws FileNotFoundException, IOException {
        String templatesPath = "C:" + File.separator + "ankieter" + File.separator + "templates";
        File templatesCatalog = new File(templatesPath);
        File[] directoryListing = templatesCatalog.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                String filePath = child.getPath();// Do something with child
                Charset ch = Charset.forName("UTF-8");
                Scanner scan = new Scanner(new InputStreamReader(new FileInputStream(filePath),ch));
                String line;
                if (scan.hasNextLine()) {
                    line = scan.nextLine();
                    if((new JsonSurveySerializator()).deserializeSurvey(line)!=null){
                        this.loadSurveyTemplate((new JsonSurveySerializator()).deserializeSurvey(line), IN_PROGRESS);
                    }
                }
            }
        }
        String activeTemplatesPath = "C:" + File.separator + "ankieter" + File.separator + "activeTemplates";
        File activeTemplatesCatalog = new File(activeTemplatesPath);
        File[] activeTemplatesListing = activeTemplatesCatalog.listFiles();
        if (activeTemplatesListing != null) {
            for (File child : activeTemplatesListing) {
                String filePath = child.getPath();// Do something with child
                Charset ch = Charset.forName("UTF-8");
                Scanner scan = new Scanner(new InputStreamReader(new FileInputStream(filePath),ch));
                String line;
                if (scan.hasNextLine()) {
                    line = scan.nextLine();
                    if ((new JsonSurveySerializator()).deserializeSurvey(line)!=null){
                        Survey survey = (new JsonSurveySerializator()).deserializeSurvey(line);
                        if (this.getSetOfIds().contains(survey.getIdOfSurveys())){
                            this.setSurveyStatus(survey.getIdOfSurveys(), ACTIVE);
                        } else {
                            this.loadSurveyTemplate(survey, ACTIVE);
                        }
                    }
                }
            }
        }
    }
}
