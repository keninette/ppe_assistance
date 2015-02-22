package com.dal;

import java.sql.*;


// D�claration de class
public class Database {
	//Variable de class
	private String url, password, login;
	private Connection con;
	
	/**
	 * Contructeur
	 * Features :
	 * 	- initialise les variables de connexion � la base de donn�es
	 * @param : none
	 * @return: none
	 */
	public Database(){
		url			= "jdbc:mysql://localhost/ppe_assistance";
		login		= "root";
		password	= "";
		con 		= null;
	}
	
	/**
	 * Features :
	 * 	- connexion � la base de donn�es
	 * 	- gestion des exceptions
	 * @param : none
	 * @return 
	 * @return: boolean
	 */
	public boolean connect() {
		try{
			con = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			con=null;
		}
		return (con==null?false:true);
	}
		
	/**
	 * Features :
	 * 	- d�connexion � la base de donn�es
	 * 	- gestion des exceptions
	 * @param : none
	 * @return: none
	 */	
	public void disconnect() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Features :
	 * 	- effectue une requ�te non pr�par�e � la base de donn�es
	 * 	- gestion des exceptions
	 * @param : none
	 * @return: ResultSet
	 */	
	
	public ResultSet executeQuery(String psQuery) {
		ResultSet rs = null;
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(psQuery);
		}
		catch (SQLException e) {
			System.out.println("[Erreur Database.executeQuery()]" +e.getMessage());
		}
		
		return rs;
		
	}
	
	/**
	 * Features :
	 * 	- effectue une requ�te pr�par�e � la base de donn�es
	 * 	- gestion des exceptions
	 * @param : 
	 * 	- String psQuerry : requ�te(avec des points d'interrogations pour chaque param�tre)
	 * 	- String pTable[][] : tableau de cha�nes contenant les param�tres de la requ�te(premi�re colonne : type du param�tre(int, String), deuxi�me colonne : valeur du param�tre)
	 * @return: ResultSet(r�sultats de la requ�te)
	 */	
	
	public ResultSet executePreparedQuery(String psQuery, String pTable[][]) {
		ResultSet 			rs = null;
		PreparedStatement 	st = null;
		//Date d;
		int i = 0, j = pTable.length, k=1;
		try {
			st = con.prepareStatement(psQuery);
			
			/**
			 * Pour r�cuperer chaque param�tre il faut parcourir toutes les lignes du tableau.
			 * Pour chaque ligne on r�cup�re dans la premi�re colonne le type du param�tre.
			 * Pour la deuxi�me colonne on r�cup�re la valeur du param�tre sans oublier de le transtyper (caster).
			 * Chaque param�tre est ajout� � la requ�te en utilisant la m�thode correspondant � son type.
			 */	
	
			for (i=0; i<j; i++){
				switch(pTable[i][0]){
					case "int" : 				
						st.setInt(k, Integer.parseInt(pTable[i][1]));
						break;
					case "String" :
						st.setString(k, pTable[i][1]);
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

