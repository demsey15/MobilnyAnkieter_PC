package bohonos.demski.mieldzioc.mobilnyankieter.serialization;

import java.util.List;

import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

public interface ISerializator {
	String serializeSurvey(Survey survey);

	Survey deserializeSurvey(String surveyInJson);

	String serializeListOfSurveys(List<Survey> surveys);

	List<Survey> deserializeListOfSurveys(String surveysInJson);
}
