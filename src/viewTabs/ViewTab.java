package viewTabs;

import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import window.Window;

import com.bll.Employee;

public abstract class ViewTab {
	protected 	JPanel 		jpTabContent;
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
		jpTabContent = new JPanel();
		jpTabContent.setLayout(null);
		jpTabContent.setBackground(Color.WHITE);
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
