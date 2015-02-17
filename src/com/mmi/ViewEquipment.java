package com.mmi;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bll.Employee;
import com.dal.Database;

import enums.ComboType;

public class ViewEquipment extends ViewTab {

	/**
	 * Class constructor
	 */
	public ViewEquipment(Window pWindow){
		super.setTab();
		super.icon = new ImageIcon("res/img/icons/icon_equipment.png");
		super.window = pWindow;
		super.jpSearchFields.setBounds(0,0,1310,200);
		super.jpSearchFields.setBackground(Color.blue);
		super.jpSearchResult.setBounds(0,200,1310,550);
		super.jpSearchResult.setBackground(Color.red);
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
	protected void triggerSearchAction() {
		String 		sSerialNumber 	= 	null;
		String 		sMedicalRep		= 	null;
		String		sBrand			= 	null;
		String		sQuery			= 	"SELECT 	* " +
										"FROM 		equipment e " +
										"LEFT JOIN	component c " +
										"ON			c.numEquipment = e.numEquipment ";
		String		sWhereClause	= 	new String("WHERE 1 = 1");
		Database	odbCon			= 	new Database();
		ResultSet	rs;
		int			i				= 0;
		
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
		// Write Where clause depending on search fields filled
		sWhereClause += (sSerialNumber.equals(null) || sSerialNumber.isEmpty()) ? "" : " AND e.serialNumber = ? ";
		if (!sWhereClause.equals("WHERE 1 = 1")) {
			String	tTemp[][] = {{"String", sSerialNumber}};
			// Execute query
			odbCon.connect();
			rs = odbCon.executePreparedQuery(sQuery +sWhereClause, tTemp);
			try {
				while(rs.next()){
					displaySearchResults(rs,i);
					i++;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			odbCon.disconnect();
		}
		super.jpSearchResult.setVisible(true);
	}

	@Override
	protected void displaySearchResults(ResultSet rs, int i) {
		ImageIcon	imgEquipIcon 		= null;
		JLabel		lbEquipIcon			= new JLabel();
		JLabel		lbEquipName			= null;
		
		// Create new content
		try {
			if (i==0){
				imgEquipIcon = new ImageIcon("res/img/equipment/" +rs.getString("photo"));
				lbEquipName = new JLabel(rs.getString("label"));
			}
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		if (i == 0) {
			// Customize new content
			lbEquipIcon.setIcon(imgEquipIcon);
			lbEquipIcon.setBounds(0, 20,300,300);
			lbEquipName.setBounds(0, 320,300,20);
			
			// Add content to JPanel
			this.jpSearchResult.add(lbEquipIcon);
			this.jpSearchResult.add(lbEquipName);
		}
	}
	
	
}
