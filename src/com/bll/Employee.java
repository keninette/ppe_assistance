package com.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.dal.Database;



public class Employee {
	
	private int 	numEmployee;
	private String 	name;
	private String 	firstName;
	private String 	address;
	private String 	postalCode;
	private String  town;
	private String 	phone;
	private String 	login;
	private String 	psw;
	private int 	rights;
	private boolean connected;
	
	/**
	 * Constructeur de la classe
	 * Par défaut : connected = false.
	 */
	public Employee(){
		numEmployee=0;
		name = "";
		firstName = "";
		connected = false;
	}
	
	/**
	 * Constructeur de la classe avec id
	 * @param :  (int) pnIdEmployee : numEmployee de l'Employee
	 * Par défaut : connected = false.
	 */
	public Employee(int pnIdEmployee){
		Database oDbCon 	= new Database();
		String sQuery 		=	"SELECT * " +
								"FROM	employee " +
								"WHERE 	numEmployee = ?";
		String tTable[][] 	= {{"int",String.valueOf(pnIdEmployee)}};
		
		oDbCon.connect();
		ResultSet rs = oDbCon.executePreparedQuery(sQuery, tTable);
		
		try {
			if (rs.first()){
				this.numEmployee 	= rs.getInt("numEmployee");
				this.name 			= rs.getString("name");
				this.firstName		= rs.getString("firstName");
				this.phone			= rs.getString("phone");
				this.rights			= rs.getInt("numEmployeeType");
				this.connected		= false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		oDbCon.disconnect();
	}
	
	
	/**
	 * features : 
	 *  - vérifie la validité du couple (login,password) en base de données
	 *  - récupère les informations concernant l'utilisateur si le couple était correct
	 * @param psLogin (String) : l'identifiant entré par l'utilisateur
	 * @param psPsw (String) : le mot de passe entré par l'utilisateur
	 * @return 0 (login et/ou password incorrect), 1(connexion réussie), -1 (une exception s'est produite)
	 */
	public int connectUser(String psLogin, String psPsw){
		Database oDbCon = new Database();
		ResultSet rs;
		String tTemp[][] = {{"String",psLogin},{"String",psPsw}};
		String sQuery = "SELECT * "+
						"FROM	employee "+
						"WHERE	login=? "+
						"AND	psw=?";
		
		// Connexion et éxécution de la requête
		oDbCon.connect();
		rs = oDbCon.executePreparedQuery(sQuery,tTemp);
		if (rs != null){
			try{
				if(rs.first()){
					this.numEmployee = rs.getInt("numEmployee");
					this.name = rs.getString("name");
					this.firstName = rs.getString("firstName");
					this.rights = rs.getInt("numEmployeeType");
					this.connected = true;
				}
			}catch(SQLException e){
				System.out.println(e.getMessage());
				return -1;
			}
		}
		oDbCon.disconnect();
		
		return this.connected?1:0;
	}
	
	public ArrayList<Ticket> findEmployeeTickets(int pnIdEmployee) {
		// Variables declaration
		ArrayList<Ticket> 	collTickets 	= new ArrayList<Ticket>();
		Database 			oDbCon			= new Database();
		String 				sQuery 			= 	"SELECT 	tp.numTicket, " +
												"			t.solved " +
												"FROM 		technician_per_ticket tp " +
												"LEFT JOIN 	ticket t " +
												"ON 		(t.numTicket = tp.numTicket) " +
												"WHERE	numTechnician = ?";
		String 				tTable[][]		= {{"int",String.valueOf(pnIdEmployee)}};
		ResultSet 			rs;
		// Connect to Database
		
		oDbCon.connect();
		rs = oDbCon.executePreparedQuery(sQuery, tTable);
		try {
			if (rs.next()){
				if (! rs.getBoolean("solved")) {
					collTickets.add(new Ticket(rs.getInt("numTicket")));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		oDbCon.disconnect();
		return collTickets;
	}
	
	// getters & setters
	/**
	 * numEmployee getter
	 * @return int numEmployee
	 */
	public int getNumEmployee(){
		return this.numEmployee;
	}
	
	/**
	 * name getter
	 * @return String name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * firstName getter
	 * @return String firstName
	 */
	public String getFirstName(){
		return this.firstName;
	}
	
	/**
	 * rights getter
	 * @return rights
	 */
	public int getRights(){
		return this.rights;
	}
	
	public int getUserOpenedTicketsNumber(int pnUserId) {
		Database oDbCon = new Database();
		ResultSet rs;
		String tTemp[][] = {{"int",String.valueOf(pnUserId)}};
		String sQuery = "SELECT COUNT(*)	AS ticketNb "+ 
						"FROM 	technician_per_ticket "+
						"WHERE	numTechnician = ?";
		// Connexion et éxécution de la requête
				oDbCon.connect();
				rs = oDbCon.executePreparedQuery(sQuery,tTemp);
				if (rs != null){
					try{
						if(rs.first()){
							return rs.getInt("ticketNb");
						}
					}catch(SQLException e){
						System.out.println(e.getMessage());
						return -1;
					}
				}
				oDbCon.disconnect();
				return 0;		
	}
}


