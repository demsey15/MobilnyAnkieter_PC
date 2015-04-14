/**
 * 
 */
package bohonos.demski.mieldzioc.survey;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.interviewer.Interviewer;

/**
 * @author Andrzej Bohonos
 *
 */
public class Survey {
    
	
	public List<Question> questions = new ArrayList<Question>();
    private GregorianCalendar startTime = null;
    private GregorianCalendar finishTime = null;
    private Interviewer interviewer;
    private int idOfSurveys;
    private String title;
    private String description;
    private String summary;
    private int numberOfSurvey;
    
    /**
     * start filling new survey
     * @return true iff action was successful
     */
    public boolean startSurvey()
    {
        if (startTime==null && finishTime==null)
        {
            startTime = new GregorianCalendar();
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * end filling new survey
     * @return true iff action was successful
     */
    public boolean finishSurvey()
    {
        if (startTime!=null && finishTime==null)
        {
            finishTime = new GregorianCalendar();
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * status of survey
     * @return true iff survey is started
     */
    public boolean isStarted() 
    {
        if (startTime==null)
            return false;
        else
            return true;
    }
    
    /**
     * status of survey
     * @return true iff survey is finished
     */
    public boolean isFinished()
    {
        if (finishTime==null)
                return false;
        else
                return true;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
        
    public String getSummary()
    {
        return summary;
    }
    
    public void setSummary(String summary)
    {
        this.summary = summary;
    }
    
    public int getIdOfSurveys()
    {
        return idOfSurveys;
    }
    
    public void setIdOfSurveys(int idOfSurveys)
    {
        this.idOfSurveys = idOfSurveys;
    }
    
    public int getNumberOfSurvey()
    {
        return numberOfSurvey;
    }
    
    public void setNumberOfSurvey(int numberOfSurvey)
    {
        this.numberOfSurvey = numberOfSurvey;
    }
    
    public Interviewer getInterviewer()
    {
        return interviewer;
    }
    
    public void setInterviewer(Interviewer interviewer)
    {
        this.interviewer = interviewer;
    }
 
    public Survey(List<Question> questions, GregorianCalendar startTime, GregorianCalendar finishTime, Interviewer interviewer, int idOfSurveys, String title, String description, String summary, int numberOfSurvey)
    {
        this.questions = questions;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.idOfSurveys = idOfSurveys;
        this.interviewer = interviewer;
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.numberOfSurvey = numberOfSurvey;
    }
    
    /**
	 * Stwórz now¹ ankietê, podaj¹c jako argument ankietera tworz¹cego ankietê. 
	 * Jeœli tworzony jest szablon ankiety, stworzon¹ ankietê nale¿y przekazaæ klasie SurveyHandler,
	 * ona nada szablonowi numer grupy ankiet. 
	 * @author Dominik Demski
	 * @param interviewer - Ankieter, który stworzy³ dan¹ ankietê.
	 */
    public Survey(Interviewer interviewer) {
		this.interviewer = interviewer;
	}

}
