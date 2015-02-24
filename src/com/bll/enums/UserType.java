package com.bll.enums;

public enum UserType {
	USER_TECH(1),
	USER_VISITEUR(2),
	USER_ADMIN(3);

	private int value;
	
	/**
	 * Enum constructor
	 */
	UserType(int value){
		this.value = value;
	}
	
	/**
	 * get enum value
	 * @return int value
	 */
	public int toInt(){
		return value;
	}
}
