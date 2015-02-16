package com.mmi;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bll.Tools;

import enums.UserType;

public class ConnectButton extends Button implements ActionListener {
	
	/**
	 * Class constructor
	 * @param pWindow : (Window) Window in which button is displayed
	 * @param pLabel : (String) Button's label
	 */
	public ConnectButton(Window pWindow, String pLabel) {
		super(pWindow, pLabel);
		this.addActionListener(this);
	}
	
	public void setAction() {
		String 			sLogin = "", sPassword = "";
		JTextField 		tfLogin = null;
		JPasswordField 	pfPsw = null;
		ErrorMessage 	emLogin = null;
		ErrorMessage 	emPsw = null;
		ErrorMessage 	emException = null;
		int 			iConnected = 0;
		Container 		jpContainer = super.window.getWindow().getContentPane();
		
		// Set all error messages invisible
		Tools.removeErrorMessages(jpContainer);
		
		// Get the information user has provided and errorMessage's instances
		Component[] t = jpContainer.getComponents();
		for (Component c : t) {
			if (c.getName() != null){
				switch (c.getName()){
					case "login" :
						tfLogin = (JTextField) c;
						sLogin = tfLogin.getText().toString();
						break;
					case "password" :
						pfPsw = (JPasswordField) c;
						String sPassword2 = new String(pfPsw.getPassword());
						sPassword=sPassword2;
						break;
					case "loginError" :
						emLogin = (ErrorMessage) c;
						break;
					case "passError" :
						emPsw = (ErrorMessage) c;
						break;
					case "exceptionError":
						emException = (ErrorMessage) c;
				}
			}
		}
			
		// Check the information user has provided and connect user
		if (sLogin.length() == 0|| sPassword.length() == 0){
				if (sLogin.length() == 0) {
					Tools.displayError(emLogin);
					Tools.displayError(tfLogin);
				} 
				if (sPassword.length() == 0) {
					Tools.displayError(emPsw);
					Tools.displayError(pfPsw);
				}
		} else {
			iConnected = this.window.getUser().connectUser(sLogin, sPassword);
			/*
			 * int iConnected : getUser() method return value
			 * 		-1 : exception during connection
			 * 		 0 : user not connected, wrong password or login 
			 * 		 1 : user connected 
			 */
			if (iConnected == 0){
				Tools.displayError(emLogin);
				Tools.displayError(tfLogin);
				Tools.displayError(emPsw);
				Tools.displayError(pfPsw);
				tfLogin.setText("");
				pfPsw.setText("");
			} else if (iConnected == 1) {
				this.window.getWindow().setVisible(false);
				//Window newWindow = new Window(this.window.getUser());
			} else {
				Tools.displayError(emException);
			}
		}		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//this.setAction();
		this.setTestAction();
	}
	
	private void setTestAction() {
		this.window.getUser().connectUser("jGilles", "test");
		this.window.getWindow().setVisible(false);
		if (this.window.getUser().getRights() == UserType.USER_TECH.toInt()){
			new TechnicianWindow(this.window.getUser());
		}
	}
}
