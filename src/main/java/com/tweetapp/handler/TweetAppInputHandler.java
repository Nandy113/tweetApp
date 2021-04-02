package com.tweetapp.handler;

import com.tweetapp.handler.impl.LoggedInInputHandler;
import com.tweetapp.handler.impl.LoggedOutInputHandler;

public class TweetAppInputHandler {

	protected static String userId = null;
	
	private static LoggedInInputHandler loggedInInputHandler = new LoggedInInputHandler();
	
	private static LoggedOutInputHandler loggedOutInputHandler = new LoggedOutInputHandler();
	
	public void handleInputOnState() {
		while(true) {
			if(userId != null) {
				loggedInInputHandler.handleInput();
			}else {
				loggedOutInputHandler.handleInput();
			}
		}
	}
	
	public static void stopApp() {
		System.out.println("Application Stopped!!");
		System.exit(0);
	}
}
