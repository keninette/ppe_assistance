package com.bll;

public class Brand {
	private	int		numBrand;
	private String	label;
	private String 	supportPhone;
	private String 	supportMail;
	
	/**
	 * Class constructor (uninitialized)
	 */
	public Brand() {
		numBrand = 0;
		label = null;
		supportPhone = null;
		supportMail = null;
	}
	
	/**
	 * Class constructor (initialized)
	 * @param pnNumBrand
	 * @param psLabel
	 * @param psSupportPhone
	 * @param psSupportMail
	 */
	public Brand(int numBrand, String label, String supportPhone, String supportMail ) {
		this.numBrand = numBrand;
		this.label = label;
		this.supportPhone = supportPhone;
		this.supportMail = supportMail;
	}

	/*************** getters & setters ***************/
	public int getNumBrand() {
		return numBrand;
	}

	public void setNumBrand(int numBrand) {
		this.numBrand = numBrand;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSupportPhone() {
		return supportPhone;
	}

	public void setSupportPhone(String supportPhone) {
		this.supportPhone = supportPhone;
	}

	public String getSupportMail() {
		return supportMail;
	}

	public void setSupportMail(String supportMail) {
		this.supportMail = supportMail;
	}
}
