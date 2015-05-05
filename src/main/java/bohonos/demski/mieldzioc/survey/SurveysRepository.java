/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.survey;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bohonos.demski.mieldzioc.interviewer.Interviewer;

/**
 *
 * @author Andrzej
 */
public class SurveysRepository {
    
    public Map<String, List<Survey>> surveys = new HashMap<String, List<Survey>>();
    public Map<String, Integer> numbersOfSurveys = new HashMap<String, Integer>();
    
    /**
     * returns list of surveys with given id
     * @param idOfSurveys given id of surveys, we are looking for
     * @return list of surveys
     */
    public List<Survey> getSurveys(String idOfSurveys) {
        return surveys.get(idOfSurveys);
    }
    
    /**
     * returns number of surveys with given id
     * @param idOfSurveys given id of surveys, we want to count
     * @return number of surveys
     */
    public int getNumberOfSurveys(String idOfSurveys) {
        return numbersOfSurveys.get(idOfSurveys);
    }
    
    /**
     * returns map of all surveys in repository
     * @return map of surveys
     */
    public Map<String, List<Survey>> getAllSurveys() {
        return surveys;
    }
    
    /**
     * returns map of numbers of all surveys in repository
     * @return map of numbers
     */
    public Map<String, Integer> getAllNumbersOfSurveys() {
        return numbersOfSurveys;
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
        for (int i=0; i<surveysWithId.size(); i++) {
            if (surveysWithId.get(i).getFinishTime().before(from)==false)
                surveysWithIdFrom.add(surveysWithId.get(i));
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
        for (int i=0; i<surveysWithId.size(); i++) {
            if (surveysWithId.get(i).getFinishTime().before(from)==false && surveysWithId.get(i).getFinishTime().after(to)==false)
                surveysWithIdFrom.add(surveysWithId.get(i));
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
     * @param interviwer interviewer, whose surveys we want to get
     * @return  map of surveys
     */
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
     * @param interviwer interviewer, whose surveys we want to get
     * @return list of surveys
     */
    public List<Survey> getInterviewerSurveys(String idOfSurveys, Interviewer interviewer) {
        List<Survey> surveysWithId = surveys.get(idOfSurveys);
        List<Survey> surveysWithIdInterviewer = new ArrayList<Survey>();
        for (int i=0; i<surveysWithId.size(); i++) {
            if (surveysWithId.get(i).getInterviewer().equals(interviewer))
                surveysWithIdInterviewer.add(surveysWithId.get(i));
        }
        return surveysWithIdInterviewer;
    }
    
    /**
     * returns map of surveys of given interviewer, finished after or in given date
     * @param from does not return surveys finishet before this date
     * @param interviwer interviewer, whose surveys we want to ge
     * @return map of surveys
     */
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
    
    /**
     * returns list of surveys with given id, of given interviewer, finished after or in given date
     * @param idOfSurveys id of group of surveys we are looking for
     * @param from does not return surveys finishet before this date
     * @param interviwer interviewer, whose surveys we want to get
     * @return list of surveys
     */
    public List<Survey> getInterviewerSurveys(String idOfSurveys, GregorianCalendar from, Interviewer interviewer) {
        List<Survey> surveysWithId = surveys.get(idOfSurveys);
        List<Survey> surveysWithIdInterviewer = new ArrayList<Survey>();
        for (int i=0; i<surveysWithId.size(); i++) {
            if (surveysWithId.get(i).getInterviewer().equals(interviewer) && surveysWithId.get(i).getFinishTime().before(from)==false)
                surveysWithIdInterviewer.add(surveysWithId.get(i));
        }
        return surveysWithIdInterviewer;
    }
    
    /**
     * returns map of surveys of given interviewer, finished between two given dates
     * @param from does not return surveys finishet before this date
     * @param to does not return surveys finishet after this date
     * @param interviewer interviewer, whose surveys we want to get
     * @return map of surveys
     */
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
    
    /**
     * returns list of surveys of given interviewer, finished between two given dates
     * @param idOfSurveys id of group of surveys we are looking for
     * @param from does not return surveys finishet before this date
     * @param to does not return surveys finishet after this date
     * @param interviewer interviewer, whose surveys we want to get
     * @return list of surveys
     */
    public List<Survey> getInterviewerSurveys(String idOfSurveys, GregorianCalendar from, GregorianCalendar to, Interviewer interviewer) {
        List<Survey> surveysWithId = surveys.get(idOfSurveys);
        List<Survey> surveysWithIdInterviewer = new ArrayList<Survey>();
        for (int i=0; i<surveysWithId.size(); i++) {
            if (surveysWithId.get(i).getInterviewer().equals(interviewer) && surveysWithId.get(i).getFinishTime().before(from)==false && surveysWithId.get(i).getFinishTime().after(to)==false)
                surveysWithIdInterviewer.add(surveysWithId.get(i));
        }
        return surveysWithIdInterviewer;
    }
    
    /**
     * add new group of surveys
     * @param survey new tamplate, we want to add to repository
     * @return id of given template or "already exists", if such group already exists
     */
    public String addNewSurveyGroup(Survey survey) {
        String id = survey.getIdOfSurveys();
        if (surveys.containsKey(id)) {
            return "already exists";
        }
        else {
            surveys.put(id, new ArrayList<Survey>());
            numbersOfSurveys.put(id, 0);
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
            numbersOfSurveys.put(id, 0);
            return true;
        }
    }
       
    public int addNewSurvey(Survey survey) {
        String id = survey.getIdOfSurveys();
        if(surveys.containsKey(id)==false) {
            surveys.put(id, new ArrayList<Survey>());
            numbersOfSurveys.put(id, 0);
        }
        int number = numbersOfSurveys.get(id);
        number++;
        survey.setNumberOfSurvey(number);
        surveys.get(id).add(survey);
        numbersOfSurveys.put(id, number);
        return number;
    }
    
    public SurveysRepository(Map<String,List<Survey>> surveys) {
        this.surveys = surveys;
    }
}
