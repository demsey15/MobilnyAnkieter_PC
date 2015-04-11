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
    private GregorianCalendar startTime;
    private GregorianCalendar finishTime;
    //public Interviewer interviewer;
    private String title;
    private String description;
    private String summary;
    private int numberOfSurvey;
    
    public boolean startSurvey()
    {
        return true;
    }
    
    public boolean finishSurvey()
    {
        return true;
    }

}
