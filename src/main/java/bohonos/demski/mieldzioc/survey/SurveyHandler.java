/*
 * 
 */
package bohonos.demski.mieldzioc.survey;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Andrzej Bohonos
 */
public class SurveyHandler {
    
    public static final int IN_PROGRESS = 0;
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 2;
    public static final int NO_SURVEY = -1;
            
    private int maxSurveysId = 0;
    private Map<Survey, Integer> surveys = new HashMap<Survey, Integer>();
    private Map<Integer, Survey> surveysId = new HashMap<Integer, Survey>();
    
    /**
     * get survey of given id
     * @param idOfSurveys survey id
     * @return survey of given id
     */
    public Survey provideSurvey(int idOfSurveys)
    {
        return surveysId.get(idOfSurveys);
    }
    
    public int getMaxSurveysId()
    {
        return maxSurveysId;
    }
    
    /**
     * add new survey template to map
     * @param survey survey to add
     * @return id of this survey template
     */
    public int addNewSurveyTemplate(Survey survey)
    {
        maxSurveysId++;
        survey.setIdOfSurveys(maxSurveysId);
        surveysId.put(maxSurveysId, survey);
        surveys.put(survey, IN_PROGRESS);     //default value
        return maxSurveysId;
    }
    
    /**
     * copy survey of given id
     * @param idOfSurveys survey id
     * @return id of new survey, if given id exists or -1 otherwise
     */
    public int copyOldAndCreateNewSurvey(int idOfSurveys)
    {
        if (surveysId.containsKey(idOfSurveys))
                {
                    maxSurveysId++;
                    Survey survey = surveysId.get(idOfSurveys);
                    survey.setIdOfSurveys(maxSurveysId);
                    surveysId.put(maxSurveysId, survey);
                    surveys.put(survey, IN_PROGRESS);     //default value
                    return maxSurveysId;
                }
        else
        {
            return NO_SURVEY;
        }
    }
    
    /**
     * get status of particular survey
     * @param idOfSurveys id of survey
     * @return status of survey, if such survey exists or -1 otherwise
     */
    public int getSurveyStatus(int idOfSurveys)
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
    public boolean setSurveyStatus(int idOfSurveys, int status)
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
}
