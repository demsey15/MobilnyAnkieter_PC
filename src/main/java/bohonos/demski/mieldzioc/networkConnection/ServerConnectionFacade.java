/**
 * 
 */
package bohonos.demski.mieldzioc.networkConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import bohonos.demski.mieldzioc.interviewer.Interviewer;
import bohonos.demski.mieldzioc.interviewer.InterviewerSurveyPrivileges;
import bohonos.demski.mieldzioc.survey.Survey;

/**
 * @author Dominik Demski
 *
 */
public class ServerConnectionFacade {
	
	public final static int PORT = 8046;
	public final static String HOST = "192.168.0.104";
	
	public final static int BAD_DATA_FORMAT = -2;
	public final static int UNKNOWN_FAIL = -1;
	public final static int LOGIN_OK = 1;
	public final static int BAD_PASSWORD = 2;
	public final static int AUTHORIZATION_FAILED = 3;
	public final static int AUTHORIZATION_OK = 4;
	
	public final static int OPERATION_OK = 5;
	
	public final static int SEND_NEW_TEMPLATE = 10;
	public final static int TEMPLATE_ALREADY_EXISTS = 11;
	
	public final static int CHANGE_SURVEY_STATUS = 12;
	
	public final static int UPDATE_SURVEY_TEMPLATE = 13;
	public final static int SURVEY_UNEDITABLE = 14;
	public final static int LACK_OF_SURVEY_TEMPLATE_WITH_ID = 15;
	
	public final static int SEND_FILLED_SURVEYS = 16;
	public final static int SURVEY_INACTIVE = 17;
	
	public final static int GET_ACTIVE_SURVEY_TEMPLATE = 18;
	public final static int GET_INACTIVE_SURVEY_TEMPLATE = 19;
	public final static int GET_IN_PROGRESS_SURVEY_TEMPLATE = 20;
	
	public final static int ADD_NEW_INTERVIEWER = 21;
	public final static int ADD_NEW_ADMINISTRATOR = 22;
	
	public final static int GET_FILLED_SURVEYS = 23;
	public final static int SENDING_FILLED_SURVEYS = 24;
	
	public final static int GET_ALL_INTERVIEWERS = 25;
	public final static int GET_INTERVIEWER = 26;
	public final static int AUTHENTICATION = 27;
	public final static int DISMISS_INTERVIEWER = 28;
	public final static int BACK_TO_WORK_INTERVIEWER = 29;
	
	public final static int GET_SURVEYS_FILLED_BY_INTERVIEWER = 30;
	
	public final static int SEND_INTERVIEWER_PRIVILAGES = 31;
	public final static int GET_INTERVIEWER_PRIVILAGES = 32;
	public final static int GET_INTERVIEWER_CREATING_PRIVILIGES = 33;
	public final static int SET_INTERVIEWER_CREATING_PRIVILIGES = 34;
	

	public final static int GET_ACTIVE_TEMPLATES_ID_FOR_INTERVIEWER = 35; //pobierz ankiety, które ankieter mo¿e wype³niaæ
	public final static int GET_EDITABLE_TEMPLATES_ID_FOR_INTERVIEWER = 36; //pobierz ankiety, które ankieter mo¿e edytowaæ
	public final static int GET_SURVEY_TEMPLATE = 37;
	
	private SocketChannel socketChannel;
	private Scanner in;
	private PrintWriter out;
	
	/**
	 * Wysy³a nowy szablon ankiety na serwer.
	 * @param survey szablon.
	 * @param usersId id przesy³aj¹cego u¿ytkownika (musi mieæ uprawnienia do tworzenia ankiet).
	 * @param password has³o u¿ytkownika.
	 * @return zwraca BAD_PASSWORD, jeœli podane has³o jest b³êdne lub nie ma u¿ytkownika o podanym id,
	 * AUTHORIZATION_FAILED, jeœli u¿ytkownik nie ma odpowienich uprawnieñ, TEMPLATE_ALREADY_EXISTS,
	 * jeœli szablon o podanym id ju¿ istnieje, OPERATION_OK, jeœli operacja przebieg³a pomyœlnie.
	 */
	public int sendSurveyTemplate(Survey survey, String usersId, char[] password){
		if(survey == null ||usersId == null || password == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		System.out.println("£¹czê");
		connect();
		System.out.println("£¹czê po");
		if(!login(usersId, password)){
			disconnect();
			System.out.println("Z³e has³o");
			return BAD_PASSWORD;
		}
		System.out.println("Wysy³am");
		sendInt(SEND_NEW_TEMPLATE);
		int authorization = readInt();
		System.out.println("Otrzyma³em " + authorization);
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return AUTHORIZATION_FAILED; 
		}
		sendObject(survey);
		int status = readInt();
		disconnect();
		return status; 
	}
	
	/**
	 * Zmienia status szablonu ankiet.
	 * @param idOfSurveys id grupy ankiet.
	 * @param status status (patrz sta³e w klasie SurevyHandler).
	 * @param usersId id u¿ytkownika zmianiaj¹cego status (status mo¿e zmieniæ tylko administrator).
	 * @param password has³o u¿ytkownika.
	 * @return BAD_PASSWORD, jeœli podano b³êdne has³o lub nie ma u¿ytkownika o podanym id,
	 * AUTHORIZATION_FAILED, jeœli u¿ytkownik nie jest administratorem, BAD_DATA_FORMAT, jeœli nie ma
	 * ankiety o zadanym id, OPERATION_OK, jeœli wszystko przebieg³o pomyœlnie.
	 */
	public int changeSurveyStatus(String idOfSurveys, int status, String usersId, char[] password){
		if(idOfSurveys == null ||usersId == null || password == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return BAD_PASSWORD;
		}
		sendInt(CHANGE_SURVEY_STATUS);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return AUTHORIZATION_FAILED;
		}
		sendString(idOfSurveys);
		sendInt(status);
		int operationStatus = readInt();
		disconnect();
		return operationStatus; 
	}
	
	/**
	 * Aktualizuj szablon ankiet.
	 * @param survey szablon.
	 * @param usersId id u¿ytkownika.
	 * @param password has³o u¿ytwkonika.
	 * @return BAD_PASSWORD, jeœli podano b³êdne has³o lub nie ma u¿ytkownika o podanym id,
	 * AUTHORIZATION_FAILED, jeœli u¿ytkownik nie ma dopowiednich uprawnieñ (uprawnienia do tworzenia
	 * ankiet), BAD_DATA_FORMAT, jeœli nie ma
	 * ankiety o zadanym id lub ankieta nie ma statusu IN_PROGRESS,
	 *  OPERATION_OK, jeœli wszystko przebieg³o pomyœlnie.
	 */
	public int updateSurveyTemplate(Survey survey, String usersId, char[] password){
		if(survey == null ||usersId == null || password == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return BAD_PASSWORD;
		}
		sendInt(UPDATE_SURVEY_TEMPLATE);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return AUTHORIZATION_FAILED; 
		}
		sendObject(survey);
		int status = readInt();
		disconnect();
		return status; 	
	}
	
	/**
	 * Wysy³a listê wype³nionych ankiet.
	 * @param surveys lista wype³nionych ankiet (nie mo¿e byæ równa null).
	 * @param usersId id ankietera.
	 * @param password has³o ankietera.
	 * @return lista z kodem: BAD_PASSWORD, jeœli podano b³êdne has³o lub nie ma u¿ytkownika o podanym id,
	 * AUTHORIZATION_FAILED, jeœli u¿ytkownik nie ma dopowiednich uprawnieñ (musi byæ ankieterem),
	 * albo lista z kodami (dla ka¿dej ankiety odpowiednio): 
	 * BAD_DATA_FORMAT, jeœli ankieta o zadanym numerze znajduje siê ju¿ w repozytorium,
	 *  OPERATION_OK, jeœli wszystko przebieg³o pomyœlnie, SURVEY_INACTIVE, jeœli ankieta nie ma
	 *  statusu "aktywna".
	 */
	public List<Integer> sendFilledSurveys(List<Survey> surveys, String usersId, char[] password){
		if(surveys == null ||usersId == null || password == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		List<Integer> results = new ArrayList<Integer>(surveys.size());
		if(!login(usersId, password)){
			disconnect();
			results.add(BAD_PASSWORD);
			return results;
		}
		sendInt(SEND_FILLED_SURVEYS);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			results.add(AUTHORIZATION_FAILED);
			return results;
		}
		sendInt(surveys.size());
		for(int i = 0; i < surveys.size(); i++){
			sendObject(surveys.get(i));
			results.add(readInt());
		}
		disconnect();
		return results;
	}
	
	public List<Survey> getActiveSurveyTemplates(String usersId, char[] password){
		if(usersId == null || password == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return null;
		}
		sendInt(GET_ACTIVE_SURVEY_TEMPLATE);
		List<Survey> surveys = new ArrayList<Survey>();
		int size = readInt();
		for(int i = 0; i < size; i++){
			surveys.add((Survey) readObject());
		}
		disconnect();
		return surveys;
	}
	
	public List<Survey> getInactiveSurveyTemplates(String usersId, char[] password){
		if(usersId == null || password == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return null;
		}
		sendInt(GET_INACTIVE_SURVEY_TEMPLATE);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return null; 
		}
		List<Survey> surveys = new ArrayList<Survey>();
		int size = readInt();
		for(int i = 0; i < size; i++){
			surveys.add((Survey) readObject());
		}
		disconnect();
		return surveys;
	}
	
	public List<Survey> getInProgressSurveyTemplates(String usersId, char[] password){
		if(usersId == null || password == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return null;
		}
		sendInt(GET_IN_PROGRESS_SURVEY_TEMPLATE);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return null; 
		}
		List<Survey> surveys = new ArrayList<Survey>();
		int size = readInt();
		for(int i = 0; i < size; i++){
			surveys.add((Survey) readObject());
		}
		disconnect();
		return surveys;
	}
	
	public int addNewInterviewer(Interviewer interviewerToAdd, char[] passwordToAdd,
									String usersId, char[] password){
		if(interviewerToAdd == null || password == null || usersId == null ||
				passwordToAdd == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return BAD_PASSWORD;
		}
		sendInt(ADD_NEW_INTERVIEWER);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return AUTHORIZATION_FAILED; 
		}
		sendObject(interviewerToAdd);
		sendString(new String(passwordToAdd));
		int status = readInt();
		disconnect();
		return status; 	
	}
	
	public int addNewAdministrator(String adminId, char[] adminPassword,
			String usersId, char[] password){
		if(adminId == null || password == null || usersId == null ||
				adminPassword == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
		disconnect();
		return BAD_PASSWORD;
		}
		sendInt(ADD_NEW_ADMINISTRATOR);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return AUTHORIZATION_FAILED; 
		}
		sendString(adminId);
		sendString(new String(adminPassword));
		int status = readInt();
		disconnect();
		return status; 	
	}
	
	/**
	 * Pobierz listê wype³nionych ankiet (mo¿e to zrobiæ tylko administrator).
	 * @param idOfSurveys id grupy ankiet do pobrania.
	 * @param usersId id administratora.
	 * @param password has³o administratora.
	 * @return listê wype³nionych ankiet dla danej grupy ankiet lub null, jeœli: podano b³êdne
	 * dane logowania, loguj¹cy siê u¿ytkownik nie jest administratorem, nie ma grupy ankiet o
	 * podanym id lub nie przes³ano jeszcze ¿adnego wyniku, wyst¹pi³ nieznany b³¹d.
	 */
	public List<Survey> getFilledSurveys(String idOfSurveys, String usersId, char[] password){
		if(idOfSurveys == null || password == null || usersId == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
		disconnect();
		return null;
		}
		sendInt(GET_FILLED_SURVEYS);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return null; 
		}
		else{
			int result = readInt();
			if(result == BAD_DATA_FORMAT){
				disconnect();
				return null;
			}
			else{
				int size = readInt();
				List<Survey> list = new ArrayList<Survey>(size);
				for(int i = 0; i < size; i++){
					Survey survey = (Survey) readObject();
					list.add(survey);
				}
				if(readInt() == OPERATION_OK){
					disconnect();
					return list;
				}
				else{
					disconnect();
					return null;   //nie powinno siê zdarzyæ
				}
			}
		}
	}
	
	/**
	 * Pobierz listê ankieterów (mo¿e to zrobiæ tylko administrator).
	 * @param usersId id administratora.
	 * @param password has³o administratora.
	 * @return listê ankieterów lub null, jeœli: podano b³êdne
	 * dane logowania, loguj¹cy siê u¿ytkownik nie jest administratorem, wyst¹pi³ nieznany b³¹d.
	 */
	public List<Interviewer> getAllInterviewers(String usersId, char[] password){
		if(password == null || usersId == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
		disconnect();
		return null;
		}
		sendInt(GET_ALL_INTERVIEWERS);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return null; 
		}
		else{
			int size = readInt();
			List<Interviewer> list = new ArrayList<Interviewer>(size);
			for(int i = 0; i < size; i++){
				list.add((Interviewer) readObject());
			}
			if(readInt() == OPERATION_OK){
				disconnect();
				return list;
			}
			else{
				disconnect();
				return null;   //nie powinno siê zdarzyæ
			}
			
		}
	}
	
	/**
	 * Pobierz ankietera (mo¿e to zrobiæ tylko administrator lub ankieter sam siebie).
	 * @param interviewerId id ankietera do pobrania.
	 * @param usersId id pobieraj¹cego u¿ytkownika.
	 * @param password has³o pobieraj¹cego u¿ytkownika.
	 * @return ankieter lub null, jeœli: podano b³êdne
	 * dane logowania, loguj¹cy siê u¿ytkownik nie ma odpowiednich uprawnieñ, brak
	 * ankietera o zadanym id.
	 */
	public Interviewer getInterviewer(String interviewerId, String usersId, char[] password){
		if(password == null || usersId == null || interviewerId == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
		disconnect();
		return null;
		}
		sendInt(GET_INTERVIEWER);
		sendString(interviewerId);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return null; 
		}
		else{
			if(readInt() == OPERATION_OK){
				Interviewer interviewer = (Interviewer) readObject();
				disconnect();
				return interviewer;
			}
			else{
				disconnect();
				return null;   //nie ma takiego interviewera.
			}
			
		}
	}
	
	/**
	 * SprawdŸ poprawnoœæ has³a i loginu.
	 * @param usersId login u¿ytkownika.
	 * @param password has³o.
	 * @return true, jeœli dane s¹ poprawne, false w przeciwnym przypadku.
	 */
	public boolean authenticate(String usersId, char[] password){
		if(password == null || usersId == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
		disconnect();
		return false;
		}
		else{
			sendInt(AUTHENTICATION);
			disconnect();
			return true;
		}
	}
	
	/**
	 * Zwalnia ankietera o zadanym id.
	 * @param interviewerId id ankietera do zwolnienia.
	 * @param relieveDay data zwolnienia.
	 * @param usersId id admnistratora, który chce zwolniæ pracownika.
	 * @param password has³o administratora.
	 * @return BAD_PASSWORD b³êdne dane logowania, AUTHORIZATION_FAILED zalogowany u¿ytkownik
	 * nie jest administratorem (tylko administrator mo¿e zwolniæ ankietera), BAD_DATA_FORMAT,
	 * jeœli nie ma ankietera o podanym id, OPERATION_OK, jeœli zwolniono ankietera.
	 */
	public int dismissInterviewer(String interviewerId, GregorianCalendar relieveDay,
			String usersId, char[] password){
		if(password == null || usersId == null || interviewerId == null || relieveDay == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
		disconnect();
		return BAD_PASSWORD;
		}
		else{
			sendInt(DISMISS_INTERVIEWER);
			int authorization = readInt();
			if(authorization == AUTHORIZATION_FAILED){
				disconnect();
				return AUTHORIZATION_FAILED; 
			}
			else{
				sendString(interviewerId);
				int status = readInt();
				if(status == BAD_DATA_FORMAT){
					disconnect();
					return BAD_DATA_FORMAT;
				}
				else{
					sendObject(relieveDay);
					disconnect();
					return OPERATION_OK;
				}
			}	
		}
	}
	
	/**
	 * Pobierz listê wype³nionych ankiet (mo¿e to zrobiæ tylko administrator).
	 * @param idOfSurveys id grupy ankiet do pobrania.
	 * @param usersId id administratora.
	 * @param password has³o administratora.
	 * @return listê wype³nionych ankiet dla danej grupy ankiet lub null, jeœli: podano b³êdne
	 * dane logowania, loguj¹cy siê u¿ytkownik nie jest administratorem, nie ma grupy ankiet o
	 * podanym id lub nie przes³ano jeszcze ¿adnego wyniku, wyst¹pi³ nieznany b³¹d.
	 */
	public List<Survey> getSurveysFilledByInterviewer(String interviewerId, 
			String usersId, char[] password){
		if(interviewerId == null || password == null || usersId == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return null;
		}
		sendInt(GET_SURVEYS_FILLED_BY_INTERVIEWER);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return null; 
		}
		else{
			sendString(interviewerId);
			int result = readInt();
			if(result == BAD_DATA_FORMAT){
				disconnect();
				return null;
			}
			else{
				int size = readInt();
				List<Survey> list = new ArrayList<Survey>(size);
				for(int i = 0; i < size; i++){
					Survey survey = (Survey) readObject();
					list.add(survey);
				}
				disconnect();
				return list;
			}
		}
	}
	
	/**
	 * Wysy³a na serwer uprawnienia ankietera odnoœnie danej grupy ankiet.
	 * @param interviewerId id ankietera.
	 * @param privileges przywileje.
	 * @param idOfSurveys id grupy ankiet.
	 * @param usersId id admnistratora wysy³aj¹cego uprawnienia.
	 * @param password has³o.
	 * @return  BAD_PASSWORD, jeœli dane do logowania s¹ niepoprawne, 
	 * AUTHORIZATION_FAILED, jeœli nadaj¹cy uprawnienia nie jest administratorem,
	 * BAD_DATA_FORMAT, jeœli nie ma zadanej grupy ankiet albo nie ma ankietera o zadanym id,
	 * jesli wszystko przebieg³o pomyœlnie - OPERATION_OK.
	 */
	public int sendInterviewerPrivileges(String interviewerId, 
			InterviewerSurveyPrivileges privileges, String idOfSurveys,
			String usersId, char[] password){
		if(interviewerId == null || password == null || usersId == null || privileges == null
				|| idOfSurveys == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return BAD_PASSWORD;
		}
		sendInt(GET_SURVEYS_FILLED_BY_INTERVIEWER);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return AUTHORIZATION_FAILED; 
		}
		else{
			sendString(interviewerId);
			int result = readInt();
			if(result == BAD_DATA_FORMAT){
				disconnect();
				return BAD_DATA_FORMAT;
			}
			else{
				sendObject(privileges);
				sendString(idOfSurveys);
				return readInt();
			}
		}
	}
	
	
	/**
	 * Odczytuje uprawnienia ankietera grup ankiet.
	 * @param interviewerId id ankietera.
	 * @param usersId id u¿ytkownika pytaj¹cego o uprawnienia. (pytaæ mo¿e
	 * u¿ytkownik o siebie samego albo administrator)
	 * @param password has³o.
	 * @return  null, jeœli dane do logowania s¹ niepoprawne, 
	 * albo pytaj¹cy nie ma odpowiednich uprawnieñ (pytaæ mo¿e
	 * u¿ytkownik o siebie samego albo administrator),
	 * albo nie ma ankietera o zadanym id,
	 * jesli wszystko przebieg³o pomyœlnie - zwraca mapê z danymi.
	 */
	public Map<String, InterviewerSurveyPrivileges> getAllInterviewerPrivileges(String interviewerId, 
			String usersId, char[] password){
		if(interviewerId == null || password == null || usersId == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return null;
		}
		sendInt(GET_SURVEYS_FILLED_BY_INTERVIEWER);
		sendString(interviewerId);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return null; 
		}
		else{
			int result = readInt();
			if(result == BAD_DATA_FORMAT){
				disconnect();
				return null;
			}
			else{
				@SuppressWarnings("unchecked")
				Map<String, InterviewerSurveyPrivileges> map =
						(Map<String, InterviewerSurveyPrivileges>)readObject();
				return map;
				}
			}
	}
	
	/**
	 * Ustala uprawnienia ankietera do tworzenia ankiet.
	 * @param interviewerId id ankietera, któremu nale¿y nadaæ uprawnienia.
	 * @param canCreate true, jeœli ankieter mo¿e tworzyæ nowe ankiety.
	 * @param usersId id administratora nadaj¹cego uprawnienia. 
	 * @param password has³o administratora.
	 * @return  BAD_PASSWORD, jeœli dane do logowania s¹ niepoprawne, AUTHORIZATION_FAILED
	 * jeœli pytaj¹cy nie ma odpowiednich uprawnieñ (nie jest administratorem),
	 * BAD_DATA_FORMAT, jeœli nie ma ankietera o zadanym id,
	 * OPERATION_OK, jeœli wszystko przebieg³o pomyœlnie.
	 */
	public int setInterviewerCreatingPrivileges(Interviewer interviewer, boolean canCreate,
			String usersId, char[] password){
		if(interviewer == null || password == null || usersId == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return BAD_PASSWORD;
		}
		sendInt(SET_INTERVIEWER_CREATING_PRIVILIGES);
		sendString(interviewer.getId());
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return AUTHORIZATION_FAILED; 
		}
		else{
			int result = readInt();
			if(result == BAD_DATA_FORMAT){
				disconnect();
				return BAD_DATA_FORMAT;
			}
			else{
					sendInt((canCreate)? 1 : 0);
					return OPERATION_OK;
				}
			}
	}
	
	/**
	 * Odczytuje uprawnienia ankietera do tworzenia ankiet.
	 * @param interviewerId id ankietera.
	 * @param usersId id u¿ytkownika pytaj¹cego o uprawnienia. (pytaæ mo¿e
	 * u¿ytkownik o siebie samego albo administrator)
	 * @param password has³o.
	 * @return  BAD_PASSWORD, jeœli dane do logowania s¹ niepoprawne, AUTHORIZATION_FAILED
	 * jeœli pytaj¹cy nie ma odpowiednich uprawnieñ (pytaæ mo¿e
	 * u¿ytkownik o siebie samego albo administrator),
	 * BAD_DATA_FORMAT, jeœli nie ma ankietera o zadanym id,
	 * 1 jeœli ankieter mo¿e tworzyæ ankiety, w przeciwnym przypadku 0.
	 */
	public int getInterviewerCreatingPrivileges(String interviewerId, 
			String usersId, char[] password){
		if(interviewerId == null || password == null || usersId == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return BAD_PASSWORD;
		}
		sendInt(GET_INTERVIEWER_CREATING_PRIVILIGES);
		sendString(interviewerId);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return AUTHORIZATION_FAILED; 
		}
		else{
			int result = readInt();
			if(result == BAD_DATA_FORMAT){
				disconnect();
				return BAD_DATA_FORMAT;
			}
			else{
					return readInt();
				}
			}
	}
	
	/**
	 * Pobiera listê z indeksami ankiet, które mo¿e wype³niaæ dany ankieter.
	 * @param interviewerId id ankietera.
	 * @param usersId id pytaj¹cego.
	 * @param password has³o pytaj¹cego.
	 * @return null, jeœli dane do logowania s¹ niepoprawne,
	 * jeœli pytaj¹cy nie ma odpowiednich uprawnieñ (pytaæ mo¿e
	 * u¿ytkownik o siebie samego albo administrator),
	 * , jeœli nie ma ankietera o zadanym id,
	 * w przeciwnym przypadku listê z indeksami.
	 */
	public List<String> getActiveIdTemplateForInterviewer(String interviewerId, String usersId, char[] password){
		if(interviewerId == null || password == null || usersId == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return null;
		}
		sendInt(GET_ACTIVE_TEMPLATES_ID_FOR_INTERVIEWER);
		sendString(interviewerId);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return null; 
		}
		else{
			int status = readInt();
			if(status == BAD_DATA_FORMAT){
				disconnect();
				return null;
			}
			else{
				int size = readInt();
				List<String> result = new ArrayList<String>(size);
				for(int i = 0; i < size; i++){
					result.add(readString());
				}
				return result;
			}
		}
	}
	
	/**
	 * Pobiera listê z indeksami ankiet, które mo¿e edytowaæ dany ankieter.
	 * @param interviewerId id ankietera.
	 * @param usersId id pytaj¹cego.
	 * @param password has³o pytaj¹cego.
	 * @return null, jeœli dane do logowania s¹ niepoprawne,
	 * jeœli pytaj¹cy nie ma odpowiednich uprawnieñ (pytaæ mo¿e
	 * u¿ytkownik o siebie samego albo administrator),
	 * , jeœli nie ma ankietera o zadanym id,
	 * w przeciwnym przypadku listê z indeksami.
	 */
	public List<String> getEditableIdTemplateForInterviewer(String interviewerId, String usersId, char[] password){
		if(interviewerId == null || password == null || usersId == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return null;
		}
		sendInt(GET_EDITABLE_TEMPLATES_ID_FOR_INTERVIEWER);
		sendString(interviewerId);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED){
			disconnect();
			return null; 
		}
		else{
			int status = readInt();
			if(status == BAD_DATA_FORMAT){
				disconnect();
				return null;
			}
			else{
				int size = readInt();
				List<String> result = new ArrayList<String>(size);
				for(int i = 0; i < size; i++){
					result.add(readString());
				}
				return result;
			}
		}
	}
	
	/**
	 * Pobierz szablon ankiety.
	 * @param idOfSurveys id szablonu.
	 * @param usersId id pytaj¹cego.
	 * @param password has³o pytaj¹cego.
	 * @return null, jeœli dane do logowania s¹ niepoprawne,
	 * jeœli pytaj¹cy nie ma odpowiednich uprawnieñ (nieaktywne ankiety mo¿e pobraæ
	 * tylko admnistrator, inne ka¿dy),
	 * , jeœli nie ma szablonu o zadanym id,
	 * w przeciwnym przypadku ankietê.
	 */
	public Survey getSurveyTemplate(String idOfSurveys, String usersId, char[] password){
		if(idOfSurveys == null || password == null || usersId == null)
			throw new NullPointerException("Przekazane argumenty nie mog¹ byæ nullami.");
		connect();
		if(!login(usersId, password)){
			disconnect();
			return null;
		}
		sendInt(GET_SURVEY_TEMPLATE);
		sendString(idOfSurveys);
		int status = readInt();
		if(status == BAD_DATA_FORMAT){
			disconnect();
			return null;
		}
		else{
			int authorization = readInt();
			if(authorization == AUTHORIZATION_FAILED){
				disconnect();
				return null;
			}
			else{
				return (Survey) readObject();
			}
		}
	}
	
	private boolean login(String usersId, char[] password){
		System.out.println("Wysy³am id");
		sendString(usersId);
		System.out.println("Wysy³am has³o");
		sendString(new String(password));
		
		int answer = readInt();
		
			for(int i = 0; i < password.length; i++){
				password[i] = 'a';
			}
		return (answer != BAD_PASSWORD);
	}
	
	private void connect(){
		try {
			socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
			out = new PrintWriter(Channels.newOutputStream(socketChannel), true);
			in = new Scanner(socketChannel);
		} catch (IOException e) {
			System.out.println("B³¹d");
			e.printStackTrace();
		}
	}
	
	private void disconnect(){
			try {
				out.close();
				in.close();
				socketChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private void sendInt(int i){
		System.out.println("Wysy³am: " + i);	
		out.println(i);
	}
	
	private void sendObject(Object obj){
		try {
			ObjectOutputStream outOb = new ObjectOutputStream(Channels.newOutputStream(socketChannel));
			outOb.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendString(String s){
		System.out.println("Wysy³am: " + s);	
		out.println(s);
	}
	
	private int readInt(){
		try{
			int i = Integer.parseInt(in.nextLine());
			System.out.println("Odczyta³em: " + i);
			return i;
		}
		catch(NumberFormatException e){
			return BAD_DATA_FORMAT;
		}
	}
	
	private String readString(){
		String received =  in.nextLine();
		System.out.println("Odczyta³em: " + received);
		return received;
	}
	
	private Object readObject(){
		try {
			ObjectInputStream inObj = new ObjectInputStream(Channels.newInputStream(socketChannel));
			Object obj = inObj.readObject();
			System.out.println("Odczyta³em obiekt: " + obj);
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		ServerConnectionFacade facade = new ServerConnectionFacade();
		Interviewer interviewer = new Interviewer("", "", "11111111111", new GregorianCalendar());
		interviewer.setInterviewerPrivileges(true);
		Survey survey = new Survey(interviewer);
		survey.setIdOfSurveys("ja");
		Survey survey2 = new Survey(interviewer);
		survey2.setIdOfSurveys("ja");
		
		List<String> list = (List<String>) facade.getActiveIdTemplateForInterviewer(interviewer.getId(), interviewer.getId(), new char[] {'a', 'b', 'c'});
    	System.out.println(Arrays.toString(list.toArray()));
		/*facade.sendSurveyTemplate(survey, interviewer.getId(), new char[] {'a', 'b', 'c'});
    	facade.changeSurveyStatus(survey.getIdOfSurveys(), SurveyHandler.ACTIVE, "admin", new char[] {'a', 'd', 'm', 'i', 'n'});
    	facade.updateSurveyTemplate(survey, interviewer.getId(), new char[] {'a', 'b', 'c'});
    	facade.changeSurveyStatus(survey.getIdOfSurveys(), SurveyHandler.IN_PROGRESS, "admin", new char[] {'a', 'd', 'm', 'i', 'n'});
    	facade.updateSurveyTemplate(survey, interviewer.getId(), new char[] {'a', 'b', 'c'});
    	facade.sendSurveyTemplate(survey, interviewer.getId(), new char[] {'a', 'b', 'c'});
    	*/
	}
}
