package com.dairyplant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dairyplant.db.DBConnection;
import com.dairyplant.model.Supplier;

public class SupplierDAO {

	private static Logger logger = Logger.getLogger(RetailerDAO.class);
	private static final String TBL_NAME = "supplier";
	private static final String COL_SUPPLIER_ID = "supplier_id";
	private static final String COL_SUPPLIER_NAME = "supplier_name";
	private static final String COL_SUPPLIER_CITY = "supplier_city";
	private static final String COL_SUPPLIER_AREA = "supplier_area";
	private static final String COL_SUPPLIER_CONTACT = "supplier_contact";

	private String insertQuery = "INSERT INTO " + TBL_NAME
			+ " values(?,?,?,?,?)";
	private String deleteQuery = "DELETE FROM " + TBL_NAME + " WHERE "
			+ COL_SUPPLIER_ID + "=?";
	private String updateQuery = "UPDATE " + TBL_NAME + " SET "
			+ COL_SUPPLIER_NAME + "=?," + COL_SUPPLIER_CITY + "=?,"
			+ COL_SUPPLIER_AREA + "=?," + COL_SUPPLIER_CONTACT + "=?"
			+ " WHERE " + COL_SUPPLIER_ID + "=?";
	private String searchRetailer = "SELECT * FROM " + TBL_NAME + " WHERE "
			+ COL_SUPPLIER_ID + "=?";
	private String searchAllSupplier = "SELECT * FROM " + TBL_NAME;

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet resultSet;

	// Method for adding retailer into table
	public Supplier add(Supplier supplier) {

		Supplier newSupplier = null;
		try {
			stmt = getStatement(insertQuery);

			stmt.setInt(1, supplier.getSupplierId());
			stmt.setString(2, supplier.getSupplierName());
			stmt.setString(3, supplier.getSupplierCity());
			stmt.setString(4, supplier.getSupplierArea());
			stmt.setString(5, supplier.getSupplierContact());

			int check = stmt.executeUpdate();

			newSupplier = getSupplier(supplier.getSupplierId());

			if (check > 0)
				logger.debug("supplier inserted");
			else
				logger.debug("failed to insert supplier");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newSupplier;
	}

	// method for deleting retailer from database
	public void delete(int supplierId) {

		try {

			stmt = getStatement(deleteQuery);
			stmt.setInt(1, supplierId);
			int check = stmt.executeUpdate();

			if (check > 0)
				logger.debug("supplier deleted with id : " + supplierId);
			else
				logger.debug("failed to delete supplier");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// method for updating a product in the database
	public Supplier update(Supplier supplier) {

		Supplier updatedSupplier = null;
		try {
			Supplier supplierInDB = new SupplierDAO().getSupplier(supplier
					.getSupplierId());
			stmt = getStatement(updateQuery);

			if (supplier.getSupplierName() == null)
				supplier.setSupplierName(supplierInDB.getSupplierName());

			if (supplier.getSupplierCity() == null)
				supplier.setSupplierCity(supplierInDB.getSupplierCity());

			if (supplier.getSupplierArea() == null)
				supplier.setSupplierArea(supplierInDB.getSupplierArea());

			if (supplier.getSupplierContact() == null)
				supplier.setSupplierContact(supplierInDB.getSupplierContact());

			stmt.setString(1, supplier.getSupplierName());
			stmt.setString(2, supplier.getSupplierCity());
			stmt.setString(3, supplier.getSupplierArea());
			stmt.setString(4, supplier.getSupplierContact());
			stmt.setInt(5, supplier.getSupplierId());

			int check = stmt.executeUpdate();
			updatedSupplier = getSupplier(supplier.getSupplierId());

			if (check > 0)
				logger.debug("supplier updated");
			else
				logger.debug("failed to update supplier");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedSupplier;
	}

	// method for searching single user from the database from its userId
	public Supplier getSupplier(int supplierId) {

		Supplier supplier = new Supplier();

		try {
			stmt = getStatement(searchRetailer);
			stmt.setInt(1, supplierId);
			resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				supplier.setSupplierId(resultSet.getInt(1));
				supplier.setSupplierName(resultSet.getString(2));
				supplier.setSupplierCity(resultSet.getString(3));
				supplier.setSupplierArea(resultSet.getString(4));
				supplier.setSupplierContact(resultSet.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplier;
	}

	// method for searching all users in the database
	public List<Supplier> getAllSupplier() {

		Supplier supplier = null;
		List<Supplier> suppliers = new ArrayList<Supplier>();

		try {
			stmt = getStatement(searchAllSupplier);
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				supplier = new Supplier();
				supplier.setSupplierId(resultSet.getInt(1));
				supplier.setSupplierName(resultSet.getString(2));
				supplier.setSupplierCity(resultSet.getString(3));
				supplier.setSupplierArea(resultSet.getString(4));
				supplier.setSupplierContact(resultSet.getString(5));

				suppliers.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return suppliers;
	}

	// private method for creating common statement instance
	private PreparedStatement getStatement(String query) throws SQLException {
		con = DBConnection.getConnection();
		PreparedStatement statement = con.prepareStatement(query);
		return statement;
	}
}
