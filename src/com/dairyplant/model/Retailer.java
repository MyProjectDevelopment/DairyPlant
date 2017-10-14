package com.dairyplant.model;

public class Retailer {

	private int retailerId;
	private String retailerName;
	private String retailerCity;
	private String retailerArea;
	private String retailerContact;

	public Retailer() {
		super();
	}

	public Retailer(int retailerId, String retailerName, String retailerCity,
			String retailerArea, String retailerContact) {
		super();
		this.retailerId = retailerId;
		this.retailerName = retailerName;
		this.retailerCity = retailerCity;
		this.retailerArea = retailerArea;
		this.retailerContact = retailerContact;
	}

	public int getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getRetailerCity() {
		return retailerCity;
	}

	public void setRetailerCity(String retailerCity) {
		this.retailerCity = retailerCity;
	}

	public String getRetailerArea() {
		return retailerArea;
	}

	public void setRetailerArea(String retailerArea) {
		this.retailerArea = retailerArea;
	}

	public String getRetailerContact() {
		return retailerContact;
	}

	public void setRetailerContact(String retailerContact) {
		this.retailerContact = retailerContact;
	}

}
