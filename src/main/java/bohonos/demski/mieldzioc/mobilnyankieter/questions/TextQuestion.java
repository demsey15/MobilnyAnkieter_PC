package bohonos.demski.mieldzioc.mobilnyankieter.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.rits.cloning.Cloner;

import bohonos.demski.mieldzioc.mobilnyankieter.constraints.IConstraint;
import bohonos.demski.mieldzioc.mobilnyankieter.constraints.TextValidator;

/**
 * @author Dominik Demski
 * 
 */
public class TextQuestion extends Question {
	private static final long serialVersionUID = 1L;
	
	private String userAnswer;
	private IConstraint constraint;
	
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
	 * @param hint hint for the user connected with this question
	 * @param constraint constraints of user's answer.
	 */
	public TextQuestion(String question, boolean obligatory,
			String hint, IConstraint constraint) {
		super(question, obligatory, hint);
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
	 * @see bohonos.demski.mieldzioc.mobilnyankieter.questions.Question#setUserAnswers(java.util.List)
	 */
	@Override
	public boolean setUserAnswers(List<String> text) {
		userAnswer = null;
		
		if(text == null){
			return false;
		}
		
		if(text.size() != 1){
			return false;
		}
		
		String answer = text.get(0);
		
		if(!TextValidator.validate(answer, constraint)){
			return false;
		}else{
			userAnswer = answer;
			
			return true;
		}
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
	
	@Override
	public TextQuestion clone() throws CloneNotSupportedException {
		return (new Cloner()).deepClone(this);
	}

	/**
	 * Zwraca odpowied� udzielon� przez u�ytkownika w postaci listy.
	 */
	@Override
	public List<String> getUserAnswersAsStringList() {
		List<String> list = new ArrayList<String>(1);
		if(isAnswered()) list.add(userAnswer);
		return list;
	}
	
	@Override
	public List<String> getCode(int index){
		List<String> list = new ArrayList<String>();
		list.add(this.getQuestion()+"<br>");
		list.add("<input type=\"text\" name=\"" + index +"\"><br>");
		list.add("<br>");
		return list;
	}
}
