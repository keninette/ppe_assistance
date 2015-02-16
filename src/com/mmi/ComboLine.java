package com.mmi;

public class ComboLine {
	private int 	itemId;
	private	String 	itemLabel;
	
	/**
	 * Class constructor
	 * @param pnLineItemId : (int) item id in database
	 * @param psLineItemLabel : (String) item label
	 */
	public ComboLine(int pnItemId, String psItemLabel) {
		itemId = pnItemId;
		itemLabel = psItemLabel;
	}
	
	/*************** getters ***************/
	
	/**
	 * getter for itemId
	 */
	public int getItemId(){
		return itemId;
	}
	
	/**
	 * getter for itemLabel
	 */
	public String getItemLabel(){
		return itemLabel;
	}
}
