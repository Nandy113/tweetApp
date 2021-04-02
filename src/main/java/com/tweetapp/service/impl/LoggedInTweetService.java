package com.tweetapp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.tweetapp.beans.Tweet;
import com.tweetapp.beans.User;
import com.tweetapp.constants.TweetAppConstants;
import com.tweetapp.dao.LoggedInDao;
import com.tweetapp.dao.LoggedOutDao;

public class LoggedInTweetService {
	
	private static LoggedInDao loggedInDao = new LoggedInDao();
	
	private static LoggedOutDao loggedOutDao = new LoggedOutDao();
	
	public void postTweet(String userId) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the tweet message ");
		String tweetMsg = sc.nextLine();
		loggedInDao.postTweet(userId, tweetMsg);
		System.out.println("Tweet posted successfully");
	}
	
	public void listUserTweets(String userId) {
		List<Tweet> userTweets = loggedInDao.getUserTweets(userId);
		if(userTweets.isEmpty()) {
			System.out.println("No tweets posted by the user");
		}
		userTweets.forEach(tweet -> {
			String tweetMsg = tweet.getTweet();
			System.out.println(String.format(TweetAppConstants.TWEET_MSG_FORMAT, userId, tweetMsg));
		});
	}
	
	public void listUserTweets() {
		List<Tweet> userTweets = loggedInDao.getUserTweets();
		if(userTweets.isEmpty()) {
			System.out.println("No tweets posted by the user");
		}
		userTweets.forEach(tweet -> {
			String userId = tweet.getUserId();
			String tweetMsg = tweet.getTweet();
			System.out.println(String.format(TweetAppConstants.TWEET_MSG_FORMAT, userId, tweetMsg));
		});
	}
	
	public void listUsers() {
		List<User> users = loggedInDao.getUsers();
		if(users.isEmpty()) {
			System.out.println("No users have registered so far!!");
		}
		users.forEach(user -> {
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String email = user.getUserId();
			System.out.println(String.format(TweetAppConstants.USER_DETAIL_FORMAT, firstName, lastName, email));
		});
	}
	
	public void logOff(String userId) {
		loggedOutDao.updateLoginStatus(userId, false);
	}
	
	public void resetPassword(String userId) {
		Scanner scanner = new Scanner(System.in);
		String password = null;
		boolean passwordMatch = false;
		while(!passwordMatch) {
			System.out.print("Enter the password for the account");
			password = scanner.nextLine();
			System.out.print("Confirm password ");
			String confirmPassword = scanner.nextLine();
			passwordMatch = password != null && confirmPassword != null && password.equals(confirmPassword);
			if(!passwordMatch) {
				System.out.println("Passwords do not match");
			}
		}
		loggedOutDao.updatePassword(userId, password);
	}

}
