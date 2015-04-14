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
	/**
	 * Zwraca listê odpowiedzi (nie odpowiedzi u¿ytkownika) dla pytania o zadanym indeksie.
	 * @param questionNumber numer pytania
	 * @return null, jeœli pytanie nie ma ¿adnych odpowiedzi lub podano z³y numer
	 * pytania (< 0 lub >= survey.questionListSize()), w przeciwnym przypadku zwraca
	 * listê odpowiedzi.
	 */
	public List<String> getAnswersAsList(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else return question.getAnswersAsStringList();
	}
	
	/**
	 * Metoda zwracaj¹ca odpowiedŸ o zadanym indeksie z wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param answerNumber numer odpowiedzi
	 * @return null, jeœli numer pytania lub numer odpowiedzi jest niepoprawny (nie ma takiego
	 * pytania/odpowiedzi) lub wykracza poza zakres, w przeciwnym przypadku treœæ odpowiedzi.
	 */
	public String getAnswerFromQuestion(int questionNumber, int answerNumber){
		List<String> answers;
		if((answers = getAnswersAsList(questionNumber)) == null) 
			return null;
		else{
			if(answerNumber < 0 || answerNumber >= answers.size()){
				return null;
			}
			else{
				return answers.get(answerNumber);
			}
		}
		
	}
	/**
	 * Ustawia tekst wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param questionText tekst pytania
	 * @return false, jeœli nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono treœæ pytania).
	 */
	public boolean setQuestionText(int questionNumber, String questionText){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		else{
			question.setQuestion(questionText);
			return true;
		}
	}
	
	/**
	 * Zwraca treœæ pytania.
	 * @param questionNumber numer pytania
	 * @return null, jeœli nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca treœæ pytania.
	 */
	public String getQuestionText(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else{
			return question.getQuestion();
		}
	}
	
	/**
	 * Zwraca wskazówkê do pytania.
	 * @param questionNumber numer pytania
	 * @return null, jeœli nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * wskazówkê do pytania.
	 */
	public String getQuestionHint(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else{
			return question.getHint();
		}
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
	
	/**
	 * Sprawdza, czy podany numer pytania jest poprawny, jeœli tak, to zwraca
	 * pytanie o zadanym indeksie. Uwaga: zwrócone pytanie potencjalnie mo¿e mieæ wartoœæ null - 
	 * klasa Survey tego nie kontroluje.
	 * @param questionNumber
	 * @return null, jeœli podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()),
	 * w przeciwnym przypadku zwraca pytanie o zadanym indeksie. Uwaga z uwagi na klasê Survey, pytanie mo¿e byæ nullem.
	 */
	private Question getQuestion(int questionNumber){
		if(questionNumber < 0 || questionNumber >= survey.questionListSize())
			return null;
		else return survey.getQuestion(questionNumber);
	}
}
