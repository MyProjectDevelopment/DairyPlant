package com.dairyplant.model;

import java.sql.Date;

public class Product {

	private int productId;
	private String productName;
	private String category;
	private double price;
	private Date productMFD;// manufacturing date of product
	private Date expDate;// expiry date of product

	public Product() {
		super();
	}

	public Product(int productId, String productName, String category,
			double price, Date productMFD, Date expDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.productMFD = productMFD;
		this.expDate = expDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getProductMFD() {
		return productMFD;
	}

	public void setProductMFD(Date productMFD) {
		this.productMFD = productMFD;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + ", category=" + category + ", price=" + price
				+ ", productMFD=" + productMFD + ", expDate=" + expDate + "]";
	}

}
