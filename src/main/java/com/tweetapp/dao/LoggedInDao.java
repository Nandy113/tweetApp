package com.tweetapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tweetapp.beans.Tweet;
import com.tweetapp.beans.User;
import com.tweetapp.constants.TweetAppSqlQueries;
import com.tweetapp.util.TweetAppUtil;

public class LoggedInDao {
	
	public void postTweet(String userId, String tweet) {
		Connection connection = TweetAppUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(TweetAppSqlQueries.POST_TWEET);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, tweet);
			preparedStatement.executeUpdate();
		}catch(Exception exception) {
			System.out.println("Error while posting the tweet. Please try again!!");
		}
	}
	
	public List<Tweet> getUserTweets(String userId) {
		Connection connection = TweetAppUtil.getConnection();
		List<Tweet> tweets = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(TweetAppSqlQueries.GET_USER_TWEETS);
			preparedStatement.setString(1, userId);
			ResultSet tweetResults = preparedStatement.executeQuery();
			if(tweetResults.next()) {
				Tweet tweet = new Tweet();
				tweet.setTweet(tweetResults.getString("tweet"));
				tweet.setUserId(tweetResults.getString("user_id"));
				tweets.add(tweet);
			}
		}catch(Exception exception) {
			System.out.println("Error while getting the tweets. Please try again!!");
		}
		return tweets;
	}
	
	public List<Tweet> getUserTweets() {
		Connection connection = TweetAppUtil.getConnection();
		List<Tweet> tweets = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(TweetAppSqlQueries.GET_ALL_TWEETS);
			ResultSet tweetResults = preparedStatement.executeQuery();
			while(tweetResults.next()) {
				Tweet tweet = new Tweet();
				tweet.setTweet(tweetResults.getString("tweet"));
				tweet.setUserId(tweetResults.getString("user_id"));
				tweets.add(tweet);
			}
		}catch(Exception exception) {
			System.out.println("Error while getting the tweets. Please try again!!");
		}
		return tweets;
	}
	
	public List<User> getUsers(){
		Connection connection = TweetAppUtil.getConnection();
		List<User> users = new ArrayList<>();
		try {
			PreparedStatement usersFetchQuery = connection.prepareStatement(TweetAppSqlQueries.GET_ALL_USERS);
			ResultSet userResults = usersFetchQuery.executeQuery();
			while(userResults.next()) {
				User user = new User();
				user.setUserId(userResults.getString("user_id"));
				user.setFirstName(userResults.getString("first_name"));
				user.setLastName(userResults.getString("last_name"));
				users.add(user);
			}
		}catch(Exception exception) {
			System.out.println("Error while getting the users. Please try again!!");
		}
		return users;
	}
	
}
