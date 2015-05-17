/**
 * 
 */
package bohonos.demski.mieldzioc.networkConnection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.GregorianCalendar;
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
		if(authorization == AUTHORIZATION_FAILED) return AUTHORIZATION_FAILED; 
		sendObject(survey);
		int status = readInt();
		disconnect();
		return status; 
	}
	
	public int changeSurveyStatus(String idOfSurveys, int status, String usersId, char[] password){
		connect();
		if(!login(usersId, password)){
			disconnect();
			return BAD_PASSWORD;
		}
		sendInt(CHANGE_SURVEY_STATUS);
		int authorization = readInt();
		if(authorization == AUTHORIZATION_FAILED) return AUTHORIZATION_FAILED;
		sendString(idOfSurveys);
		sendInt(status);
		int operationStatus = readInt();
		disconnect();
		return operationStatus; 
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
	
	public static void main(String[] args) {
		ServerConnectionFacade facade = new ServerConnectionFacade();
		Interviewer interviewer = new Interviewer("", "", "92110908338", new GregorianCalendar());
		interviewer.setInterviewerPrivileges(true);
		Survey survey = new Survey(interviewer);
		survey.setIdOfSurveys("ja");
    	facade.sendSurveyTemplate(survey, interviewer.getId(), new char[] {'a', 'b', 'c'});
    	facade.changeSurveyStatus(survey.getIdOfSurveys(), SurveyHandler.ACTIVE, "admin", new char[] {'a', 'd', 'm', 'i', 'n'});
	}
}
