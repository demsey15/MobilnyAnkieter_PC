/**
 * 
 */
package bohonos.demski.mieldzioc.survey;

import java.util.List;
import java.util.regex.Pattern;

import bohonos.demski.mieldzioc.constraints.NumberConstraint;
import bohonos.demski.mieldzioc.constraints.TextConstraint;
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
	 * Stw�rz klas� odpowiedzialn� za tworzenie nowej ankiety.
	 * @param interviewer ankieter tworz�cy dan� ankiet�.
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
	 * Zwraca list� odpowiedzi (nie odpowiedzi u�ytkownika) dla pytania o zadanym indeksie.
	 * @param questionNumber numer pytania
	 * @return null, je�li pytanie nie ma �adnych odpowiedzi lub podano z�y numer
	 * pytania (< 0 lub >= survey.questionListSize()), w przeciwnym przypadku zwraca
	 * list� odpowiedzi.
	 */
	public List<String> getAnswersAsList(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else return question.getAnswersAsStringList();
	}
	
	/**
	 * Metoda zwracaj�ca odpowied� o zadanym indeksie z wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param answerNumber numer odpowiedzi
	 * @return null, je�li numer pytania lub numer odpowiedzi jest niepoprawny (nie ma takiego
	 * pytania/odpowiedzi) lub wykracza poza zakres, w przeciwnym przypadku tre�� odpowiedzi.
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
	 * @return false, je�li nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono tre�� pytania).
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
	 * Zwraca tre�� pytania.
	 * @param questionNumber numer pytania
	 * @return null, je�li nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca tre�� pytania.
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
	 * Zwraca wskaz�wk� do pytania.
	 * @param questionNumber numer pytania
	 * @return null, je�li nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * wskaz�wk� do pytania.
	 */
	public String getQuestionHint(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else{
			return question.getHint();
		}
	}
	
	/**
	 * Zwraca tekst b��du pytania.
	 * @param questionNumber numer pytania
	 * @return null, je�li nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * tekst b��du pytania.
	 */
	public String getQuestionErrorMessage(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else{
			return question.getErrorMessage();
		}
	}
	
	/**
	 * Ustawia wskaz�wk� do wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param hint tekst wskaz�wki
	 * @return false, je�li nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono wskaz�wk�).
	 */
	public boolean setQuestionHint(int questionNumber, String hint){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		else{
			question.setHint(hint);
			return true;
		}
	}
	
	/**
	 * Ustawia tekst b��du wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param error tekst b��du.
	 * @return false, je�li nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono tekst b��du).
	 */
	public boolean setQuestionErrorMessage(int questionNumber, String error){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		else{
			question.setErrorMessage(error);
			return true;
		}
	}
	
	/**
	 * Ustawia tekst b��du wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param obligatory true, je�li pytanie ma by� oznaczone jako obowi�zkowe (czyli czy wymagana jest odpowied� na to pytanie),
	 * w przeciwnym przypadku false.
	 * @return false, je�li nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true.
	 */
	public boolean setQuestionObligatory(int questionNumber, boolean obligatory){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		else{
			question.setObligatory(obligatory);
			return true;
		}
	}
	
	/**
	 * Sprawdza, czy pytanie oznaczono jako obowi�zkowe (czyli czy wymagana jest odpowied� na to pytanie) .
	 * @param questionNumber numer pytania
	 * @return null, je�li nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * true, je�li pytanie jest obowi�zkowe, false, je�li nie.
	 */
	public Boolean getQuestionObligatory(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else{
			return question.isObligatory();
		}
	}
	/**
	 * Ustawia ograniczenia typu tekstowego dla pytania tekstowego. Je�li nie chcesz ustawia� jakiego� parametru,
	 * wpisz null.
	 * @param questionNumber numer pytania.
	 * @param minLength wymagana minimalna d�ugo�� odpowiedzi. 
	 * @param maxLength wymagana maksymalna d�ugo�� odpowiedzi.
	 * @param regex wyra�enie regularne, kt�re musi spe�nia� odpowied�.
	 * @return false, je�li nie ma pytania o zadanym indeksie (lub jest r�wne null) lub minLength > maxLength, lub
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego, w przeciwnym przypadku - je�li uda�o si�
	 * ustawi� ograniczenia, zwraca true.
	 */
	public boolean setTextConstraints(int questionNumber, Integer minLength, Integer maxLength, Pattern regex){
		try{
			Question question;
			if((question = getQuestion(questionNumber)) == null)
				return false;
			if(question instanceof TextQuestion){
				TextQuestion textQuestion = (TextQuestion) question;
				TextConstraint textConstraint = new TextConstraint(minLength, maxLength, regex);
				textQuestion.setConstraint(textConstraint);
				return true;
			}
			else return false;
		}
		catch(IllegalArgumentException e){
			return false;
		}
	}
	/**
	 *  Ustawia ograniczenia typu liczbowego dla pytania tekstowego. Je�li nie chcesz ustawia� jakiego� parametru,
	 * wpisz null. Tekst z takim ograniczeniem nie mo�e zawiera� innych znak�w opr�cz liczby.
	 * @param questionNumber numer pytania
	 * @param minValue minimalna warto�� sprawdzanej liczby
	 * @param maxValue maksymalna warto�� sprawdzanej liczby
	 * @param mustBeInteger true, je�li liczba musi by� typu ca�kowitego, inaczej false
	 * @param notEquals liczba, kt�rej sprawdzana liczba nie mo�e si� r�wna�
	 * @param notBetweenMaxAndMinValue true, je�li sprawdzana liczba nie mo�e by� w przedziale [minValue, maxValue]
	 * @return false, je�li nie ma pytania o zadanym indeksie (lub jest r�wne null) lub
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego, w przeciwnym przypadku - je�li uda�o si�
	 * ustawi� ograniczenia, zwraca true.
	 */
	public boolean setNumberConstraints(int questionNumber, Double minValue, Double maxValue, 
			boolean mustBeInteger, Double notEquals, boolean notBetweenMaxAndMinValue){
		try{
			Question question;
			if((question = getQuestion(questionNumber)) == null)
				return false;
			if(question instanceof TextQuestion){
				TextQuestion textQuestion = (TextQuestion) question;
				NumberConstraint numberConstraint = new NumberConstraint(minValue, maxValue, mustBeInteger, notEquals, notBetweenMaxAndMinValue);
				textQuestion.setConstraint(numberConstraint);
				return true;
			}
			else return false;
		}
		catch(IllegalArgumentException e){
			return false;
		}
	}
	/**
	 * Zwraca liczb� pyta� dodanych do ankiety.
	 * @return liczba pyta� dodanych do ankiety.
	 */
	public int getQuestionsCount(){
		return survey.questionListSize();
	}
	
	/**
	 * Ustaw tytu� tworzonej ankiety.
	 * @param title tytu� ankiety
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
	 * Przesuwa pytanie o zadanym indeksie o jeden indeks do przodu.
	 * @param questionNumber numer pytania (numeracja od zera).
	 * @return false, je�li nie ma pytania o zadanym indeksie albo nie ma pytania o p�niejszym indeksie
	 * (w�wczas nie ma dok�d przesun�� pytania), w przeciwnym przypadku true.
	 */
	public boolean moveQuestionForwards(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null || (getQuestion(questionNumber + 1)) == null)
			return false;
		else{
			survey.removeQuestion(questionNumber);
			survey.addQuestion(questionNumber + 1, question);
			return true;
		}
	}
	
	/**
	 * Przesuwa pytanie o zadanym indeksie o jeden indeks do ty�u.
	 * @param questionNumber numer pytania (numeracja od zera).
	 * @return false, je�li nie ma pytania o zadanym indeksie albo nie ma pytania o wcze�niejszym indeksie
	 * (w�wczas nie ma dok�d przesun�� pytania), w przeciwnym przypadku true.
	 */
	public boolean moveQuestionBackwards(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null || (getQuestion(questionNumber - 1)) == null)
			return false;
		else{
			survey.removeQuestion(questionNumber);
			survey.addQuestion(questionNumber - 1, question);
			return true;
		}
	}
	
	/**
	 * Sprawdza, czy podany numer pytania jest poprawny, je�li tak, to zwraca
	 * pytanie o zadanym indeksie. Uwaga: zwr�cone pytanie potencjalnie mo�e mie� warto�� null - 
	 * klasa Survey tego nie kontroluje.
	 * @param questionNumber
	 * @return null, je�li podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()),
	 * w przeciwnym przypadku zwraca pytanie o zadanym indeksie. Uwaga z uwagi na klas� Survey, pytanie mo�e by� nullem.
	 */
	private Question getQuestion(int questionNumber){
		if(questionNumber < 0 || questionNumber >= survey.questionListSize())
			return null;
		else return survey.getQuestion(questionNumber);
	}
}
