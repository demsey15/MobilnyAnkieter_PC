/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.survey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bohonos.demski.mieldzioc.mobilnyankieter.serialization.jsonserialization.*;
/**
 *
 * @author Andrzej
 */
public class SurveysRepository {
    
    public Map<String, List<Survey>> surveys = new HashMap<String, List<Survey>>();
    public Map<String, Long> maxNumbersOfSurveys = new HashMap<String, Long>();
    
    /**
     * returns list of surveys with given id
     * @param idOfSurveys given id of surveys, we are looking for
     * @return list of surveys
     */
    public List<Survey> getSurveys(String idOfSurveys) {
        return surveys.get(idOfSurveys);
    }
    
    /**
     * returns maximal number of surveys with given id
     * @param idOfSurveys given id of surveys, we want to count
     * @return number of surveys
     */
    public long getMaxNumberOfSurveys(String idOfSurveys) {
        return maxNumbersOfSurveys.get(idOfSurveys);
    }
    
    /**
     * returns map of all surveys in repository
     * @return map of surveys
     */
    public Map<String, List<Survey>> getAllSurveys() {
        return surveys;
    }
    
    /**
     * returns map of maximal numbers of all surveys in repository
     * @return map of numbers
     */
    public Map<String, Long> getAllMaxNumbersOfSurveys() {
        return maxNumbersOfSurveys;
    }   
    
    /**
     * overwrites maximal number of surveys with given id
     * @param idOfSurveys given id of surveys
     * @return maximal number of surveys from given group
     */
    private long countMaxNumberOfSurveys(String idOfSurveys) {
        List<Survey> surveysWithId = surveys.get(idOfSurveys); 
        long number=0;
        for (Survey surveysWithId1 : surveysWithId) {
            if (number < surveysWithId1.getNumberOfSurvey()) {
                number = surveysWithId1.getNumberOfSurvey();
            }
        }
        maxNumbersOfSurveys.put(idOfSurveys, number);
        return number;
    }
    
    /**
     * overwrites maximal numbers of all surveys in repository
     */
    public void countAllMaxNumbersOfSurveys() {
        for (Map.Entry<String,List<Survey>> entry : surveys.entrySet()) {
            countMaxNumberOfSurveys(entry.getKey());
        }        
    }
    
    /**
     * returns list of surveys of given id, finished after or in given date
     * @param idOfSurveys id of group of surveys we are looking for
     * @param from method returns surveys finished after this date or equal
     * @return list of surveys of particular id, finished after or in given date
     */
    public List<Survey> getSurveys(String idOfSurveys, GregorianCalendar from) {
        List<Survey> surveysWithId = surveys.get(idOfSurveys);
        List<Survey> surveysWithIdFrom = new ArrayList<Survey>();
        for (Survey surveysWithId1 : surveysWithId) {
            if (surveysWithId1.getFinishTime().before(from) == false) {
                surveysWithIdFrom.add(surveysWithId1);
            }
        }
        return surveysWithIdFrom;
    }
    
    /**
     * returns map of surveys finished after or in given date
     * @param from method returns surveys finished after this date or equal
     * @return map of surveys finished after or in given date
     */
    public Map<String,List<Survey>> getAllSurveys(GregorianCalendar from) {
        int i;
        Map<String,List<Survey>> surveysFrom = new HashMap<String,List<Survey>>();
        for (Map.Entry<String,List<Survey>> entry : surveys.entrySet()) {
            List<Survey> surveysWithIdFrom = new ArrayList<Survey>();
            for (i=0; i<entry.getValue().size(); i++) {
                if (entry.getValue().get(i).getFinishTime().before(from)==false) {
                    surveysWithIdFrom.add(entry.getValue().get(i));
                }
            }
            surveysFrom.put(entry.getKey(), surveysWithIdFrom);
        }
        return surveysFrom;
    }
    
    /**
     * returns list of surveys of given id, finished between two given dates
     * @param idOfSurveys id of group of surveys we are looking for
     * @param from does not return surveys finishet before this date
     * @param to does not return surveys finishet after this date
     * @return list of surveys
     */
    public List<Survey> getSurveys(String idOfSurveys, GregorianCalendar from, GregorianCalendar to) {
        List<Survey> surveysWithId = surveys.get(idOfSurveys);
        List<Survey> surveysWithIdFrom = new ArrayList<Survey>();
        for (Survey surveysWithId1 : surveysWithId) {
            if (surveysWithId1.getFinishTime().before(from) == false && surveysWithId1.getFinishTime().after(to) == false) {
                surveysWithIdFrom.add(surveysWithId1);
            }
        }
        return surveysWithIdFrom;
    }    
    
    /**
     * returns map of surveys finished between two given dates
     * @param from does not return surveys finishet before this date
     * @param to does not return surveys finishet after this date
     * @return map of surveys
     */
    public Map<String,List<Survey>> getAllSurveys(GregorianCalendar from, GregorianCalendar to) {
        int i;
        Map<String,List<Survey>> surveysFrom = new HashMap<String,List<Survey>>();
        for (Map.Entry<String,List<Survey>> entry : surveys.entrySet()) {
            List<Survey> surveysWithIdFrom = new ArrayList<Survey>();
            for (i=0; i<entry.getValue().size(); i++) {
                if (entry.getValue().get(i).getFinishTime().before(from)==false && entry.getValue().get(i).getFinishTime().after(to)==false) {
                    surveysWithIdFrom.add(entry.getValue().get(i));
                }
            }
            surveysFrom.put(entry.getKey(), surveysWithIdFrom);
        }
        return surveysFrom;
    }
    
    /**
     * returns map of surveys of given interviewer
     * @param interviewer interviewer, whose surveys we want to get
     * @return  map of surveys
     */
    /*
    public Map<String,List<Survey>> getAllInterviewerSurveys(Interviewer interviewer) {
        int i;
        Map<String,List<Survey>> surveysFrom = new HashMap<String,List<Survey>>();
        for (Map.Entry<String,List<Survey>> entry : surveys.entrySet()) {
            List<Survey> surveysWithIdFrom = new ArrayList<Survey>();
            for (i=0; i<entry.getValue().size(); i++) {
                if (entry.getValue().get(i).getInterviewer().equals(interviewer)) {
                    surveysWithIdFrom.add(entry.getValue().get(i));
                }
            }
            surveysFrom.put(entry.getKey(), surveysWithIdFrom);
        }
        return surveysFrom;
    }
 
    /**
     * returns list of surveys with given id, of given interviewer
     * @param idOfSurveys id of group of surveys we are looking for
     * @param interviewer interviewer, whose surveys we want to get
     * @return list of surveys
     */
    /*  
    public List<Survey> getInterviewerSurveys(String idOfSurveys, Interviewer interviewer) {
        List<Survey> surveysWithId = surveys.get(idOfSurveys);
        List<Survey> surveysWithIdInterviewer = new ArrayList<Survey>();
        for (Survey surveysWithId1 : surveysWithId) {
            if (surveysWithId1.getInterviewer().equals(interviewer)) {
                surveysWithIdInterviewer.add(surveysWithId1);
            }
        }
        return surveysWithIdInterviewer;
    }
    */
    
    /**
     * returns map of surveys of given interviewer, finished after or in given date
     * @param from does not return surveys finished before this date
     * @param interviewer interviewer, whose surveys we want to get
     * @return map of surveys
     */
    /*
    public Map<String,List<Survey>> getAllInterviewerSurveys(GregorianCalendar from, Interviewer interviewer) {
        int i;
        Map<String,List<Survey>> surveysFrom = new HashMap<String,List<Survey>>();
        for (Map.Entry<String,List<Survey>> entry : surveys.entrySet()) {
            List<Survey> surveysWithIdFrom = new ArrayList<Survey>();
            for (i=0; i<entry.getValue().size(); i++) {
                if (entry.getValue().get(i).getInterviewer().equals(interviewer) && entry.getValue().get(i).getFinishTime().before(from)==false) {
                    surveysWithIdFrom.add(entry.getValue().get(i));
                }
            }
            surveysFrom.put(entry.getKey(), surveysWithIdFrom);
        }
        return surveysFrom;
    }    
    */
    /**
     * returns list of surveys with given id, of given interviewer, finished after or in given date
     * @param idOfSurveys id of group of surveys we are looking for
     * @param from does not return surveys finishet before this date
     * @param interviewer interviewer, whose surveys we want to get
     * @return list of surveys
     */
    /*
    public List<Survey> getInterviewerSurveys(String idOfSurveys, GregorianCalendar from, Interviewer interviewer) {
        List<Survey> surveysWithId = surveys.get(idOfSurveys);
        List<Survey> surveysWithIdInterviewer = new ArrayList<Survey>();
        for (Survey surveysWithId1 : surveysWithId) {
            if (surveysWithId1.getInterviewer().equals(interviewer) && surveysWithId1.getFinishTime().before(from) == false) {
                surveysWithIdInterviewer.add(surveysWithId1);
            }
        }
        return surveysWithIdInterviewer;
    }
    */
    /**
     * returns map of surveys of given interviewer, finished between two given dates
     * @param from does not return surveys finishet before this date
     * @param to does not return surveys finishet after this date
     * @param interviewer interviewer, whose surveys we want to get
     * @return map of surveys
     */
    /*
    public Map<String,List<Survey>> getAllInterviewerSurveys(GregorianCalendar from, GregorianCalendar to, Interviewer interviewer) {
        int i;
        Map<String,List<Survey>> surveysFrom = new HashMap<String,List<Survey>>();
        for (Map.Entry<String,List<Survey>> entry : surveys.entrySet()) {
            List<Survey> surveysWithIdFrom = new ArrayList<Survey>();
            for (i=0; i<entry.getValue().size(); i++) {
                if (entry.getValue().get(i).getInterviewer().equals(interviewer) && entry.getValue().get(i).getFinishTime().before(from)==false && entry.getValue().get(i).getFinishTime().after(to)==false) {
                    surveysWithIdFrom.add(entry.getValue().get(i));
                }
            }
            surveysFrom.put(entry.getKey(), surveysWithIdFrom);
        }
        return surveysFrom;
    }  
    */
    /**
     * returns list of surveys of given interviewer, finished between two given dates
     * @param idOfSurveys id of group of surveys we are looking for
     * @param from does not return surveys finishet before this date
     * @param to does not return surveys finishet after this date
     * @param interviewer interviewer, whose surveys we want to get
     * @return list of surveys
     */
    /*
    public List<Survey> getInterviewerSurveys(String idOfSurveys, GregorianCalendar from, GregorianCalendar to, Interviewer interviewer) {
        List<Survey> surveysWithId = surveys.get(idOfSurveys);
        List<Survey> surveysWithIdInterviewer = new ArrayList<Survey>();
        for (Survey surveysWithId1 : surveysWithId) {
            if (surveysWithId1.getInterviewer().equals(interviewer) && surveysWithId1.getFinishTime().before(from) == false && surveysWithId1.getFinishTime().after(to) == false) {
                surveysWithIdInterviewer.add(surveysWithId1);
            }
        }
        return surveysWithIdInterviewer;
    }
    */
    /**
     * add new group of surveys
     * @param survey new template, we want to add to repository
     * @return id of given template or "already exists", if such group already exists
     */
    public String addNewSurveyGroup(Survey survey) {
        String id = survey.getIdOfSurveys();
        if (surveys.containsKey(id)) {
            return "already exists";
        }
        else {
            surveys.put(id, new ArrayList<Survey>());
            maxNumbersOfSurveys.put(id, 0L);
            return id;
        }
    }
    
    /**
     * add new group of surveys
     * @param id id of new tamplate, we want to add to repository
     * @return true, if action was successful or false, if such group already exists
     */
    public boolean addNewSurveyGroup(String id) {
        if (surveys.containsKey(id)) {
            return false;
        }
        else {
            surveys.put(id, new ArrayList<Survey>());
            maxNumbersOfSurveys.put(id, 0L);
            return true;
        }
    }
    
    /**
     * adds new survey to repository and gives it own number
     * if list of surveys of such id doesn't exist, method adds such list
     * @param survey survey to add
     * @return number of added survey
     */
    public long addNewSurvey(Survey survey) {
        String id = survey.getIdOfSurveys();
        if(surveys.containsKey(id)==false) {
            surveys.put(id, new ArrayList<Survey>());
            maxNumbersOfSurveys.put(id, 0L);
        }
        long number = maxNumbersOfSurveys.get(id);
        number++;
        survey.setNumberOfSurvey(number);
        surveys.get(id).add(survey);
        maxNumbersOfSurveys.put(id, number);
        return number;
    }
    
    public SurveysRepository(Map<String, List<Survey>> surveys) {
        this.surveys = surveys;
        countAllMaxNumbersOfSurveys();
    }
    
    public SurveysRepository(Map<String, List<Survey>> surveys, Map<String, Long> numbersOfSurveys) throws IOException {
        this.surveys = surveys;
        this.maxNumbersOfSurveys = numbersOfSurveys;
        LoadSurveys();
    }
    public SurveysRepository() throws IOException{
        LoadSurveys();
    }

    /**
     * zwraca liste adresow MAC, ktore wystepuja w ankietach o danym id szablonu
     * @param selectedIdSurvey id szablonu, dla ktorego chcemy znalezc liste
     * @return liste adresow MAC
     */
    public List<String> getMacsOfSurvey(String selectedIdSurvey) {
        List<Survey> surveysWithId = surveys.get(selectedIdSurvey);
        List<String> listOfMacs = new ArrayList<String>();
        for (Survey survey : surveysWithId) {
            String deviceId = survey.getDeviceId();
            if (deviceId.length()>16) {
                String mac = deviceId.substring(0, 16);
                if (!listOfMacs.contains(mac)) {
                    listOfMacs.add(mac);
                }
            }
        }
        return listOfMacs;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * zwraca liste wypelnionych ankiet z danego szablonu i z danymi adresami MAC
     * @param id id szablonu ankiety
     * @param macs lista adresow MAC
     * @return lista ankiet spelniajacych podane kryteria
     */
    public List<Survey> getSurveysOfMacs(String id, List<String> macs) {
        List<Survey> surveysWithId = surveys.get(id);
        List<Survey> surveysWithMacs = new ArrayList<Survey>();
        for (Survey survey : surveysWithId) {
            String deviceId = survey.getDeviceId();
            if (deviceId.length()>16) {
                String mac = deviceId.substring(0, 16);
                if (macs.contains(mac)) {
                    surveysWithMacs.add(survey);
                }
            }
        }
        return surveysWithMacs;
    }
    
    private void LoadSurveys() throws FileNotFoundException, IOException{
        File folder = new File("C:" + File.separator + "ankieter" + File.separator + "surveys");
        File[] listOfFiles = folder.listFiles();
        JsonSurveySerializator jsonSerializator = new JsonSurveySerializator();
        //List<File> listOfSurveys = new ArrayList<File>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println("Nazwa wczytanej ankiety: "+file.getName());
                //listOfSurveys.add(file);
                String filePath = file.getPath();// Do something with child
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line = br.readLine();
                if (line!=null) {
                    System.out.println("Wczytana linia wypelnionej ankiety: " + line);
                    List<Survey> listOfSurveys = jsonSerializator.deserializeListOfSurveys(line);
                    for(Survey survey : listOfSurveys){
                        addNewSurvey(survey);
                    }
            }
        }
    }

    }
}
