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
	 * Stw�rz klas� odpowiedzialn� za tworzenie nowej ankiety.
	 * @param interviewer ankieter tworz�cy dan� ankiet�.
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
	 * Zwraca list� odpowiedzi (nie odpowiedzi u�ytkownika) dla pytania o zadanym indeksie.
	 * @param questionNumber numer pytania
	 * @return null, je�li pytanie nie ma �adnych odpowiedzi lub podano z�y numer
	 * pytania (< 0 lub >= survey.questionListSize()), w przeciwnym przypadku zwraca
	 * list� odpowiedzi.
	 */
	public List<String> getAnswersAsList(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return null;
		else return question.getAnswersAsStringList();
	}
	
	/**
	 * Metoda zwracaj�ca odpowied� o zadanym indeksie z wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param answerNumber numer odpowiedzi
	 * @return null, je�li numer pytania lub numer odpowiedzi jest niepoprawny (nie ma takiego
	 * pytania/odpowiedzi) lub wykracza poza zakres, w przeciwnym przypadku tre�� odpowiedzi.
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
	 * @return false, je�li nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono tre�� pytania).
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
	 * Zwraca tre�� pytania.
	 * @param questionNumber numer pytania
	 * @return null, je�li nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca tre�� pytania.
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
	 * Zwraca wskaz�wk� do pytania.
	 * @param questionNumber numer pytania
	 * @return null, je�li nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * wskaz�wk� do pytania.
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
	 * Zwraca tekst b��du pytania.
	 * @param questionNumber numer pytania
	 * @return null, je�li nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * tekst b��du pytania.
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
	 * Ustawia wskaz�wk� do wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param hint tekst wskaz�wki
	 * @return false, je�li nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono wskaz�wk�).
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
	 * Ustawia tekst b��du wybranego pytania.
	 * @param questionNumber numer pytania
	 * @param error tekst b��du.
	 * @return false, je�li nie ma pytania o zadanym indeksie, w przeciwnym przypadku
	 * true (ustawiono tekst b��du).
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
	 * Ustawia, czy pytanie jest obowi�zkowe.
	 * @param questionNumber numer pytania
	 * @param obligatory true, je�li pytanie ma by� oznaczone jako obowi�zkowe (czyli czy wymagana jest odpowied� na to pytanie),
	 * w przeciwnym przypadku false.
	 * @return false, je�li nie ma pytania o zadanym indeksie, w przeciwnym przypadku
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
	 * Sprawdza, czy pytanie oznaczono jako obowi�zkowe (czyli czy wymagana jest odpowied� na to pytanie) .
	 * @param questionNumber numer pytania
	 * @return null, je�li nie ma pytania o zadanym numerze lub pytanie jest nullem, w przeciwnym przypadku zwraca 
     * true, je�li pytanie jest obowi�zkowe, false, je�li nie.
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
	 * Ustawia ograniczenia typu tekstowego dla pytania tekstowego. Je�li nie chcesz ustawia� jakiego� parametru,
	 * wpisz null.
	 * @param questionNumber numer pytania.
	 * @param minLength wymagana minimalna d�ugo�� odpowiedzi. 
	 * @param maxLength wymagana maksymalna d�ugo�� odpowiedzi.
	 * @param regex wyra�enie regularne, kt�re musi spe�nia� odpowied�.
	 * @return false, je�li nie ma pytania o zadanym indeksie (lub jest r�wne null) lub minLength > maxLength, lub
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego, 
	 * lub podane wyra�enie nie jest wyra�eniem regularnym,
	 * w przeciwnym przypadku - je�li uda�o si�
	 * ustawi� ograniczenia, zwraca true.
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
	 *  Ustawia ograniczenia typu liczbowego dla pytania tekstowego. Je�li nie chcesz ustawia� jakiego� parametru,
	 * wpisz null. Tekst z takim ograniczeniem nie mo�e zawiera� innych znak�w opr�cz liczby.
	 * @param questionNumber numer pytania
	 * @param minValue minimalna warto�� sprawdzanej liczby
	 * @param maxValue maksymalna warto�� sprawdzanej liczby
	 * @param mustBeInteger true, je�li liczba musi by� typu ca�kowitego, inaczej false
	 * @param notEquals liczba, kt�rej sprawdzana liczba nie mo�e si� r�wna�
	 * @param notBetweenMaxAndMinValue true, je�li sprawdzana liczba nie mo�e by� w przedziale [minValue, maxValue]
	 * @return false, je�li nie ma pytania o zadanym indeksie (lub jest r�wne null) lub
	 * pytanie o zadanym indeksie nie jest pytaniem typu tekstowego, w przeciwnym przypadku - je�li uda�o si�
	 * ustawi� ograniczenia, zwraca true.
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
	 * Zwraca liczb� pyta� dodanych do ankiety.
	 * @return liczba pyta� dodanych do ankiety.
	 */
	public int getQuestionsCount(){
		return survey.questionListSize();
	}
	
	/**
	 * Ustaw tytu� tworzonej ankiety.
	 * @param title tytu� ankiety
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
	 * @return false, je�li nie ma pytania o zadanym indeksie albo nie ma pytania o p�niejszym indeksie
	 * (w�wczas nie ma dok�d przesun�� pytania), w przeciwnym przypadku true.
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
	 * Przesuwa pytanie o zadanym indeksie o jeden indeks do ty�u.
	 * @param questionNumber numer pytania (numeracja od zera).
	 * @return false, je�li nie ma pytania o zadanym indeksie albo nie ma pytania o wcze�niejszym indeksie
	 * (w�wczas nie ma dok�d przesun�� pytania), w przeciwnym przypadku true.
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
	 * Zwraca typ pytania (patrz sta�e wklasie Question).
	 * @param questionNumber numer pytania.
	 * @return -1, je�li nie ma pytania o zadanym indeksie albo typ pytania jest nieznany
	 */
	public int getQuestionType(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			return -1;
		else return question.getQuestionType();
	}
	
	/**
	 * Sprawdza, czy podany numer pytania jest poprawny, je�li tak, to zwraca
	 * pytanie o zadanym indeksie. Uwaga: zwr�cone pytanie potencjalnie mo�e mie� warto�� null - 
	 * klasa Survey tego nie kontroluje.
	 * @param questionNumber
	 * @return null, je�li podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()),
	 * w przeciwnym przypadku zwraca pytanie o zadanym indeksie. Uwaga z uwagi na klas� Survey, pytanie mo�e by� nullem.
	 */
	public Question getQuestion(int questionNumber){
		if(questionNumber < 0 || questionNumber >= survey.questionListSize())
			return null;
		else return survey.getQuestion(questionNumber);
	}
	
	/**
	 * Ustawia etykiet� przy minimalnej warto�ci pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param minLabel etykieta do ustawienia.
	 * @return false, je�li podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
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
	 * Ustawia etykiet� przy maksymalnej warto�ci pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param maxLabel etykieta do ustawienia.
	 * @return false, je�li podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
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
	 * Ustawia maksymaln� warto�� pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param maxValue warto�� do ustawienia.
	 * @return false, je�li podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
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
	 * Ustawia minimaln� warto�� pytania typu skala.
	 * @param questionNumber numer pytania.
	 * @param minValue warto�� do ustawienia.
	 * @return false, je�li podany numer pytania nie jest poprawny (< 0 lub >= survey.questionListSize()) lub
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
	 * Dodaj odpowied� mo�liw� do zaznaczenia dla pytania wyboru (jednokrotnego, wielokrotnego
	 * i listy rozwijanej).
	 * @param questionNumber numer pytania
	 * @param answer odpowied� do dodania
	 * @return true, je�li dodano odpowied�, false, je�li nie (nie ma pytania o zadanym numerze
	 * albo pytanie jest z�ego typu).
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
	 * Dodaj odpowied� mo�liw� do zaznaczenia dla pytania wyboru (jednokrotnego, wielokrotnego
	 * i listy rozwijanej) w konkretne miejsce.
	 * @param questionNumber numer pytania
	 * @param answer odpowied� do dodania
	 * @param position indeks, pod kt�ry ma zosta� dodana odpowied� (numeracja od zera).
	 * @return true, je�li dodano odpowied�, false, je�li nie (nie ma pytania o zadanym numerze
	 * albo pytanie jest z�ego typu, albo position < 0 lub position >= liczba dotychczasowych odpowiedzi).
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
	 *  Usu� odpowied� mo�liw� do zaznaczenia dla pytania wyboru (jednokrotnego, wielokrotnego
	 * i listy rozwijanej).
	 * @param questionNumber numer pytania
	 * @param position numer odpowiedzi (oba numery liczone od zera)
	 * @return true, je�li odpowied� usuni�to, false je�li nie ma takiej odpowiedzi, nie ma takiego
	 * pytania lub pytanie jest z�ego typu.
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
	 * Przesu� odpowied� do pytania typu wyboru (jednoktornego, wielokrotnego,
	 * rozwijana lista) o jeden indeks do przodu.
	 * @param questionNumber numer pytania.
	 * @param answeNo numer odpowiedzi do przesuni�cia.
	 * @return true, je�li odpowied� przesuni�to, false je�li nie (pytanie by�o z�ego typu,
	 * nie ma pytania o zadanym indeksie lub podano b��dny indeks odpowiedzi: answeNo < 0 || 
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
	 * Przesu� odpowied� do pytania typu wyboru (jednoktornego, wielokrotnego,
	 * rozwijana lista) o jeden indeks w ty�u.
	 * @param questionNumber numer pytania.
	 * @param answeNo numer odpowiedzi do przesuni�cia.
	 * @return true, je�li odpowied� przesuni�to, false je�li nie (pytanie by�o z�ego typu,
	 * nie ma pytania o zadanym indeksie lub podano b��dny indeks odpowiedzi: answeNo < 1 || answeNo >= answers.size()).
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
	 * Zwraca list� mo�liwych do wyboru odpowiedzi dla pytania o zadanym indeksie.
	 * Je�li nie ma pytania o zadanym indeksie, wyrzuca wyj�tek NullPointerException.
	 * @param questionNumber numer pytania.
	 * @return lista mo�liwych odpowiedzi.
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
	 * @return true, jesli usuni�to pytanie, false, jesli zadany numer jest nieprawid�owy:
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
	 * @param labels etykiety, lista nie mo�e by� r�wna null.
	 */
	public void setGridColumnLabels(int questionNumber, List<String> labels){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			throw new NullPointerException("Nie ma pytania o zadanym numerze");
		if(question.getQuestionType() == Question.GRID_QUESTION){
			GridQuestion gridQuestion = (GridQuestion) question;
			gridQuestion.setColumnLabels(labels);
		}
		else throw new IllegalArgumentException("Podane pytanie powinno by� typu GridQuestion");
	}
	
	/**
	 * Ustawia etykiety wierszy dla pytania typu tabela.
	 * @param questionNumber numer pytania
	 * @param labels etykiety, lista nie mo�e by� r�wna null.
	 */
	public void setGridRowLabels(int questionNumber, List<String> labels){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			throw new NullPointerException("Nie ma pytania o zadanym numerze");
		if(question.getQuestionType() == Question.GRID_QUESTION){
			GridQuestion gridQuestion = (GridQuestion) question;
			gridQuestion.setRowLabels(labels);
		}
		else throw new IllegalArgumentException("Podane pytanie powinno by� typu GridQuestion");
	}
	
	/**
	 * Zwraca etykiety wierszy dla pytania typu tabela.
	 * @param questionNumber numer pytania.
	 * @return etykiety wierszy, nigdy nie zwr�ci null.
	 */
	public List<String> getGridRowLabels(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			throw new NullPointerException("Nie ma pytania o zadanym numerze");
		if(question.getQuestionType() == Question.GRID_QUESTION){
			GridQuestion gridQuestion = (GridQuestion) question;
			return gridQuestion.getRowLabels();
		}
		else throw new IllegalArgumentException("Podane pytanie powinno by� typu GridQuestion");
	}
	
	/**
	 * Zwraca etykiety kolumn dla pytania typu tabela.
	 * @param questionNumber numer pytania.
	 * @return etykiety kolumn, nigdy nie zwr�ci null.
	 */
	public List<String> getGridColumnLabels(int questionNumber){
		Question question;
		if((question = getQuestion(questionNumber)) == null)
			throw new NullPointerException("Nie ma pytania o zadanym numerze");
		if(question.getQuestionType() == Question.GRID_QUESTION){
			GridQuestion gridQuestion = (GridQuestion) question;
			return gridQuestion.getColumnLabels();
		}
		else throw new IllegalArgumentException("Podane pytanie powinno by� typu GridQuestion");
	}
	
	/**
	 * Zwraca utworzon� ankiet�. Nie ma ona jeszcze nadanego id. Nale�y to zrobi� w SurveyHandler
	 * @return utworzona ankieta.
	 */
	public Survey finishCreating(){
		return survey;
	}
}
