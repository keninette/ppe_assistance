package com.mmi;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.bll.Employee;

public abstract class ViewTab {
	protected 	JPanel 		jpTabContent;
	protected 	ImageIcon 	icon;
	protected	Window 		window;
	
	protected abstract JPanel createTabComponents(Employee pUser);
	
	public void setTab(){
		jpTabContent = new JPanel();
		jpTabContent.setLayout(null);
		jpTabContent.setBackground(Color.WHITE);
	}
	
	/*************** getters ***************/
	
	/**
	 * Getter for icon
	 */
	public ImageIcon getIcon(){
		return icon;
	}
}
