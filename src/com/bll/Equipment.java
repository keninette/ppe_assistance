package com.bll;

import java.sql.Date;
import java.util.ArrayList;

import com.dal.BasicRequests;

public class Equipment {
	private int 					numEquipment;
	private String					label;
	private	String					serialNumber;
	//private	String					purchaseDate;
	private	boolean					originalComponent;
	private	Supplier				supplier;
	private	Employee				employee;
	private	Brand					brand;
	private	String					photo;
	private ArrayList<Component> 	components;
	
	/**
	 * Class constructor (uninitialized)
	 */
	public Equipment() {
		numEquipment = 0;
		label = null;
		serialNumber = null;
		originalComponent = true;
		supplier = new Supplier();
		employee = new Employee();
		brand = new Brand();
		photo = null;
		components = new ArrayList<Component>();
	}
	
	/**
	 * Class constructor (initialized)
	 */
	public Equipment(int numEquipment, String label, String serialNumber, Date purchaseDate, Date warrantyDate, boolean originalComponents,
						int numSupplier, int numEmployee, int numBrand, String photo) {
		this.numEquipment = numEquipment;
		this.label = label;
		this.serialNumber = serialNumber;
		this.originalComponent = originalComponents;
		this.supplier = (Supplier) BasicRequests.createInstance("supplier",numSupplier);
		this.employee = (Employee) BasicRequests.createInstance("employee", numEmployee);
		this.brand = (Brand) BasicRequests.createInstance("brand",numBrand);
		this.photo = photo;
		// TODO : arraylist
	}
	
	/************** getters & setters  ***************/
	
	public int getNumEquipment() {
		return numEquipment;
	}

	public void setNumEquipment(int numEquipment) {
		this.numEquipment = numEquipment;
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

	public boolean isOriginalComponent() {
		return originalComponent;
	}

	public void setOriginalComponent(boolean originalComponent) {
		this.originalComponent = originalComponent;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public ArrayList<Component> getComponents() {
		return this.components;
	}
}
