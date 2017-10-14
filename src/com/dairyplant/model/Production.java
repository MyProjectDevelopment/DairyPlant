package com.dairyplant.model;

import java.util.Date;

public class Production {

	private Product product;
	private double producedQuantity;
	private double productCostPrice;
	private Date productionDate;

	public Production() {
		super();
	}

	public Production(Product product, double producedQuantity,
			double productCostPrice, Date productionDate) {
		super();
		this.product = product;
		this.producedQuantity = producedQuantity;
		this.productCostPrice = productCostPrice;
		this.productionDate = productionDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getProducedQuantity() {
		return producedQuantity;
	}

	public void setProducedQuantity(double producedQuantity) {
		this.producedQuantity = producedQuantity;
	}

	public double getProductCostPrice() {
		return productCostPrice;
	}

	public void setProductCostPrice(double productCostPrice) {
		this.productCostPrice = productCostPrice;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

}
