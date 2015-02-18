package searchTabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import viewTabs.ViewTab;
import window.Window;

import buttons.SearchButton;

import com.bll.Employee;
import com.bll.Equipment;
import com.dal.Database;
import combos.Combo;

import enums.ComboType;

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
		super.jpSearchFields.add(lbSearch);
		super.jpSearchFields.add(lbSerialNumber);
		super.jpSearchFields.add(tfSerialNumber);
		super.jpSearchFields.add(lbBrand);
		super.jpSearchFields.add(cbBrand);
		super.jpSearchFields.add(lbMedicalRep);
		super.jpSearchFields.add(tfMedicalRep);
		super.jpSearchFields.add(sbSearchButton);
		
		return jpTabContent;
	}

	@Override
	public void triggerSearchAction() {
		ResultSet	rs				= 	null;
		String 		sSerialNumber 	= 	null;
		String 		sMedicalRep		= 	null;
		String		sBrand			= 	null;
		String		sWhereClauseE	= 	new String("WHERE 1 = 1");
		Database	odbCon			= 	new Database();
		
		// Delete all results from precedent 
		super.jpSearchResult.setVisible(false);
		super.jpSearchResult.removeAll();
		
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
						break;
				}
			}
		}
		
		// Write Where clause depending on search fields filled + add parameters for query
		sWhereClauseE += (sSerialNumber.equals(null) || sSerialNumber.isEmpty()) ? "" : " AND e.serialNumber = ? ";
		String	tTableE[][] = {{"String", sSerialNumber}};
		
		// Execute query
		if (!sWhereClauseE.equals("WHERE 1 = 1")) {
			odbCon.connect();
			rs = this.findEquipment(sWhereClauseE, tTableE, odbCon);
			// Display results
			try {
				while(rs.next()){
					String tTableC[][] = {{"int", Integer.toString(rs.getInt("numEquipment"))}};
					this.displaySearchResults(rs, findComponent("WHERE numEquipment = ?", tTableC, odbCon));
				}
			} catch (SQLException e) {
				System.out.println("[Error in SearchEquipment.triggerSearchAction()] " +e.getMessage());
			}
			odbCon.disconnect();
		}
		
		// use results
		super.jpSearchResult.setVisible(true);
	}

	@Override
	protected void displaySearchResults(ResultSet rsE, ResultSet rsC) {
		ImageIcon	imgEIcon 				= 	null;
		JLabel		lbEIcon					= 	new JLabel();
		JLabel		lbEBrand				= 	null;
		JLabel		lbEName					= 	null;
		JLabel		lbESerialNumber			= 	null;
		JLabel		lbOriginalComponents 	= 	null;
		JLabel		lbESupplier				= 	null;
		int 		i = 1;
		
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
					(this.jpSearchResult.add(new JLabel(rsC.getString("ctLabel") +" : " +rsC.getString("label")))).setBounds(500, i*20, 100,20);
				}
			}
		} catch (SQLException e){
			System.out.println("[Error in SearchEquipment.displaySearchResults] " +e.getMessage());
		}
		// Customize new content
		lbEIcon.setIcon(imgEIcon);
		lbEIcon.setBounds(0,0,200,200);
		lbEBrand.setBounds(210,20,200,20);
		lbEName.setBounds(210,40,200,20);
		lbESerialNumber.setBounds(210,60,200,20);
		lbOriginalComponents.setBounds(210,80,200,20);
		lbESupplier.setBounds(210,100,200,20);
		
		// Add content to JPanel
		this.jpSearchResult.add(lbEIcon);
		this.jpSearchResult.add(lbEBrand);
		this.jpSearchResult.add(lbEName);
		this.jpSearchResult.add(lbESerialNumber);
		this.jpSearchResult.add(lbOriginalComponents);
		this.jpSearchResult.add(lbESupplier);
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
								"			c.label			AS 	cLabel, " +
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
								"ON			s.numSupplier = c.numSupplier " +
								"WHERE 		c.numEquipment = ?";
		System.out.println(tTable);
		rs = odbCon.executePreparedQuery(sQuery +psWhereClause, tTable);
		return rs;
	}	
}
