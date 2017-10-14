package com.dairyplant.model;

import java.util.Date;

public class Stock {

	private Product product;
	private double availableProducts;
	private Date dateOfStock;

	public Stock() {
		super();
	}

	public Stock(Product product, double availableProducts, Date dateOfStock) {
		super();
		this.product = product;
		this.availableProducts = availableProducts;
		this.dateOfStock = dateOfStock;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getAvailableProducts() {
		return availableProducts;
	}

	public void setAvailableProducts(double availableProducts) {
		this.availableProducts = availableProducts;
	}

	public Date getDateOfStock() {
		return dateOfStock;
	}

	public void setDateOfStock(Date dateOfStock) {
		this.dateOfStock = dateOfStock;
	}

}
