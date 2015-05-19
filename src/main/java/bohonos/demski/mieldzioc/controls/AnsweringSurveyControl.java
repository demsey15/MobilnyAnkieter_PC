package bohonos.demski.mieldzioc.controls;

import java.util.ArrayList;
import java.util.List;

import bohonos.demski.mieldzioc.interviewer.Interviewer;
import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.survey.Survey;
import bohonos.demski.mieldzioc.survey.SurveyHandler;
import bohonos.demski.mieldzioc.survey.SurveysRepository;

public class AnsweringSurveyControl {
	
	private SurveyHandler surveyHandler;
	private Survey survey;
	
	public AnsweringSurveyControl(SurveyHandler surveyHandler) {
		if(surveyHandler == null) throw new NullPointerException("Obiekt SurveyHandler nie mo�e"
				+ " by� nullem!");
		this.surveyHandler = surveyHandler;
	}

	public void startAnswering(String idOfSurveys, Interviewer interviewer){
		try {
			if((this.survey = surveyHandler.provideSurvey(idOfSurveys)) == null)
				throw new NullPointerException("Wype�nianie ankiety - nie ma ankiety o zadanym id "
						+ "grupy ankiet.");
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		survey.setInterviewer(interviewer);
		survey.startSurvey();
	}
	
	/**
	 * 
	 * @return liczba pyta� w wype�nianej ankiecie.
	 * @throws NullPointerException je�li nie wywo�ano wczesniej metody startAnswering.
	 */
	public int getNumberOfQuestions(){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		return survey.questionListSize();
	}
	/**
	 * Zwraca pytanie wype�nianej ankiety o zadanym numerze.
	 * @param questionsNumber numer pytania (pytania liczone od 0).
	 * @return  pytanie o zadanym numerze.
	 * @throws NullPointerException je�li nie wywo�ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException je�li nie ma pytania o podanym numerze.
	 */
	public Question getQuestion(int questionsNumber){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		return survey.getQuestion(questionsNumber);
	}
	
	/**
	 * Dodaj odpowied� do pytania jednokrotnego wyboru.
	 * @param questionsNumber numer pytania.
	 * @param answer odpowied�.
	 * @return true, je�li dodano odpowied�, w przeciwnym przypadku false.
	 * @throws NullPointerException je�li nie wywo�ano wczesniej metody startAnswering lub odpowied�
	 * jest nullem.
	 * @throws IndexOutOfBoundsException je�li nie ma pytania o podanym numerze.
	 */
	public boolean setOneChoiceQuestionAnswer(int questionsNumber, String answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		if(answer == null) throw new NullPointerException("Odpowied� nie mo�e by� nullem");
		
		List<String> answerList = new ArrayList<String>(1);
		
		answerList.add(answer);
		return survey.getQuestion(questionsNumber).setUserAnswers(answerList);
	}
	
	/**
	 * Dodaj odpowied� do pytania wielokrotnego wyboru.
	 * @param questionsNumber numer pytania.
	 * @param answer lista odpowiedzi.
	 * @return true, je�li dodano odpowied�, w przeciwnym przypadku false.
	 * @throws NullPointerException je�li nie wywo�ano wczesniej metody startAnswering lub odpowied�
	 * jest nullem.
	 * @throws IndexOutOfBoundsException je�li nie ma pytania o podanym numerze.
	 */
	public boolean setMultipleChoiceQuestionAnswer(int questionsNumber, List<String> answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		if(answer == null) throw new NullPointerException("Odpowied� nie mo�e by� nullem");
		
		return survey.getQuestion(questionsNumber).setUserAnswers(answer);
	}
	
	/**
	 * Dodaj odpowied� do pytania typu lista.
	 * Ka�da odpowied� powinna by� w formacie: #rowLabel# ^columnLabel^ 
	 * @param questionsNumber numer pytania.
	 * @param answer odpowied�.
	 * @return true, je�li dodano odpowied�, w przeciwnym przypadku false.
	 * @throws NullPointerException je�li nie wywo�ano wczesniej metody startAnswering lub odpowied�
	 * jest nullem.
	 * @throws IndexOutOfBoundsException je�li nie ma pytania o podanym numerze.
	 */
	public boolean setGridQuestionAnswer(int questionsNumber, List<String> answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		if(answer == null) throw new NullPointerException("Odpowied� nie mo�e by� nullem");
		
		return survey.getQuestion(questionsNumber).setUserAnswers(answer);
	}
	
	/**
	 * Dodaj odpowied� do pytania typu data i czas.
	 * miesi�c to liczba od 1 do 12, rok powinien by� po 1970 roku.
	  * @param questionsNumber numer pytania.
	 * @param day  dzie�.
	 * @param month miesi�c (od 1 do 12).
	 * @param year rok.
	 * @param hour godzina.
	 * @param minute minuta.
	 * @param second sekunda.
	 * @return true, je�li dodano odpowied�, w przeciwnym przypadku false.
	 * @throws NullPointerException je�li nie wywo�ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException je�li nie ma pytania o podanym numerze.
	 */
	public boolean setDateAndTimeQuestionAnswer(int questionsNumber, int day, int month, int year,
			int hour, int minute, int second){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		
		List<String> answer = new ArrayList<String>(6);
		answer.add(String.valueOf(day));
		answer.add(String.valueOf(month));
		answer.add(String.valueOf(year));
		answer.add(String.valueOf(hour));
		answer.add(String.valueOf(minute));
		answer.add(String.valueOf(second));
		
		return survey.getQuestion(questionsNumber).setUserAnswers(answer);
	}
		
	/**
	 * Dodaj odpowied� do pytania typu data (bez czasu).
	 * miesi�c to liczba od 1 do 12, rok powinien by� po 1970 roku.
	  * @param questionsNumber numer pytania.
	 * @param day  dzie�.
	 * @param month miesi�c (od 1 do 12).
	 * @param year rok.
	 * @return true, je�li dodano odpowied�, w przeciwnym przypadku false.
	 * @throws NullPointerException je�li nie wywo�ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException je�li nie ma pytania o podanym numerze.
	 */
	public boolean setDateQuestionAnswer(int questionsNumber, int day, int month, int year){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		
		List<String> answer = new ArrayList<String>(3);
		answer.add(String.valueOf(day));
		answer.add(String.valueOf(month));
		answer.add(String.valueOf(year));
		
		return survey.getQuestion(questionsNumber).setUserAnswers(answer);
	}
	
	/**
	 * Dodaj odpowied� do pytania typu skala.
	 * Ka�da odpowied� powinna by� w formacie: #rowLabel# ^columnLabel^ 
	 * @param questionsNumber numer pytania.
	 * @param answer odpowied�.
	 * @return true, je�li dodano odpowied�, w przeciwnym przypadku false (prawdopodobnie
	 * odpowied� nie spe�nia doatkowych ogranicze�).
	 * @throws NullPointerException je�li nie wywo�ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException je�li nie ma pytania o podanym numerze.
	 */
	public boolean setScaleQuestionAnswer(int questionsNumber, int answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		
		List<String> answerList = new ArrayList<String>(1);
		answerList.add(String.valueOf(answer));
		return survey.getQuestion(questionsNumber).setUserAnswers(answerList);
	}
			
			
	/**
	 * Dodaj odpowied� do pytania typu tekstowego.
	 * Ka�da odpowied� powinna by� w formacie: #rowLabel# ^columnLabel^ 
	 * @param questionsNumber numer pytania.
	 * @param answer odpowied�.
	 * @return true, je�li dodano odpowied�, w przeciwnym przypadku false (prawdopodobnie
	 * odpowied� nie spe�nia doatkowych ogranicze�).
	 * @throws NullPointerException je�li nie wywo�ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException je�li nie ma pytania o podanym numerze.
	 */
	public boolean setTextQuestionAnswer(int questionsNumber, String answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		
		List<String> answerList = new ArrayList<String>(1);
		answerList.add(answer);
		return survey.getQuestion(questionsNumber).setUserAnswers(answerList);
	}
	
	/**
	 * Zako�cz wype�nianie ankiety.
	 * @param surveysRepository repozytorium ankiet
	 * @return true, je�li uko�czono ankiet� i uda�o si� j� doda� do repozytorium,
	 * w przeciwnym przypadku false (nie uko�czono jeszcze wype�niania ankiety).
	 */
	public boolean finishAnswering(SurveysRepository surveysRepository){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		if(survey.finishSurvey()){
			if(surveysRepository.addNewSurvey(survey) == -1) return false;
			survey = null;
			return true;
		}
		else return false;
	}
	
	/**
	 * 
	 * @return zwraca id grupy ankiet wype�nianej ankiety.
	 */
	public String getIdOfSurveysFillingSurvey(){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		return survey.getIdOfSurveys();
	}
	/**
	 * 
	 * @return zwracan tytu� wype�nianej ankiety.
	 */
	public String getSurveysTitle(){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		return survey.getTitle();
	}
	
	/**
	 * 
	 * @return zwraca opis wype�nianej ankiety.
	 */
	public String getSurveysDescription(){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		return survey.getDescription();
	}
	
	/**
	 * 
	 * @return zwraca podsumowanie wype�nianej ankiety.
	 */
	public String getSurveysSummary(){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype�niania ankiety"
				+ " wywo�aj metod� startAnswering");
		return survey.getSummary();
	}
}
