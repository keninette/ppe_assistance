package com.mmi;

import javax.swing.ImageIcon;

public class SearchButton extends Button {

	public SearchButton(Window pWindow, String pLabel) {
		super(pWindow, pLabel);
		this.setIcon(new ImageIcon("res/img/icons/icon_search.png"));
	}
}
