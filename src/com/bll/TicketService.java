package com.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.dal.Database;

/**
 * Classe permettant la manipulation des tickets pour alimenter le tableau les listant
 * @author kbj
 */

public class TicketService {
	private HashSet<Ticket> collTickets;
	
	/**
	 * Constructeur de la classe 
	 * @param nIdUser : (int) id de l'utilisateur connecté ou de l'employé sélectionné
	 */
	public TicketService(int pnIdEmployee){
		Database oDbCon		= new Database();
		String sQuery 		= 	"SELECT numTicket " +
								"FROM 	technician_per_ticket " +
								"WHERE	numTechnician = ?";
		String tTable[][] 	= {{"int",String.valueOf(pnIdEmployee)}};
		ResultSet rs		= oDbCon.executePreparedQuery(sQuery, tTable);
		
		try {
			if (rs.first()){
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
