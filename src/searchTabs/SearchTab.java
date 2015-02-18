package searchTabs;

import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.bll.Employee;

import window.Window;

public abstract class SearchTab {
	protected 	JPanel 		jpTabContent;
	protected	JPanel		jpSearchFields;
	protected	JPanel		jpSearchResult;
	private 	ImageIcon 	icon;
	private	Window 		window;
	
	
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
		jpSearchFields.setBounds(0,0,1310,200);
		//jpSearchFields.setBackground(Color.blue);
		
		jpSearchResult = new JPanel();
		jpSearchResult.setLayout(null);
		jpSearchResult.setBackground(Color.WHITE);
		jpSearchResult.setVisible(false);
		jpSearchResult.setBounds(0,200,1310,550);
		//jpSearchResult.setBackground(Color.red);
		
		jpTabContent.add(jpSearchFields);
		jpTabContent.add(jpSearchResult);
	}
	
	/**
	 * Auto create all tab components
	 * @param pUser : (Employee) user associated to window
	 * @return JPanel : (JPanel) panel to be used in tab creation
	 */
	protected abstract JPanel createTabComponents(Employee pUser);
	
	/**
	 * Define and create tab's search action
	 */
	public abstract void triggerSearchAction();
	
	
	/**
	 * Display search results
	 * This function displays one equipment and its components information
	 * Call this function for every equipment you wish to display
	 * @param rsE : (ResultSet) One Equipment resultSet
	 * @param rsC : (ResultSet) That equipment's components ResulSet
	 */
	protected abstract void displaySearchResults(ResultSet rsE, ResultSet rsC);
	
	
	/*************** getters ***************/
	public Window getWindow() {
		return window;
	}
	
	public ImageIcon getIcon() {
		return icon;
	}

	/*************** setters ***************/
	public void setWindow(Window pWindow) {
		this.window = pWindow;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}	
}

