package com.dairyplant.model;

import java.util.Date;

public class Supply {

	private Date dateOfSupply;
	private double suppliedQuantity;
	private double totalAmount;
	private Supplier supplier;
	private Material material;

	public Supply() {
		super();
	}

	public Supply(Date dateOfSupply, double suppliedQuantity,
			double totalAmount, Supplier supplier, Material material) {
		super();
		this.dateOfSupply = dateOfSupply;
		this.suppliedQuantity = suppliedQuantity;
		this.totalAmount = totalAmount;
		this.supplier = supplier;
		this.material = material;
	}

	public Date getDateOfSupply() {
		return dateOfSupply;
	}

	public void setDateOfSupply(Date dateOfSupply) {
		this.dateOfSupply = dateOfSupply;
	}

	public double getSuppliedQuantity() {
		return suppliedQuantity;
	}

	public void setSuppliedQuantity(double suppliedQuantity) {
		this.suppliedQuantity = suppliedQuantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
