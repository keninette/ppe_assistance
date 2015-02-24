package com.bll.enums;

public enum Categories {
	CAT_BRAND("brand","numBrand"),
	CAT_COMPONENTTYPE("component_type","numComponentType"),
	CAT_EMPLOYEETYPE("employee_type","numEmployeeType"),
	CAT_INCIDENTTYPE("incident_type","numIncidentType"),
	CAT_INTERVENTIONTYPE("intervention_type","numInterventionType"),
	CAT_SOFTWARETYPE("software_type","numSoftwareType"),
	CAT_SOLUTIONTYPE("solution_type","numSolutionType"),
	CAT_SUPPLIER("supplier","numSupplier"),
	CAT_TICKETLEVEL("ticket_level","numTicketLevel");

	private String value;
	private String columnName;
	
	/**
	 * Enum constructor
	 */
	Categories(String value, String columnName){
		this.value = value;
		this.columnName = columnName;
	}
	
	/**
	 * To get enum member value
	 * @return : String value
	 */
	public String toString(){
		return value;
	}
	
	/**
	 * To get enum member value
	 * @return : value
	 */
	public String getColumnName(){
		return columnName;
	}
}
