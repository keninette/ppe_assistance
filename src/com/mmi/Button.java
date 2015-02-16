package com.mmi;

import javax.swing.JButton;


public class Button extends JButton {
	
	protected String 			name;
	protected Window 			window;
	
	/**
	 * Class constructor
	 * @param pWindow : (Window) Window in which button is displayed
	 * @param pLabel : (String) Button's label
	 */
	public Button (Window pWindow,String pLabel){
		super(pLabel);
		this.name = pLabel;
		this.window = pWindow;
	}
	
	/**
	 * Setter for label
	 * @param psLabel : (String) button's label
	 */
	public void setLabel(String psLabel){
		super.setText(psLabel);
	}
}

