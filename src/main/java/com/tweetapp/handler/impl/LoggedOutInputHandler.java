package com.tweetapp.handler.impl;

import java.util.Scanner;

import com.tweetapp.constants.TweetAppConstants;
import com.tweetapp.handler.TweetAppInputHandler;
import com.tweetapp.service.impl.GuestTweetService;

public class LoggedOutInputHandler extends TweetAppInputHandler{
	
	private static GuestTweetService guestTweetService = new GuestTweetService();

	public void handleInput() {
		System.out.print("\n");
		TweetAppConstants.NON_LOGGED_IN_OPTIONS.stream().forEach(option -> {
			System.out.println(option);
		});
		System.out.print("Enter your choice ");
		Scanner scanner = new Scanner(System.in);
		scanner.reset();
		int option = scanner.nextInt();
		scanner.nextLine();
		System.out.print("\n");
		invokeFunction(option);
	}
	
	private void invokeFunction(int option) {
		switch(option) {
		case TweetAppConstants.ONE:
			guestTweetService.register();
			break;
		case TweetAppConstants.TWO:
			TweetAppInputHandler.userId = guestTweetService.login();
			break;
		case TweetAppConstants.THREE:
			guestTweetService.resetPassword();
			break;
		case TweetAppConstants.FOUR:
			TweetAppInputHandler.stopApp();
			break;	
		default:
			System.out.println("Invalid option. Please try again");	
		}
	}

}
