package com.mmi;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.bll.Employee;

public class ViewTickets extends ViewTab {
	
	public ViewTickets(){
		super.setTab();
		super.icon = new ImageIcon("res/img/icons/icon_welcome.png");
	}
	
	public JPanel createTabComponents(Employee pUser) {
		TicketTableModel 	tableModel 	= new TicketTableModel(pUser);
		JTable				jtTable 	= new JTable(tableModel);
		JLabel 				lbHello		= new JLabel("Bonjour " +pUser.getFirstName() +" " +pUser.getName() +" ! "
													+"Vous avez " + pUser.getUserOpenedTicketsNumber(pUser.getNumEmployee()) 
													+" ticket(s) en cours."	);		
		
		
		// Customizing JPanel and components
		super.jpTabContent.setLayout(null);
		super.jpTabContent.setBackground(Color.white);
		// Customizing fields
		lbHello.setBounds(10,10,550,20);
		jtTable.setBounds(20,20,550,300);
		// Adding content to JPanel
		super.jpTabContent.add(lbHello);
		super.jpTabContent.add(jtTable.getTableHeader(), BorderLayout.NORTH);
		super.jpTabContent.add(jtTable, BorderLayout.CENTER);
		
		return(jpTabContent);
	}
}