package com.bll;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

public class Tools {
	
	
	/**
	 * Changes the component style to display an error
	 *  - if c is a ErrorMessage component : it becomes visible.
	 *  - if c is a TextField or PasswordField component : its border become red.
	 * @param Component c
	 */
	public static void displayError(Component c){
		if (c.getClass().toString().equals("class com.mmi.ErrorMessage")){
			c.setVisible(true);
		}
		if (c.getClass().toString().equals("class javax.swing.JTextField")	
				|| c.getClass().toString().equals("class javax.swing.JPasswordField")	){
			((JComponent) c).setBorder(new LineBorder (Color.red));
		}
	}
	
	/**
	 * Removes any sign of error message on panel
	 *  - if it's a ErrorMessage component : sets it invisible
	 *  - if it's a JPasswordField or JTextField : sets its border color to gray
	 * @param JPanel pan
	 */
	public static void removeErrorMessages(Container c){
		Component[] t = c.getComponents();
		
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
	 * Format a date
	 * @param String format
	 * @param String date
	 * @return Date finalDdate
	 */
	/*public static Date stringToDate(String date){
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
