package window;

import java.awt.Color;

import javax.swing.JTabbedPane;

import searchTabs.SearchEquipment;
import viewTabs.ViewTickets;


import com.bll.Employee;

public class TechnicianWindow extends Window {
	private ViewTickets 	viewTicketsTab;
	private SearchEquipment	searchEquipmentTab;
	
	public TechnicianWindow(Employee pUser){
		super.createWindow();
		super.user = pUser;
		super.window.setSize(1310,760);
		super.window.setTitle("Assistance - Gestion d'incidents");
		super.window.setResizable(false);
		this.createComponents();
	}
	
	@Override
	public void createComponents() {
		JTabbedPane tabs = new JTabbedPane();
		viewTicketsTab = new ViewTickets();
		searchEquipmentTab = new SearchEquipment(this);
		
		tabs.setTabPlacement(2);
		tabs.setBounds(0,0,1310,750);
		tabs.setBackground(Color.WHITE);
		
		// Adding tabs to JTabbedPane
		tabs.addTab("", viewTicketsTab.getIcon(), viewTicketsTab.createTabComponents(super.user));
		tabs.addTab("", searchEquipmentTab.getIcon(), searchEquipmentTab.createTabComponents(super.user),null);
		super.container.add(tabs);
		super.window.setVisible(true);
	}
}
