package com.bll.categories;

import com.bll.enums.Categories;
import com.dal.JdomXml;

public class IncidentType extends Category {
	
	/**
	 * Class constructor
	 */
	public IncidentType() {
		this.num = 0;
		this.label = null;
	}
	
	/**
	 * Class constructor with parameter
	 * Get label from .xml to avoid connection to database
	 * @param int numIncidentType
	 */
	public IncidentType(int numIncidentType) {
		this.num = numIncidentType;
		this.label = JdomXml.xmlGetLabel(Categories.CAT_INCIDENTTYPE.toString(), numIncidentType);
	}
}
