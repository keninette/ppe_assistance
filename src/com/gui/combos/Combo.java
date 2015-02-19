package com.gui.combos;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import com.dal.Database;

public class Combo extends JComboBox {
	
	public Combo(){
		super();
	}
	
	/**
	 * 
	 * @param psComboType
	 */
	public void fillComboBox(String psComboType){
		String 		sQuery 			= 	new String("SELECT 	* FROM	" +psComboType +" ORDER BY label");
		String		sIdColumnName 	=	new String("num" +Character.toUpperCase(psComboType.charAt(0)) 
													+psComboType.substring(1, psComboType.length())); 	
		Database	oDbCon			= 	new Database();;
		
		this.setRenderer(new ComboLineRenderer());
		this.removeAll();
		this.addItem(new ComboLine(0,"Aucun(e)"));
		
		oDbCon.connect();
		ResultSet rs = oDbCon.executeQuery(sQuery);
		try {
			while (rs.next()){
				this.addItem(new ComboLine(rs.getInt(sIdColumnName),rs.getString("label")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		oDbCon.disconnect();
	}
}
