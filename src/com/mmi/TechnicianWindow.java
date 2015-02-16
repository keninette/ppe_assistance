package com.mmi;

import java.awt.Color;

import javax.swing.JTabbedPane;

import com.bll.Employee;

public class TechnicianWindow extends Window {
	private ViewTickets 	viewTicketsTab;
	private ViewEquipment	viewEquipmentTab;
	
	public TechnicianWindow(Employee pUser){
		super.createWindow();
		super.user = pUser;
		super.window.setSize(800,600);
		super.window.setTitle("Assistance - Gestion d'incidents");
		super.window.setResizable(false);
		this.createComponents();
	}
	
	@Override
	public void createComponents() {
		JTabbedPane tabs = new JTabbedPane();
		viewTicketsTab = new ViewTickets();
		viewEquipmentTab = new ViewEquipment(this);
		
		tabs.setTabPlacement(2);
		tabs.setBounds(0,0,800,600);
		tabs.setBackground(Color.WHITE);
		
		// Adding tabs to JTabbedPane
		tabs.addTab("", viewTicketsTab.icon, viewTicketsTab.createTabComponents(super.user));
		tabs.addTab("", viewEquipmentTab.icon, viewEquipmentTab.createTabComponents(super.user),null);
		super.container.add(tabs);
		super.window.setVisible(true);
	}
}
