package com.tweetapp.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class TweetAppUtil {

	public static Connection getConnection() {
		Connection conn = null;
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("src/main/res/application.properties"));
			String driverName = properties.getProperty("jdbc.connection.driver");
			String url = properties.getProperty("jdbc.connection.url");
			String user = properties.getProperty("jdbc.connection.user");
			String password = properties.getProperty("jdbc.connection.password");
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception exception) {
			System.out.println("Error while connecting to the database. Please try again");
			exception.printStackTrace();
		}
		return conn;
	}
}
