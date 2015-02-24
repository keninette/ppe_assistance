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
import com.bll.enums.Categories;
import com.dal.Database;
import com.gui.buttons.SearchButton;
import com.gui.combos.Combo;
import com.gui.combos.ComboLine;
import com.gui.window.Window;


public class SearchEquipment extends SearchTab {

	/**
	 * Class constructor (initialized)
	 */
	public SearchEquipment(Window window){
		this.setTab();
		this.setIcon(new ImageIcon("res/img/icons/icon_equipment.png"));
		this.setWindow(window);
	}
	
	@Override
	public JPanel createTabComponents(Employee user) {
		JLabel 			searchLabel 		= new JLabel("Recherche d'un matériel");
		JLabel			serialNbLabel 		= new JLabel("Par numéro de série");
		JTextField		serialNbField		= new JTextField();
		JLabel			brandLabel			= new JLabel("Par marque");
		Combo			brandCombo			= new Combo();
		JLabel			medicalRepLabel		= new JLabel("Par visiteur médical");
		JTextField		medicalRepField		= new JTextField();
		SearchButton	searchButton		= new SearchButton(this,"","search");
	
		
		// Customize components
		// JLabel
		searchLabel.setBounds(20,0,500,40);
		searchLabel.setFont(new Font("Arial",Font.BOLD, 20));
		serialNbLabel.setBounds(20,40,200,20);
		brandLabel.setBounds(240,40,200,20);
		medicalRepLabel.setBounds(460,40,200,20);
		// JTextField
		serialNbField.setBounds(20,60,200,20);
		serialNbField.setName("serialNumber");
		medicalRepField.setBounds(460,60,200,20);
		medicalRepField.setName("medicalRep");
		// Combo
		brandCombo.setBounds(240,60,200,20);
		brandCombo.setName("brand");
		// JButton
		searchButton.setBounds(680,50,30,30);
		// Fill comboBoxes
		brandCombo.fillComboBox(Categories.CAT_BRAND);
		// Add components to JPanel
		this.searchFields.add(searchLabel);
		this.searchFields.add(serialNbLabel);
		this.searchFields.add(serialNbField);
		this.searchFields.add(brandLabel);
		this.searchFields.add(brandCombo);
		this.searchFields.add(medicalRepLabel);
		this.searchFields.add(medicalRepField);
		this.searchFields.add(searchButton);
		
		return tabContent;
	}

	@Override
	public void triggerSearchAction() {
		ResultSet	rs				= 	null;
		String 		serialNumber 	= 	null;
		String 		medicalRep		= 	null;
		int			numBrand		= 	0;
		String		whereClause		= 	new String("WHERE 1 = 1");
		Database	db				= 	new Database();
		int			i = 0;
		
		// Delete all results from precedent 
		this.searchResult.setVisible(false);
		this.searchResult.removeAll();
		
		// Get search fields values
		Component t[] = this.searchFields.getComponents();
		for (Component c : t){
			if (c.getName() != null){
				switch(c.getName()) {
					case "serialNumber":
						serialNumber = new String(((JTextField) c).getText().toString());
						break;
					//case "medicalRep":
						//break;
					case "brand":
						numBrand = ((ComboLine)((JComboBox) c).getSelectedItem()).getItemId();
						break;
				}
			}
		}
			
		// Write Where clause depending on search fields filled + add parameters for query
		whereClause += (serialNumber== null || serialNumber.isEmpty()) ? "" : " AND e.serialNumber = ? ";
		whereClause += numBrand == 0 ? "" : " AND e.numBrand = ?";
		String	te[][] = 	{{serialNumber== null || serialNumber.isEmpty() ? "none" : "String", serialNumber}
								,{numBrand == 0 ? "none" : "int", Integer.toString(numBrand)}};
		
		
		// Execute query
		if (!whereClause.equals("WHERE 1 = 1")) {
			db.connect();
			rs = this.findEquipment(whereClause, te, db);
			// Display results
			try {
				while(rs.next()){
					String tc[][] = {{"int", Integer.toString(rs.getInt("numEquipment"))}};
					this.displaySearchResults(rs, findComponent("WHERE numEquipment = ?", tc, db),i);
					i++;
				}
			} catch (SQLException e) {
				System.out.println("[Error in SearchEquipment.triggerSearchAction()] " +e.getMessage());
			}
			db.disconnect();
		}
		
		// use results
		this.searchResult.setVisible(true);
}

	@Override
	protected void displaySearchResults(ResultSet rsE, ResultSet rsC, int k) {
		JPanel		resultContainer			= 	new JPanel();
		ImageIcon	img		 				= 	null;
		JLabel		labelForImg				= 	new JLabel();
		JLabel		brand				= 	null;
		JLabel		name					= 	null;
		JLabel		serialNumber			= 	null;
		JLabel		originalComponents 	= 	null;
		JLabel		supplier				= 	null;
		int 		i = 0, j=1;
		
		// Create new content
		try {
			// Equipment info
			if(rsE.first()){
				img 				= 	new ImageIcon("res/img/equipment/" +rsE.getString("photo"));
				brand				=	new JLabel("Marque : " +rsE.getString("bLabel"));
				name 				= 	new JLabel("Modèle : " +rsE.getString("label"));
				serialNumber 		= 	new JLabel("Numéro de série : " +rsE.getString("serialNumber"));
				originalComponents	= 	new JLabel("Composants d'origine : " +(rsE.getInt("originalComponents") == 1 ? "oui" : "non"));
				supplier			=	new JLabel("Fournisseur : " +rsE.getString("bLabel"));
				
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
		labelForImg.setIcon(img);
		labelForImg.setBounds(0,0,200,200);
		brand.setBounds(250,20,150,20);
		name.setBounds(250,40,150,20);
		serialNumber.setBounds(250,60,150,20);
		originalComponents.setBounds(250,80,150,20);
		supplier.setBounds(250,100,200,20);
		
		// Add content to JPanel
		resultContainer.add(labelForImg);
		resultContainer.add(brand);
		resultContainer.add(name);
		resultContainer.add(serialNumber);
		resultContainer.add(originalComponents);
		resultContainer.add(supplier);
		this.searchResult.add(resultContainer);
	}
	
	/**
	 * Execute a query to find equipment with custom where clause
	 * @param String whereClause
	 * @param String t[][] 
	 * @param Database db
	 * @return ResultSet rs
	 */
	private ResultSet findEquipment(String whereClause, String t[][], Database db){
		ResultSet	rs 		= null;
		String		query	= 	"SELECT 	e.numEquipment, " +
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
		rs = db.executePreparedQuery(query +whereClause +" ORDER BY e.label", t);
		return rs;
	}
	
	/**
	 * Execute a query to find components with custom where clause
	 * @param String whereClause
	 * @param String t[][]
	 * @param Database db
	 * @return ResultSet rs
	 */
	private ResultSet findComponent(String psWhereClause, String t[][], Database db){
		ResultSet	rs 		= null;
		String 		query	= 	"SELECT		c.numComponent, " +
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
		rs = db.executePreparedQuery(query +psWhereClause, t);
		return rs;
	}	
}
