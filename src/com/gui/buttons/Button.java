package com.gui.buttons;

import javax.swing.JButton;

import com.gui.window.Window;




public class Button extends JButton {
	
	protected String 	name;
	protected Window 	window;
	
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
	 * Class constructor (2)
	 * @param pWindow : (Window) Window in which button is displayed
	 * @param pLabel : (String) Button's label
	 */
	public Button (Window pWindow,String pLabel, String pName){
		super(pLabel);
		this.name = pName;
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

