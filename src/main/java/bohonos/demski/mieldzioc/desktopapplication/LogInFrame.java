/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.desktopapplication;

import bohonos.demski.mieldzioc.networkConnection.ServerConnectionFacade;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Andrzej
 */
public class LogInFrame extends JFrame implements ActionListener  {

    private JButton logInButton;
    private JLabel peselLabel, passwordLabel;
    private JTextField peselField, passwordField;
    
    public LogInFrame() {
        
        super("logowanie");
        setSize(300,250);
        setLocation(200,200);
        setResizable(false);
        this.setLayout(null);
        
        peselLabel = new JLabel("PESEL :");
        peselLabel.setBounds(30, 50, 80, 20);
        this.add(peselLabel);
        
        passwordLabel = new JLabel("HAS£O :");
        passwordLabel.setBounds(30, 100, 80, 20);
        this.add(passwordLabel);
        
        peselField = new JTextField();
        peselField.setBounds(120, 50, 150, 20);
        this.add(peselField);
        
        passwordField = new JTextField();
        passwordField.setBounds(120, 100, 150, 20);
        this.add(passwordField);
        
        logInButton = new JButton("Zaloguj");
        logInButton.setBounds(100, 150, 100, 50);
        logInButton.addActionListener(this);
        this.add(logInButton);
        
        setVisible(true);
    }
            
    public void actionPerformed(ActionEvent ae) {

        Object source = ae.getSource();
        
        if (source == logInButton) {
            ServerConnectionFacade connectionFacade = new ServerConnectionFacade("150.254.79.18");
            if (connectionFacade.authenticate(peselField.getText(), passwordField.getText().toCharArray())) {
                GraphicInterface g= new GraphicInterface(connectionFacade);
                dispose();
            }
            else {
                passwordField.setText("");
            }
        }
        
    }
}
