package com.dairyplant.model;

import java.util.Date;

public class Consumption {

	private int consumptionId;
	private double consumedQuantity;
	private Date consumedDate;
	private Material material;

	public Consumption() {
		super();
	}

	public Consumption(int consumptionId, double consumedQuantity,
			Date consumedDate, Material material) {
		super();
		this.consumptionId = consumptionId;
		this.consumedQuantity = consumedQuantity;
		this.consumedDate = consumedDate;
		this.material = material;
	}

	public int getConsumptionId() {
		return consumptionId;
	}

	public void setConsumptionId(int consumptionId) {
		this.consumptionId = consumptionId;
	}

	public double getConsumedQuantity() {
		return consumedQuantity;
	}

	public void setConsumedQuantity(double consumedQuantity) {
		this.consumedQuantity = consumedQuantity;
	}

	public Date getConsumedDate() {
		return consumedDate;
	}

	public void setConsumedDate(Date consumedDate) {
		this.consumedDate = consumedDate;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
