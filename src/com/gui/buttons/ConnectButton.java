package com.gui.buttons;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;



import com.bll.Tools;
import com.bll.enums.UserType;
import com.gui.ErrorMessage;
import com.gui.window.TechnicianWindow;
import com.gui.window.Window;


public class ConnectButton extends Button implements ActionListener {
	
	/**
	 * Class constructor
	 * @param Window
	 * @param JLabel
	 */
	public ConnectButton(Window window, String label) {
		super(window, label);
		this.addActionListener(this);
	}
	
	/**
	 * Set button's action
	 */
	public void setAction() {
		String 			loginValue = null, pswValue = null;
		JTextField 		loginField = null;
		JPasswordField 	pswField = null;
		ErrorMessage 	loginError = null;
		ErrorMessage 	pswError = null;
		ErrorMessage 	exceptionError = null;
		int 			connected = 0;
		Container 		container = super.window.getWindow().getContentPane();
		
		// Set all error messages invisible
		Tools.removeErrorMessages(container);
		
		// Get the information user has provided and errorMessage's instances
		Component[] t = container.getComponents();
		for (Component c : t) {
			if (c.getName() != null){
				switch (c.getName()){
					case "login" :
						loginField = (JTextField) c;
						loginValue = loginField.getText().toString();
						break;
					case "password" :
						pswField = (JPasswordField) c;
						String psw2 = new String(pswField.getPassword());
						pswValue = psw2;
						break;
					case "loginError" :
						loginError = (ErrorMessage) c;
						break;
					case "passError" :
						pswError = (ErrorMessage) c;
						break;
					case "exceptionError":
						exceptionError = (ErrorMessage) c;
				}
			}
		}
			
		// Check the information user has provided and connect user
		if (loginValue.length() == 0|| pswValue.length() == 0){
				if (loginValue.length() == 0) {
					Tools.displayError(loginError);
					Tools.displayError(loginField);
				} 
				if (pswValue.length() == 0) {
					Tools.displayError(pswError);
					Tools.displayError(pswField);
				}
		} else {
			connected = this.window.getUser().connectUser(loginValue, pswValue);
			/*
			 * int connected : getUser() method return value
			 * 		-1 : exception during connection
			 * 		 0 : user not connected, wrong password or login 
			 * 		 1 : user connected 
			 */
			if (connected == 0){
				Tools.displayError(loginError);
				Tools.displayError(loginField);
				Tools.displayError(pswError);
				Tools.displayError(pswField);
				loginField.setText("");
				pswField.setText("");
			} else if (connected == 1) {
				this.window.getWindow().setVisible(false);
				//Window newWindow = new Window(this.window.getUser());
			} else {
				Tools.displayError(exceptionError);
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
		if (this.window.getUser().getEmployeeType().getNum() == UserType.USER_TECH.toInt()){
			new TechnicianWindow(this.window.getUser());
		}
	}
}
