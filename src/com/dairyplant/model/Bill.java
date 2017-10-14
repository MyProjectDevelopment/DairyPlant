package com.dairyplant.model;

import java.util.Date;
import java.util.List;

/**
 *
 * {@code Bill} used to generate bill of multiple products
 *
 */
public class Bill {

	private int billNo;
	private Retailer retailer;
	private Date dateOfSale;
	private List<Product> products;
	private double soldQuantity;//total product sold quantity

	public Bill() {
		super();
	}

	public Bill(int billNo, Retailer retailer, Date dateOfSale,
			List<Product> products, double saleQuantity) {
		super();
		this.billNo = billNo;
		this.retailer = retailer;
		this.dateOfSale = dateOfSale;
		this.products = products;
		this.soldQuantity = saleQuantity;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public Retailer getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}

	public Date getDateOfSale() {
		return dateOfSale;
	}

	public void setDateOfSale(Date dateOfSale) {
		this.dateOfSale = dateOfSale;
	}

	public List<Product> getProduct() {
		return products;
	}

	public void setProduct(List<Product> products) {
		this.products = products;
	}

	public double getSaleQuantity() {
		return soldQuantity;
	}

	public void setSaleQuantity(double saleQuantity) {
		this.soldQuantity = saleQuantity;
	}

}
