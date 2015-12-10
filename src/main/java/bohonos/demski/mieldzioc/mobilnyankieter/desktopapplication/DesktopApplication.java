/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

import java.awt.EventQueue;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Delirus
 */
public class DesktopApplication {
    public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {

		//	@Override
			public void run() {
                            try {
                                GraphicInterface g= new GraphicInterface();
                                //LogInFrame logInFrame = new LogInFrame();
                                //g.repaint();
                            } catch (IOException ex) {
                                Logger.getLogger(DesktopApplication.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(DesktopApplication.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
		});
    }
}
