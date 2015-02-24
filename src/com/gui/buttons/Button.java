package com.gui.buttons;

import javax.swing.JButton;

import com.gui.window.Window;

public class Button extends JButton {
	protected String 	name;
	protected Window 	window;
	
	/**
	 * Class constructor (initialized)
	 * @param pWindow 
	 * @param pLabel
	 */
	public Button (Window window,String label){
		super(label);
		this.name = label;
		this.window = window;
	}
	
	/**
	 * Class constructor
	 * @param pWindow
	 * @param pLabel
	 */
	public Button (Window window,String label, String name){
		super(label);
		this.name = name;
		this.window = window;
	}
	
	/*************** getters & setters ***************/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}
}

