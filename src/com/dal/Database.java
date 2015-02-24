package com.dal;

import java.sql.*;


// D�claration de class
public class Database {
	//Variable de class
	private String url, password, login;
	private Connection con;
	
	/**
	 * Class constructor
	 */
	public Database(){
		url			= "jdbc:mysql://localhost/ppe_assistance";
		login		= "root";
		password	= "";
		con 		= null;
	}
	
	/**
	 * Establish connection with database
	 * @return: boolean
	 */
	public boolean connect() {
		try{
			con = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("[Error Database.connect()] " +e.getMessage());
			con=null;
		}
		return (con==null?false:true);
	}
		
	/**
	 * Disconnection from database
	 */	
	public void disconnect() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Error Database.disconnect()] " +e.getMessage());
		}
	}
	
	/**
	 * Execute a simple query 
	 * @param String query
	 * @return: ResultSet rs
	 */	
	
	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		}
		catch (SQLException e) {
			System.out.println("[Erreur Database.executeQuery()]" +e.getMessage());
		}
		
		return rs;
		
	}
	
	/**
	 * Execute prepared query
	 * @param String query
	 * @param String t[][]
	 * @return ResultSet rs
	 */
	public ResultSet executePreparedQuery(String query, String t[][]) {
		// t[parameter class][parameter value]
		ResultSet 			rs = null;
		PreparedStatement 	st = null;
		//Date d;
		int i = 0, j = t.length, k=1;
		try {
			st = con.prepareStatement(query);
			
			/**
			 * Pour r�cuperer chaque param�tre il faut parcourir toutes les lignes du tableau.
			 * Pour chaque ligne on r�cup�re dans la premi�re colonne le type du param�tre.
			 * Pour la deuxi�me colonne on r�cup�re la valeur du param�tre sans oublier de le transtyper (caster).
			 * Chaque param�tre est ajout� � la requ�te en utilisant la m�thode correspondant � son type.
			 */	
	
			for (i=0; i<j; i++){
				switch(t[i][0]){
					case "int" : 				
						st.setInt(k, Integer.parseInt(t[i][1]));
						break;
					case "String" :
						st.setString(k, t[i][1]);
						break;
					case "Date" :
						//d = Tools.stringToDate("dd-MM-yy",pTable[i][1]);
						//st.setDate(k+1, d);
						break;
					case "DateTime" :
						//d = Tools.stringToDate("dd-MM-yy hh:mm:ss ",pTable[i][1]);
						//st.setDate(k+1, d);
						break;
					case "none" :
						k--;
						break;
				}
				k++;
			}
			rs = st.executeQuery();
		}
		catch (SQLException e) {
			System.out.println("[Error Database.executePreparedQuery] " +e.getMessage());
		}
		return rs;
	}
} 

