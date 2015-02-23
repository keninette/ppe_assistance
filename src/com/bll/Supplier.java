package com.bll;

public class Supplier {
	private	int		numSupplier;
	private	String	label;
	private String 	phone;
	private String 	mail;
	
	/**
	 * Class constructor uninitialized
	 */
	public Supplier() {
		numSupplier = 0;
		label = null;
		phone = null;
		mail = null;
	}
	
	/**
	 * Class constructor initialized
	 * @param pnNumSupplier
	 * @param psLabel
	 * @param psPhone
	 * @param psMail
	 */
	public Supplier(int numSupplier, String label, String phone, String mail) {
		this.numSupplier = numSupplier;
		this.label = label;
		this.phone = phone;
		this.mail = mail;
	}
	
	/*************** getters & setters ***************/
	public int getNumSupplier() {
		return numSupplier;
	}

	public void setNumSupplier(int numSupplier) {
		this.numSupplier = numSupplier;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
