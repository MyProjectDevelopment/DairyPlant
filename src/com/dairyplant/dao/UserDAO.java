package com.dairyplant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dairyplant.db.DBConnection;
import com.dairyplant.model.User;

public class UserDAO {

	private static Logger logger = Logger.getLogger(UserDAO.class);
	private static final String TBL_NAME = "user";
	private static final String COL_USER_ID = "userId";
	private static final String COL_PASSWORD = "password";
	private static final String COL_USER_TYPE = "type";

	private String insertQuery = "INSERT INTO " + TBL_NAME + " values(?,?,?)";
	private String deleteQuery = "DELETE FROM " + TBL_NAME + " WHERE "
			+ COL_USER_ID + "=?";
	private String updateQuery = "UPDATE " + TBL_NAME + " SET " + COL_PASSWORD
			+ "=?," + COL_USER_TYPE + "=?" + " WHERE " + COL_USER_ID + "=?";
	private String searchUser = "SELECT * FROM " + TBL_NAME + " WHERE "
			+ COL_USER_ID + "=?";
	private String searchAllUsers = "SELECT * FROM " + TBL_NAME;

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet resultSet;

	// Method for adding user into table
	public User add(User user) {

		User newUser = null;
		try {
			stmt = getStatement(insertQuery);

			stmt.setString(1, user.getUserId());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getUserType());

			int check = stmt.executeUpdate();
			user = getUser(user.getUserId());

			if (check > 0)
				logger.debug("user inserted");
			else
				logger.debug("failed to insert user");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newUser;
	}

	// method for deleting student from database
	public void delete(String userId) {

		try {
			stmt = getStatement(deleteQuery);
			stmt.setString(1, userId);
			int check = stmt.executeUpdate();

			if (check > 0)
				logger.debug("user deleted");
			else
				logger.debug("failed to delete user");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// method for updating a student in the database
	public User update(User user) {
		User updatedUser = null;
		try {
			User userInDB = new UserDAO().getUser(user.getUserId());
			stmt = getStatement(updateQuery);

			if (user.getPassword() == null)
				user.setPassword(userInDB.getPassword());

			if (user.getUserType() == null)
				user.setUserType(userInDB.getUserType());

			stmt.setString(1, user.getPassword());
			stmt.setString(2, user.getUserType());
			stmt.setString(3, user.getUserId());

			int check = stmt.executeUpdate();
			updatedUser = getUser(user.getUserId());

			if (check > 0)
				logger.debug("user updated");
			else
				logger.debug("failed to update user");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedUser;
	}

	// method for searching single user from the database from its userId
	public User getUser(String userId) {

		User user = new User();
		try {
			stmt = getStatement(searchUser);
			stmt.setString(1, userId);
			resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				user.setUserId(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setUserType(resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// method for searching all users in the database
	public List<User> getAllUsers() {

		User user = null;
		List<User> users = new ArrayList<User>();
		try {
			stmt = getStatement(searchAllUsers);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setUserId(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setUserType(resultSet.getString(3));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	// private method for creating common statement instance
	private PreparedStatement getStatement(String query) throws SQLException {
		con = DBConnection.getConnection();
		PreparedStatement statement = con.prepareStatement(query);
		return statement;
	}
}
