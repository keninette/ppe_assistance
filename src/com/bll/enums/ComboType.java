package com.bll.enums;

public enum ComboType {
	COMBO_BRAND("brand"),
	COMBO_NAME("employee");

	private String value;
	
	/**
	 * Enum constructor
	 */
	ComboType(String pValue){
		this.value = pValue;
	}
	
	/**
	 * To get enum member value
	 * @return : value
	 */
	public String toString(){
		return value;
	}
}
