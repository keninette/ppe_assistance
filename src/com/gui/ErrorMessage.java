package com.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;




public class ErrorMessage extends JLabel {
	
	/**
	 * Class constructor
	 * @param label
	 * @param name
	 */
	public ErrorMessage(String label, String name){
		super(label);
		super.setName(name);
		this.setForeground(Color.red);
		this.setFont(new Font("", Font.PLAIN, 10));
		this.setVisible(false);
	}
	/*
	/**
	 * Get name
	 * @return String
	 
	public String getName(){
		return(super.getName());
	}
	
	/**
	 * Set name
	 
	public void setLabel(String label){
		super.setText(label);
		
	}
	
	public void setName(String name){
		super.setName(name);
	}*/
}
