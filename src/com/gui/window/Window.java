package com.gui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.bll.Employee;
import com.gui.TicketTableModel;

/**
 * Class to create windows
 */
public abstract class Window {
	protected 	JFrame		window;
	protected 	JPanel 		container;
	protected 	Employee 	user;
	
	/**
	 * To be overrided : method to create window components
	 */
	public abstract void createComponents();
	
	/**
	 * Create a new Jframe, a new JPanel and set default properties
	 */
	public void createWindow(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window = new JFrame();
		container = new JPanel(); 
		
		window.setContentPane(container);
		window.setLocationRelativeTo(null);
		window.setLocation((screen.width - window.getSize().width)/2,(screen.height - window.getSize().height)/2);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setLayout(null);
		container.setBackground(Color.white);
	}
	
	 /************** getters & setters ***************/
	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}

	public JPanel getContainer() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}

	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}

	/**
	 
	public void addTechFields(){
		JTabbedPane tabs = new JTabbedPane();
		// Customizing JTabbedPane
		tabs.setTabPlacement(2);
		tabs.setBounds(0,0,800,600);
		tabs.setBackground(Color.WHITE);
		// Creating tabs icons
		ImageIcon iconWelcome = new ImageIcon("res/img/icons/icon_welcome.png");
		// Adding tabs to JTabbedPane
		tabs.addTab("", iconWelcome, createTechVisWelcomeTab(),null);
		tabs.addTab("Tab 2", null, createAddTicketTab(),null);
		
		container.add(tabs);
	}
	
	public JPanel createTechVisWelcomeTab(){
		TicketTableModel tableModel = new TicketTableModel(this.user);
		JTable	jtTable = new JTable(tableModel);
		JPanel jpTabContent = new JPanel();
		JLabel lbHello 		= new JLabel("Bonjour " +this.getUser().getFirstName() +" " +this.getUser().getName() +" ! "
										 +"Vous avez " + this.getUser().getUserOpenedTicketsNumber(this.getUser().getNumEmployee()) 
										 +" ticket(s) en cours."	);		
		// Customizing JPanel
		jpTabContent.setLayout(null);
		jpTabContent.setBackground(Color.white);
		// Customizing fields
		lbHello.setBounds(0,0,550,20);
		jtTable.setBounds(20,20,550,300);
		// Adding content to JPanel
		jpTabContent.add(lbHello);
		jpTabContent.add(jtTable.getTableHeader(), BorderLayout.NORTH);
		jpTabContent.add(jtTable, BorderLayout.CENTER);
		
		return(jpTabContent);
	}
	

	public JPanel createAddTicketTab(){
		JPanel jpTabContent = new JPanel();
		JLabel lbTemp = new JLabel("add ticket");
		
		jpTabContent.add(lbTemp);
		return(jpTabContent);
	}*/
}

