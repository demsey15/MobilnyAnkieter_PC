package bohonos.demski.mieldzioc.mobilnyankieter.survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rits.cloning.Cloner;

public class SurveyHandler2 {
	public static final int IN_PROGRESS = 0;
	public static final int ACTIVE = 1;
	public static final int INACTIVE = 2;
	public static final int NO_SURVEY = -1;

	private List<Survey> surveys = new ArrayList<>();
	private Map<String, Integer> surveysStatus = new HashMap<>();

	private int lastCreatedSurveyNumber;

	public SurveyHandler2(int lastCreatedSurveyNumber) {
		this.lastCreatedSurveyNumber = lastCreatedSurveyNumber;
	}

	public Survey provideSurvey(String idOfSurveys) {
		for (Survey survey : surveys) {
			if (survey.getIdOfSurveys() == idOfSurveys) {
				return (new Cloner().deepClone(survey));
			}
		}

		return null;
	}

	public String addNewSurveyTemplate(Survey survey) {
		String id = survey.getDeviceId() + (lastCreatedSurveyNumber + 1);

		lastCreatedSurveyNumber++;

		survey.setIdOfSurveys(id);

		surveys.add(survey);
		surveysStatus.put(survey.getIdOfSurveys(), IN_PROGRESS);

		return id;
	}

	public boolean loadSurveyTemplate(Survey survey, int status) {
		if (surveys.contains(survey)) {
			return false;
		} else {
			surveys.add(survey);
			surveysStatus.put(survey.getIdOfSurveys(), status);

			return true;
		}
	}

	public int getSurveyStatus(String idOfSurveys) {
		Integer surveyStatus = surveysStatus.get(idOfSurveys);
		
		if (surveyStatus != null) {
			return surveyStatus;
		} else {
			return NO_SURVEY;
		}
	}

	public boolean setSurveyStatus(Survey survey, int status) {
		if (surveys.contains(survey) && status != NO_SURVEY) {
			surveysStatus.put(survey.getIdOfSurveys(), status);
			
			return true;
		} else {
			return false;
		}
	}

	public void deleteSurveyTemplate(Survey survey) {
		surveys.remove(survey);
		surveysStatus.remove(survey.getIdOfSurveys());
	}
}
