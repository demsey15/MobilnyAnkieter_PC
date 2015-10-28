/**
 * 
 */
package bohonos.demski.mieldzioc.mobilnyankieter.controls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.SurveyHandler;

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
	
	/**
	 * Zwraca listê wszystkich szablonów ankiet znajduj¹cych siê w surveyHandler
	 * o zadanym id. Lista jest posortowana w porz¹dku
	 * alfabetycznym wzglêdem tytu³ów ankiet (bez znaczenia jest wielkoœæ znaków).
	 * @param surveysId id grupy ankiet.
	 * @return lista wszystkich szablonów ankiet znajduj¹cych siê w surveyHandler
	 * o zadanym id w porz¹dku alfabetycznym wzglêdem tytu³u ankiet.
	 */
	public List<Survey> getSurveysWithId(int surveysId){
		Set<Survey> set = surveyHandler.getStatusSurveys(surveysId).keySet();
		List<Survey> list = new ArrayList<Survey>();
		list.addAll(set);
		Collections.sort(list, new Comparator<Survey>() {

			public int compare(Survey o1, Survey o2) {
				String o1Title = o1.getTitle();
				String o2Title = o2.getTitle();
				
				if(o1Title == null){
					return 1;
				}
				
				if(o2Title == null){
					return -1;
				}
				
				return o1Title.compareToIgnoreCase(o2Title);
			}
		});
		return list;
	}
}
