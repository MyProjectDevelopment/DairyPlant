package com.dairyplant.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dairyplant.dao.ProductDAO;
import com.dairyplant.model.Product;

public class ProductServiceImpl implements GenericServiceProvider<Product> {

	private ProductDAO productDAO = new ProductDAO();
	private List<Product> products = productDAO.getAllProducts();

	public Set<String> getCategories() {

		Set<String> categories = new HashSet<String>();

		for (Product product : products) {
			categories.add(product.getCategory());
		}
		return categories;
	}

	public List<Product> getProductByCategory(String category) {
		List<Product> newProducts = new ArrayList<Product>();
		for (Product product : products) {
			if (product.getCategory().equals(category))
				newProducts.add(product);
		}
		return newProducts;
	}

	@Override
	public Product add(Product product) {
		return productDAO.add(product);
	}

	@Override
	public void delete(int id) {
		productDAO.delete(id);
	}

	@Override
	public Product update(Product product) {
		return productDAO.update(product);
	}

	@Override
	public List<Product> retrieveAll() {
		return productDAO.getAllProducts();
	}

	@Override
	public Product retrieve(int id) {
		return productDAO.getProduct(id);
	}
}
