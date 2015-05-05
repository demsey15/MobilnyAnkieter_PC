package bohonos.demski.mieldzioc.controls;

import bohonos.demski.mieldzioc.interviewer.Interviewer;
import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.survey.Survey;
import bohonos.demski.mieldzioc.survey.SurveyHandler;

public class AnsweringSurveyControl {
	
	private SurveyHandler surveyHandler;
	private Survey survey;
	
	public AnsweringSurveyControl(SurveyHandler surveyHandler) {
		if(surveyHandler == null) throw new NullPointerException("Obiekt SurveyHandler nie mo¿e"
				+ " byæ nullem!");
		this.surveyHandler = surveyHandler;
	}

	public void startAnswering(String idOfSurveys, Interviewer interviewer){
		if((this.survey = surveyHandler.provideSurvey(idOfSurveys)) == null)
			throw new NullPointerException("Wype³nianie ankiety - nie ma ankiety o zadanym id "
					+ "grupy ankiet.");
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
	
	public void setQuestionAnswer(int q){
		
	}
	

}
