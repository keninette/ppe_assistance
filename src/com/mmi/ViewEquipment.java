package com.mmi;

import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bll.Employee;
import enums.ComboType;

public class ViewEquipment extends ViewTab {

	/**
	 * Class constructor
	 */
	public ViewEquipment(Window pWindow){
		super.setTab();
		super.icon = new ImageIcon("res/img/icons/icon_equipment.png");
		super.window = pWindow;
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
		SearchButton	sbSearchButton	= new SearchButton(super.window,"");
	
		
		// Customize components
		// JLabel
		lbSearch.setBounds(20,0,500,40);
		lbSearch.setFont(new Font("Arial",Font.BOLD, 20));
		lbSerialNumber.setBounds(20,40,200,20);
		lbBrand.setBounds(240,40,200,20);
		lbMedicalRep.setBounds(460,40,200,20);
		// JTextField
		tfSerialNumber.setBounds(20,60,200,20);
		tfMedicalRep.setBounds(460,60,200,20);
		// Combo
		cbBrand.setBounds(240,60,200,20);
		// JButton
		sbSearchButton.setBounds(680,50,30,30);
		// Fill comboBoxes
		cbBrand.fillComboBox(ComboType.COMBO_BRAND.toString());
		// Add components to JPanel
		super.jpTabContent.add(lbSearch);
		super.jpTabContent.add(lbSerialNumber);
		super.jpTabContent.add(tfSerialNumber);
		super.jpTabContent.add(lbBrand);
		super.jpTabContent.add(cbBrand);
		super.jpTabContent.add(lbMedicalRep);
		super.jpTabContent.add(tfMedicalRep);
		super.jpTabContent.add(sbSearchButton);
		
		return jpTabContent;
	}
}
