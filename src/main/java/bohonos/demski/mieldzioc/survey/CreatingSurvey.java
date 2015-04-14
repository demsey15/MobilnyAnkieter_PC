/**
 * 
 */
package bohonos.demski.mieldzioc.survey;

import java.util.List;

import bohonos.demski.mieldzioc.interviewer.Interviewer;
import bohonos.demski.mieldzioc.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.questions.GridQuestion;
import bohonos.demski.mieldzioc.questions.MultipleChoiceQuestion;
import bohonos.demski.mieldzioc.questions.OneChoiceQuestion;
import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.questions.ScaleQuestion;
import bohonos.demski.mieldzioc.questions.TextQuestion;

/**
 * @author Dominik Demski
 *
 */
public class CreatingSurvey {
	Survey survey;

	/**
	 * Stwórz klasê odpowiedzialn¹ za tworzenie nowej ankiety.
	 * @param interviewer ankieter tworz¹cy dan¹ ankietê.
	 */
	public CreatingSurvey(Interviewer interviewer) {
		this.survey = new Survey(interviewer);
	}
	
	/**
	 * Add question to creating survey as a last question.
	 * @param type of question, see Question constants.
	 * @return number of added question, -1 if question hasn't been added
	 */
	public int addQuestion(int questionType){
		Question question;
		switch(questionType){
		case Question.DATE_QUESTION:
			question = new DateTimeQuestion(null, false, false, true);
			break;
		case Question.DROP_DOWN_QUESTION:
			question = new OneChoiceQuestion(null, false, null, true);
			break;
		case Question.GRID_QUESTION:
			question = new GridQuestion(null, false);
			break;
		case Question.TEXT_QUESTION:
			question = new TextQuestion(null, false);
			break;
		case Question.MULTIPLE_CHOICE_QUESTION:
			question = new MultipleChoiceQuestion(null, true);
			break;
		case Question.ONE_CHOICE_QUESTION:
			question = new OneChoiceQuestion(null, false);
			break;
		case Question.SCALE_QUESTION:
			question = new ScaleQuestion(null, false, 0, 5);
			break;
		case Question.TIME_QUESTION:
			question = new DateTimeQuestion(null, false, true, false);
			break;
		default:
			return -1;
		}
		if(survey.addQuestion(question)){
			return survey.questionListSize() - 1;
		}
		else return -1;
	}
	
	public List<String> getAnswersAsList(int questionNumber){
		//Question question = survey.
	throw new UnsupportedOperationException();
	}
	
	public String getAnswerFromQuestion(int questionNumber, int answerNumber){
		throw new UnsupportedOperationException();
	}
	
	public boolean setQuestionText(int questionNumber){
		throw new UnsupportedOperationException();
	}
	
	public String getQuestionText(int questionNumber){
		throw new UnsupportedOperationException();
	}
	
	public String getQuestionHint(int questionNumber){
		throw new UnsupportedOperationException();
	}
	
	public String getQuestionError(int questionNumber){
		throw new UnsupportedOperationException();
	}
	
	public boolean setQuestionHint(int questionNumber, String hint){
		throw new UnsupportedOperationException();
	}
	
	public boolean setQuestionError(int questionNumber, String error){
		throw new UnsupportedOperationException();
	}
	
	public boolean setQuestionObligatory(int questionNumber, boolean obligatory){
		throw new UnsupportedOperationException();
	}
	
	public boolean getQuestionObligatory(int questionNumber){
		throw new UnsupportedOperationException();
	}
	
	public boolean setTextConstraints(){
		throw new UnsupportedOperationException();
	}
	
	public boolean setNumberConstraints(){
		throw new UnsupportedOperationException();
	}
	
	public int getQuestionsCount(){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Ustaw tytu³ tworzonej ankiety.
	 * @param title tytu³ ankiety
	 */
	public void setSurveyTitle(String title){
		survey.setTitle(title);
	}
	/**
	 * Ustaw opis tworzonej ankiety.
	 * @param description opis ankiety
	 */
	public void setSurveyDescription(String description){
		survey.setDescription(description);
	}
	
	/**
	 * Ustaw podsumowanie tworzonej ankiety.
	 * @param summary podsumowanie
	 */
	public void setSurveySummary(String summary){
		survey.setSummary(summary);
	}	
}
