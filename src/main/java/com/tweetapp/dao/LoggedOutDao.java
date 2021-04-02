package com.tweetapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tweetapp.beans.User;
import com.tweetapp.constants.TweetAppSqlQueries;
import com.tweetapp.util.TweetAppUtil;

public class LoggedOutDao {
	
	public void register(User user) {
		Connection connection = TweetAppUtil.getConnection();
		try {
			PreparedStatement saveQuery = connection.prepareStatement(TweetAppSqlQueries.CREATE_USER_QUERY);
			saveQuery.setString(1, user.getUserId());
			saveQuery.setString(2, user.getFirstName());
			saveQuery.setString(3, user.getLastName());
			saveQuery.setString(4, user.getMobileNumber());
			saveQuery.setString(5, user.getUrl());
			saveQuery.setString(6, user.getGender());
			saveQuery.setDate(7, new Date(user.getDob().getTime()));
			saveQuery.setString(8, user.getPassword());
			saveQuery.setBoolean(9, user.isLoggedIn());
			saveQuery.executeUpdate();
			System.out.println("Registration Successful!!");
		} catch (Exception exception) {
			System.out.println("Error occurred while creating the user");
		}
	}
	
	public boolean isValidUser(String userId, String password) {
		Connection connection = TweetAppUtil.getConnection();
		boolean isValid = false;
		try {
			PreparedStatement validUserQuery = connection.prepareStatement(TweetAppSqlQueries.USER_GET_QUERY);
			validUserQuery.setString(1, userId);
			validUserQuery.setString(2, password);
			ResultSet results = validUserQuery.executeQuery();
			isValid = results.next();
		}catch(Exception exception) {
			System.out.println("Error while logging in");
		}
		return isValid;
	}
	
	public void updateLoginStatus(String userId, boolean isLoggedIn) {
		Connection connection = TweetAppUtil.getConnection();
		try {
			PreparedStatement updateLoginStatusQuery = connection.prepareStatement(TweetAppSqlQueries.USER_LOGIN_STATUS_UPDATE);
			updateLoginStatusQuery.setBoolean(1, isLoggedIn);
			updateLoginStatusQuery.setString(2, userId);
			updateLoginStatusQuery.executeUpdate();
		}catch(Exception exception) {
			System.out.println("Error while logging in");
		}
	}

	public boolean isValidUser(String userId, java.util.Date dobDate) {
		boolean isValid = false;
		try {
			Connection connection = TweetAppUtil.getConnection();
			PreparedStatement validUserQuery = connection.prepareStatement(TweetAppSqlQueries.USER_GET_QUERY_DOB);
			validUserQuery.setString(1, userId);
			validUserQuery.setDate(2, new Date(dobDate.getTime()));
			ResultSet results = validUserQuery.executeQuery();
			isValid = results.next();
		}catch(Exception exception) {
			System.out.println("Error while resetting password");
		}
		return isValid;
	}
	
	public void updatePassword(String userId, String password) {
		Connection connection = TweetAppUtil.getConnection();
		try {
			PreparedStatement updateLoginStatusQuery = connection.prepareStatement(TweetAppSqlQueries.USER_PASSWORD_UPDATE);
			updateLoginStatusQuery.setString(1, password);
			updateLoginStatusQuery.setString(2, userId);
			updateLoginStatusQuery.executeUpdate();
		}catch(Exception exception) {
			System.out.println("Error while updating password. Please try again!!");
		}
	}
}
