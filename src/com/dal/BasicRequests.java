package com.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bll.Brand;
import com.bll.Employee;
import com.bll.Equipment;
import com.bll.Supplier;
import com.bll.Ticket;

public abstract class BasicRequests {
	private static Database db = new Database();
	private static ResultSet rs;
	
	/**
	 * Create instance of an object to be casted into right class
	 * @param String tableName
	 * @param int numEntry
	 * @return Object 
	 */
	public static Object createInstance(String tableName, int numEntry) {
		String		numColumnName 	= 	new String("num" +tableName.substring(0,1).toUpperCase() +tableName.substring(1, tableName.length()));	
		String 		query 			= 	"SELECT	* " +
										"FROM " +tableName +
										"WHERE "+numColumnName +" = ?";
		String		t[][]			=	{{"int",Integer.toString(numEntry)}};
		
		rs = null;
		db.connect();
		rs = db.executePreparedQuery(query, t);
		try {
			if(rs.first()) {
				switch(tableName) {
					case "brand":
						return new Brand(rs.getInt("numBrand"),rs.getString("label"), rs.getString("supportPhone"), 
											rs.getString("supportMail"));
					case "supplier":
						return new Supplier(rs.getInt("numSupplier"),rs.getString("label"), rs.getString("phone"), 
											rs.getString("mail"));
					case "equipment":
						return new Equipment(rs.getInt("numEquipment"),rs.getString("label"), rs.getString("serialNumber"),
												rs.getDate("purchaseDate"), rs.getDate("warrantyDate"), rs.getBoolean("originalComponents"),
												rs.getInt("numSupplier"), rs.getInt("numEmployee"), rs.getInt("numBrand"),rs.getString("photo"));
					case "employee":
						return new Employee(rs.getInt("numEmployee"), rs.getString("name"), rs.getString("firstName"),
												rs.getString("address"), rs.getString("postalCode"), rs.getString("town"),
												rs.getString("phone"), rs.getString("login"), rs.getString("psw"), rs.getInt("numEmployeeType"));
				}	
				
			}
		} catch (SQLException e) {
			System.out.println("[Error BasicSelects.createInstance()] " +e.getMessage());
		}
		return null;
	}
	
	/*************** TICKETS ***************/
	// TODO : requete a revoir
	/**
	 * Get user's tickets
	 * @param int numEmployee
	 * @param int limit
	 * @return ArrayList<Ticket> collTickets
	 */
	public static ArrayList<Ticket> getUserTickets(int numEmployee, int limit){
		ArrayList<Ticket> 	collTickets 	= new ArrayList<Ticket>();
		String 				query 			= 	"SELECT 	tp.numTicket, " +
												"			tp.numTechnician, " +
												"			t.openDateTime, " +
												" 			t.closeDateTime, " +
												"			t.incidentDescription, " +
												"			t.solutionDescription, " +
												"			t.solved, " +
												"			t.numSolutionType, " +
												"			t.numIncidentType, " +
												"			t.numTicketLevel, " +
												"			t.numEquipment " + 	
												"FROM 		technician_per_ticket tp " +
												"LEFT JOIN 	ticket t " +
												"ON 		t.numTicket = tp.numTicket " +
												"WHERE		tp.numTechnician = ? " +
												"AND		t.solved = false " +
												"ORDER BY	t.openDateTime DESC ";
		query += limit == 0 ? "" : "LIMIT ?";
		String 				t[][]		= {{"int",String.valueOf(numEmployee)},{limit == 0 ? "none" : "int",String.valueOf(limit)}};
		
		rs = null;
		db.connect();
		rs = db.executePreparedQuery(query, t);
		try {
			if (rs.next()){
				if (! rs.getBoolean("solved")) {
					//collTickets.add(new Ticket(rs.getInt("numTicket"), rs.getString("incidentDescription"), ));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		db.disconnect();
		return collTickets;
	}
	
	/**
	 * Get number of user's currently opened tickets
	 * @param int numEmployee
	 * @return int
	 */
	public static int getUserOpenedTicketsNumber(int numEmployee) {
		String t[][] 	= 	{{"int",String.valueOf(numEmployee)}};
		String query 	= 	"SELECT 	COUNT(tp.numTicket)	AS ticketNb " +
							"FROM 		technician_per_ticket tp " +
							"LEFT JOIN	ticket t " +	
							"ON			t.numTicket = tp.numTicket " +
							"WHERE		tp.numTechnician = ? " +
							"AND		t.solved = false " +
							"GROUP BY	t.numTicket";
		rs = null;
		db.connect();
		rs = db.executePreparedQuery(query,t);
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
		db.disconnect();
		return 0;		
	}
	
	/**
	 * Find all technicians who have operated on this ticket
	 * @param  int numTicket
	 * @return ArrayList<Employee>
	 */
	public static ArrayList<Employee> findTicketTechnicians(int numTicket){
		ArrayList<Employee> collEmployee = new ArrayList<Employee>();
		Database oDbCon 	= new Database();
		String sQuery 		=	"SELECT 	* " +
								"FROM 		technician_per_ticket tp " +
								"LEFT JOIN 	employee e " +
								"ON			e.numEmployee = tp.numTechnician " +
								"WHERE	numTicket = ?";
		String tTable[][] 	= {{"int",String.valueOf(numTicket)}};
		oDbCon.connect();
		ResultSet rs = oDbCon.executePreparedQuery(sQuery, tTable);
		try {
			while (rs.next()) {
				collEmployee.add(new Employee(rs.getInt("numTechnician"), rs.getString("name"), rs.getString("fName"), 
									rs.getString("address"), rs.getString("postalCode"), rs.getString("town"),
									rs.getString("phone"), rs.getString("login"), rs.getString("psw"), rs.getInt("numEmployeeType")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		oDbCon.disconnect();
		return collEmployee;
	}
	
	/**
	 * find all info about ticket's equipment
	 * @param int umEquipment
	 * @return
	 */
	public static Equipment findTicketEquipmentInfo(int numEquipment){
		Database oDbCon 	= new Database();
		String sQuery 		=	"SELECT * " +
								"FROM 	equipment " +
								"WHERE	numEquipment = ?";
		String tTable[][] 	= {{"int",String.valueOf(numEquipment)}};
		oDbCon.connect();
		ResultSet rs = oDbCon.executePreparedQuery(sQuery, tTable);
		try {
			if (rs.first()) {
				Equipment equip = new Equipment(rs.getInt("numEquipement"), rs.getString("label"), rs.getString("serialNumber")
												,rs.getDate("purchaseDate"), rs.getDate("warrantyDate"), rs.getBoolean("originalComponents")
												, rs.getInt("numSupplier"), rs.getInt("numEmployee"), rs.getInt("numBrand"),rs.getString("photo"));
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		oDbCon.disconnect();
		return new Equipment();
	}
	/*************** EQUIPEMENT  **************/
}
