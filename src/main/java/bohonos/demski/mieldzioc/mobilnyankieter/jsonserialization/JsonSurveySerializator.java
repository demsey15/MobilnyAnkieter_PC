package bohonos.demski.mieldzioc.mobilnyankieter.jsonserialization;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bohonos.demski.mieldzioc.mobilnyankieter.constraints.IConstraint;
import bohonos.demski.mieldzioc.mobilnyankieter.questions.Question;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

public class JsonSurveySerializator {
	private static final String QUESTION_PACKAGE_NAME;
	private static final String CONSTRAINTS_PACKAGE_NAME;

	static{
		QUESTION_PACKAGE_NAME = Question.class.getPackage().getName() + ".";
		CONSTRAINTS_PACKAGE_NAME = IConstraint.class.getPackage().getName() + ".";
	}
	
	public String serializeSurvey(Survey survey) {
		Gson gson = prepareGson();

		return gson.toJson(survey);
	}

	public Survey deserializeSurvey(String surveyInJson) {
		Gson gson = prepareGson();

		return gson.fromJson(surveyInJson, Survey.class);
	}
	
	public String serializeListOfSurveys(List<Survey> surveys){
		Gson gson = prepareGson();
		
		return gson.toJson(surveys);
	}
	
	public List<Survey> deserializeListOfSurveys(String surveysInJson){
		Gson gson = prepareGson();
		
		return gson.fromJson(surveysInJson, List.class);
	}

	private Gson prepareGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.registerTypeAdapter(Question.class, new JsonAdapter<Question>(QUESTION_PACKAGE_NAME));
		gsonBuilder.registerTypeAdapter(IConstraint.class, new JsonAdapter<IConstraint>(CONSTRAINTS_PACKAGE_NAME));

		Gson gson = gsonBuilder.create();
		return gson;
	}
}
