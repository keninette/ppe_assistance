package com.mmi;

import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.bll.Employee;

public abstract class ViewTab {
	protected 	JPanel 		jpTabContent;
	protected	JPanel		jpSearchFields;
	protected	JPanel		jpSearchResult;
	protected 	ImageIcon 	icon;
	protected	Window 		window;
	
	/**
	 * Auto create all tab components
	 * @param pUser : (Employee) user associated to window
	 * @return JPanel : (JPanel) panel to be used in tab creation
	 */
	protected abstract JPanel createTabComponents(Employee pUser);
	
	/**
	 * Define and create tab's search action
	 */
	protected abstract void triggerSearchAction();
	
	/**
	 * Add fields and display search results
	 * @param rs : (ResultSet) : a single line of the ResultSet
	 */
	protected abstract void displaySearchResults(ResultSet rs, int i);
	
	/**
	 * Set all tabs general properties
	 */
	public void setTab(){
		jpTabContent = new JPanel();
		jpTabContent.setLayout(null);
		jpTabContent.setBackground(Color.WHITE);
		jpSearchFields = new JPanel();
		jpSearchFields.setLayout(null);
		jpSearchFields.setBackground(Color.WHITE);
		jpSearchResult = new JPanel();
		jpSearchResult.setLayout(null);
		jpSearchResult.setBackground(Color.WHITE);
		jpSearchResult.setVisible(false);
		
		jpTabContent.add(jpSearchFields);
		jpTabContent.add(jpSearchResult);
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
