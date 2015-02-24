package com.gui.viewTabs;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import com.bll.Employee;
import com.dal.BasicRequests;
import com.gui.TicketTableModel;

public class ViewTickets extends ViewTab {
	private JPanel 		panel1;
	private JPanel 		panel2;
	private JScrollPane	scroll; 
	
	/**
	 * Class constructor 
	 */
	public ViewTickets(){
		super.setTab();
		super.icon = new ImageIcon("res/img/icons/icon_welcome.png");
		panel1  = new JPanel();
		panel2  = new JPanel();
	
		// Customize panels
		panel1.setBounds(0,0,1200,40);
		panel1.setLayout(null);
		panel1.setBackground(Color.white);
		panel2.setBounds(0,220,1200,20);
		panel2.setLayout(null);
		panel2.setBackground(Color.green);
	}
	
	/**
	 * Create tab components
	 * @param Employee user
	 * @return jPanel
	 */
	public JPanel createTabComponents(Employee user) {
		TicketTableModel 	tableModel 	= new TicketTableModel(user);
		JTable				t			= new JTable(tableModel);
		JLabel 				welcomeMsg	= new JLabel("Bonjour " +user.getFirstName() +" " +user.getName() +" ! "
													+"Vous avez " + BasicRequests.getUserOpenedTicketsNumber(user.getNumEmployee()) 
													+" ticket(s) en cours."	);		

		// Customize fields
		scroll = new JScrollPane(t);
		scroll.setBounds(0,40,1200,200);
		scroll.setBackground(Color.red);
		welcomeMsg.setBounds(0,0,550,20);
		t.setBounds(20,20,550,300);
		// Add content to Panel1
		panel1.add(welcomeMsg);
		// Add all panes to container
		super.tabContainer.add(panel1);
		super.tabContainer.add(scroll);
		super.tabContainer.add(panel2);
		return(super.tabContainer);
	}
}