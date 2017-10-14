package com.dairyplant.model;

public class Supplier {

	private int supplierId;
	private String supplierName;
	private String supplierCity;
	private String supplierArea;
	private String supplierContact;

	public Supplier() {
		super();
	}

	public Supplier(int supplierId, String supplierName, String supplierCity,
			String supplierArea, String supplierContact) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierCity = supplierCity;
		this.supplierArea = supplierArea;
		this.supplierContact = supplierContact;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCity() {
		return supplierCity;
	}

	public void setSupplierCity(String supplierCity) {
		this.supplierCity = supplierCity;
	}

	public String getSupplierArea() {
		return supplierArea;
	}

	public void setSupplierArea(String supplierArea) {
		this.supplierArea = supplierArea;
	}

	public String getSupplierContact() {
		return supplierContact;
	}

	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}

}
