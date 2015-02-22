package com.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bll.categories.IncidentType;
import com.bll.categories.SolutionType;
import com.bll.categories.TicketLevel;
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
	public Ticket	(int pnNumTicket, String psIncidentDescription, int pnNumIncidentType, String psSolutionDescription
					, int pnNumSolutionType, int pnNumTicketLevel, int pnNumEquipment, boolean pbSolved){
		
		this.numTicket = pnNumTicket;
		this.incidentDescription = psIncidentDescription;
		this.solutionDescription = psSolutionDescription;
		this.incidentType = new IncidentType(pnNumIncidentType);
		this.solutionType = new SolutionType(pnNumSolutionType);
		this.ticketLevel = new TicketLevel(pnNumTicketLevel);
		this.solved = pbSolved;
		this.collEmployee = this.findTicketTechnicians(pnNumTicket);
		this.equipment = this.findTicketEquipment(pnNumEquipment);
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
	
	public Equipment findTicketEquipment(int pnNumEquipment){
		Database oDbCon 	= new Database();
		String sQuery 		=	"SELECT * " +
								"FROM 	equipment " +
								"WHERE	numEquipment = ?";
		String tTable[][] 	= {{"int",String.valueOf(pnNumEquipment)}};
		oDbCon.connect();
		ResultSet rs = oDbCon.executePreparedQuery(sQuery, tTable);
		try {
			if (rs.first()) {
				Equipment equip = new Equipement(rs.getInt("numEquipement"), rs.getString("label"), rs.getString("serialNumber")
												,rs.getDate("purchaseDate"), rs.getDate("warrantyDate"), rs.getString("originalComponents")
												, rs.getInt("umSupplier"), rs.getInt("numEmployee"), rs.getInt("numBrand"),rs.getString("photo"));
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		oDbCon.disconnect();
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
