/**
 * 
 */
package bohonos.demski.mieldzioc.survey;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import bohonos.demski.mieldzioc.constraints.NumberConstraint;
import bohonos.demski.mieldzioc.constraints.TextConstraint;
import bohonos.demski.mieldzioc.questions.DateTimeQuestion;
import bohonos.demski.mieldzioc.questions.GridQuestion;
import bohonos.demski.mieldzioc.questions.MultipleChoiceQuestion;
import bohonos.demski.mieldzioc.questions.OneChoiceQuestion;
import bohonos.demski.mieldzioc.questions.Question;
import bohonos.demski.mieldzioc.questions.ScaleQuestion;
import bohonos.demski.mieldzioc.questions.TextQuestion;

/**
 * @author Dominik Demski
 * 
 * Klasa odpowiedzialna za tworzenie nowej ankiety.
 *
 */
public class CreatingSurvey {
	Survey survey;

	/**
	 * Stwórz klasê odpowiedzialn¹ za tworzenie nowej ankiety.
	 * @param interviewer ankieter tworz¹cy dan¹ ankietê.
	 */
	public CreatingSurvey(String deviceId) {
		this.survey = new Survey(deviceId);
	}
	
	/**
	 * Add question to creating survey as a last question.
	 * @param type of question, see Question constants.
	 * @return number of added question, -1 if question hasn't been added
	 */
	public int addQuestion(int questionType){
		Question question;
		switch(questionType){
		case Question.DATE_QUESTION:
			question = new DateTimeQuestion(null, false, false, true);
			break;
		case Question.DROP_DOWN_QUESTION:
			question = new OneChoiceQuestion(null, false, new ArrayList<String>(), true);
			break;
		case Question.GRID_QUESTION:
			question = new GridQuestion(null, false);
			break;
		case Question.TEXT_QUESTION:
			question = new TextQuestion(null, false);
			break;
		case Question.MULTIPLE_CHOICE_QUESTION:
			question = new MultipleChoiceQuestion(null, false);
			break;
		case Question.ONE_CHOICE_QUESTION:
			question = new OneChoiceQuestion(null, false);
			break;
		case Question.SCALE_QUESTION:
			question = new ScaleQuestion(null, false, 0, 5);
			break;
		case Question.TIME_QUESTION:
			question = new DateTimeQuestion(null, false, true, false);
			break;
		default:
			return -1;
		}
		if(survey.addQuestion(question)){
			return survey.questionListSize() - 1;
		}
		else return -1;
	}
	/**
	 * Zwraca listê odpowiedzi (nie odpowiedzi u¿ytkownika) dla pytania o zadanym indeksie.
	 * @param questionNumber numer pytania
	 * @return null, jeœli pytanie nie ma ¿adnych odpowiedzi lub podano z³y numer
	 * pytania (< 0 lub >= survey.questionListSize()), w przeciwnym przypadku zwraca
	 * listê odpowiedzi.
	 */
	public List<String> getAnswersAsList(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else return question.getAnswersAsStringList();
	}
	
	/**
	 * Metoda zwracaj¹ca odpowiedŸ o zadanym indeksie z wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param answerNumber numer odpowiedzi
	 * @return null, jeœli numer pytania lub numer odpowiedzi jest niepoprawny (nie ma takiego
	 * pytania/odpowiedzi) lub wykracza poza zakres, w przeciwnym przypadku treœæ odpowiedzi.
	 */
	public String getAnswerFromQuestion(int questionNumber, int answerNumber){
		List<String> answers;
		if((answers = getAnswersAsList(questionNumber)) == null) 
			return null;
		else{
			if(answerNumber < 0 || answerNumber >= answers.size()){
				return null;
			}
			else{
				return answers.get(answerNumber);
			}
		}
		
	}
	/**
	 * Ustawia tekst wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param questionText tekst pytania
	 * @return false, jeœli nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono treœæ pytania).
	 */
	public boolean setQuestionText(int questionNumber, String questionText){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		else{
			question.setQuestion(questionText);
			return true;
		}
	}
	
	/**
	 * Zwraca treœæ pytania.
	 * @param questionNumber numer pytania
	 * @return null, jeœli nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca treœæ pytania.
	 */
	public String getQuestionText(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else{
			return question.getQuestion();
		}
	}
	
	/**
	 * Zwraca wskazówkê do pytania.
	 * @param questionNumber numer pytania
	 * @return null, jeœli nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * wskazówkê do pytania.
	 */
	public String getQuestionHint(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else{
			return question.getHint();
		}
	}
	
	/**
	 * Zwraca tekst b³êdu pytania.
	 * @param questionNumber numer pytania
	 * @return null, jeœli nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * tekst b³êdu pytania.
	 */
	public String getQuestionErrorMessage(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else{
			return question.getErrorMessage();
		}
	}
	
	/**
	 * Ustawia wskazówkê do wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param hint tekst wskazówki
	 * @return false, jeœli nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono wskazówkê).
	 */
	public boolean setQuestionHint(int questionNumber, String hint){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		else{
			question.setHint(hint);
			return true;
		}
	}
	
	/**
	 * Ustawia tekst b³êdu wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param error tekst b³êdu.
	 * @return false, jeœli nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono tekst b³êdu).
	 */
	public boolean setQuestionErrorMessage(int questionNumber, String error){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		else{
			question.setErrorMessage(error);
			return true;
		}
	}
	
	/**
	 * Ustawia, czy pytanie jest obowi¹zkowe.
	 * @param questionNumber numer pytania
	 * @param obligatory true, jeœli pytanie ma byæ oznaczone jako obowi¹zkowe (czyli czy wymagana jest odpowiedŸ na to pytanie),
	 * w przeciwnym przypadku false.
	 * @return false, jeœli nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true.
	 */
	public boolean setQuestionObligatory(int questionNumber, boolean obligatory){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		else{
			question.setObligatory(obligatory);
			return true;
		}
	}
	
	/**
	 * Sprawdza, czy pytanie oznaczono jako obowi¹zkowe (czyli czy wymagana jest odpowiedŸ na to pytanie) .
	 * @param questionNumber numer pytania
	 * @return null, jeœli nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * true, jeœli pytanie jest obowi¹zkowe, false, jeœli nie.
	 */
	public Boolean getQuestionObligatory(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else{
			return question.isObligatory();
		}
	}
	/**
	 * Ustawia ograniczenia typu tekstowego dla pytania tekstowego. Jeœli nie chcesz ustawiaæ jakiegoœ parametru,
	 * wpisz null.
	 * @param questionNumber numer pytania.
	 * @param minLength wymagana minimalna d³ugoœæ odpowiedzi. 
	 * @param maxLength wymagana maksymalna d³ugoœæ odpowiedzi.
	 * @param regex wyra¿enie regularne, które musi spe³niaæ odpowiedŸ.
	 * @return false, jeœli nie ma pytania o zadanym indeksie (lub jest równe null) lub minLength > maxLength, lub
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego, 
	 * lub podane wyra¿enie nie jest wyra¿eniem regularnym,
	 * w przeciwnym przypadku - jeœli uda³o siê
	 * ustawiæ ograniczenia, zwraca true.
	 */
	public boolean setTextConstraints(int questionNumber, Integer minLength, 
			Integer maxLength, String regex){
		try{
			Question question;
			if((question = getQuestion(questionNumber)) == null)
				return false;
			if(question instanceof TextQuestion){
				TextQuestion textQuestion = (TextQuestion) question;
				Pattern regex2 = (regex == null) ? null : Pattern.compile(regex);
				TextConstraint textConstraint = new TextConstraint(minLength, maxLength, regex2);
				textQuestion.setConstraint(textConstraint);
				return true;
			}
			else return false;
		}
		catch(IllegalArgumentException e){
			return false;
		}
	}
	/**
	 *  Ustawia ograniczenia typu liczbowego dla pytania tekstowego. Jeœli nie chcesz ustawiaæ jakiegoœ parametru,
	 * wpisz null. Tekst z takim ograniczeniem nie mo¿e zawieraæ innych znaków oprócz liczby.
	 * @param questionNumber numer pytania
	 * @param minValue minimalna wartoœæ sprawdzanej liczby
	 * @param maxValue maksymalna wartoœæ sprawdzanej liczby
	 * @param mustBeInteger true, jeœli liczba musi byæ typu ca³kowitego, inaczej false
	 * @param notEquals liczba, której sprawdzana liczba nie mo¿e siê równaæ
	 * @param notBetweenMaxAndMinValue true, jeœli sprawdzana liczba nie mo¿e byæ w przedziale [minValue, maxValue]
	 * @return false, jeœli nie ma pytania o zadanym indeksie (lub jest równe null) lub
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego, w przeciwnym przypadku - jeœli uda³o siê
	 * ustawiæ ograniczenia, zwraca true.
	 */
	public boolean setNumberConstraints(int questionNumber, Double minValue, Double maxValue, 
			boolean mustBeInteger, Double notEquals, boolean notBetweenMaxAndMinValue){
		try{
			Question question;
			if((question = getQuestion(questionNumber)) == null)
				return false;
			if(question instanceof TextQuestion){
				TextQuestion textQuestion = (TextQuestion) question;
				NumberConstraint numberConstraint = new NumberConstraint(minValue, maxValue, mustBeInteger, notEquals, notBetweenMaxAndMinValue);
				textQuestion.setConstraint(numberConstraint);
				return true;
			}
			else return false;
		}
		catch(IllegalArgumentException e){
			return false;
		}
	}
	/**
	 * Zwraca liczbê pytañ dodanych do ankiety.
	 * @return liczba pytañ dodanych do ankiety.
	 */
	public int getQuestionsCount(){
		return survey.questionListSize();
	}
	
	/**
	 * Ustaw tytu³ tworzonej ankiety.
	 * @param title tytu³ ankiety
	 */
	public void setSurveyTitle(String title){
		survey.setTitle(title);
	}
	/**
	 * Ustaw opis tworzonej ankiety.
	 * @param description opis ankiety
	 */
	public void setSurveyDescription(String description){
		survey.setDescription(description);
	}
	
	/**
	 * Ustaw podsumowanie tworzonej ankiety.
	 * @param summary podsumowanie
	 */
	public void setSurveySummary(String summary){
		survey.setSummary(summary);
	}	
	
	/**
	 * Przesuwa pytanie o zadanym indeksie o jeden indeks do przodu.
	 * @param questionNumber numer pytania (numeracja od zera).
	 * @return false, jeœli nie ma pytania o zadanym indeksie albo nie ma pytania o póŸniejszym indeksie
	 * (wówczas nie ma dok¹d przesun¹æ pytania), w przeciwnym przypadku true.
	 */
	public boolean moveQuestionForwards(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null || (getQuestion(questionNumber + 1)) == null)
			return false;
		else{
			survey.removeQuestion(questionNumber);
			survey.addQuestion(questionNumber + 1, question);
			return true;
		}
	}
	
	/**
	 * Przesuwa pytanie o zadanym indeksie o jeden indeks do ty³u.
	 * @param questionNumber numer pytania (numeracja od zera).
	 * @return false, jeœli nie ma pytania o zadanym indeksie albo nie ma pytania o wczeœniejszym indeksie
	 * (wówczas nie ma dok¹d przesun¹æ pytania), w przeciwnym przypadku true.
	 */
	public boolean moveQuestionBackwards(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null || (getQuestion(questionNumber - 1)) == null)
			return false;
		else{
			survey.removeQuestion(questionNumber);
			survey.addQuestion(questionNumber - 1, question);
			return true;
		}
	}
	
	/**
	 * Zwraca typ pytania (patrz sta³e wklasie Question).
	 * @param questionNumber numer pytania.
	 * @return -1, jeœli nie ma pytania o zadanym indeksie albo typ pytania jest nieznany
	 */
	public int getQuestionType(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return -1;
		else return question.getQuestionType();
	}
	
	/**
	 * Sprawdza, czy podany numer pytania jest poprawny, jeœli tak, to zwraca
	 * pytanie o zadanym indeksie. Uwaga: zwrócone pytanie potencjalnie mo¿e mieæ wartoœæ null - 
	 * klasa Survey tego nie kontroluje.
	 * @param questionNumber
	 * @return null, jeœli podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()),
	 * w przeciwnym przypadku zwraca pytanie o zadanym indeksie. Uwaga z uwagi na klasê Survey, pytanie mo¿e byæ nullem.
	 */
	public Question getQuestion(int questionNumber){
		if(questionNumber < 0 || questionNumber >= survey.questionListSize())
			return null;
		else return survey.getQuestion(questionNumber);
	}
	
	/**
	 * Ustawia etykietê przy minimalnej wartoœci pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param minLabel etykieta do ustawienia.
	 * @return false, jeœli podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
	 * pytanie o zadanym numerze nie jest typu skala, w przeciwnym przypadku true.
	 */
	public boolean setScaleQuestionMinLabel(int questionNumber, String minLabel){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		if(! (question instanceof ScaleQuestion)) return false;
		ScaleQuestion scaleQuestion = (ScaleQuestion) question;
		scaleQuestion.setMinLabel(minLabel);
		return true;
	}
	
	/**
	 * Ustawia etykietê przy maksymalnej wartoœci pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param maxLabel etykieta do ustawienia.
	 * @return false, jeœli podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
	 * pytanie o zadanym numerze nie jest typu skala, w przeciwnym przypadku true.
	 */
	public boolean setScaleQuestionMaxLabel(int questionNumber, String maxLabel){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		if(! (question instanceof ScaleQuestion)) return false;
		ScaleQuestion scaleQuestion = (ScaleQuestion) question;
		scaleQuestion.setMaxLabel(maxLabel);
		return true;
	}
	
	/**
	 * Ustawia maksymaln¹ wartoœæ pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param maxValue wartoœæ do ustawienia.
	 * @return false, jeœli podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
	 * pytanie o zadanym numerze nie jest typu skala, w przeciwnym przypadku true.
	 */
	public boolean setScaleQuestionMaxValue(int questionNumber, int maxValue){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		if(! (question instanceof ScaleQuestion)) return false;
		ScaleQuestion scaleQuestion = (ScaleQuestion) question;
		scaleQuestion.setMaxValue(maxValue);
		return true;
	}
	
	/**
	 * Ustawia minimaln¹ wartoœæ pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param minValue wartoœæ do ustawienia.
	 * @return false, jeœli podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
	 * pytanie o zadanym numerze nie jest typu skala, w przeciwnym przypadku true.
	 */
	public boolean setScaleQuestionMinValue(int questionNumber, int minValue){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		if(! (question instanceof ScaleQuestion)) return false;
		ScaleQuestion scaleQuestion = (ScaleQuestion) question;
		scaleQuestion.setMinValue(minValue);
		return true;
	}	
	
	/**
	 * Dodaj odpowiedŸ mo¿liw¹ do zaznaczenia dla pytania wyboru (jednokrotnego, wielokrotnego
	 * i listy rozwijanej).
	 * @param questionNumber numer pytania
	 * @param answer odpowiedŸ do dodania
	 * @return true, jeœli dodano odpowiedŸ, false, jeœli nie (nie ma pytania o zadanym numerze
	 * albo pytanie jest z³ego typu).
	 */
	public boolean addAnswerToChooseQuestion(int questionNumber, String answer){
		if(answer == null) throw new NullPointerException("Odpowiedz nie moze byc nullem!");
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		if(question instanceof MultipleChoiceQuestion){
			MultipleChoiceQuestion question2 = (MultipleChoiceQuestion) question;
			question2.addAnswer(answer);
			return true;
		}
		else if(question instanceof OneChoiceQuestion){
			OneChoiceQuestion question2 = (OneChoiceQuestion) question;
			question2.addAnswer(answer);
			return true;
		}
		else return false;
	}
	
	/**
	 * Dodaj odpowiedŸ mo¿liw¹ do zaznaczenia dla pytania wyboru (jednokrotnego, wielokrotnego
	 * i listy rozwijanej) w konkretne miejsce.
	 * @param questionNumber numer pytania
	 * @param answer odpowiedŸ do dodania
	 * @param position indeks, pod który ma zostaæ dodana odpowiedŸ (numeracja od zera).
	 * @return true, jeœli dodano odpowiedŸ, false, jeœli nie (nie ma pytania o zadanym numerze
	 * albo pytanie jest z³ego typu, albo position < 0 lub position >= liczba dotychczasowych odpowiedzi).
	 */
	public boolean addAnswerToChooseQuestion(int questionNumber, String answer, int position){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		if(question instanceof MultipleChoiceQuestion){
			MultipleChoiceQuestion question2 = (MultipleChoiceQuestion) question;
			return question2.addAnswer(answer, position);
		}
		else if(question instanceof OneChoiceQuestion){
			OneChoiceQuestion question2 = (OneChoiceQuestion) question;
			return question2.addAnswer(answer, position);
		}
		else return false;
	}
	/**
	 *  Usuñ odpowiedŸ mo¿liw¹ do zaznaczenia dla pytania wyboru (jednokrotnego, wielokrotnego
	 * i listy rozwijanej).
	 * @param questionNumber numer pytania
	 * @param position numer odpowiedzi (oba numery liczone od zera)
	 * @return true, jeœli odpowiedŸ usuniêto, false jeœli nie ma takiej odpowiedzi, nie ma takiego
	 * pytania lub pytanie jest z³ego typu.
	 */
	public boolean removeAnswerFromChooseQuestion(int questionNumber, int position){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		if(question instanceof MultipleChoiceQuestion){
			MultipleChoiceQuestion question2 = (MultipleChoiceQuestion) question;
			return question2.deleteAnswer(position);
		}
		else if(question instanceof OneChoiceQuestion){
			OneChoiceQuestion question2 = (OneChoiceQuestion) question;
			return question2.deleteAnswer(position);
		}
		else return false;
	}
	/**
	 * Przesuñ odpowiedŸ do pytania typu wyboru (jednoktornego, wielokrotnego,
	 * rozwijana lista) o jeden indeks do przodu.
	 * @param questionNumber numer pytania.
	 * @param answeNo numer odpowiedzi do przesuniêcia.
	 * @return true, jeœli odpowiedŸ przesuniêto, false jeœli nie (pytanie by³o z³ego typu,
	 * nie ma pytania o zadanym indeksie lub podano b³êdny indeks odpowiedzi: answeNo < 0 || 
	 * answeNo >= answers.size() || (answeNo + 1) >= answers.size()).
	 */
	public boolean moveAnswerForChooseQuestionForwards(int questionNumber, int answeNo){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		if(question instanceof MultipleChoiceQuestion){
			MultipleChoiceQuestion question2 = (MultipleChoiceQuestion) question;
			List<String> answers = question2.getAnswersAsStringList();
			if(answeNo < 0 || answeNo >= answers.size() || (answeNo + 1) >= answers.size()){
				return false;
			}
			else{
				String nextAnswer = answers.get(answeNo + 1);
				answers.add(answeNo, nextAnswer);
				return true;
			}
			
		}
		else if(question instanceof OneChoiceQuestion){
			OneChoiceQuestion question2 = (OneChoiceQuestion) question;
			List<String> answers = question2.getAnswersAsStringList();
			if(answeNo < 0 || answeNo >= answers.size() || (answeNo + 1) >= answers.size()){
				return false;
			}
			else{
				String nextAnswer = answers.get(answeNo + 1);
				answers.add(answeNo, nextAnswer);
				return true;
			}
		}
		else return false;
	}
	
	/**
	 * Przesuñ odpowiedŸ do pytania typu wyboru (jednoktornego, wielokrotnego,
	 * rozwijana lista) o jeden indeks w ty³u.
	 * @param questionNumber numer pytania.
	 * @param answeNo numer odpowiedzi do przesuniêcia.
	 * @return true, jeœli odpowiedŸ przesuniêto, false jeœli nie (pytanie by³o z³ego typu,
	 * nie ma pytania o zadanym indeksie lub podano b³êdny indeks odpowiedzi: answeNo < 1 || answeNo >= answers.size()).
	 */
	public boolean moveAnswerForChooseQuestionBackwards(int questionNumber, int answeNo){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return false;
		if(question instanceof MultipleChoiceQuestion){
			MultipleChoiceQuestion question2 = (MultipleChoiceQuestion) question;
			List<String> answers = question2.getAnswersAsStringList();
			if(answeNo < 1 || answeNo >= answers.size()){
				return false;
			}
			else{
				String previousAnswer = answers.remove(answeNo - 1);
				answers.add(answeNo, previousAnswer);
				return true;
			}
			
		}
		else if(question instanceof OneChoiceQuestion){
			OneChoiceQuestion question2 = (OneChoiceQuestion) question;
			List<String> answers = question2.getAnswersAsStringList();
			if(answeNo < 1 || answeNo >= answers.size()){
				return false;
			}
			else{
				String previousAnswer = answers.remove(answeNo - 1);
				answers.add(answeNo, previousAnswer);
				return true;
			}
		}
		else return false;
	}
	
	/**
	 * Zwraca listê mo¿liwych do wyboru odpowiedzi dla pytania o zadanym indeksie.
	 * Jeœli nie ma pytania o zadanym indeksie, wyrzuca wyj¹tek NullPointerException.
	 * @param questionNumber numer pytania.
	 * @return lista mo¿liwych odpowiedzi.
	 */
	public List<String> getAnswersAsStringList(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			throw new NullPointerException("Nie ma pytania o zadanym numerze");
		return question.getAnswersAsStringList();
	}
	
	/**
	 * Usuwa pytanie o zadanym indeksie.
	 * @param questionNumber numer pytania (pytania liczone od 0).
	 * @return true, jesli usuniêto pytanie, false, jesli zadany numer jest nieprawid³owy:
	 * questionNumber < 0 || questionNumber >= survey.questionListSize().
	 */
	public boolean removeQuestion(int questionNumber){
		if(questionNumber < 0 || questionNumber >= survey.questionListSize())
			return false;
		survey.removeQuestion(questionNumber);
		return true;
	}
	
	/**
	 * Ustawia etykiety kolumn dla pytania typu tabela.
	 * @param questionNumber numer pytania
	 * @param labels etykiety, lista nie mo¿e byæ równa null.
	 */
	public void setGridColumnLabels(int questionNumber, List<String> labels){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			throw new NullPointerException("Nie ma pytania o zadanym numerze");
		if(question.getQuestionType() == Question.GRID_QUESTION){
			GridQuestion gridQuestion = (GridQuestion) question;
			gridQuestion.setColumnLabels(labels);
		}
		else throw new IllegalArgumentException("Podane pytanie powinno byæ typu GridQuestion");
	}
	
	/**
	 * Ustawia etykiety wierszy dla pytania typu tabela.
	 * @param questionNumber numer pytania
	 * @param labels etykiety, lista nie mo¿e byæ równa null.
	 */
	public void setGridRowLabels(int questionNumber, List<String> labels){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			throw new NullPointerException("Nie ma pytania o zadanym numerze");
		if(question.getQuestionType() == Question.GRID_QUESTION){
			GridQuestion gridQuestion = (GridQuestion) question;
			gridQuestion.setRowLabels(labels);
		}
		else throw new IllegalArgumentException("Podane pytanie powinno byæ typu GridQuestion");
	}
	
	/**
	 * Zwraca etykiety wierszy dla pytania typu tabela.
	 * @param questionNumber numer pytania.
	 * @return etykiety wierszy, nigdy nie zwróci null.
	 */
	public List<String> getGridRowLabels(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			throw new NullPointerException("Nie ma pytania o zadanym numerze");
		if(question.getQuestionType() == Question.GRID_QUESTION){
			GridQuestion gridQuestion = (GridQuestion) question;
			return gridQuestion.getRowLabels();
		}
		else throw new IllegalArgumentException("Podane pytanie powinno byæ typu GridQuestion");
	}
	
	/**
	 * Zwraca etykiety kolumn dla pytania typu tabela.
	 * @param questionNumber numer pytania.
	 * @return etykiety kolumn, nigdy nie zwróci null.
	 */
	public List<String> getGridColumnLabels(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			throw new NullPointerException("Nie ma pytania o zadanym numerze");
		if(question.getQuestionType() == Question.GRID_QUESTION){
			GridQuestion gridQuestion = (GridQuestion) question;
			return gridQuestion.getColumnLabels();
		}
		else throw new IllegalArgumentException("Podane pytanie powinno byæ typu GridQuestion");
	}
	
	/**
	 * Zwraca utworzon¹ ankietê. Nie ma ona jeszcze nadanego id. Nale¿y to zrobiæ w SurveyHandler
	 * @return utworzona ankieta.
	 */
	public Survey finishCreating(){
		return survey;
	}
}
