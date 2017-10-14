package com.dairyplant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dairyplant.db.DBConnection;
import com.dairyplant.model.Retailer;

public class RetailerDAO {

	private static Logger logger = Logger.getLogger(RetailerDAO.class);
	private static final String TBL_NAME = "retailer";
	private static final String COL_RETAILER_ID = "retailer_id";
	private static final String COL_RETAILER_NAME = "retailer_name";
	private static final String COL_RETAILER_CITY = "retailer_city";
	private static final String COL_RETAILER_AREA = "retailer_area";
	private static final String COL_RETAILER_CONTACT = "retailer_contact";

	private String insertQuery = "INSERT INTO " + TBL_NAME
			+ " values(?,?,?,?,?)";
	private String deleteQuery = "DELETE FROM " + TBL_NAME + " WHERE "
			+ COL_RETAILER_ID + "=?";
	private String updateQuery = "UPDATE " + TBL_NAME + " SET "
			+ COL_RETAILER_NAME + "=?," + COL_RETAILER_CITY + "=?,"
			+ COL_RETAILER_AREA + "=?," + COL_RETAILER_CONTACT + "=?"
			+ " WHERE " + COL_RETAILER_ID + "=?";
	private String searchRetailer = "SELECT * FROM " + TBL_NAME + " WHERE "
			+ COL_RETAILER_ID + "=?";
	private String searchAllRetailers = "SELECT * FROM " + TBL_NAME;

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet resultSet;

	// Method for adding retailer into table
	public Retailer add(Retailer retailer) {

		Retailer newRetailer = null;
		try {
			stmt = getStatement(insertQuery);

			stmt.setInt(1, retailer.getRetailerId());
			stmt.setString(2, retailer.getRetailerName());
			stmt.setString(3, retailer.getRetailerCity());
			stmt.setString(4, retailer.getRetailerArea());
			stmt.setString(5, retailer.getRetailerContact());

			int check = stmt.executeUpdate();
			newRetailer = getRetailer(retailer.getRetailerId());
			if (check > 0)
				logger.debug("retailer inserted");
			else
				logger.debug("failed to insert retailer");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newRetailer;
	}

	// method for deleting retailer from database
	public void delete(int retailerId) {

		try {
			stmt = getStatement(deleteQuery);
			stmt.setInt(1, retailerId);
			int check = stmt.executeUpdate();

			if (check > 0)
				logger.debug("retailer deleted");
			else
				logger.debug("failed to delete retailer");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// method for updating a product in the database
	public Retailer update(Retailer retailer) {

		Retailer updatedRetailer = null;
		try {
			Retailer retailerInDB = new RetailerDAO().getRetailer(retailer
					.getRetailerId());
			stmt = getStatement(updateQuery);

			if (retailer.getRetailerName() == null)
				retailer.setRetailerName(retailerInDB.getRetailerName());

			if (retailer.getRetailerCity() == null)
				retailer.setRetailerCity(retailerInDB.getRetailerCity());

			if (retailer.getRetailerArea() == null)
				retailer.setRetailerArea(retailerInDB.getRetailerArea());

			if (retailer.getRetailerContact() == null)
				retailer.setRetailerContact(retailerInDB.getRetailerContact());

			stmt.setString(1, retailer.getRetailerName());
			stmt.setString(2, retailer.getRetailerCity());
			stmt.setString(3, retailer.getRetailerArea());
			stmt.setString(4, retailer.getRetailerContact());
			stmt.setInt(5, retailer.getRetailerId());

			int check = stmt.executeUpdate();
			updatedRetailer = getRetailer(retailer.getRetailerId());
			if (check > 0)
				logger.debug("retailer updated");
			else
				logger.debug("failed to update retailer");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedRetailer;
	}

	// method for searching single user from the database from its userId
	public Retailer getRetailer(int retailerId) {

		Retailer retailer = new Retailer();
		try {
			stmt = getStatement(searchRetailer);
			stmt.setInt(1, retailerId);
			resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				retailer.setRetailerId(resultSet.getInt(1));
				retailer.setRetailerName(resultSet.getString(2));
				retailer.setRetailerCity(resultSet.getString(3));
				retailer.setRetailerArea(resultSet.getString(4));
				retailer.setRetailerContact(resultSet.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retailer;
	}

	// method for searching all users in the database
	public List<Retailer> getAllRetailer() {

		Retailer retailer = null;
		List<Retailer> retailers = new ArrayList<Retailer>();

		try {
			stmt = getStatement(searchAllRetailers);
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				retailer = new Retailer();
				retailer.setRetailerId(resultSet.getInt(1));
				retailer.setRetailerName(resultSet.getString(2));
				retailer.setRetailerCity(resultSet.getString(3));
				retailer.setRetailerArea(resultSet.getString(4));
				retailer.setRetailerContact(resultSet.getString(5));

				retailers.add(retailer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retailers;
	}

	// private method for creating common statement instance
	private PreparedStatement getStatement(String query) throws SQLException {
		con = DBConnection.getConnection();
		PreparedStatement statement = con.prepareStatement(query);
		return statement;
	}
}
