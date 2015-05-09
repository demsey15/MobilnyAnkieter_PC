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
		if(surveyHandler == null) throw new NullPointerException("Obiekt SurveyHandler nie moøe"
				+ " byÊ nullem!");
		this.surveyHandler = surveyHandler;
	}

	public void startAnswering(String idOfSurveys, Interviewer interviewer){
		try {
			if((this.survey = surveyHandler.provideSurvey(idOfSurveys)) == null)
				throw new NullPointerException("Wype≥nianie ankiety - nie ma ankiety o zadanym id "
						+ "grupy ankiet.");
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		survey.setInterviewer(interviewer);
		survey.startSurvey();
	}
	
	/**
	 * 
	 * @return liczba pytaÒ w wype≥nianej ankiecie.
	 * @throws NullPointerException jeúli nie wywo≥ano wczesniej metody startAnswering.
	 */
	public int getNumberOfQuestions(){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype≥niania ankiety"
				+ " wywo≥aj metodÍ startAnswering");
		return survey.questionListSize();
	}
	/**
	 * Zwraca pytanie wype≥nianej ankiety o zadanym numerze.
	 * @param questionsNumber numer pytania (pytania liczone od 0).
	 * @return  pytanie o zadanym numerze.
	 * @throws NullPointerException jeúli nie wywo≥ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException jeúli nie ma pytania o podanym numerze.
	 */
	public Question getQuestion(int questionsNumber){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype≥niania ankiety"
				+ " wywo≥aj metodÍ startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		return survey.getQuestion(questionsNumber);
	}
	
	/**
	 * Dodaj odpowiedü do pytania jednokrotnego wyboru.
	 * @param questionsNumber numer pytania.
	 * @param answer odpowiedü.
	 * @return true, jeúli dodano odpowiedü, w przeciwnym przypadku false.
	 * @throws NullPointerException jeúli nie wywo≥ano wczesniej metody startAnswering lub odpowiedü
	 * jest nullem.
	 * @throws IndexOutOfBoundsException jeúli nie ma pytania o podanym numerze.
	 */
	public boolean setOneChoiceQuestionAnswer(int questionsNumber, String answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype≥niania ankiety"
				+ " wywo≥aj metodÍ startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		if(answer == null) throw new NullPointerException("Odpowiedü nie moøe byÊ nullem");
		
		List<String> answerList = new ArrayList<String>(1);
		answerList.add(answer);
		return survey.getQuestion(questionsNumber).setUserAnswers(answerList);
	}
	
	/**
	 * Dodaj odpowiedü do pytania wielokrotnego wyboru.
	 * @param questionsNumber numer pytania.
	 * @param answer odpowiedü.
	 * @return true, jeúli dodano odpowiedü, w przeciwnym przypadku false.
	 * @throws NullPointerException jeúli nie wywo≥ano wczesniej metody startAnswering lub odpowiedü
	 * jest nullem.
	 * @throws IndexOutOfBoundsException jeúli nie ma pytania o podanym numerze.
	 */
	public boolean setMultipleChoiceQuestionAnswer(int questionsNumber, List<String> answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype≥niania ankiety"
				+ " wywo≥aj metodÍ startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		if(answer == null) throw new NullPointerException("Odpowiedü nie moøe byÊ nullem");
		
		return survey.getQuestion(questionsNumber).setUserAnswers(answer);
	}
	
	/**
	 * Dodaj odpowiedü do pytania typu lista.
	 * Kaøda odpowiedü powinna byÊ w formacie: #rowLabel# ^columnLabel^ 
	 * @param questionsNumber numer pytania.
	 * @param answer odpowiedü.
	 * @return true, jeúli dodano odpowiedü, w przeciwnym przypadku false.
	 * @throws NullPointerException jeúli nie wywo≥ano wczesniej metody startAnswering lub odpowiedü
	 * jest nullem.
	 * @throws IndexOutOfBoundsException jeúli nie ma pytania o podanym numerze.
	 */
	public boolean setGridQuestionAnswer(int questionsNumber, List<String> answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype≥niania ankiety"
				+ " wywo≥aj metodÍ startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		if(answer == null) throw new NullPointerException("Odpowiedü nie moøe byÊ nullem");
		
		return survey.getQuestion(questionsNumber).setUserAnswers(answer);
	}
	
	/**
	 * Dodaj odpowiedü do pytania typu data i czas.
	 * miesiπc to liczba od 1 do 12, rok powinien byÊ po 1970 roku.
	  * @param questionsNumber numer pytania.
	 * @param day  dzieÒ.
	 * @param month miesiπc (od 1 do 12).
	 * @param year rok.
	 * @param hour godzina.
	 * @param minute minuta.
	 * @param second sekunda.
	 * @return true, jeúli dodano odpowiedü, w przeciwnym przypadku false.
	 * @throws NullPointerException jeúli nie wywo≥ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException jeúli nie ma pytania o podanym numerze.
	 */
	public boolean setDateAndTimeQuestionAnswer(int questionsNumber, int day, int month, int year,
			int hour, int minute, int second){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype≥niania ankiety"
				+ " wywo≥aj metodÍ startAnswering");
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
	 * Dodaj odpowiedü do pytania typu data (bez czasu).
	 * miesiπc to liczba od 1 do 12, rok powinien byÊ po 1970 roku.
	  * @param questionsNumber numer pytania.
	 * @param day  dzieÒ.
	 * @param month miesiπc (od 1 do 12).
	 * @param year rok.
	 * @return true, jeúli dodano odpowiedü, w przeciwnym przypadku false.
	 * @throws NullPointerException jeúli nie wywo≥ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException jeúli nie ma pytania o podanym numerze.
	 */
	public boolean setDateQuestionAnswer(int questionsNumber, int day, int month, int year){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype≥niania ankiety"
				+ " wywo≥aj metodÍ startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		
		List<String> answer = new ArrayList<String>(3);
		answer.add(String.valueOf(day));
		answer.add(String.valueOf(month));
		answer.add(String.valueOf(year));
		
		return survey.getQuestion(questionsNumber).setUserAnswers(answer);
	}
	
	/**
	 * Dodaj odpowiedü do pytania typu skala.
	 * Kaøda odpowiedü powinna byÊ w formacie: #rowLabel# ^columnLabel^ 
	 * @param questionsNumber numer pytania.
	 * @param answer odpowiedü.
	 * @return true, jeúli dodano odpowiedü, w przeciwnym przypadku false (prawdopodobnie
	 * odpowiedü nie spe≥nia doatkowych ograniczeÒ).
	 * @throws NullPointerException jeúli nie wywo≥ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException jeúli nie ma pytania o podanym numerze.
	 */
	public boolean setScaleQuestionAnswer(int questionsNumber, int answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype≥niania ankiety"
				+ " wywo≥aj metodÍ startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		
		List<String> answerList = new ArrayList<String>(1);
		answerList.add(String.valueOf(answer));
		return survey.getQuestion(questionsNumber).setUserAnswers(answerList);
	}
			
			
	/**
	 * Dodaj odpowiedü do pytania typu tekstowego.
	 * Kaøda odpowiedü powinna byÊ w formacie: #rowLabel# ^columnLabel^ 
	 * @param questionsNumber numer pytania.
	 * @param answer odpowiedü.
	 * @return true, jeúli dodano odpowiedü, w przeciwnym przypadku false (prawdopodobnie
	 * odpowiedü nie spe≥nia doatkowych ograniczeÒ).
	 * @throws NullPointerException jeúli nie wywo≥ano wczesniej metody startAnswering.
	 * @throws IndexOutOfBoundsException jeúli nie ma pytania o podanym numerze.
	 */
	public boolean setTextQuestionAnswer(int questionsNumber, String answer){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype≥niania ankiety"
				+ " wywo≥aj metodÍ startAnswering");
		if(questionsNumber < 0 || questionsNumber >= getNumberOfQuestions()) throw new 
			IndexOutOfBoundsException("Nie ma pytania o podanym numerze.");
		
		List<String> answerList = new ArrayList<String>(1);
		answerList.add(answer);
		return survey.getQuestion(questionsNumber).setUserAnswers(answerList);
	}
	
	/**
	 * ZakoÒcz wype≥nianie ankiety.
	 * @return true, jeúli odpowiedziano na wszystkie pytania obowiπzkowe (ankieta zosta≥a ukoÒczona),
	 * w przeciwnym przypadku false (nie ukoÒczono jeszcze wype≥niania ankiety).
	 */
	public boolean finishAnswering(SurveysRepository surveysRepository){
		if(survey == null) throw new NullPointerException("Przed rozpoczeciem wype≥niania ankiety"
				+ " wywo≥aj metodÍ startAnswering");
		if(survey.finishSurvey()){
			survey = null;
			return true;
		}
		else return false;
	}
}
