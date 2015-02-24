package com.gui.combos;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import com.bll.enums.Categories;
import com.dal.Database;

public class Combo extends JComboBox {
	
	/**
	 * Class constructor (uninitialized)
	 */
	public Combo(){
		super();
	}
	
	/**
	 * Add combo content
	 * @param Categories cat
	 */
	public void fillComboBox(Categories cat){
		String 		sQuery 			= 	new String("SELECT 	* FROM	" +cat.toString() +" ORDER BY label"); 	
		Database	oDbCon			= 	new Database();;
		
		this.setRenderer(new ComboLineRenderer());
		this.removeAll();
		this.addItem(new ComboLine(0,"Aucun(e)"));
		
		oDbCon.connect();
		ResultSet rs = oDbCon.executeQuery(sQuery);
		try {
			while (rs.next()){
				this.addItem(new ComboLine(rs.getInt(cat.getColumnName()),rs.getString("label")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		oDbCon.disconnect();
	}
}
