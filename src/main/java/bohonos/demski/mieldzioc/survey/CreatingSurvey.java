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
	
	/**
	 * Zwraca tekst b³êdu pytania.
	 * @param questionNumber numer pytania
	 * @return null, jeœli nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * tekst b³êdu pytania.
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
	 * Ustawia wskazówkê do wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param hint tekst wskazówki
	 * @return false, jeœli nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono wskazówkê).
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
	 * Ustawia tekst b³êdu wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param error tekst b³êdu.
	 * @return false, jeœli nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono tekst b³êdu).
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
	 * Ustawia tekst b³êdu wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param obligatory true, jeœli pytanie ma byæ oznaczone jako obowi¹zkowe (czyli czy wymagana jest odpowiedŸ na to pytanie),
	 * w przeciwnym przypadku false.
	 * @return false, jeœli nie ma pytania o zadanym indeksie, w przeciwnym przypadku
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
	 * Sprawdza, czy pytanie oznaczono jako obowi¹zkowe (czyli czy wymagana jest odpowiedŸ na to pytanie) .
	 * @param questionNumber numer pytania
	 * @return null, jeœli nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * true, jeœli pytanie jest obowi¹zkowe, false, jeœli nie.
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
	 * Ustawia ograniczenia typu tekstowego dla pytania tekstowego. Jeœli nie chcesz ustawiaæ jakiegoœ parametru,
	 * wpisz null.
	 * @param questionNumber numer pytania.
	 * @param minLength wymagana minimalna d³ugoœæ odpowiedzi. 
	 * @param maxLength wymagana maksymalna d³ugoœæ odpowiedzi.
	 * @param regex wyra¿enie regularne, które musi spe³niaæ odpowiedŸ.
	 * @return false, jeœli nie ma pytania o zadanym indeksie (lub jest równe null) lub minLength > maxLength, lub
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego, w przeciwnym przypadku - jeœli uda³o siê
	 * ustawiæ ograniczenia, zwraca true.
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
	 *  Ustawia ograniczenia typu liczbowego dla pytania tekstowego. Jeœli nie chcesz ustawiaæ jakiegoœ parametru,
	 * wpisz null. Tekst z takim ograniczeniem nie mo¿e zawieraæ innych znaków oprócz liczby.
	 * @param questionNumber numer pytania
	 * @param minValue minimalna wartoœæ sprawdzanej liczby
	 * @param maxValue maksymalna wartoœæ sprawdzanej liczby
	 * @param mustBeInteger true, jeœli liczba musi byæ typu ca³kowitego, inaczej false
	 * @param notEquals liczba, której sprawdzana liczba nie mo¿e siê równaæ
	 * @param notBetweenMaxAndMinValue true, jeœli sprawdzana liczba nie mo¿e byæ w przedziale [minValue, maxValue]
	 * @return false, jeœli nie ma pytania o zadanym indeksie (lub jest równe null) lub
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego, w przeciwnym przypadku - jeœli uda³o siê
	 * ustawiæ ograniczenia, zwraca true.
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
	 * Zwraca liczbê pytañ dodanych do ankiety.
	 * @return liczba pytañ dodanych do ankiety.
	 */
	public int getQuestionsCount(){
		return survey.questionListSize();
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
	 * Przesuwa pytanie o zadanym indeksie o jeden indeks do przodu.
	 * @param questionNumber numer pytania (numeracja od zera).
	 * @return false, jeœli nie ma pytania o zadanym indeksie albo nie ma pytania o póŸniejszym indeksie
	 * (wówczas nie ma dok¹d przesun¹æ pytania), w przeciwnym przypadku true.
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
	 * Przesuwa pytanie o zadanym indeksie o jeden indeks do ty³u.
	 * @param questionNumber numer pytania (numeracja od zera).
	 * @return false, jeœli nie ma pytania o zadanym indeksie albo nie ma pytania o wczeœniejszym indeksie
	 * (wówczas nie ma dok¹d przesun¹æ pytania), w przeciwnym przypadku true.
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
