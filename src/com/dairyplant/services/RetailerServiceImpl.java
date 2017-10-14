package com.dairyplant.services;

import java.util.List;

import com.dairyplant.dao.RetailerDAO;
import com.dairyplant.model.Retailer;

public class RetailerServiceImpl implements GenericServiceProvider<Retailer> {

	private RetailerDAO retailerDAO = new RetailerDAO();

	@Override
	public Retailer add(Retailer retailer) {
		return retailerDAO.add(retailer);
	}

	@Override
	public void delete(int retailerId) {
		retailerDAO.delete(retailerId);
	}

	@Override
	public Retailer update(Retailer retailer) {
		return retailerDAO.update(retailer);
	}

	@Override
	public List<Retailer> retrieveAll() {
		return retailerDAO.getAllRetailer();
	}

	@Override
	public Retailer retrieve(int retailerId) {
		return retailerDAO.getRetailer(retailerId);
	}

}
