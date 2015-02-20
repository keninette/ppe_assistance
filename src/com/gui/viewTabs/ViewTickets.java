package com.gui.viewTabs;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import com.bll.Employee;
import com.gui.TicketTableModel;

public class ViewTickets extends ViewTab {
	private JPanel 		jpPanel1;
	private JPanel 		jpPanel2;
	private JScrollPane	jscScroll; 	// Contains tickets table
	
	public ViewTickets(){
		super.setTab();
		super.icon = new ImageIcon("res/img/icons/icon_welcome.png");
		jpPanel1  = new JPanel();
		jpPanel2  = new JPanel();
	
		// Customize panels
		jpPanel1.setBounds(0,0,1200,40);
		jpPanel1.setLayout(null);
		jpPanel1.setBackground(Color.white);
		jpPanel2.setBounds(0,220,1200,20);
		jpPanel2.setLayout(null);
		jpPanel2.setBackground(Color.green);
	}
	
	public JPanel createTabComponents(Employee pUser) {
		TicketTableModel 	tableModel 	= new TicketTableModel(pUser);
		JTable				jtTable 	= new JTable(tableModel);
		JLabel 				lbHello		= new JLabel("Bonjour " +pUser.getFirstName() +" " +pUser.getName() +" ! "
													+"Vous avez " + pUser.getUserOpenedTicketsNumber(pUser.getNumEmployee()) 
													+" ticket(s) en cours."	);		
		
		
		
		
		// Customize fields
		jscScroll = new JScrollPane(jtTable);
		jscScroll.setBounds(0,40,1200,200);
		jscScroll.setBackground(Color.red);
		lbHello.setBounds(0,0,550,20);
		jtTable.setBounds(20,20,550,300);
		// Add content to Panel1
		jpPanel1.add(lbHello);
		// Add all panes to container
		super.jpTabContainer.add(jpPanel1);
		super.jpTabContainer.add(jscScroll);
		super.jpTabContainer.add(jpPanel2);
		return(super.jpTabContainer);
	}
}