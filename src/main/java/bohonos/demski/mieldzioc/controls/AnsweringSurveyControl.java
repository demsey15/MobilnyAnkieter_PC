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
		if(surveyHandler == null) throw new NullPointerException("Obiekt SurveyHandler nie mo¿e"
				+ " byæ nullem!");
		this.surveyHandler = surveyHandler;
	}

	public void startAnswering(String idOfSurveys, Interviewer interviewer){
		try {
			if((this.survey = surveyHandler.provideSurvey(idOfSurveys)) == null)
				throw new NullPointerException("Wype³nianie ankiety - nie ma ankiety o zadanym id "
						+ "grupy ankiet.");
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		survey.setInterviewer(interviewer);
		survey.startSurvey();
	}
	
	/**
	 * 
	 * @return liczba pytañ w wype³nianej ankiecie.
	 * @throws NullPointerException jeœli nie wywo³ano wczesniej metody startAnswering.
	 */
	public int getNumberOfQuestions(){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		return survey.questionListSize();
	}
	/**
	 * Zwraca pytanie wype³nianej ankiety o zadanym numerze.
	 * @param questionsNumber numer pytania (pytania liczone od 0).
	 * @return  pytanie o zadanym numerze.
	 * @throws NullPointerException jeœli nie wywo³ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException jeœli nie ma pytania o podanym numerze.
	 */
	public Question getQuestion(int questionsNumber){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		return survey.getQuestion(questionsNumber);
	}
	
	/**
	 * Dodaj odpowiedŸ do pytania jednokrotnego wyboru.
	 * @param questionsNumber numer pytania.
	 * @param answer odpowiedŸ.
	 * @return true, jeœli dodano odpowiedŸ, w przeciwnym przypadku false.
	 * @throws NullPointerException jeœli nie wywo³ano wczesniej metody startAnswering lub odpowiedŸ
	 * jest nullem.
	 * @throws IndexOutOfBoundsException jeœli nie ma pytania o podanym numerze.
	 */
	public boolean setOneChoiceQuestionAnswer(int questionsNumber, String answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		if(answer == null) throw new NullPointerException("OdpowiedŸ nie mo¿e byæ nullem");
		
		List<String> answerList = new ArrayList<String>(1);
		
		answerList.add(answer);
		return survey.getQuestion(questionsNumber).setUserAnswers(answerList);
	}
	
	/**
	 * Dodaj odpowiedŸ do pytania wielokrotnego wyboru.
	 * @param questionsNumber numer pytania.
	 * @param answer lista odpowiedzi.
	 * @return true, jeœli dodano odpowiedŸ, w przeciwnym przypadku false.
	 * @throws NullPointerException jeœli nie wywo³ano wczesniej metody startAnswering lub odpowiedŸ
	 * jest nullem.
	 * @throws IndexOutOfBoundsException jeœli nie ma pytania o podanym numerze.
	 */
	public boolean setMultipleChoiceQuestionAnswer(int questionsNumber, List<String> answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		if(answer == null) throw new NullPointerException("OdpowiedŸ nie mo¿e byæ nullem");
		
		return survey.getQuestion(questionsNumber).setUserAnswers(answer);
	}
	
	/**
	 * Dodaj odpowiedŸ do pytania typu lista.
	 * Ka¿da odpowiedŸ powinna byæ w formacie: #rowLabel# ^columnLabel^ 
	 * @param questionsNumber numer pytania.
	 * @param answer odpowiedŸ.
	 * @return true, jeœli dodano odpowiedŸ, w przeciwnym przypadku false.
	 * @throws NullPointerException jeœli nie wywo³ano wczesniej metody startAnswering lub odpowiedŸ
	 * jest nullem.
	 * @throws IndexOutOfBoundsException jeœli nie ma pytania o podanym numerze.
	 */
	public boolean setGridQuestionAnswer(int questionsNumber, List<String> answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		if(answer == null) throw new NullPointerException("OdpowiedŸ nie mo¿e byæ nullem");
		
		return survey.getQuestion(questionsNumber).setUserAnswers(answer);
	}
	
	/**
	 * Dodaj odpowiedŸ do pytania typu data i czas.
	 * miesi¹c to liczba od 1 do 12, rok powinien byæ po 1970 roku.
	  * @param questionsNumber numer pytania.
	 * @param day  dzieñ.
	 * @param month miesi¹c (od 1 do 12).
	 * @param year rok.
	 * @param hour godzina.
	 * @param minute minuta.
	 * @param second sekunda.
	 * @return true, jeœli dodano odpowiedŸ, w przeciwnym przypadku false.
	 * @throws NullPointerException jeœli nie wywo³ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException jeœli nie ma pytania o podanym numerze.
	 */
	public boolean setDateAndTimeQuestionAnswer(int questionsNumber, int day, int month, int year,
			int hour, int minute, int second){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
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
	 * Dodaj odpowiedŸ do pytania typu data (bez czasu).
	 * miesi¹c to liczba od 1 do 12, rok powinien byæ po 1970 roku.
	  * @param questionsNumber numer pytania.
	 * @param day  dzieñ.
	 * @param month miesi¹c (od 1 do 12).
	 * @param year rok.
	 * @return true, jeœli dodano odpowiedŸ, w przeciwnym przypadku false.
	 * @throws NullPointerException jeœli nie wywo³ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException jeœli nie ma pytania o podanym numerze.
	 */
	public boolean setDateQuestionAnswer(int questionsNumber, int day, int month, int year){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		
		List<String> answer = new ArrayList<String>(3);
		answer.add(String.valueOf(day));
		answer.add(String.valueOf(month));
		answer.add(String.valueOf(year));
		
		return survey.getQuestion(questionsNumber).setUserAnswers(answer);
	}
	
	/**
	 * Dodaj odpowiedŸ do pytania typu skala.
	 * Ka¿da odpowiedŸ powinna byæ w formacie: #rowLabel# ^columnLabel^ 
	 * @param questionsNumber numer pytania.
	 * @param answer odpowiedŸ.
	 * @return true, jeœli dodano odpowiedŸ, w przeciwnym przypadku false (prawdopodobnie
	 * odpowiedŸ nie spe³nia doatkowych ograniczeñ).
	 * @throws NullPointerException jeœli nie wywo³ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException jeœli nie ma pytania o podanym numerze.
	 */
	public boolean setScaleQuestionAnswer(int questionsNumber, int answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		
		List<String> answerList = new ArrayList<String>(1);
		answerList.add(String.valueOf(answer));
		return survey.getQuestion(questionsNumber).setUserAnswers(answerList);
	}
			
			
	/**
	 * Dodaj odpowiedŸ do pytania typu tekstowego.
	 * Ka¿da odpowiedŸ powinna byæ w formacie: #rowLabel# ^columnLabel^ 
	 * @param questionsNumber numer pytania.
	 * @param answer odpowiedŸ.
	 * @return true, jeœli dodano odpowiedŸ, w przeciwnym przypadku false (prawdopodobnie
	 * odpowiedŸ nie spe³nia doatkowych ograniczeñ).
	 * @throws NullPointerException jeœli nie wywo³ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException jeœli nie ma pytania o podanym numerze.
	 */
	public boolean setTextQuestionAnswer(int questionsNumber, String answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		
		List<String> answerList = new ArrayList<String>(1);
		answerList.add(answer);
		return survey.getQuestion(questionsNumber).setUserAnswers(answerList);
	}
	
	/**
	 * Zakoñcz wype³nianie ankiety.
	 * @param surveysRepository repozytorium ankiet
	 * @return true, jeœli ukoñczono ankietê i uda³o siê j¹ dodaæ do repozytorium,
	 * w przeciwnym przypadku false (nie ukoñczono jeszcze wype³niania ankiety).
	 */
	public boolean finishAnswering(SurveysRepository surveysRepository){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		if(survey.finishSurvey()){
			if(surveysRepository.addNewSurvey(survey) == -1) return false;
			survey = null;
			return true;
		}
		else return false;
	}
	
	/**
	 * 
	 * @return zwraca id grupy ankiet wype³nianej ankiety.
	 */
	public String getIdOfSurveysFillingSurvey(){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		return survey.getIdOfSurveys();
	}
	/**
	 * 
	 * @return zwracan tytu³ wype³nianej ankiety.
	 */
	public String getSurveysTitle(){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		return survey.getTitle();
	}
	
	/**
	 * 
	 * @return zwraca opis wype³nianej ankiety.
	 */
	public String getSurveysDescription(){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		return survey.getDescription();
	}
	
	/**
	 * 
	 * @return zwraca podsumowanie wype³nianej ankiety.
	 */
	public String getSurveysSummary(){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype³niania ankiety"
				+ " wywo³aj metodê startAnswering");
		return survey.getSummary();
	}
}
