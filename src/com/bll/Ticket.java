package com.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	//private IncidentType			incidentType;
	//private SolutionType 			solutionType;
	//private TicketLevel			ticketLevel;
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
		this.solved = false;
	}
	
	/**
	 * Class constructor
	 */	
	public Ticket	(int pnNumTicket, String psIncidentDescription, int pnNumIncidentType, String psSolutionDescription
					, int pnNumSolutionType, int pnNumTicketLevel, boolean pbSolved){
		
		this.numTicket = pnNumTicket;
		this.incidentDescription = psIncidentDescription;
		this.numIncidentType = pnNumIncidentType;
		this.solutionDescription = psSolutionDescription;
		this.numSolutionType = pnNumSolutionType;
		this.numTicketLevel = pnNumTicketLevel;
		this.solved = pbSolved;
		this.collEmployee = this.findTicketTechnicians(pnNumTicket);
	}	
	
	public ArrayList<Employee> findTicketTechnicians(int pnIdTicket){
		ArrayList<Employee> collEmployee = new ArrayList<Employee>();
		Database oDbCon 	= new Database();
		String sQuery 		=	"SELECT numTechnician " +
								"FROM 	technician_per_ticket " +
								"WHERE	numTicket = ?";
		String tTable[][] 	= {{"int",String.valueOf(pnIdTicket)}};
		oDbCon.connect();
		ResultSet rs = oDbCon.executePreparedQuery(sQuery, tTable);
		try {
			while (rs.next()) {
				collEmployee.add(new Employee(rs.getInt("numTechnician")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		oDbCon.disconnect();
		return collEmployee;
	}
	
	public Equipment findTicketEquipment(){
		
		return new Equipment();
	}
	
	// *************** getters ***************
	public int getNumTicket() {
		return this.numTicket;
	}
	
	public String getIncidentDescription() {
		return this.incidentDescription;
	}
	
	public int getNumIncidentType()	{
		return this.numIncidentType;
	}
	
	// TODO : date ouverture
	
	public int getNumTicketLevel() {
		return this.numTicketLevel;
	}
	
	public int getNumEquipment() {
		return this.numEquipment;
	}
	
	public boolean getSolved(){
		return this.solved;
	}
}
