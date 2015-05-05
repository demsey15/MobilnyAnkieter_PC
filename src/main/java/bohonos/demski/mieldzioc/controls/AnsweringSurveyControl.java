package bohonos.demski.mieldzioc.controls;

import bohonos.demski.mieldzioc.interviewer.Interviewer;
import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.survey.Survey;
import bohonos.demski.mieldzioc.survey.SurveyHandler;

public class AnsweringSurveyControl {
	
	private SurveyHandler surveyHandler;
	private Survey survey;
	
	public AnsweringSurveyControl(SurveyHandler surveyHandler) {
		if(surveyHandler == null) throw new NullPointerException("Obiekt SurveyHandler nie mo�e"
				+ " by� nullem!");
		this.surveyHandler = surveyHandler;
	}

	public void startAnswering(String idOfSurveys, Interviewer interviewer){
		if((this.survey = surveyHandler.provideSurvey(idOfSurveys)) == null)
			throw new NullPointerException("Wype�nianie ankiety - nie ma ankiety o zadanym id "
					+ "grupy ankiet.");
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
	
	public void setQuestionAnswer(int q){
		
	}
	

}
