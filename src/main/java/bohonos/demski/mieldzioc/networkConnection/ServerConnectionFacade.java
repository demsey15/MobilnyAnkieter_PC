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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import bohonos.demski.mieldzioc.interviewer.Interviewer;
import bohonos.demski.mieldzioc.survey.Survey;
import bohonos.demski.mieldzioc.survey.SurveyHandler;

/**
 * @author Dominik Demski
 *
 */
public class ServerConnectionFacade {
	
	public final static int PORT = 8046;
	public final static String HOST = "192.168.1.2";
	
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
	
	
	private SocketChannel socketChannel;
	private Scanner in;
	private PrintWriter out;
	
	/**
	 * Wysy�a nowy szablon ankiety na serwer.
	 * @param survey szablon.
	 * @param usersId id przesy�aj�cego u�ytkownika (musi mie� uprawnienia do tworzenia ankiet).
	 * @param password has�o u�ytkownika.
	 * @return zwraca BAD_PASSWORD, je�li podane has�o jest b��dne lub nie ma u�ytkownika o podanym id,
	 * AUTHORIZATION_FAILED, je�li u�ytkownik nie ma odpowienich uprawnie�, TEMPLATE_ALREADY_EXISTS,
	 * je�li szablon o podanym id ju� istnieje, OPERATION_OK, je�li operacja przebieg�a pomy�lnie.
	 */
	public int sendSurveyTemplate(Survey survey, String usersId, char[] password){
		if(survey == null ||usersId == null || password == null)
			throw new NullPointerException("Przekazane argumenty nie mog� by� nullami.");
		System.out.println("��cz�");
		connect();
		System.out.println("��cz� po");
		if(!login(usersId, password)){
			disconnect();
			System.out.println("Z�e has�o");
			return BAD_PASSWORD;
		}
		System.out.println("Wysy�am");
		sendInt(SEND_NEW_TEMPLATE);
		int authorization = readInt();
		System.out.println("Otrzyma�em " + authorization);
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
	 * @param status status (patrz sta�e w klasie SurevyHandler).
	 * @param usersId id u�ytkownika zmianiaj�cego status (status mo�e zmieni� tylko administrator).
	 * @param password has�o u�ytkownika.
	 * @return BAD_PASSWORD, je�li podano b��dne has�o lub nie ma u�ytkownika o podanym id,
	 * AUTHORIZATION_FAILED, je�li u�ytkownik nie jest administratorem, BAD_DATA_FORMAT, je�li nie ma
	 * ankiety o zadanym id, OPERATION_OK, je�li wszystko przebieg�o pomy�lnie.
	 */
	public int changeSurveyStatus(String idOfSurveys, int status, String usersId, char[] password){
		if(idOfSurveys == null ||usersId == null || password == null)
			throw new NullPointerException("Przekazane argumenty nie mog� by� nullami.");
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
	 * @param usersId id u�ytkownika.
	 * @param password has�o u�ytwkonika.
	 * @return BAD_PASSWORD, je�li podano b��dne has�o lub nie ma u�ytkownika o podanym id,
	 * AUTHORIZATION_FAILED, je�li u�ytkownik nie ma dopowiednich uprawnie� (uprawnienia do tworzenia
	 * ankiet), BAD_DATA_FORMAT, je�li nie ma
	 * ankiety o zadanym id lub ankieta nie ma statusu IN_PROGRESS,
	 *  OPERATION_OK, je�li wszystko przebieg�o pomy�lnie.
	 */
	public int updateSurveyTemplate(Survey survey, String usersId, char[] password){
		if(survey == null ||usersId == null || password == null)
			throw new NullPointerException("Przekazane argumenty nie mog� by� nullami.");
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
	 * Wysy�a list� wype�nionych ankiet.
	 * @param surveys lista wype�nionych ankiet (nie mo�e by� r�wna null).
	 * @param usersId id ankietera.
	 * @param password has�o ankietera.
	 * @return lista z kodem: BAD_PASSWORD, je�li podano b��dne has�o lub nie ma u�ytkownika o podanym id,
	 * AUTHORIZATION_FAILED, je�li u�ytkownik nie ma dopowiednich uprawnie� (musi by� ankieterem),
	 * albo lista z kodami (dla ka�dej ankiety odpowiednio): 
	 * BAD_DATA_FORMAT, je�li ankieta o zadanym numerze znajduje si� ju� w repozytorium,
	 *  OPERATION_OK, je�li wszystko przebieg�o pomy�lnie, SURVEY_INACTIVE, je�li ankieta nie ma
	 *  statusu "aktywna".
	 */
	public List<Integer> sendFilledSurveys(List<Survey> surveys, String usersId, char[] password){
		if(surveys == null ||usersId == null || password == null)
			throw new NullPointerException("Przekazane argumenty nie mog� by� nullami.");
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
			throw new NullPointerException("Przekazane argumenty nie mog� by� nullami.");
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
			throw new NullPointerException("Przekazane argumenty nie mog� by� nullami.");
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
			throw new NullPointerException("Przekazane argumenty nie mog� by� nullami.");
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
			throw new NullPointerException("Przekazane argumenty nie mog� by� nullami.");
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
			throw new NullPointerException("Przekazane argumenty nie mog� by� nullami.");
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
	 * Pobierz list� wype�nionych ankiet (mo�e to zrobi� tylko administrator).
	 * @param idOfSurveys id grupy ankiet do pobrania.
	 * @param usersId id administratora.
	 * @param password has�o administratora.
	 * @return list� wype�nionych ankiet dla danej grupy ankiet lub null, je�li: podano b��dne
	 * dane logowania, loguj�cy si� u�ytkownik nie jest administratorem, nie ma grupy ankiet o
	 * podanym id lub nie przes�ano jeszcze �adnego wyniku, wyst�pi� nieznany b��d.
	 */
	public List<Survey> getFilledSurveys(String idOfSurveys, String usersId, char[] password){
		if(idOfSurveys == null || password == null || usersId == null)
			throw new NullPointerException("Przekazane argumenty nie mog� by� nullami.");
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
					return null;   //nie powinno si� zdarzy�
				}
			}
		}
	}
	
	private boolean login(String usersId, char[] password){
		System.out.println("Wysy�am id");
		sendString(usersId);
		System.out.println("Wysy�am has�o");
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
			System.out.println("B��d");
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
		System.out.println("Wysy�am: " + i);	
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
		System.out.println("Wysy�am: " + s);	
		out.println(s);
	}
	
	private int readInt(){
		try{
			int i = Integer.parseInt(in.nextLine());
			System.out.println("Odczyta�em: " + i);
			return i;
		}
		catch(NumberFormatException e){
			return BAD_DATA_FORMAT;
		}
	}
	
	private Object readObject(){
		try {
			ObjectInputStream inObj = new ObjectInputStream(Channels.newInputStream(socketChannel));
			return inObj.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		ServerConnectionFacade facade = new ServerConnectionFacade();
		Interviewer interviewer = new Interviewer("", "", "92110908338", new GregorianCalendar());
		interviewer.setInterviewerPrivileges(true);
		Survey survey = new Survey(interviewer);
		survey.setIdOfSurveys("ja");
		Survey survey2 = new Survey(interviewer);
		survey2.setIdOfSurveys("ja");
		
    	facade.sendSurveyTemplate(survey, interviewer.getId(), new char[] {'a', 'b', 'c'});
    	facade.changeSurveyStatus(survey.getIdOfSurveys(), SurveyHandler.ACTIVE, "admin", new char[] {'a', 'd', 'm', 'i', 'n'});
    	facade.updateSurveyTemplate(survey, interviewer.getId(), new char[] {'a', 'b', 'c'});
    	facade.changeSurveyStatus(survey.getIdOfSurveys(), SurveyHandler.IN_PROGRESS, "admin", new char[] {'a', 'd', 'm', 'i', 'n'});
    	facade.updateSurveyTemplate(survey, interviewer.getId(), new char[] {'a', 'b', 'c'});
    	facade.sendSurveyTemplate(survey, interviewer.getId(), new char[] {'a', 'b', 'c'});
	}
}
