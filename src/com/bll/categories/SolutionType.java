package com.bll.categories;

import com.bll.JdomXml;
import com.bll.enums.Categories;

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
	 * @param pnNumSolutionType : (int) numSolutionType
	 */
	public SolutionType(int pnNumSolutionType){
		this.num = pnNumSolutionType;
		this.label = JdomXml.xmlGetLabel(Categories.CAT_SOLUTIONTYPE.toString(), pnNumSolutionType);
	}
}
