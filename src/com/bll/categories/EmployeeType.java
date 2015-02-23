package com.bll.categories;

import com.bll.enums.Categories;
import com.dal.JdomXml;

public class EmployeeType extends Category{
	/**
	 * Class constructor
	 */
	public EmployeeType() {
		this.num = 0;
		this.label = null;
	}
	
	/**
	 * Class constructor with parameter
	 * Get label from .xml to avoid connection to database
	 * @param numEmployeeType
	 */
	public EmployeeType(int numEmployeeType) {
		this.num = numEmployeeType;
		this.label = JdomXml.xmlGetLabel(Categories.CAT_EMPLOYEETYPE.toString(), numEmployeeType);
	}
}
