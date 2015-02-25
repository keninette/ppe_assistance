package com.bll;

import java.util.ArrayList;

import com.bll.categories.IncidentType;
import com.bll.categories.SolutionType;
import com.bll.categories.TicketLevel;
import com.dal.BasicQueries;


/**
 * Classe contenant toutes les informations et méthodes d'un ticket
 */
public class Ticket {
	private int 					numTicket;
	//private GregorianCalendar 	openDateTime;
	//private GregorianCalendar 	closeDateTime;
	private String 					incidentDescription;
	private String 					solutionDescription;
	private IncidentType			incidentType;
	private SolutionType 			solutionType;
	private TicketLevel				ticketLevel;
	private boolean 				solved;
	private ArrayList<Employee> 	collEmployee;
	private ArrayList<Intervention> collIntervention;
	private	Equipment				equipment;
	
	/**
	 * Constructeur n'attendant pas de paramètres
	 */
	public Ticket() {
		this.numTicket = 0;
		this.incidentDescription = "";
		this.solutionDescription = "";
		this.incidentType = null;
		this.solutionType = null;
		this.ticketLevel = null;
		this.solved = false;
		this.collEmployee = new ArrayList<Employee>();
		this.collIntervention = new ArrayList<Intervention>();
		this.equipment = null;
	}
	
	/**
	 * Class constructor
	 */	
	public Ticket	(int numTicket, String incidentDescription, int numIncidentType, 
						String solutionDescription, int numSolutionType, int numTicketLevel,
						int numEquipment, boolean solved){
		
		this.numTicket = numTicket;
		this.incidentDescription = incidentDescription;
		this.solutionDescription = solutionDescription;
		this.incidentType = new IncidentType(numIncidentType);
		this.solutionType = new SolutionType(numSolutionType);
		this.ticketLevel = new TicketLevel(numTicketLevel);
		this.solved = solved;
		this.collEmployee = BasicQueries.findTicketTechnicians(numTicket);
		this.collIntervention = BasicQueries.findTicketInterventions(numTicket);
		this.equipment = BasicQueries.findTicketEquipmentInfo(numEquipment);
	}
	
	/*************** getters & setters ***************/
	public int getNumTicket() {
		return numTicket;
	}

	public void setNumTicket(int numTicket) {
		this.numTicket = numTicket;
	}

	public String getIncidentDescription() {
		return incidentDescription;
	}

	public void setIncidentDescription(String incidentDescription) {
		this.incidentDescription = incidentDescription;
	}

	public String getSolutionDescription() {
		return solutionDescription;
	}

	public void setSolutionDescription(String solutionDescription) {
		this.solutionDescription = solutionDescription;
	}

	public IncidentType getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}

	public SolutionType getSolutionType() {
		return solutionType;
	}

	public void setSolutionType(SolutionType solutionType) {
		this.solutionType = solutionType;
	}

	public TicketLevel getTicketLevel() {
		return ticketLevel;
	}

	public void setTicketLevel(TicketLevel ticketLevel) {
		this.ticketLevel = ticketLevel;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public ArrayList<Employee> getCollEmployee() {
		return collEmployee;
	}

	public void setCollEmployee(ArrayList<Employee> collEmployee) {
		this.collEmployee = collEmployee;
	}

	public ArrayList<Intervention> getCollIntervention() {
		return collIntervention;
	}

	public void setCollIntervention(ArrayList<Intervention> collIntervention) {
		this.collIntervention = collIntervention;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}	
	

}
