package window;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.bll.Employee;
import com.mmi.TicketTableModel;

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
	 * Creates a new Jframe, a new JPanel and sets default properties
	 */
	public void createWindow(){
		window = new JFrame();
		container = new JPanel(); 
		
		window.setContentPane(container);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setLayout(null);
		container.setBackground(Color.white);
	}

	/**
	 * @feature : add all fields for technician
	 * @param : none
	 * @return : none
	 */
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
	}
	
	/**
	 * Getter for user
	 * @return : Employee
	 */
	public Employee getUser(){
		return this.user;
	}
	
	/**
	 * Getter for window
	 * @return : (Window) window 
	 */
	public JFrame getWindow(){
		return this.window;
	}
}

