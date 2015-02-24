package com.bll.categories;

import com.bll.enums.Categories;
import com.dal.JdomXml;

public class ComponentType extends Category {
	/**
	 * Class constructor
	 */
	public ComponentType() {
		this.num = 0;
		this.label = null;
	}
	
	/**
	 * Class constructor with parameter
	 * Get label from .xml to avoid connection to database
	 * @param int numIncidentType
	 */
	public ComponentType(int numComponentType) {
		this.num = numComponentType;
		this.label = JdomXml.xmlGetLabel(Categories.CAT_COMPONENTTYPE.toString(), numComponentType);
	}
}
