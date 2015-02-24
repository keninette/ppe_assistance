package com.gui.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import com.gui.searchTabs.SearchTab;


public class SearchButton extends Button implements MouseListener {
	private SearchTab tab;
	
	public SearchButton(SearchTab tab, String label, String name) {
		super(tab.getWindow(), "", name);
		this.setIcon(new ImageIcon("res/img/icons/icon_search.png"));
		this.addMouseListener(this);
		this.tab = tab;
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
