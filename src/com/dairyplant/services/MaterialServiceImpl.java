package com.dairyplant.services;

import java.util.List;

import com.dairyplant.dao.MaterialDAO;
import com.dairyplant.model.Material;

public class MaterialServiceImpl implements GenericServiceProvider<Material> {

	private MaterialDAO materialDAO = new MaterialDAO();

	@Override
	public Material add(Material material) {
		return materialDAO.add(material);
	}

	@Override
	public void delete(int id) {
		materialDAO.delete(id);
	}

	@Override
	public Material update(Material material) {
		return materialDAO.update(material);
	}

	@Override
	public List<Material> retrieveAll() {
		return materialDAO.getAllMaterials();
	}

	@Override
	public Material retrieve(int id) {
		return materialDAO.getMaterial(id);
	}

}
