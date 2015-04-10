/**
 * 
 */
package bohonos.demski.mieldzioc.questions;

import java.util.List;
import java.util.Objects;

/**
 * @author Dominik Demski
 *
 */
public abstract class Question {
	
	public static final int ONE_CHOICE_QUESTION = 0;
	public static final int MULTIPLE_CHOICE_QUESTION = 1;
	public static final int DROP_DOWN_QUESTION = 2;
	public static final int GRID_QUESTION = 3;
	public static final int TEXT_QUESTION = 4;
	public static final int SCALE_QUESTION = 5;
	public static final int DATE_QUESTION = 6;
	public static final int TIME_QUESTION = 7;
	
	
	private boolean obligatory;
	private String errorMessage;
	private String hint;
	private String pictureUrl;
	private String question;
	
	
	public Question(String question){
		this(question, true);
	}
	
	public Question(String question, boolean obligatory){
		this.question = question;
		this.obligatory = obligatory;
	}
	
	public Question(String question, boolean obligatory, String errorMessage, String hint){
		this(question, obligatory);
		this.errorMessage = errorMessage;
		this.hint = hint;
	}

	/**
	 *  Method returns text of this question.
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 *  Set text of question.
	 *  @param question text to set
	 */
	public void setQuestion(String question) {
		this.question = question;	
	}
	/**
	 * 
	 * @return true if answer this question is obligatory, otherwise false 
	 */
	public boolean isObligatory() {
		return this.obligatory;
	}
	/**
	 * Set if answer this question is obligatory.
	 * @param obligatory true if answer this question is obligatory.
	 */
	public void setObligatory(boolean obligatory) {
		this.obligatory = obligatory;
	}
	/**
	 * 
	 * @return error message (message to be provided to the user when the answer is wrong or there is no answer) 
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}

	/**
	 * Set an error message.
	 * @param message message to be provided to the user when the answer is wrong or there is no answer
	 */
	public void setErrorMessage(String message) {
		this.errorMessage = message;
	}
	/**
	 * Set hint for the user.
	 * @param hint hint for the user connected with this question.
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}
	
	/**
	 * 
	 * @return hint for the user connected with this question
	 */
	public String getHint() {
		return this.hint;
	}

	/**
	 * Set url of picture which should be attach to the question.
	 * @param url url of picture to be attached.
	 */
	public void setPictureURL(String url) {
		this.pictureUrl = url;
	}

	/**
	 * 
	 * @return url of picture which should be attach to the question
	 */
	public String getPictureURL() {
		return this.pictureUrl;
	}
	
	/**
	 * Set user answers of this question. Be careful! All past answers will be removed regardless of result of this method. 
	 * @param text list of answers
	 * @return true is format and amount of answers are correct (then answers are added) otherwise false
	 */
	public abstract boolean setUserAnswers(List<String> text);
	
	/**
	 * 
	 * @return answers as string list in proper order, 
	 * null if there is no answers in this type of question 
	 */
	public abstract List<String> getAnswersAsStringList();
	
	/**
	 * 
	 * @return true if there are user's answers for this question, otherwise false
	 */
	public abstract boolean isAnswered();
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null) return false;
		if(this.getClass() != o.getClass()) return false;
		
		Question o2 = (Question) o;
		
		return this.obligatory == o2.obligatory && Objects.equals(this.errorMessage, o2.errorMessage) && 
				Objects.equals(this.hint, o2.hint) && Objects.equals(this.pictureUrl, o2.pictureUrl) &&
				Objects.equals(this.question, o2.question);
	}
}
