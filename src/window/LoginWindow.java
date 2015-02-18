package window;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import buttons.Button;
import buttons.ConnectButton;

import com.bll.Employee;
import com.mmi.ErrorMessage;

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
		Button 			btnConnect = new ConnectButton(this, "Connexion");
		JTextField 		tfLogin = new JTextField();
		JTextField 		tfPsw = new JPasswordField();
		JLabel 			lbLogin =  new JLabel("Identifiant");
		JLabel 			lbPsw = new JLabel("Mot de passe");
		ErrorMessage 	emLoginError = new ErrorMessage("Identifiant non renseigné ou  incorrect", "loginError"); 
		ErrorMessage 	emPassError = new ErrorMessage("Mot de passe non renseigné incorrect", "passError");
		ErrorMessage 	emException = new ErrorMessage("Une erreur s'est produite pendant la connexion à la base de données", "exceptionError");
		
		// customizing content
		emException.setName("exceptionError");
		emException.setBounds(40,0,350,20);
		emLoginError.setBounds (150,50,200, 20);
		emPassError.setBounds (150,100,200, 20);
		
		lbLogin.setBounds(10,30,100, 20);
		lbPsw.setBounds(10,80,100, 20);
		
		tfLogin.setBounds(150,30,150, 20);
		tfLogin.setName("login");
		tfPsw.setBounds(150,80,150,20);
		tfPsw.setName("password");
		
		btnConnect.setBounds(285,132,100,30);
		btnConnect.setEnabled(true);
		super.window.getRootPane().setDefaultButton(btnConnect);
		
		// adding content
		super.container.add(emException);
		super.container.add(lbLogin);
		super.container.add(lbPsw);
		super.container.add(tfLogin);
		super.container.add(tfPsw);
		super.container.add(emLoginError);
		super.container.add(emPassError);		
		super.container.add(btnConnect);
		super.window.setVisible(true);
	}
}