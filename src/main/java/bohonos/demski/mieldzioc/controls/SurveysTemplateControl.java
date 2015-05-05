/**
 * 
 */
package bohonos.demski.mieldzioc.controls;

import java.util.List;

import bohonos.demski.mieldzioc.survey.Survey;
import bohonos.demski.mieldzioc.survey.SurveyHandler;

/**
 * @author Dominik Demski
 * Klasa bêd¹ca kontrolerem. Dotyczy obs³ugi ankiet (z wyj¹tkiem tworzenia nowej i odpowiadania 
 * na istniej¹ce ankiety).
 *
 */
public class SurveysTemplateControl {

	private SurveyHandler surveyHandler;

	public SurveysTemplateControl(SurveyHandler surveyHandler) {
		this.surveyHandler = surveyHandler;
	}
	
	public List<Survey> getSurveysWithId(int surveysId){
		throw new UnsupportedOperationException("Metoda jeszcze nie jest zaimplementowana, "
				+ "czekam na metodê klasy Survey Handler");
	}
	
	
	
}
