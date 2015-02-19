package com.bll.enums;

public enum UserType {
	USER_TECH(1),
	USER_VISITEUR(2),
	USER_ADMIN(3);

	private int value;
	
	/**
	 * Enum constructor
	 * @return 
	 */
	UserType(int pValue){
		this.value = pValue;
	}
	
	public int toInt(){
		return value;
	}
}
