package bohonos.demski.mieldzioc.jsonserialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bohonos.demski.mieldzioc.constraints.IConstraint;
import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.survey.Survey;

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

	private Gson prepareGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.registerTypeAdapter(Question.class, new JsonAdapter<Question>(QUESTION_PACKAGE_NAME));
		gsonBuilder.registerTypeAdapter(IConstraint.class, new JsonAdapter<IConstraint>(CONSTRAINTS_PACKAGE_NAME));

		Gson gson = gsonBuilder.create();
		return gson;
	}
	
	public static void main(String[] args) {
		System.out.println(Survey.class.getPackage().getName());
	}
}
