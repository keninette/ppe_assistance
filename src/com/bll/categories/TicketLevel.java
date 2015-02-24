package com.bll.categories;

import com.bll.enums.Categories;
import com.dal.JdomXml;

public class TicketLevel extends Category {
	
	/**
	 * Class constructor
	 */
	public TicketLevel(){
		this.num = 0;
		this.label = null;
	}
	
	/**
	 * Class constructor with parameter
	 * Get label from .xml to avoid another connection to database
	 * @param int numTicketLevel
	 */
	public TicketLevel(int numTicketLevel) {
		this.num = numTicketLevel;
		this.label = JdomXml.xmlGetLabel(Categories.CAT_TICKETLEVEL.toString(), numTicketLevel);
	}
}
