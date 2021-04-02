package com.tweetapp.constants;

public class TweetAppSqlQueries {

	public static String CREATE_USER_QUERY = "INSERT INTO user (user_id, first_name, last_name, mobile_number, url, gender, dob, password, logged_in) VALUES (?,?,?,?,?,?,?,?,?)";
	
	public static String USER_GET_QUERY = "SELECT user_id, password from user WHERE user_id = ? AND password = ?";
	
	public static String USER_GET_QUERY_DOB = "SELECT user_id, password from user WHERE user_id = ? AND dob = ?";
	
	public static String USER_LOGIN_STATUS_UPDATE = "UPDATE user SET logged_in = ? WHERE user_id = ?";
	
	public static String USER_PASSWORD_UPDATE = "UPDATE user SET password = ? WHERE user_id = ?";
	
	public static String POST_TWEET = "INSERT INTO tweet (user_id, tweet) VALUES (?,?)";
	
	public static String GET_USER_TWEETS = "SELECT user_id, tweet FROM tweet WHERE user_id = ?";
	
	public static String GET_ALL_TWEETS = "SELECT user_id, tweet FROM tweet";
	
	public static String GET_ALL_USERS = "SELECT user_id, first_name, last_name FROM user";
}
