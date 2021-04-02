package com.tweetapp.constants;

import java.util.Arrays;
import java.util.List;

public class TweetAppConstants {
	
	public static final List<String> LOGGED_IN_OPTIONS = Arrays.asList("1. Post a tweet","2. View my tweets","3. View all tweets","4. View all users","5. Reset Password","6. Logout", "7. Exit");
	
	public static final List<String> NON_LOGGED_IN_OPTIONS = Arrays.asList("1. Register","2. Login","3. Forgot Password", "4. Exit");
	
	public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
	
	public static final int ONE = 1;
	
	public static final int TWO = 2;

	public static final int THREE = 3;
	
	public static final int FOUR = 4;
	
	public static final int FIVE = 5;
	
	public static final int SIX = 6;
	
	public static final int SEVEN = 7;
	
	public static final String TWEET_MSG_FORMAT = "Tweet by %s: %s";
	
	public static final String USER_DETAIL_FORMAT = "Name: %s %s e-mail: %s";
}
