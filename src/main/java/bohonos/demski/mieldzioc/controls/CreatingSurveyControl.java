package bohonos.demski.mieldzioc.controls;

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
	 * Metoda, kt�ra musi by� wywo�ana przed wywo�aniem kt�rejkolwiek innej metody tej klasy.
	 */
	public void createNewSurvey(){
		creatingSurvey = new CreatingSurvey(null); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}
	
	
	/**
	 * Dodaje nowe pytanie typu data do tworzonej ankiety.
	 * @return true, je�li dodano pytanie, false je�li nie (np. nie wywo�ano wczesniej metody createNewSurvey());
	 */
	public boolean addDateQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.DATE_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie typu lista rozwijana do tworzonej ankiety.
	 * @return true, je�li dodano pytanie, false je�li nie (np. nie wywo�ano wczesniej metody createNewSurvey());
	 */
	public boolean addDropDownListQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.DROP_DOWN_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie typu siatka do tworzonej ankiety.
	 * @return true, je�li dodano pytanie, false je�li nie (np. nie wywo�ano wczesniej metody createNewSurvey());
	 */
	public boolean addGridQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.GRID_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie wielokrotnego wyboru do tworzonej ankiety.
	 * @return true, je�li dodano pytanie, false je�li nie (np. nie wywo�ano wczesniej metody createNewSurvey());
	 */
	public boolean addMultipleChoiceQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.MULTIPLE_CHOICE_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie jednokrotnego wyboru do tworzonej ankiety.
	 * @return true, je�li dodano pytanie, false je�li nie (np. nie wywo�ano wczesniej metody createNewSurvey());
	 */
	public boolean addOneChoiceQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.ONE_CHOICE_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie typu pytanie ze skal� (z domy�ln� skal� 0 - 5) do tworzonej ankiety.
	 * @return true, je�li dodano pytanie, false je�li nie (np. nie wywo�ano wczesniej metody createNewSurvey());
	 */
	public boolean addScaleQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.SCALE_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie typu tekstowego do tworzonej ankiety.
	 * @return true, je�li dodano pytanie, false je�li nie (np. nie wywo�ano wczesniej metody createNewSurvey());
	 */
	public boolean addTextQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.TEXT_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Dodaje nowe pytanie typu godzina do tworzonej ankiety.
	 * @return true, je�li dodano pytanie, false je�li nie (np. nie wywo�ano wczesniej metody createNewSurvey());
	 */
	public boolean addTimeQuestion(){
		if(creatingSurvey == null) return false;
		return (creatingSurvey.addQuestion(Question.TIME_QUESTION) == -1) ? false : true;
	}
	
	/**
	 * Ustawia tekst wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param questionText tekst pytania
	 * @return false, je�li nie ma pytania o zadanym indeksie albo nie wywo�ano wczesniej metody createNewSurvey(), 
	 * w przeciwnym przypadku true (ustawiono tre�� pytania).
	 */
	public boolean setQuestionText(int questionNumber, String questionText){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setQuestionText(questionNumber, questionText);
	}
		

	/**
	 * Ustawia wskaz�wk� do wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param hint tekst wskaz�wki
	 * @return false, je�li nie ma pytania o zadanym indeksie albo nie wywo�ano wczesniej metody createNewSurvey(),
	 * w przeciwnym przypadku true (ustawiono wskaz�wk�).
	 */
	public boolean setQuestionHint(int questionNumber, String hint){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setQuestionHint(questionNumber, hint);
	}
	
	/**
	 * Ustawia tekst b��du wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param error tekst b��du.
	 * @return false, je�li nie ma pytania o zadanym indeksie albo nie wywo�ano wczesniej metody createNewSurvey(), w przeciwnym przypadku
	 * true (ustawiono tekst b��du).
	 */
	public boolean setQuestionErrorMessage(int questionNumber, String error){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setQuestionErrorMessage(questionNumber, error);
	}
	
	/**
	 * Ustawia, czy pytanie jest obowi�zkowe
	 * @param questionNumber numer pytania
	 * @param obligatory true, je�li pytanie ma by� oznaczone jako obowi�zkowe (czyli czy wymagana jest odpowied� na to pytanie),
	 * w przeciwnym przypadku false.
	 * @return false, je�li nie ma pytania o zadanym indeksie albo nie wywo�ano wczesniej metody createNewSurvey(), w przeciwnym przypadku
	 * true.
	 */
	public boolean setQuestionObligatory(int questionNumber, boolean obligatory){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setQuestionObligatory(questionNumber, obligatory);
	}
	
	/**
	 * Ustawia ograniczenia typu tekstowego dla pytania tekstowego. Je�li nie chcesz ustawia� jakiego� parametru,
	 * wpisz null.
	 * @param questionNumber numer pytania.
	 * @param minLength wymagana minimalna d�ugo�� odpowiedzi. 
	 * @param maxLength wymagana maksymalna d�ugo�� odpowiedzi.
	 * @param regex wyra�enie regularne, kt�re musi spe�nia� odpowied�.
	 * @return false, je�li nie ma pytania o zadanym indeksie (lub jest r�wne null) lub minLength > maxLength, lub
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego albo nie wywo�ano wczesniej metody createNewSurvey(),
	 *  w przeciwnym przypadku - je�li uda�o si� ustawi� ograniczenia, zwraca true.
	 */
	public boolean setTextConstraints(int questionNumber, Integer minLength, Integer maxLength, String regex){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setTextConstraints(questionNumber, minLength, maxLength, regex);
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
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego, albo nie wywo�ano wczesniej metody createNewSurvey()
	 * w przeciwnym przypadku - je�li uda�o si� ustawi� ograniczenia, zwraca true.
	 */
	public boolean setNumberConstraints(int questionNumber, Double minValue, Double maxValue, 
			boolean mustBeInteger, Double notEquals, boolean notBetweenMaxAndMinValue){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setNumberConstraints(questionNumber, minValue, maxValue, mustBeInteger, notEquals, notBetweenMaxAndMinValue);
	}
	/**
	 * Zwraca liczb� pyta� dodanych do ankiety.
	 * @return liczba pyta� dodanych do ankiety, -1, je�li nie wywo�ano wczesniej metody createNewSurvey().
	 */
	public int getQuestionsCount(){
		if(creatingSurvey == null) return -1;
		return creatingSurvey.getQuestionsCount();
	}
	
	/**
	 * Ustaw tytu� tworzonej ankiety.
	 * @param title tytu� ankiety
	 * @return true, je�li ustawiono tytu�, false, je�li nie wywo�ano wczesniej metody createNewSurvey().
	 */
	public boolean setSurveyTitle(String title){
		if(creatingSurvey == null) return false;
		creatingSurvey.setSurveyTitle(title);
		return true;
	}
	/**
	 * Ustaw opis tworzonej ankiety.
	 * @param description opis ankiety
	 * @return true, je�li ustawiono opis, false, je�li nie wywo�ano wczesniej metody createNewSurvey().
	 */
	public boolean setSurveyDescription(String description){
		if(creatingSurvey == null) return false;
		creatingSurvey.setSurveyDescription(description);
		return true;
	}
	
	/**
	 * Ustaw podsumowanie tworzonej ankiety.
	 * @param summary podsumowanie
	 * @return true, je�li ustawiono podsumowanie, false, je�li nie wywo�ano wczesniej metody createNewSurvey().
	 */
	public boolean setSurveySummary(String summary){
		if(creatingSurvey == null) return false;
		creatingSurvey.setSurveySummary(summary);
		return true;
	}	
	
	/**
	 * Przesuwa pytanie o zadanym indeksie o jeden indeks do przodu.
	 * @param questionNumber numer pytania (numeracja od zera).
	 * @return false, je�li nie ma pytania o zadanym indeksie albo nie ma pytania o p�niejszym indeksie
	 * (w�wczas nie ma dok�d przesun�� pytania), albo nie wywo�ano wczesniej metody createNewSurvey(), w przeciwnym przypadku true.
	 */
	public boolean moveQuestionForwards(int questionNumber){
		if(creatingSurvey == null) return false;
		return creatingSurvey.moveQuestionForwards(questionNumber);
	}
	
	/**
	 * Przesuwa pytanie o zadanym indeksie o jeden indeks do ty�u.
	 * @param questionNumber numer pytania (numeracja od zera).
	 * @return false, je�li nie ma pytania o zadanym indeksie albo nie ma pytania o wcze�niejszym indeksie
	 * (w�wczas nie ma dok�d przesun�� pytania), albo nie wywo�ano wczesniej metody createNewSurvey(), w przeciwnym przypadku true.
	 */
	public boolean moveQuestionBackwards(int questionNumber){
		if(creatingSurvey == null) return false;
		return creatingSurvey.moveQuestionBackwards(questionNumber);
	}
	
	/**
	 * Sprawdza, czy podany numer pytania jest poprawny, je�li tak, to zwraca
	 * pytanie o zadanym indeksie. Uwaga: zwr�cone pytanie potencjalnie mo�e mie� warto�� null - 
	 * klasa Survey tego nie kontroluje.
	 * @param questionNumber
	 * @return null, je�li podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()), albo nie wywo�ano wczesniej metody createNewSurvey(),
	 * w przeciwnym przypadku zwraca pytanie o zadanym indeksie. Uwaga z uwagi na klas� Survey, pytanie mo�e by� nullem.
	 */
	public Question getQuestion(int questionNumber){
		if(creatingSurvey == null) return null;
		return creatingSurvey.getQuestion(questionNumber);
	}
	
	/**
	 * Zwraca typ pytania (patrz sta�e wklasie Question).
	 * @param questionNumber numer pytania.
	 * @return -1, je�li nie ma pytania o zadanym indeksie albo nie wywo�ano wczesniej metody createNewSurvey(), albo typ pytania jest nieznany
	 */
	public int getQuestionType(int questionNumber){
		if(creatingSurvey == null) return -1;
		return creatingSurvey.getQuestionType(questionNumber);
	}
	
	/**
	 * Ustawia etykiet� przy minimalnej warto�ci pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param minLabel etykieta do ustawienia.
	 * @return false, je�li podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
	 * pytanie o zadanym numerze nie jest typu skala, albo nie wywo�ano wczesniej metody createNewSurvey(), w przeciwnym przypadku true.
	 */
	public boolean setScaleQuestionMinLabel(int questionNumber, String minLabel){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setScaleQuestionMinLabel(questionNumber, minLabel);
	}
	
	/**
	 * Ustawia etykiet� przy maksymalnej warto�ci pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param maxLabel etykieta do ustawienia.
	 * @return false, je�li podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
	 * pytanie o zadanym numerze nie jest typu skala, albo nie wywo�ano wczesniej metody createNewSurvey(),w przeciwnym przypadku true.
	 */
	public boolean setScaleQuestionMaxLabel(int questionNumber, String maxLabel){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setScaleQuestionMaxLabel(questionNumber, maxLabel);
	}
	
	/**
	 * Ustawia maksymaln� warto�� pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param maxValue warto�� do ustawienia.
	 * @return false, je�li podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
	 * pytanie o zadanym numerze nie jest typu skala, albo nie wywo�ano wczesniej metody createNewSurvey(), w przeciwnym przypadku true.
	 */
	public boolean setScaleQuestionMaxValue(int questionNumber, int maxValue){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setScaleQuestionMaxValue(questionNumber, maxValue);
	}
	
	/**
	 * Ustawia minimaln� warto�� pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param minValue warto�� do ustawienia.
	 * @return false, je�li podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
	 * pytanie o zadanym numerze nie jest typu skala, albo nie wywo�ano wczesniej metody createNewSurvey(), w przeciwnym przypadku true.
	 */
	public boolean setScaleQuestionMinValue(int questionNumber, int minValue){
		if(creatingSurvey == null) return false;
		return creatingSurvey.setScaleQuestionMinValue(questionNumber, minValue);
	}	


	/**
	 * Dodaj odpowied� mo�liw� do zaznaczenia dla pytania wyboru (jednokrotnego, wielokrotnego
	 * i listy rozwijanej).
	 * @param questionNumber numer pytania
	 * @param answer odpowied� do dodania
	 * @return true, je�li dodano odpowied�, false, je�li nie (nie ma pytania o zadanym numerze
	 * albo pytanie jest z�ego typu, albo nie wywo�ano wczesniej metody createNewSurvey()).
	 */
	public boolean addAnswerToChooseQuestion(int questionNumber, String answer){
		if(creatingSurvey == null) return false;
		return creatingSurvey.addAnswerToChooseQuestion(questionNumber, answer);
	}	
	/**
	 * Dodaj odpowied� mo�liw� do zaznaczenia dla pytania wyboru (jednokrotnego, wielokrotnego
	 * i listy rozwijanej) w konkretne miejsce.
	 * @param questionNumber numer pytania
	 * @param answer odpowied� do dodania
	 * @param position indeks, pod kt�ry ma zosta� dodana odpowied� (numeracja od zera).
	 * @return true, je�li dodano odpowied�, false, je�li nie (nie ma pytania o zadanym numerze
	 * albo pytanie jest z�ego typu, albo position < 0 lub position >= liczba dotychczasowych odpowiedzi,
	 *  albo nie wywo�ano wczesniej metody createNewSurvey()).
	 */
	public boolean addAnswerToChooseQuestion(int questionNumber, String answer, int position){
		if(creatingSurvey == null) return false;
		return creatingSurvey.addAnswerToChooseQuestion(questionNumber, answer, position);
	}
	/**
	 *  Usu� odpowied� mo�liw� do zaznaczenia dla pytania wyboru (jednokrotnego, wielokrotnego
	 * i listy rozwijanej).
	 * @param questionNumber numer pytania
	 * @param position numer odpowiedzi (oba numery liczone od zera)
	 * @return true, je�li odpowied� usuni�to, false je�li nie ma takiej odpowiedzi, nie ma takiego
	 * pytania lub pytanie jest z�ego typu, albo nie wywo�ano wczesniej metody createNewSurvey()).
	 */
	public boolean removeAnswerFromChooseQuestion(int questionNumber, int position){
		if(creatingSurvey == null) return false;
		return creatingSurvey.removeAnswerFromChooseQuestion(questionNumber, position);
	}
	/**
	 * Przesu� odpowied� do pytania typu wyboru (jednoktornego, wielokrotnego,
	 * rozwijana lista) o jeden indeks do przodu.
	 * @param questionNumber numer pytania.
	 * @param answeNo numer odpowiedzi do przesuni�cia.
	 * @return true, je�li odpowied� przesuni�to, false je�li nie (pytanie by�o z�ego typu,
	 * nie ma pytania o zadanym indeksie lub podano b��dny indeks odpowiedzi: answeNo < 0 || 
	 * answeNo >= answers.size() || (answeNo + 1) >= answers.size()), 
	 * albo nie wywo�ano wczesniej metody createNewSurvey()).
	 */
	public boolean moveAnswerForChooseQuestionForwards(int questionNumber, int answeNo){
		if(creatingSurvey == null) return false;
		return creatingSurvey.moveAnswerForChooseQuestionForwards(questionNumber, answeNo);	
	}
	
	/**
	 * Przesu� odpowied� do pytania typu wyboru (jednoktornego, wielokrotnego,
	 * rozwijana lista) o jeden indeks w ty�u.
	 * @param questionNumber numer pytania.
	 * @param answeNo numer odpowiedzi do przesuni�cia.
	 * @return true, je�li odpowied� przesuni�to, false je�li nie (pytanie by�o z�ego typu,
	 * nie ma pytania o zadanym indeksie lub podano b��dny indeks odpowiedzi: answeNo < 1 || answeNo >= answers.size()).
	 *albo nie wywo�ano wczesniej metody createNewSurvey()).
	 */
	public boolean moveAnswerForChooseQuestionBackwards(int questionNumber, int answeNo){
		if(creatingSurvey == null) return false;
		return creatingSurvey.moveAnswerForChooseQuestionBackwards(questionNumber, answeNo);
	}
}
