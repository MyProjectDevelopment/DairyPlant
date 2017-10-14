package com.dairyplant.model;

public class Material {

	private int materialId;
	private String materialName;
	private double price;
	private String category;

	public Material() {
		super();
	}

	public Material(int materialId, String materialName, double price,
			String category) {
		super();
		this.materialId = materialId;
		this.materialName = materialName;
		this.price = price;
		this.category = category;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Material [materialId=" + materialId + ", materialName="
				+ materialName + ", price=" + price + ", category=" + category
				+ "]";
	}

}
