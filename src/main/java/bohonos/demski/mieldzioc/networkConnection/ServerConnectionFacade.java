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
	
	private SocketChannel socketChannel;
//	private Socket socketChannel;
	private Scanner in;
	private PrintWriter out;
	
	public int sendSurveyTemplate(Survey survey, String usersId, char[] password){
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
		if(authorization == AUTHORIZATION_FAILED) return AUTHORIZATION_FAILED; 
		sendObject(survey);
		int status = readInt();
		disconnect();
		return status; 
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
	
	public static void main(String[] args) {
		ServerConnectionFacade facade = new ServerConnectionFacade();
		Interviewer interviewer = new Interviewer("", "", "92110908338", new GregorianCalendar());
		interviewer.setInterviewerPrivileges(true);
		Survey survey = new Survey(interviewer);
		survey.setIdOfSurveys("ja");
    	facade.sendSurveyTemplate(survey, interviewer.getId(), new char[] {'a', 'b', 'c'});
	
	}
}
