package com.dairyplant.model;

import java.util.Date;

public class Inventory {

	private int inventoryId;
	private Material material;
	private double availableQuantity;
	private Date inventoryDate;

	public Inventory() {
		super();
	}

	public Inventory(int inventoryId, Material material,
			double availableQuantity, Date inventoryDate) {
		super();
		this.inventoryId = inventoryId;
		this.material = material;
		this.availableQuantity = availableQuantity;
		this.inventoryDate = inventoryDate;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public double getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(double availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Date getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}

}
