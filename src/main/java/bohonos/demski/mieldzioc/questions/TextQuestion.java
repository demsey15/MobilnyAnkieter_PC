/**
 * 
 */
package bohonos.demski.mieldzioc.questions;

import java.util.List;
import java.util.Objects;

import bohonos.demski.mieldzioc.constraints.IConstraint;
import bohonos.demski.mieldzioc.constraints.TextValidator;

/**
 * @author Dominik Demski
 * 
 */
public class TextQuestion extends Question {


	String userAnswer;
	IConstraint constraint;
	
	
	/**
	 * Create TextQuestion object with no constraint and obligatory as true.
	 * @param question text of question
	 * 	 
	 */
	public TextQuestion(String question) {
		super(question);
		this.constraint = null;
	}

	/**
	 * Create TextQuestion object with no constraint.
	 * @param question text of question
	 * @param obligatory true if answer this question is obligatory.
	 */
	public TextQuestion(String question, boolean obligatory) {
		super(question, obligatory);
		this.constraint = null;
	}
	
	/**
	 * Create TextQuestion object.
	 * @param question text of question
	 * @param obligatory true if answer this question is obligatory.
	 * @param constraint constraints of user's answer.
	 */
	public TextQuestion(String question, boolean obligatory, IConstraint constraint) {
		super(question, obligatory);
		this.constraint = constraint;
	}

	/**
	 * Create TextQuestion object.
	 * @param question text of question
	 * @param obligatory true if answer this question is obligatory.
	 * @param errorMessage message to be provided to the user when the answer is wrong or there is no answer
	 * @param hint hint for the user connected with this question
	 * @param constraint constraints of user's answer.
	 */
	public TextQuestion(String question, boolean obligatory, String errorMessage,
			String hint, IConstraint constraint) {
		super(question, obligatory, errorMessage, hint);
		this.constraint = constraint;
	}
	
	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public IConstraint getConstraint() {
		return constraint;
	}

	public void setConstraint(IConstraint constraint) {
		this.constraint = constraint;
	}

	/**
	 * Put the answer at the first index of the text list.
	 * @see bohonos.demski.mieldzioc.questions.Question#setUserAnswers(java.util.List)
	 */
	@Override
	public boolean setUserAnswers(List<String> text) {
		userAnswer = null;
		if(text == null) return false;
		if(text.size() != 1) return false;
		String answer = text.get(0);
		if(!TextValidator.validate(answer, constraint)) return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see bochonos.demski.mieldzioc.questions.Question#getAnswersAsStringList()
	 */
	@Override
	public List<String> getAnswersAsStringList() {
		return null;
	}

	/* (non-Javadoc)
	 * @see bochonos.demski.mieldzioc.questions.Question#isAnswered()
	 */
	@Override
	public boolean isAnswered() {
		return userAnswer != null;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null) return false;
		if(this.getClass() != o.getClass()) return false;
		
		TextQuestion o2 = (TextQuestion) o;
		
		return super.equals(o2) && Objects.equals(userAnswer, o2.userAnswer) &&
				Objects.equals(constraint, o2.constraint);
	}

}
