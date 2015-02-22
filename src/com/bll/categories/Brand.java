package com.bll.categories;

public class Brand extends Category {
	private String supportPhone;
	private String supportMail;
	
	/**
	 * Class constructor (uninitialized)
	 */
	public Brand() {
		num 			= 0;
		label 			= null;
		supportPhone 	= null;
		supportMail 	= null;
	}
	
	/**
	 * Class constructor (initialized)
	 * @param pnNumBrand : (int) num
	 * @param psLabel : (String) label
	 * @param psSupportPhone : (String) : supportPhone
	 * @param psSupportMail : (String) : supportMail
	 */
	public Brand(int pnNumBrand, String psLabel, String psSupportPhone, String psSupportMail ) {
		num = pnNumBrand;
		label = psLabel;
		supportPhone = psSupportPhone;
		supportMail = psSupportMail;
	}
	
	/*************** getters ***************/
	public String getSupportPhone() {
		return supportPhone;
	}
	
	public String getSupportMail() {
		return supportMail;
	}
	
	/*************** setters ***************/
	public void setSupportPhone(String psSupportPhone) {
		supportPhone = psSupportPhone;
	}
	
	public void setSupportMail(String psSupportMail) {
		supportMail = psSupportMail;
	}
}
