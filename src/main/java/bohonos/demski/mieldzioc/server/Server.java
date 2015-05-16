/**
 * 
 */
package bohonos.demski.mieldzioc.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Dominik
 *
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(8189);
			while(true){
			Socket incoming = s.accept();
			System.out.println("Nawi¹zano po³¹czenie z " + incoming.getInetAddress().getHostAddress());
			InputStream inputStream = incoming.getInputStream();
			OutputStream outputStream = incoming.getOutputStream();
			Scanner in = new Scanner(inputStream);
			System.out.println(in.nextLine());
			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
