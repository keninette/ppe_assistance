package com.gui.viewTabs;

import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import com.bll.Employee;
import com.gui.window.Window;

public abstract class ViewTab {
	protected 	JPanel 		jpTabContainer; 	// JPanel returned to tabs.add(), contains all the other elements	
	protected 	ImageIcon 	icon;
	protected	Window 		window;
	
	/**
	 * Auto create all tab components
	 * @param pUser : (Employee) user associated to window
	 * @return JPanel : (JPanel) panel to be used in tab creation
	 */
	protected abstract JPanel createTabComponents(Employee pUser);
	
	
	/**
	 * Set all tabs general properties
	 */
	public void setTab(){
		jpTabContainer = new JPanel();
		jpTabContainer.setLayout(null);
		jpTabContainer.setBackground(Color.WHITE);
	}
	
	
	/*************** getters ***************/
	
	/**
	 * Getter for icon
	 * @return : (ImageIcon) tab icon
	 */
	public ImageIcon getIcon(){
		return icon;
	}

}
