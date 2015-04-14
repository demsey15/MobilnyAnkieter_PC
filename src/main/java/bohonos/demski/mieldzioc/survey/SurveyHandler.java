/*
 * 
 */
package bohonos.demski.mieldzioc.survey;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Andrzej
 */
public class SurveyHandler {
    
    private int maxSurveysId = 0;
    private Map<Survey, Integer> surveys = new HashMap<Survey, Integer>();
    private Map<Integer, Survey> surveysId = new HashMap<Integer, Survey>();
    
    /**
     * get survey of given id
     * @param idOfSurveys survej id
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
        surveys.put(survey, 0);     //default value: 0
        return maxSurveysId;
    }
    
    public int copyOldAndCreateNewSurvey(int idOfSurveys)
    {
        if (surveysId.containsKey(idOfSurveys))
                {
                    maxSurveysId++;
                    Survey survey = surveysId.get(idOfSurveys);
                    survey.setIdOfSurveys(maxSurveysId);
                    surveysId.put(maxSurveysId, survey);
                    surveys.put(survey, 0);     //default value: 0
                    return maxSurveysId;
                }
        else
        {
            return -1;
        }
    }
}
