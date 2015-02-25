package com.bll.categories;

import com.bll.enums.Categories;
import com.dal.JdomXml;

public class InterventionType extends Category {
		
		/**
		 * Class constructor
		 */
		public InterventionType() {
			this.num = 0;
			this.label = null;
		}
		
		/**
		 * Class constructor with parameter
		 * Get label from .xml to avoid connection to database
		 * @param int numIncidentType
		 */
		public InterventionType(int numInterventionType) {
			this.num = numInterventionType;
			this.label = JdomXml.xmlGetLabel(Categories.CAT_INTERVENTIONTYPE.toString(), numInterventionType);
		}
}
