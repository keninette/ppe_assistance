package com.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.dal.Database;

/**
 * Add tickets to table
 */
public class TicketService {
	private HashSet<Ticket> collTickets;
	
	/**
	 * Class constructor  
	 * @param numEmployee
	 */
	public TicketService(int numEmployee){
		Database 	db		= new Database();
		String 		query 	= 	"SELECT numTicket " +
								"FROM 	technician_per_ticket " +
								"WHERE	numTechnician = ?";
		String t[][] 		= {{"int",String.valueOf(numEmployee)}};
		ResultSet rs		= db.executePreparedQuery(query, t);
		
		try {
			if (rs.first()){
				// TODO
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
