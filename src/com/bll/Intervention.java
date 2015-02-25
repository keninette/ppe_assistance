package com.bll;

import com.bll.categories.InterventionType;
import com.dal.BasicQueries;

public class Intervention {
	private int 				numIntervention;
	private Ticket				ticket;
	//date
	private String				description;
	//durée
	private InterventionType 	interventionType;
	
	/**
	 * Class constructor
	 */
	public Intervention() {
		numIntervention 	= 0;
		ticket 				= new Ticket();
		description 		= null;
		interventionType 	= new InterventionType();
	}
	
	/**
	 * Class constructor
	 * @param int numIntervention
	 * @param Ticket ticket
	 * @param String description
	 * @param int numInterventionType
	 */
	public Intervention(int numIntervention, int numTicket, String description, int numInterventionType) {
		this.numIntervention = numIntervention;
		this.ticket = (Ticket) BasicQueries.createInstance("ticket", numTicket);
		this.description = description;
		this.interventionType = new InterventionType(numIntervention);
	}
	
	/*************** getters & setters **************/
	public int getNumIntervention() {
		return numIntervention;
	}

	public void setNumIntervention(int numIntervention) {
		this.numIntervention = numIntervention;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InterventionType getInterventionType() {
		return interventionType;
	}

	public void setInterventionType(InterventionType interventionType) {
		this.interventionType = interventionType;
	} 
}
