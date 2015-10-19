/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import java.awt.EventQueue;

/**
 *
 * @author Delirus
 */
public class DesktopApplication {
    public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {

		//	@Override
			public void run() {
        //GraphicInterface g= new GraphicInterface();
        LogInFrame logInFrame = new LogInFrame();
        //g.repaint();
                        }
		});
    }
}
