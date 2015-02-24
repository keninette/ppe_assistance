package com.gui.window;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



import com.bll.Employee;
import com.gui.ErrorMessage;
import com.gui.buttons.Button;
import com.gui.buttons.ConnectButton;

public class LoginWindow extends Window {
	
	/**
	 * 	Class constructor 
	 */
	public LoginWindow() {
		super.createWindow();
		super.user = new Employee();
		super.window.setSize(400,200);
		super.window.setTitle("Bienvenue sur Assistance !");
		super.window.setResizable(false);
		this.createComponents();
	}
	
	/**
	 *  Create all LoginWindow components and set components properties
	 */
	public void createComponents() {
		Button 			connectButton 	= new ConnectButton(this, "Connexion");
		JTextField 		loginField		= new JTextField();
		JTextField 		pswField 		= new JPasswordField();
		JLabel 			loginLabel 		= new JLabel("Identifiant");
		JLabel 			pswLabel		= new JLabel("Mot de passe");
		ErrorMessage 	loginError 		= new ErrorMessage("Identifiant non renseigné ou  incorrect", "loginError"); 
		ErrorMessage 	pswError 		= new ErrorMessage("Mot de passe non renseigné incorrect", "passError");
		ErrorMessage 	exceptionError 	= new ErrorMessage("Une erreur s'est produite pendant la connexion à la base de données", "exceptionError");
		
		// customizing content
		exceptionError.setName("exceptionError");
		exceptionError.setBounds(40,0,350,20);
		loginError.setBounds (150,50,200, 20);
		pswError.setBounds (150,100,200, 20);
		
		loginLabel.setBounds(10,30,100, 20);
		pswLabel.setBounds(10,80,100, 20);
		
		loginField.setBounds(150,30,150, 20);
		loginField.setName("login");
		pswField.setBounds(150,80,150,20);
		pswField.setName("password");
		
		connectButton.setBounds(285,132,100,30);
		connectButton.setEnabled(true);
		super.window.getRootPane().setDefaultButton(connectButton);
		
		// adding content
		super.container.add(exceptionError);
		super.container.add(loginLabel);
		super.container.add(pswLabel);
		super.container.add(loginField);
		super.container.add(pswField);
		super.container.add(loginError);
		super.container.add(pswError);		
		super.container.add(connectButton);
		super.window.setVisible(true);
	}
}