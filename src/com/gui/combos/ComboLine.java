package com.gui.combos;

public class ComboLine {
	private int 	itemId;
	private	String 	itemLabel;
	
	/**
	 * Class constructor
	 * @param int lineItemId
	 * @param String lineItemLabel
	 */
	public ComboLine(int numItem, String itemLabel) {
		this.itemId = numItem;
		this.itemLabel = itemLabel;
	}
	
	/*************** getters ***************/
	
	public int getItemId(){
		return itemId;
	}
	
	public String getItemLabel(){
		return itemLabel;
	}
}
