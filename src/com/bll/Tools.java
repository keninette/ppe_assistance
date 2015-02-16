package com.bll;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;





public class Tools {
	
	
	/**
	 * Features : Changes the component to display an error
	 *  - if pComponent is a ErrorMessage component : it becomes visible.
	 *  - if pComponent is a TextField or PasswordField component : its border become red.
	 * @param pComponent : JFrame Component
	 * @return : none
	 */
	public static void displayError(Component pComponent){
		if (pComponent.getClass().toString().equals("class com.mmi.ErrorMessage")){
			pComponent.setVisible(true);
		}
		if (pComponent.getClass().toString().equals("class javax.swing.JTextField")	|| pComponent.getClass().toString().equals("class javax.swing.JPasswordField")	){
			((JComponent) pComponent).setBorder(new LineBorder (Color.red));
		}
	}
	
	/**
	 * Features : removes any sign of error message on panel
	 *  - if it's a ErrorMessage component : sets it invisible
	 *  - if it's a JPasswordField or JTextField : sets its border color to gray
	 * @param jpTemp : JPanel
	 * @return : none
	 */
	public static void removeErrorMessages(Container jpTemp){
		Component[] t = jpTemp.getComponents();
		
		for (int i=0, j=t.length; i<j; i++){
			// On rend les messages d'erreur invisibles
			if (t[i].getClass().toString().equals("class com.mmi.ErrorMessage")){
				t[i].setVisible(false);
			}
			if (t[i].getClass().toString().equals("class javax.swing.JPasswordField") || t[i].getClass().toString().equals("class javax.swing.JTextField") ){
				((JComponent) t[i]).setBorder(new LineBorder(Color.GRAY));
			}
		}
	}
	
	
	/**
	 * Feature : convertit une date selon le format demandé
	 * @param String psFormat : format demandé
	 * @param String psDate : date à convertir
	 * @return Date dDate : date formatée 
	 */
	/*public static Date stringToDate(String psDate){
		/*DateFormat readFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
	    DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		
		Date dDate = null;
		//try {
			System.out.println(psDate);
			//java.util.Date dTemp = readFormat.parse(psDate);
			//dDate = new java.sql.Date(dTemp.getTime());
			//System.out.println(dTemp.getTime());
			System.out.println(dDate);
			writeFormat.format(dDate);
			System.out.println(dDate);
		//}
		/*catch(ParseException e){
			System.out.println(e.getMessage());
		}
		
		return dDate;	
	}*/
}
