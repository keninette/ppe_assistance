package com.bll.categories;

import com.bll.enums.Categories;
import com.dal.JdomXml;

public class SolutionType extends Category {
	
	/**
	 * Class constructor
	 */
	public SolutionType(){
		this.num = 0;
		this.label = null;
	}
	
	/**
	 * Class constructor with parameter
	 * @param int numSolutionType
	 */
	public SolutionType(int numSolutionType){
		this.num = numSolutionType;
		this.label = JdomXml.xmlGetLabel(Categories.CAT_SOLUTIONTYPE.toString(), numSolutionType);
	}
}
