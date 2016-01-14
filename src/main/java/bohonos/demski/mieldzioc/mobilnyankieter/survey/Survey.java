/**
 * 
 */
package bohonos.demski.mieldzioc.mobilnyankieter.survey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.rits.cloning.Cloner;

import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;

/**
 * @author Andrzej Bohonos
 *
 */
public class Survey implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
	
    private List<Question> questions = new ArrayList<Question>();
    
    private GregorianCalendar startTime = null;
    private GregorianCalendar finishTime = null;
    
    private String deviceId;
    private String idOfSurveys;
    private String title;
    private String description;
    private String summary;
    
    private long numberOfSurvey;
    
    /**
 	 * Stwórz now¹ ankietê, podaj¹c jako argument numer urz¹dzenia, na którym ankieta jest tworzona. 
 	 * Jeœli tworzony jest szablon ankiety, stworzon¹ ankietê nale¿y przekazaæ klasie SurveyHandler,
 	 * ona nada szablonowi numer grupy ankiet. 
 	 * @author Dominik Demski
 	 * @param deviceId - numer urz¹dzenia, na którym stworzono dan¹ ankietê.
 	 */
     public Survey(String deviceId) {
 		this.deviceId = deviceId;
 	}
    
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
    
    public GregorianCalendar getStartTime() {
        return startTime;
    }
    
    public GregorianCalendar getFinishTime() {
        return finishTime;
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
    
    public String getIdOfSurveys()
    {
        return idOfSurveys;
    }
    
    public void setIdOfSurveys(String idOfSurveys)
    {
        this.idOfSurveys = idOfSurveys;
    }
    
    public long getNumberOfSurvey()
    {
        return numberOfSurvey;
    }
    
    public void setNumberOfSurvey(long numberOfSurvey)
    {
        this.numberOfSurvey = numberOfSurvey;
    }
     
    /**
     * @return size of questions list
     */
    public int questionListSize()
    {
        return questions.size();
    }
    
    /**
     * add new question in the end of list
     * @param question new question to add
     * @return true iff action was successful
     */
    public boolean addQuestion(Question question)
    {
        return questions.add(question);
    }
    
    /**
     * add new question in the particular place
     * @param index place where do add
     * @param question new question to add
     * @return true iff action was successful
     */
    public boolean addQuestion(int index, Question question)
    {
        if (index<questions.size())
        {
            questions.add(index, question);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * clear whole list of questions
     */
    public void questionListClear()
    {
        questions.clear();
    }
    
    /**
     * remove question from particular place
     * @param index place from where we remove
     * @return removed question
     */
    public Question removeQuestion(int index)
    {
        return questions.remove(index);
    }
    
    /**
     * remove question from the list
     * @param question question to remove
     * @return true iff action was successful
     */
    public boolean removeQuestion(Question question)
    {
        return questions.remove(question);
    }
    
    /**
     * get question of particular index without removing it
     * @param index index of question
     * @return question of given index
     */
    public Question getQuestion(int index)
    {
    	return questions.get(index);
    }
    
    /**
     * check if list contains particular question
     * @param question question we check
     * @return true iff list contains this question
     */
    public boolean questionListContains(Question question)
    {
        return questions.contains(question);
    }
    
    /**
     * return index of particular question
     * @param question question we check
     * @return index of question, if list contains this question or -1 otherwise
     */
    public int indexOfQuestion(Question question)
    {
        if (questions.contains(question))
        {
            return questions.indexOf(question);
        }
        else
        {
            return -1;
        }
    }
         
    /**
     * @return true iff question list is empty
     */
    public boolean questionListEmpty()
    {
        return questions.isEmpty();
    }
    
    /**
     * replace question of given index by other one
     * @param index place, where we want to make replacement
     * @param question new question
     * @return old question
     */
    public Question setQuestion(int index, Question question)
    {
        return questions.set(index, question);
    }
     
    /**
     * @author Dominik Demski
     * @param startTime
     */
    public void setStartTime(GregorianCalendar startTime) {
		this.startTime = startTime;
	}

    /**
     * @author Dominik Demski
     * @param finishTime
     */
	public void setFinishTime(GregorianCalendar finishTime) {
		this.finishTime = finishTime;
	}
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	/**
	 * generates html code of template
	 * @return list of html code rows
	 */
	public List<String> getHtmlCode(){
		List<String> list = new ArrayList<String>();
		list.add("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">");
		list.add("<form>");
		list.add("<br>");
		list.add("<b>" + this.getTitle() + "</b><br>");
		list.add("<br>");
		list.add(this.getDescription() + "<br>");
		list.add("<br>");
		int index=0;
		for(Question question : this.questions){
			index++;
			List<String> questionCode = question.getCode(index);
			for(String row : questionCode){
				list.add(row);
			}
		}
		list.add("<br>");
		list.add(this.getSummary() + "<br>");
		list.add("</form>");
		return list;
	}

	/**
     * 
     * @return
     * @throws CloneNotSupportedException 
     */
    @Override
    public Survey clone() throws CloneNotSupportedException {
	return (new Cloner()).deepClone(this);
    }
    
    /**
     * overwritten equals method
     * @param o other object to compare
     * @return true iff both are surveys with the some ids and numbers 
     */
    @Override
    public boolean equals(Object o)
    {
        if (this==o) 
            return true;
        if (o==null)
            return false;
        if (this.getClass()!=o.getClass())
            return false;
        Survey otherSurvey = (Survey)o;
        if (this.idOfSurveys.equals(otherSurvey.getIdOfSurveys()) && this.numberOfSurvey==otherSurvey.getNumberOfSurvey())
            return true;
        else
            return false;
    }
}
