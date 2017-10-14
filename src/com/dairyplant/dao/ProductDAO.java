package com.dairyplant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dairyplant.db.DBConnection;
import com.dairyplant.model.Product;

public class ProductDAO {

	private static Logger logger = Logger.getLogger(UserDAO.class);
	private static final String TBL_NAME = "product";
	private static final String COL_PROD_ID = "product_id";
	private static final String COL_PROD_NAME = "product_name";
	private static final String COL_PROD_CATEGORY = "product_category";
	private static final String COL_PROD_PRICE = "product_price";
	private static final String COL_PROD_MANUFACTURING_DATE = "product_mfd";
	private static final String COL_PROD_EXPIRY_DATE = "product_exp";

	private String insertQuery = "INSERT INTO " + TBL_NAME
			+ " values(?,?,?,?,?,?)";
	private String deleteQuery = "DELETE FROM " + TBL_NAME + " WHERE "
			+ COL_PROD_ID + "=?";
	private String updateQuery = "UPDATE " + TBL_NAME + " SET " + COL_PROD_NAME
			+ "=?," + COL_PROD_CATEGORY + "=?," + COL_PROD_PRICE + "=?,"
			+ COL_PROD_MANUFACTURING_DATE + "=?," + COL_PROD_EXPIRY_DATE + "=?"
			+ " WHERE " + COL_PROD_ID + "=?";
	private String searchProduct = "SELECT * FROM " + TBL_NAME + " WHERE "
			+ COL_PROD_ID + "=?";
	private String searchAllProducts = "SELECT * FROM " + TBL_NAME;

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet resultSet;

	// Method for adding user into table
	public Product add(Product product) {

		Product prod = null;

		try {

			stmt = getStatement(insertQuery);

			stmt.setInt(1, product.getProductId());
			stmt.setString(2, product.getProductName());
			stmt.setString(3, product.getCategory());
			stmt.setDouble(4, product.getPrice());
			stmt.setDate(5, product.getProductMFD());
			stmt.setDate(6, product.getExpDate());

			int check = stmt.executeUpdate();
			prod = getProduct(product.getProductId());

			if (check > 0)
				logger.debug("product inserted");
			else
				logger.debug("failed to insert product");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prod;
	}

	// method for deleting product from database
	public void delete(int productId) {

		try {
			stmt = getStatement(deleteQuery);
			stmt.setInt(1, productId);
			int check = stmt.executeUpdate();

			if (check > 0)
				logger.debug("product deleted");
			else
				logger.debug("failed to delete product");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// method for updating a product in the database
	public Product update(Product product) {

		Product prod = null;

		try {
			Product productInDB = new ProductDAO().getProduct(product
					.getProductId());
			stmt = getStatement(updateQuery);

			if (product.getProductName() == null)
				product.setProductName(productInDB.getProductName());

			if (product.getCategory() == null)
				product.setCategory(productInDB.getCategory());

			if (product.getPrice() == 0)
				product.setPrice(productInDB.getPrice());

			if (product.getProductMFD() == null)
				product.setProductMFD(productInDB.getProductMFD());

			if (product.getExpDate() == null)
				product.setExpDate(productInDB.getExpDate());

			stmt.setString(1, product.getProductName());
			stmt.setString(2, product.getCategory());
			stmt.setDouble(3, product.getPrice());
			stmt.setDate(4, product.getProductMFD());
			stmt.setDate(5, product.getExpDate());
			stmt.setInt(6, product.getProductId());

			int check = stmt.executeUpdate();
			if (check > 0)
				logger.debug("product updated with product id : "
						+ product.getProductId());
			else
				logger.debug("failed to update product");
			prod = getProduct(product.getProductId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prod;
	}

	// method for searching single user from the database from its userId
	public Product getProduct(int productId) {

		Product product = new Product();
		try {
			stmt = getStatement(searchProduct);
			stmt.setInt(1, productId);
			resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				product.setProductId(resultSet.getInt(1));
				product.setProductName(resultSet.getString(2));
				product.setCategory(resultSet.getString(3));
				product.setPrice(resultSet.getDouble(4));
				product.setProductMFD(resultSet.getDate(5));
				product.setExpDate(resultSet.getDate(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	// method for searching all users in the database
	public List<Product> getAllProducts() {

		Product product = null;

		List<Product> products = new ArrayList<Product>();
		try {

			stmt = getStatement(searchAllProducts);
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {

				product = new Product();
				product.setProductId(resultSet.getInt(1));
				product.setProductName(resultSet.getString(2));
				product.setCategory(resultSet.getString(3));
				product.setPrice(resultSet.getDouble(4));
				product.setProductMFD(resultSet.getDate(5));
				product.setExpDate(resultSet.getDate(6));

				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	// private method for creating common statement instance
	private PreparedStatement getStatement(String query) throws SQLException {
		con = DBConnection.getConnection();
		PreparedStatement statement = con.prepareStatement(query);
		return statement;
	}

}
