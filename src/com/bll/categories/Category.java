package com.bll.categories;

public abstract class Category {
	int		num;
	String	label;
	
	/************** getters ***************/
	public int getNum() {
		return this.num;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	/************** setters ***************/
	public void setNum (int pnNum) {
		num = pnNum;
	}
	
	public void setLabel(String psLabel) {
		label = psLabel;
	}
}
