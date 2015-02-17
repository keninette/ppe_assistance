package com.mmi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class SearchButton extends Button implements MouseListener {
	private ViewTab tab;
	
	public SearchButton(ViewTab pTab, String pLabel, String pName) {
		super(pTab.window, "", pName);
		this.setIcon(new ImageIcon("res/img/icons/icon_search.png"));
		this.addMouseListener(this);
		this.tab = pTab;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.tab.triggerSearchAction();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
