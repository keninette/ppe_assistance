package com.gui.searchTabs;

import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



import com.bll.Employee;
import com.bll.enums.ComboType;
import com.dal.Database;
import com.gui.buttons.SearchButton;
import com.gui.combos.Combo;
import com.gui.combos.ComboLine;
import com.gui.window.Window;


public class SearchEquipment extends SearchTab {

	/**
	 * Class constructor
	 */
	public SearchEquipment(Window pWindow){
		this.setTab();
		this.setIcon(new ImageIcon("res/img/icons/icon_equipment.png"));
		this.setWindow(pWindow);
	}
	
	@Override
	public JPanel createTabComponents(Employee pUser) {
		JLabel 			lbSearch 		= new JLabel("Recherche d'un matériel");
		JLabel			lbSerialNumber 	= new JLabel("Par numéro de série");
		JTextField		tfSerialNumber	= new JTextField();
		JLabel			lbBrand			= new JLabel("Par marque");
		Combo			cbBrand			= new Combo();
		JLabel			lbMedicalRep	= new JLabel("Par visiteur médical");
		JTextField		tfMedicalRep	= new JTextField();
		SearchButton	sbSearchButton	= new SearchButton(this,"","search");
	
		
		// Customize components
		// JLabel
		lbSearch.setBounds(20,0,500,40);
		lbSearch.setFont(new Font("Arial",Font.BOLD, 20));
		lbSerialNumber.setBounds(20,40,200,20);
		lbBrand.setBounds(240,40,200,20);
		lbMedicalRep.setBounds(460,40,200,20);
		// JTextField
		tfSerialNumber.setBounds(20,60,200,20);
		tfSerialNumber.setName("serialNumber");
		tfMedicalRep.setBounds(460,60,200,20);
		tfMedicalRep.setName("medicalRep");
		// Combo
		cbBrand.setBounds(240,60,200,20);
		cbBrand.setName("brand");
		// JButton
		sbSearchButton.setBounds(680,50,30,30);
		// Fill comboBoxes
		cbBrand.fillComboBox(ComboType.COMBO_BRAND.toString());
		// Add components to JPanel
		this.jpSearchFields.add(lbSearch);
		this.jpSearchFields.add(lbSerialNumber);
		this.jpSearchFields.add(tfSerialNumber);
		this.jpSearchFields.add(lbBrand);
		this.jpSearchFields.add(cbBrand);
		this.jpSearchFields.add(lbMedicalRep);
		this.jpSearchFields.add(tfMedicalRep);
		this.jpSearchFields.add(sbSearchButton);
		
		return jpTabContent;
	}

	@Override
	public void triggerSearchAction() {
		ResultSet	rs				= 	null;
		String 		sSerialNumber 	= 	null;
		String 		sMedicalRep		= 	null;
		int			nBrand			= 	0;
		String		sWhereClauseE	= 	new String("WHERE 1 = 1");
		Database	odbCon			= 	new Database();
		int			i = 0;
		
		// Delete all results from precedent 
		this.jpSearchResult.setVisible(false);
		this.jpSearchResult.removeAll();
		
		// Get search fields values
		Component t[] = this.jpSearchFields.getComponents();
		for (Component c : t){
			if (c.getName() != null){
				switch(c.getName()) {
					case "serialNumber":
						sSerialNumber = new String(((JTextField) c).getText().toString());
						break;
					//case "medicalRep":
						//break;
					case "brand":
						nBrand = ((ComboLine)((JComboBox) c).getSelectedItem()).getItemId();
						break;
				}
			}
		}
			
		// Write Where clause depending on search fields filled + add parameters for query
		sWhereClauseE += (sSerialNumber== null || sSerialNumber.isEmpty()) ? "" : " AND e.serialNumber = ? ";
		sWhereClauseE += nBrand == 0 ? "" : " AND e.numBrand = ?";
		String	tTableE[][] = 	{{sSerialNumber== null || sSerialNumber.isEmpty() ? "none" : "String", sSerialNumber}
								,{nBrand == 0 ? "none" : "int", Integer.toString(nBrand)}};
		
		
		// Execute query
		if (!sWhereClauseE.equals("WHERE 1 = 1")) {
			odbCon.connect();
			rs = this.findEquipment(sWhereClauseE, tTableE, odbCon);
			// Display results
			try {
				while(rs.next()){
					String tTableC[][] = {{"int", Integer.toString(rs.getInt("numEquipment"))}};
					this.displaySearchResults(rs, findComponent("WHERE numEquipment = ?", tTableC, odbCon),i);
					i++;
				}
			} catch (SQLException e) {
				System.out.println("[Error in SearchEquipment.triggerSearchAction()] " +e.getMessage());
			}
			odbCon.disconnect();
		}
		
		// use results
		this.jpSearchResult.setVisible(true);
}

	@Override
	protected void displaySearchResults(ResultSet rsE, ResultSet rsC, int k) {
		JPanel		resultContainer			= 	new JPanel();
		ImageIcon	imgEIcon 				= 	null;
		JLabel		lbEIcon					= 	new JLabel();
		JLabel		lbEBrand				= 	null;
		JLabel		lbEName					= 	null;
		JLabel		lbESerialNumber			= 	null;
		JLabel		lbOriginalComponents 	= 	null;
		JLabel		lbESupplier				= 	null;
		int 		i = 0, j=1;
		
		// Create new content
		try {
			// Equipment info
			if(rsE.first()){
				imgEIcon 				= 	new ImageIcon("res/img/equipment/" +rsE.getString("photo"));
				lbEBrand				=	new JLabel("Marque : " +rsE.getString("bLabel"));
				lbEName 				= 	new JLabel("Modèle : " +rsE.getString("label"));
				lbESerialNumber 		= 	new JLabel("Numéro de série : " +rsE.getString("serialNumber"));
				lbOriginalComponents	= 	new JLabel("Composants d'origine : " +(rsE.getInt("originalComponents") == 1 ? "oui" : "non"));
				lbESupplier				=	new JLabel("Fournisseur : " +rsE.getString("bLabel"));
				
				while(rsC.next()){
					if (i < 8) {
						i++;
					} else {
						i=1;
						j++;
					}
					(resultContainer.add(new JLabel(rsC.getString("ctLabel") +" : " +rsC.getString("label")))).setBounds(170+j*290, i*20, 250,20);				}
			}
		} catch (SQLException e){
			System.out.println("[Error in SearchEquipment.displaySearchResults] " +e.getMessage());
		}
		// Customize new content
		resultContainer.setBounds(0,k*210,1220,210);
		resultContainer.setLayout(null);
		lbEIcon.setIcon(imgEIcon);
		lbEIcon.setBounds(0,0,200,200);
		lbEBrand.setBounds(250,20,150,20);
		lbEName.setBounds(250,40,150,20);
		lbESerialNumber.setBounds(250,60,150,20);
		lbOriginalComponents.setBounds(250,80,150,20);
		lbESupplier.setBounds(250,100,200,20);
		
		// Add content to JPanel
		resultContainer.add(lbEIcon);
		resultContainer.add(lbEBrand);
		resultContainer.add(lbEName);
		resultContainer.add(lbESerialNumber);
		resultContainer.add(lbOriginalComponents);
		resultContainer.add(lbESupplier);
		this.jpSearchResult.add(resultContainer);
	}
	
	/**
	 * Execute a query to find equipment with custom where clause
	 * @param psWhereClause : (String) custom where clause
	 * @param tTable : (String tTable[][]) : contains info to use Database.executePreparedQuery()
	 * @return ResultSet : all equipments matching query
	 */
	private ResultSet findEquipment(String psWhereClause, String tTable[][], Database odbCon){
		ResultSet	rs 		= null;
		String		sQuery	= 	"SELECT 	e.numEquipment, " +
								"			e.label, " +
								"			e.serialNumber, " +
								"			e.purchaseDate	AS 	ePurchaseDate, " +
								"			e.warrantyDate	AS	eWarrantyDate," +
								"			e.originalComponents, " +
								" 			e.photo, " +
								"			b.label	AS	bLabel, " +
								"			s.label	AS	sLabel " +
								"FROM 		equipment e " +
								"LEFT JOIN	brand b	" +
								"ON 		b.numBrand = e.numBrand " +
								"LEFT JOIN	supplier s " +
								"ON 		s.numSupplier = e.numSupplier ";
		rs = odbCon.executePreparedQuery(sQuery +psWhereClause +" ORDER BY e.label", tTable);
		return rs;
	}
	
	/**
	 * Execute a query to find components with custom where clause
	 * @param psWhereClause : (String) custom where clause
	 * @param tTable : (String tTable[][]) : contains info to use Database.executePreparedQuery()
	 * @return ResultSet : all components matching query
	 */
	private ResultSet findComponent(String psWhereClause, String tTable[][], Database odbCon){
		ResultSet	rs 		= null;
		String 		sQuery	= 	"SELECT		c.numComponent, " +
								"			c.label, " +
								"			c.serialNumber, " +
								" 			c.purchaseDate, " +
								" 			c.warrantyDate, " +
								"			b.label			AS	sLabel, " +
								"			ct.label		AS	ctLabel, " +
								"			s.label			AS	sLabel " +
								"FROM		component c " +
								"LEFT JOIN	brand b " +
								"ON			b.numBrand = c.numBrand " +
								"LEFT JOIN	component_type ct " +
								"ON			ct.numComponentType = c.numComponentType " +
								"LEFT JOIN	supplier s " +
								"ON			s.numSupplier = c.numSupplier ";
		rs = odbCon.executePreparedQuery(sQuery +psWhereClause, tTable);
		return rs;
	}	
}
