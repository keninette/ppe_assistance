package com.gui.insertTabs;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bll.enums.Categories;
import com.gui.combos.Combo;

public class InsertTicketTab extends InsertTabs{
	
	
	public InsertTicketTab() {
		super.setTab();
		super.icon = new ImageIcon("res/img/icons/icon_add_ticket.png");
	}
	@SuppressWarnings("null")
	@Override
	public JPanel createTabComponents(int numTicket) {
		// Create all fields
		JTextField 	numTicketField 	= new JTextField();
		JLabel 		openDateLabel 	= new JLabel("Date d'ouverture"); 
		JTextField 	openDateField 	= new JTextField();
		JLabel		closeDateLabel	= new JLabel("Date de fermeture");
		JTextField 	closeDateField 	= new JTextField();
		JLabel		ticketLevelLabel 	= null;
		JLabel		incidentTypeLabel 	= new JLabel("Type d'incident");
		Combo		incidentTypeCombo	= new Combo();
		JLabel		incidentDescriptionLabel	= new JLabel("Description de l'incident");
		JTextArea	incidentDescriptionField	= new JTextArea();
		// Ajouter interventions
		JLabel		solutionTypeLabel	= new JLabel("Solution type");
		Combo		solutionTypeCombo	= new Combo();
		// Ajouter équipement
		
		// Customize fields
		numTicketField.setVisible(false);
		openDateLabel.setBounds(0,0,100,20);
		openDateField.setBounds(0,110,150,20);
		closeDateLabel.setBounds(0, 280, 100, 20);
		closeDateField.setBounds(0, 390, 150, 20);
		//ticketLevelLabel.setBounds(30,0,150,20);
		incidentTypeLabel.setBounds(60, 0, 100, 20);
		incidentTypeCombo.setBounds(60, 110, 150, 20);
		incidentDescriptionLabel.setBounds(90, 0, 100, 20);
		incidentDescriptionField.setBounds(90, 110, 400, 20);
		solutionTypeLabel.setBounds(120, 0, 100, 20);
		solutionTypeCombo.setBounds(120, 110, 150, 20);
		
		
		// Fill fields with values
		incidentTypeCombo.fillComboBox(Categories.CAT_INCIDENTTYPE);
		solutionTypeCombo.fillComboBox(Categories.CAT_SOLUTIONTYPE);
		
		// Add fields to container
		tabContainer.add(numTicketField);
		tabContainer.add(openDateLabel);
		tabContainer.add(openDateField);
		tabContainer.add(closeDateLabel);
		tabContainer.add(closeDateField);
		//tabContainer.add(ticketLevelLabel);
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
