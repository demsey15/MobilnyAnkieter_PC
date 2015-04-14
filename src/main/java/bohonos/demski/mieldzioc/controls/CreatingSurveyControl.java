package bohonos.demski.mieldzioc.controls;

import java.util.regex.Pattern;

import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.survey.CreatingSurvey;



/**
 * 
 * @author Dominik Demski
 *
 */
public class CreatingSurveyControl {
	
	private static CreatingSurveyControl instance;
	private CreatingSurvey creatingSurvey;
	
	private CreatingSurveyControl() {
		
	}
	public static CreatingSurveyControl getInstance(){
		return (instance == null)? ((instance = new CreatingSurveyControl())) : instance;
	}
	
	/**
	 * Metoda, która musi byæ wywo³ana przed wywo³aniem którejkolwiek innej metody tej klasy.
	 */
	public void createNewSurvey(){
		creatingSurvey = new CreatingSurvey(null); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}
	
	
	/**
	 * Dodaje nowe pytanie typu data do tworzonej ankiety.
	 * @return true, jeœli dodano pytanie, false jeœli nie (np. nie wywo³ano wczesniej metody createNewSurvey());
	 */
	public boolean addDateQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.DATE_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie typu lista rozwijana do tworzonej ankiety.
	 * @return true, jeœli dodano pytanie, false jeœli nie (np. nie wywo³ano wczesniej metody createNewSurvey());
	 */
	public boolean addDropDownListQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.DROP_DOWN_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie typu siatka do tworzonej ankiety.
	 * @return true, jeœli dodano pytanie, false jeœli nie (np. nie wywo³ano wczesniej metody createNewSurvey());
	 */
	public boolean addGridQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.GRID_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie wielokrotnego wyboru do tworzonej ankiety.
	 * @return true, jeœli dodano pytanie, false jeœli nie (np. nie wywo³ano wczesniej metody createNewSurvey());
	 */
	public boolean addMultipleChoiceQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.MULTIPLE_CHOICE_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie jednokrotnego wyboru do tworzonej ankiety.
	 * @return true, jeœli dodano pytanie, false jeœli nie (np. nie wywo³ano wczesniej metody createNewSurvey());
	 */
	public boolean addOneChoiceQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.ONE_CHOICE_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie typu pytanie ze skal¹ (z domyœln¹ skal¹ 0 - 5) do tworzonej ankiety.
	 * @return true, jeœli dodano pytanie, false jeœli nie (np. nie wywo³ano wczesniej metody createNewSurvey());
	 */
	public boolean addScaleQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.SCALE_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie typu tekstowego do tworzonej ankiety.
	 * @return true, jeœli dodano pytanie, false jeœli nie (np. nie wywo³ano wczesniej metody createNewSurvey());
	 */
	public boolean addTextQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.TEXT_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie typu godzina do tworzonej ankiety.
	 * @return true, jeœli dodano pytanie, false jeœli nie (np. nie wywo³ano wczesniej metody createNewSurvey());
	 */
	public boolean addTimeQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.TIME_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Ustawia tekst wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param questionText tekst pytania
	 * @return false, jeœli nie ma pytania o zadanym indeksie albo nie wywo³ano wczesniej metody createNewSurvey(), 
	 * w przeciwnym przypadku true (ustawiono treœæ pytania).
	 */
	public boolean setQuestionText(int questionNumber, String questionText){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setQuestionText(questionNumber, questionText);
	}
		

	/**
	 * Ustawia wskazówkê do wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param hint tekst wskazówki
	 * @return false, jeœli nie ma pytania o zadanym indeksie albo nie wywo³ano wczesniej metody createNewSurvey(),
	 * w przeciwnym przypadku true (ustawiono wskazówkê).
	 */
	public boolean setQuestionHint(int questionNumber, String hint){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setQuestionHint(questionNumber, hint);
	}
	
	/**
	 * Ustawia tekst b³êdu wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param error tekst b³êdu.
	 * @return false, jeœli nie ma pytania o zadanym indeksie albo nie wywo³ano wczesniej metody createNewSurvey(), w przeciwnym przypadku
	 * true (ustawiono tekst b³êdu).
	 */
	public boolean setQuestionErrorMessage(int questionNumber, String error){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setQuestionErrorMessage(questionNumber, error);
	}
	
	/**
	 * Ustawia, czy pytanie jest obowi¹zkowe
	 * @param questionNumber numer pytania
	 * @param obligatory true, jeœli pytanie ma byæ oznaczone jako obowi¹zkowe (czyli czy wymagana jest odpowiedŸ na to pytanie),
	 * w przeciwnym przypadku false.
	 * @return false, jeœli nie ma pytania o zadanym indeksie albo nie wywo³ano wczesniej metody createNewSurvey(), w przeciwnym przypadku
	 * true.
	 */
	public boolean setQuestionObligatory(int questionNumber, boolean obligatory){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setQuestionObligatory(questionNumber, obligatory);
	}
	
	/**
	 * Ustawia ograniczenia typu tekstowego dla pytania tekstowego. Jeœli nie chcesz ustawiaæ jakiegoœ parametru,
	 * wpisz null.
	 * @param questionNumber numer pytania.
	 * @param minLength wymagana minimalna d³ugoœæ odpowiedzi. 
	 * @param maxLength wymagana maksymalna d³ugoœæ odpowiedzi.
	 * @param regex wyra¿enie regularne, które musi spe³niaæ odpowiedŸ.
	 * @return false, jeœli nie ma pytania o zadanym indeksie (lub jest równe null) lub minLength > maxLength, lub
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego albo nie wywo³ano wczesniej metody createNewSurvey(),
	 *  w przeciwnym przypadku - jeœli uda³o siê ustawiæ ograniczenia, zwraca true.
	 */
	public boolean setTextConstraints(int questionNumber, Integer minLength, Integer maxLength, Pattern regex){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setTextConstraints(questionNumber, minLength, maxLength, regex);
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
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego, albo nie wywo³ano wczesniej metody createNewSurvey()
	 * w przeciwnym przypadku - jeœli uda³o siê ustawiæ ograniczenia, zwraca true.
	 */
	public boolean setNumberConstraints(int questionNumber, Double minValue, Double maxValue, 
			boolean mustBeInteger, Double notEquals, boolean notBetweenMaxAndMinValue){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setNumberConstraints(getQuestionsCount(), minValue, maxValue, mustBeInteger, notEquals, notBetweenMaxAndMinValue);
	}
	/**
	 * Zwraca liczbê pytañ dodanych do ankiety.
	 * @return liczba pytañ dodanych do ankiety, -1, jeœli nie wywo³ano wczesniej metody createNewSurvey().
	 */
	public int getQuestionsCount(){
		if(creatingSurvey == null) return -1;
		return creatingSurvey.getQuestionsCount();
	}
	
	/**
	 * Ustaw tytu³ tworzonej ankiety.
	 * @param title tytu³ ankiety
	 * @return true, jeœli ustawiono tytu³, false, jeœli nie wywo³ano wczesniej metody createNewSurvey().
	 */
	public boolean setSurveyTitle(String title){
		if(creatingSurvey == null) return false;
		creatingSurvey.setSurveyTitle(title);
		return true;
	}
	/**
	 * Ustaw opis tworzonej ankiety.
	 * @param description opis ankiety
	 * @return true, jeœli ustawiono opis, false, jeœli nie wywo³ano wczesniej metody createNewSurvey().
	 */
	public boolean setSurveyDescription(String description){
		if(creatingSurvey == null) return false;
		creatingSurvey.setSurveyDescription(description);
		return true;
	}
	
	/**
	 * Ustaw podsumowanie tworzonej ankiety.
	 * @param summary podsumowanie
	 * @return true, jeœli ustawiono podsumowanie, false, jeœli nie wywo³ano wczesniej metody createNewSurvey().
	 */
	public boolean setSurveySummary(String summary){
		if(creatingSurvey == null) return false;
		creatingSurvey.setSurveySummary(summary);
		return true;
	}	
	
	/**
	 * Przesuwa pytanie o zadanym indeksie o jeden indeks do przodu.
	 * @param questionNumber numer pytania (numeracja od zera).
	 * @return false, jeœli nie ma pytania o zadanym indeksie albo nie ma pytania o póŸniejszym indeksie
	 * (wówczas nie ma dok¹d przesun¹æ pytania), albo nie wywo³ano wczesniej metody createNewSurvey(), w przeciwnym przypadku true.
	 */
	public boolean moveQuestionForwards(int questionNumber){
		if(creatingSurvey == null) return false;
		return creatingSurvey.moveQuestionForwards(questionNumber);
	}
	
	/**
	 * Przesuwa pytanie o zadanym indeksie o jeden indeks do ty³u.
	 * @param questionNumber numer pytania (numeracja od zera).
	 * @return false, jeœli nie ma pytania o zadanym indeksie albo nie ma pytania o wczeœniejszym indeksie
	 * (wówczas nie ma dok¹d przesun¹æ pytania), albo nie wywo³ano wczesniej metody createNewSurvey(), w przeciwnym przypadku true.
	 */
	public boolean moveQuestionBackwards(int questionNumber){
		if(creatingSurvey == null) return false;
		return creatingSurvey.moveQuestionBackwards(questionNumber);
	}
	
	/**
	 * Sprawdza, czy podany numer pytania jest poprawny, jeœli tak, to zwraca
	 * pytanie o zadanym indeksie. Uwaga: zwrócone pytanie potencjalnie mo¿e mieæ wartoœæ null - 
	 * klasa Survey tego nie kontroluje.
	 * @param questionNumber
	 * @return null, jeœli podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()), albo nie wywo³ano wczesniej metody createNewSurvey(),
	 * w przeciwnym przypadku zwraca pytanie o zadanym indeksie. Uwaga z uwagi na klasê Survey, pytanie mo¿e byæ nullem.
	 */
	public Question getQuestion(int questionNumber){
		if(creatingSurvey == null) return null;
		return creatingSurvey.getQuestion(questionNumber);
	}
	
	/**
	 * Zwraca typ pytania (patrz sta³e wklasie Question).
	 * @param questionNumber numer pytania.
	 * @return -1, jeœli nie ma pytania o zadanym indeksie albo nie wywo³ano wczesniej metody createNewSurvey(), albo typ pytania jest nieznany
	 */
	public int getQuestionType(int questionNumber){
		if(creatingSurvey == null) return -1;
		return creatingSurvey.getQuestionType(questionNumber);
	}
	


}
