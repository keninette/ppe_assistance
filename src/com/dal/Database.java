package com.dal;

import java.sql.*;


// Déclaration de class
public class Database {
	//Variable de class
	private String url, password, login;
	private Connection con;
	
	/**
	 * Contructeur
	 * Features :
	 * 	- initialise les variables de connexion à la base de données
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
	 * 	- connexion à la base de données
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
	 * 	- déconnexion à la base de données
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
	 * 	- effectue une requête non préparée à la base de données
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
	 * 	- effectue une requête préparée à la base de données
	 * 	- gestion des exceptions
	 * @param : 
	 * 	- String psQuerry : requête(avec des points d'interrogations pour chaque paramètre)
	 * 	- String pTable[][] : tableau de chaînes contenant les paramètres de la requête(première colonne : type du paramètre(int, String), deuxième colonne : valeur du paramètre)
	 * @return: ResultSet(résultats de la requête)
	 */	
	
	public ResultSet executePreparedQuery(String psQuery, String pTable[][]) {
		ResultSet 			rs = null;
		PreparedStatement 	st = null;
		//Date d;
		int i = 0, j = pTable.length, k=1;
		try {
			st = con.prepareStatement(psQuery);
			
			/**
			 * Pour récuperer chaque paramètre il faut parcourir toutes les lignes du tableau.
			 * Pour chaque ligne on récupère dans la première colonne le type du paramètre.
			 * Pour la deuxième colonne on récupère la valeur du paramètre sans oublier de le transtyper (caster).
			 * Chaque paramètre est ajouté à la requête en utilisant la méthode correspondant à son type.
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

