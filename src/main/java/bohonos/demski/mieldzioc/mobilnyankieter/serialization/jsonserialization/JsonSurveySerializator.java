package bohonos.demski.mieldzioc.mobilnyankieter.serialization.jsonserialization;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import bohonos.demski.mieldzioc.mobilnyankieter.constraints.IConstraint;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.serialization.ISerializator;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

public class JsonSurveySerializator implements ISerializator {
	private static final String QUESTION_PACKAGE_NAME;
	private static final String CONSTRAINTS_PACKAGE_NAME;

	static {
		QUESTION_PACKAGE_NAME = Question.class.getPackage().getName() + ".";
		CONSTRAINTS_PACKAGE_NAME = IConstraint.class.getPackage().getName() + ".";
	}

	public String serializeSurvey(Survey survey) {
		Gson gson = prepareGson();

		return gson.toJson(survey);
	}

	public Survey deserializeSurvey(String surveyInJson) {
		Gson gson = prepareGson();

		try {
			Survey survey = gson.fromJson(surveyInJson, Survey.class);

			if (survey == null || survey.getTitle() == null) {
				return null;
			}

			return survey;
		} catch (JsonSyntaxException e) {
			return null;
		}

	}

	public String serializeListOfSurveys(List<Survey> surveys) {
		Gson gson = prepareGson();

		return gson.toJson(surveys);
	}

	public List<Survey> deserializeListOfSurveys(String surveysInJson) {
		Gson gson = prepareGson();

		try {
                        Type listType = new TypeToken<List<Survey>>() {}.getType();
                        List<Survey> surveys = gson.fromJson(surveysInJson, listType);
			//List<Survey> surveys = (List<Survey>) gson.fromJson(surveysInJson, List.class);

			for (Survey survey : surveys) {
				if (survey.getTitle() == null) {
					return null;
				}
			}

			return surveys;
		} catch (JsonSyntaxException e) {
			return null;
		}
	}

	private Gson prepareGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.registerTypeAdapter(Question.class, new JsonAdapter<Question>(QUESTION_PACKAGE_NAME));
		gsonBuilder.registerTypeAdapter(IConstraint.class, new JsonAdapter<IConstraint>(CONSTRAINTS_PACKAGE_NAME));

		Gson gson = gsonBuilder.create();
		return gson;
	}
}
