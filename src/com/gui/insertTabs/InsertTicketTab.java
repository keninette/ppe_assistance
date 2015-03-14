package com.gui.insertTabs;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
		Combo		ticketLevelCombo	= new Combo();
		JLabel		incidentTypeLabel 	= new JLabel("Type d'incident");
		Combo		incidentTypeCombo	= new Combo();
		JLabel		incidentDescriptionLabel	= new JLabel("Description de l'incident");
		JTextArea	incidentDescriptionField	= new JTextArea("");
		JLabel		solutionTypeLabel	= new JLabel("Solution type");
		Combo		solutionTypeCombo	= new Combo();
		JLabel		solutionDescriptionLabel	= new JLabel("Description de la solution");
		JTextArea	solutionDescriptionField	= new JTextArea();
		
		// Ajouter équipement
		
		// Customize fields
		numTicketField.setVisible(false);
		openDateLabel.setBounds(0,0,150,20);
		openDateField.setBounds(160,0,150,20);
		closeDateLabel.setBounds(320, 0, 150, 20);
		closeDateField.setBounds(480, 0, 150, 20);
		ticketLevelLabel.setBounds(0,30,150,20);
		ticketLevelCombo.setBounds(160,30,250,20);
		incidentTypeLabel.setBounds(0, 60, 150, 20);
		incidentTypeCombo.setBounds(160, 60, 250, 20);
		incidentDescriptionLabel.setBounds(0, 90, 150, 20);
		incidentDescriptionField.setBounds(160, 90, 400, 20);
		incidentDescriptionField.setSize(300, 50);
		incidentDescriptionField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		solutionTypeLabel.setBounds(0, 150, 150, 20);
		solutionTypeCombo.setBounds(160, 150, 250, 20);
		solutionDescriptionLabel.setBounds(0, 180, 150, 20);
		solutionDescriptionField.setBounds(160, 180, 400, 20);
		solutionDescriptionField.setSize(300, 50);
		solutionDescriptionField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		
		// Fill fields with values
		ticketLevelCombo.fillComboBox(Categories.CAT_TICKETLEVEL);
		incidentTypeCombo.fillComboBox(Categories.CAT_INCIDENTTYPE);
		solutionTypeCombo.fillComboBox(Categories.CAT_SOLUTIONTYPE);
		
		// Add fields to container
		tabContainer.add(numTicketField);
		tabContainer.add(openDateLabel);
		tabContainer.add(openDateField);
		tabContainer.add(closeDateLabel);
		tabContainer.add(closeDateField);
		tabContainer.add(ticketLevelLabel);
		tabContainer.add(ticketLevelCombo);
		tabContainer.add(incidentTypeLabel);
		tabContainer.add(incidentTypeCombo);
		tabContainer.add(incidentTypeCombo);
		tabContainer.add(incidentDescriptionLabel);
		tabContainer.add(incidentDescriptionField);
		tabContainer.add(solutionTypeLabel);
		tabContainer.add(solutionTypeCombo);
		tabContainer.add(solutionDescriptionLabel);
		tabContainer.add(solutionDescriptionField);
		
		return tabContainer;
	}
	
}
