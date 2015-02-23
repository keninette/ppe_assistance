package com.bll;

import com.bll.categories.ComponentType;
import com.dal.BasicRequests;

public class Component {
	private int				numComponent;
	private String			label;
	private String			serialNumber;
	//dates
	private ComponentType	componentType;
	private	Supplier		supplier;
	private	Brand			brand;
	private Equipment		equipment;
	
	/**
	 * Class constructor (uninitialized)
	 */
	public Component() {
		numComponent = 0;
		label = null;
		serialNumber = null;
		componentType = new ComponentType();
		supplier = new Supplier();
		brand = new Brand();
		equipment = new Equipment();
	}
	
	/**
	 * Class constructor (initialized)
	 * @param numComponent
	 * @param label
	 * @param serialNumber
	 * @param numComponentType
	 * @param numSupplier
	 * @param numBrand
	 * @param numEquipment
	 */
	public Component(int numComponent, String label, String serialNumber, int numComponentType, 
						int numSupplier, int numBrand, int numEquipment) {
		this.numComponent = numComponent;
		this.label = label;
		this.serialNumber = serialNumber;
		this.componentType = new ComponentType(numComponentType);
		this.supplier = (Supplier) BasicRequests.createInstance("supplier", numSupplier);
		this.brand = (Brand) BasicRequests.createInstance("brand", numBrand);
		this.equipment = (Equipment) BasicRequests.createInstance("equipment", numEquipment);
	}
	
	/*************** getters & setters ***************/
	public int getNumComponent() {
		return numComponent;
	}

	public void setNumComponent(int numComponent) {
		this.numComponent = numComponent;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public ComponentType getComponentType() {
		return componentType;
	}

	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}	
}
