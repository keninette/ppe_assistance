package com.gui.insertTabs;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bll.Ticket;
import com.bll.enums.Categories;
import com.dal.BasicQueries;
import com.gui.combos.Combo;

public class InsertTicketTab extends InsertTabs{
	private Ticket ticket;
	
	public InsertTicketTab() {
		super.setTab();
		super.icon = new ImageIcon("res/img/icons/icon_add_ticket.png");
	}
	@SuppressWarnings("null")
	@Override
	public JPanel createTabComponents(int numTicket) {
		this.ticket = (Ticket) (numTicket == 0 ? new Ticket() : BasicQueries.createInstance("ticket",numTicket));
		// Create all fields
		JTextField 	numTicketField 	= new JTextField();
		JLabel 		openDateLabel 	= new JLabel("Date d'ouverture"); 
		JTextField 	openDateField 	= new JTextField();
		JLabel		closeDateLabel	= new JLabel("Date de fermeture");
		JTextField 	closeDateField 	= new JTextField();
		JLabel		ticketLevelLabel 	= new JLabel("Niveau du ticket");
		JLabel		incidentTypeLabel 	= new JLabel("Type d'incident");
		Combo		incidentTypeCombo	= new Combo();
		JLabel		incidentDescriptionLabel	= new JLabel("Description de l'incident");
		JTextArea	incidentDescriptionField	= new JTextArea();
		JLabel		solutionTypeLabel	= new JLabel("Solution type");
		Combo		solutionTypeCombo	= new Combo();
		// Ajouter équipement
		
		// Customize fields
		numTicketField.setVisible(false);
		openDateLabel.setBounds(0,0,150,20);
		openDateField.setBounds(160,0,150,20);
		closeDateLabel.setBounds(320, 0, 150, 20);
		closeDateField.setBounds(480, 0, 150, 20);
		ticketLevelLabel.setBounds(30,0,150,20);
		incidentTypeLabel.setBounds(0, 60, 150, 20);
		incidentTypeCombo.setBounds(160, 60, 150, 20);
		incidentDescriptionLabel.setBounds(0, 90, 150, 20);
		incidentDescriptionField.setBounds(160, 90, 400, 20);
		solutionTypeLabel.setBounds(0, 120, 150, 20);
		solutionTypeCombo.setBounds(160, 120, 150, 20);
		
		
		// Fill fields with values
		incidentTypeCombo.fillComboBox(Categories.CAT_INCIDENTTYPE);
		solutionTypeCombo.fillComboBox(Categories.CAT_SOLUTIONTYPE);
		
		// Add fields to container
		tabContainer.add(numTicketField);
		tabContainer.add(openDateLabel);
		tabContainer.add(openDateField);
		tabContainer.add(closeDateLabel);
		tabContainer.add(closeDateField);
		tabContainer.add(ticketLevelLabel);
		tabContainer.add(incidentTypeLabel);
		tabContainer.add(incidentTypeCombo);
		tabContainer.add(incidentTypeCombo);
		tabContainer.add(incidentDescriptionLabel);
		tabContainer.add(incidentDescriptionField);
		tabContainer.add(solutionTypeLabel);
		tabContainer.add(solutionTypeCombo);
		
		return tabContainer;
	}
	
}
