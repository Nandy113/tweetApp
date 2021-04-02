package com.tweetapp.handler.impl;

import java.util.Scanner;

import com.tweetapp.constants.TweetAppConstants;
import com.tweetapp.handler.TweetAppInputHandler;
import com.tweetapp.service.impl.LoggedInTweetService;

public class LoggedInInputHandler extends TweetAppInputHandler {
	
	private static LoggedInTweetService loggedInTweetService = new LoggedInTweetService();

	public void handleInput() {
		System.out.print("\n");
		TweetAppConstants.LOGGED_IN_OPTIONS.stream().forEach(option -> {
			System.out.println(option);
		});
		System.out.print("Enter your choice ");
		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		System.out.print("\n");
		invokeFunction(option);
		scanner.nextLine();
	}
	
	public void invokeFunction(int option) {
		switch(option) {
			case TweetAppConstants.ONE:
				loggedInTweetService.postTweet(TweetAppInputHandler.userId);
				break;
			case TweetAppConstants.TWO:
				loggedInTweetService.listUserTweets(TweetAppInputHandler.userId);
				break;
			case TweetAppConstants.THREE:
				loggedInTweetService.listUserTweets();
				break;
			case TweetAppConstants.FOUR:
				loggedInTweetService.listUsers();
				break;
			case TweetAppConstants.FIVE:
				loggedInTweetService.resetPassword(TweetAppInputHandler.userId);
				break;
			case TweetAppConstants.SIX:
				loggedInTweetService.logOff(TweetAppInputHandler.userId);
				TweetAppInputHandler.userId = null;
				break;
			case TweetAppConstants.SEVEN:
				TweetAppInputHandler.stopApp();
				break;
			default:
				System.out.println("Invalid option. Please try again");
		}
	}
}
