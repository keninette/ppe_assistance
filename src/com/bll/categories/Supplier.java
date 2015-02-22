package com.bll.categories;

public class Supplier extends Category {
	private String phone;
	private String mail;
	
	/**
	 * Class constructor uninitialized
	 */
	public Supplier() {
		num = 0;
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
	public Supplier(int pnNumSupplier, String psLabel, String psPhone, String psMail) {
		num = pnNumSupplier;
		label = psLabel;
		phone = psPhone;
		mail = psMail;
	}
}
