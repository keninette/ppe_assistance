package com.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dal.Database;


/**
 * Classe contenant toutes les informations et méthodes d'un ticket
 */
public class Ticket {
	private int 	numTicket;
	//private GregorianCalendar openDateTime;
	//private GregorianCalendar closeDateTime;
	private String 	incidentDescription;
	private String 	solutionDescription;
	private int 	numIncidentType;
	private int 	numSolutionType;
	private int 	numTicketLevel;
	private int 	numEquipment;
	private boolean solved;
	private ArrayList<Employee> collEmployee;
	//private HashSet<Intervention> collIntervention;
	
	/**
	 * Constructeur n'attendant pas de paramètres
	 */
	public Ticket() {
		this.numTicket = 0;
		this.incidentDescription = "";
		this.numIncidentType = 0;
		this.solutionDescription = "";
		this.numSolutionType = 0;
		this.numTicketLevel = 0;
		this.solved = false;
	}
	
	/**
	 * Constructeur n'attendant avec paramètre
	 * @param : (int) pnIdTicket : numTicket de ticket
	 */	
	public Ticket (int pnIdTicket){
		Database oDbCon = new Database();
		String sQuery = "SELECT * " +
						"FROM 	ticket " +
						"WHERE 	numTicket = ?";
		String tTable[][] = {{"int",String.valueOf(pnIdTicket)}};
		oDbCon.connect();
		ResultSet rs = oDbCon.executePreparedQuery(sQuery, tTable);
		
		try {
			if(rs.first()){
				this.numTicket = rs.getInt("numTicket");
				this.incidentDescription = rs.getString("incidentDescription");
				this.numIncidentType = rs.getInt("numIncidentType");
				this.solutionDescription = rs.getString("solutionDescription");
				this.numSolutionType = rs.getInt("numSolutionType");
				this.collEmployee = this.findTicketTechnicians(pnIdTicket);
			}
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		oDbCon.disconnect();
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
