package com.bll.categories;

public abstract class Category {
	int		num;
	String	label;
	
	/*************** getters & setters ***************/
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
