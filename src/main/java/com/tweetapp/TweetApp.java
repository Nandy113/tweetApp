package com.tweetapp;

import com.tweetapp.handler.TweetAppInputHandler;

/**
 * @author Nandhini
 *
 */
public class TweetApp {
	
	private static TweetAppInputHandler inputHandler = new TweetAppInputHandler();
		
    public static void main(String[] args){
    	System.out.println("Tweet App");
        inputHandler.handleInputOnState();
    }
    
}
