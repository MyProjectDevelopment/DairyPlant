package com.dairyplant.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBConnection {

	private static Connection con = null;
	public static Logger logger = Logger.getLogger(DBConnection.class);

	private DBConnection() {
		super();
	}

	public static Connection getConnection() {
		try {
			if (con != null) {
				if (!con.isClosed()) {
					return con;
				}
			}
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream input = classLoader
					.getResourceAsStream("com/dairyplant/resources/db.properties");
			Properties property = new Properties();
			property.load(input);

			String dbDriver = property.getProperty("db.driver");
			System.out.println(dbDriver);
			String dbHost = property.getProperty("db.host");
			String dbProtocol = property.getProperty("db.protocol");
			String dbName = property.getProperty("db.dbname");
			String dbPort = property.getProperty("db.port");
			String dbUser = property.getProperty("db.user");
			String dbPassword = property.getProperty("db.password");

			String connectionString = dbProtocol + "://" + dbHost + ":"
					+ dbPort + "/" + dbName;

			Class.forName(dbDriver);
			con = DriverManager.getConnection(connectionString, dbUser,
					dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Connection established " + con);
		return con;
	}
}
