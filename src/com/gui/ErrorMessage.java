package com.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;




public class ErrorMessage extends JLabel {
	
	public ErrorMessage(String psLabel, String psName){
		super(psLabel);
		super.setName(psName);
		this.setForeground(Color.red);
		this.setFont(new Font("", Font.PLAIN, 10));
		this.setVisible(false);
	}
	
	public String getName(){
		return(super.getName());
	}
	
	public void setLabel(String psLabel){
		super.setText(psLabel);
		
	}
	
	public void setName(String psName){
		super.setName(psName);
	}
	
	
}
