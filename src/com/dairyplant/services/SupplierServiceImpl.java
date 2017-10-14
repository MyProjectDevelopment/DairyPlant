package com.dairyplant.services;

import java.util.List;

import com.dairyplant.dao.SupplierDAO;
import com.dairyplant.model.Supplier;

public class SupplierServiceImpl implements GenericServiceProvider<Supplier> {

	private SupplierDAO supplierDAO = new SupplierDAO();

	@Override
	public Supplier add(Supplier supplier) {
		return supplierDAO.add(supplier);
	}

	@Override
	public void delete(int id) {
		supplierDAO.delete(id);
	}

	@Override
	public Supplier update(Supplier supplier) {
		return supplierDAO.update(supplier);
	}

	@Override
	public List<Supplier> retrieveAll() {
		return supplierDAO.getAllSupplier();
	}

	@Override
	public Supplier retrieve(int id) {
		return supplierDAO.getSupplier(id);
	}

}
