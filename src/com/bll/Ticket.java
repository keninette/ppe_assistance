package com.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bll.categories.IncidentType;
import com.bll.categories.SolutionType;
import com.bll.categories.TicketLevel;
import com.dal.BasicRequests;
import com.dal.Database;


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
	//private ArrayList<Intervention> collIntervention;
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
		this.collEmployee = BasicRequests.findTicketTechnicians(numTicket);
		this.equipment = BasicRequests.findTicketEquipmentInfo(numEquipment);
	}	
	
	/*************** getters ***************/
	public int getNumTicket() {
		return this.numTicket;
	}
	
	public String getIncidentDescription() {
		return this.incidentDescription;
	}
	
	public int getNumIncidentType()	{
		return this.incidentType.getNum();
	}
	
	// TODO : date ouverture
	
	public int getNumTicketLevel() {
		return this.ticketLevel.getNum();
	}
	
	public int getNumEquipment() {
		return this.equipment.getNumEquipment();
	}
	
	public boolean getSolved(){
		return this.solved;
	}
}
