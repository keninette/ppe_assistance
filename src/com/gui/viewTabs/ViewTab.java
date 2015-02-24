package com.gui.viewTabs;

import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import com.bll.Employee;
import com.gui.window.Window;

public abstract class ViewTab {
	protected 	JPanel 		tabContainer; 	// JPanel returned to tabs.add(), contains all the other elements	
	protected 	ImageIcon 	icon;
	protected	Window 		window;
	
	/**
	 * Auto create all tab components
	 * @param Employee user
	 * @return JPanel
	 */
	protected abstract JPanel createTabComponents(Employee user);
	
	
	/**
	 * Set all tabs general properties
	 */
	public void setTab(){
		tabContainer = new JPanel();
		tabContainer.setLayout(null);
		tabContainer.setBackground(Color.WHITE);
	}

	/**************** getters & setters **************/
	public JPanel getTabContainer() {
		return tabContainer;
	}


	public void setTabContainer(JPanel tabContainer) {
		this.tabContainer = tabContainer;
	}


	public ImageIcon getIcon() {
		return icon;
	}


	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}


	public Window getWindow() {
		return window;
	}


	public void setWindow(Window window) {
		this.window = window;
	}
}
