/**
 * 
 */
package bohonos.demski.mieldzioc.survey;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import bohonos.demski.mieldzioc.questions.Question;

/**
 * @author Andrzej Bohonos
 *
 */
public class Survey {
    
    public List<Question> questions = new ArrayList<Question>();
    private GregorianCalendar startTime = null;
    private GregorianCalendar finishTime = null;
    //public Interviewer interviewer; //todo
    private int idOfSurveys;
    private String title;
    private String description;
    private String summary;
    private int numberOfSurvey;
    
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
    
    public boolean isStarted() 
    {
        if (startTime==null)
            return false;
        else
            return true;
    }
    
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
    
    public Survey()
    {
        
    }

}
