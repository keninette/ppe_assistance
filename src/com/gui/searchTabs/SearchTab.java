package com.gui.searchTabs;

import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.bll.Employee;
import com.gui.window.Window;


public abstract class SearchTab {
	protected 	JPanel 		tabContent;
	protected	JPanel		searchFields;
	protected	JPanel		searchResult;
	private 	ImageIcon 	icon;
	private		Window 		window;
	
	
	/**
	 * Set all tabs general properties
	 */
	public void setTab(){
		tabContent = new JPanel();
		tabContent.setLayout(null);
		tabContent.setBackground(Color.WHITE);
		
		searchFields = new JPanel();
		searchFields.setLayout(null);
		searchFields.setBackground(Color.WHITE);
		searchFields.setBounds(0,0,1310,200);
		//searchFields.setBackground(Color.blue);
		
		searchResult = new JPanel();
		searchResult.setLayout(null);
		searchResult.setBackground(Color.WHITE);
		searchResult.setVisible(false);
		searchResult.setBounds(0,200,1310,550);
		//searchResult.setBackground(Color.red);
		
		tabContent.add(searchFields);
		tabContent.add(searchResult);
	}
	
	/**
	 * Auto create all tab components
	 * @param Employee user
	 * @return JPanel pan
	 */
	protected abstract JPanel createTabComponents(Employee user);
	
	/**
	 * Define and create tab's search action
	 */
	public abstract void triggerSearchAction();
	
	
	/**
	 * Display search results
	 * This function displays one equipment and its components information
	 * Call this function for every equipment you wish to display
	 * @param ResultSet rsE
	 * @param ResultSet rsC 
	 */
	protected abstract void displaySearchResults(ResultSet rsE, ResultSet rsC, int k);
	
	/*************** getters & setters **************/
	public JPanel getTabContent() {
		return tabContent;
	}

	public void setTabContent(JPanel tabContent) {
		this.tabContent = tabContent;
	}

	public JPanel getSearchFields() {
		return searchFields;
	}

	public void setSearchFields(JPanel searchFields) {
		this.searchFields = searchFields;
	}

	public JPanel getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(JPanel searchResult) {
		this.searchResult = searchResult;
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

