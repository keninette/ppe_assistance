package com.gui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import com.bll.Employee;
import com.bll.Ticket;


public class TicketTableModel extends AbstractTableModel {
	private final String titles[] = {"id", "Description", "Ouvert le", "Niveau", "Equipement"};
	private Employee user;
	private ArrayList<Ticket> collTickets = new ArrayList<Ticket>();
	
	/**
	 * Constructor without parameters
	 */
	public TicketTableModel(Employee poUser) {
		super();
		this.user = poUser;
		this.collTickets = this.user.findEmployeeTickets(this.user.getNumEmployee(),10);
	}
	
	@Override
	public int getColumnCount() {
		return(titles.length);
	}

	@Override
	public int getRowCount() {
		return collTickets.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0:
				return this.collTickets.get(rowIndex).getNumTicket();
			case 1:
				//return this.collTickets.get(rowIndex).getNumIncidentType() +this.collTickets.get(rowIndex).getIncidentDescription();
			case 2:
				return "";
			case 3:
				/*switch(this.collTickets.get(rowIndex).getNumTicketLevel()) {
					case 1:
						return(new String("Basique"));
					case 2:
						return(new String("Interv. à distance"));
					case 3:
						return(new String("Interv. mat/install"));
					case 4:
						return(new String("Applis web"));	
				}*/
			case 4:
				return null;
		}
		return null;
	}
	
	@Override
	public String getColumnName(int nColumnIndex) {
		return(titles[nColumnIndex]);
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			default:
				return Object.class;
		}
	}
	
	// *************** getters ***************
	public String[] getTitles() {
		return this.titles;
	}
}
