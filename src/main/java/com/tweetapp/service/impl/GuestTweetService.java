package com.tweetapp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.tweetapp.beans.User;
import com.tweetapp.constants.TweetAppConstants;
import com.tweetapp.dao.LoggedOutDao;

public class GuestTweetService{
	
	private static LoggedOutDao loggedOutDao = new LoggedOutDao();
	
	public void register() {
		Scanner scanner = new Scanner(System.in);
		User user = new User();
		System.out.print("Enter your email id. This will be the user id for your account ");
		String emailId = scanner.next();
		user.setUserId(emailId);
		while(!validateEmail(emailId)) {
			System.out.print("Invalid email. Please enter a valid email id ");
			emailId = scanner.next();
		}
		scanner.nextLine();
		System.out.print("Enter your first name ");
		String firstName = scanner.nextLine();
		user.setFirstName(firstName);
		System.out.print("Enter your last name ");
		String lastName = scanner.nextLine();
		user.setLastName(lastName);
		
		System.out.print("Enter your Mobile Number ");
		String mobileNumber = null;
		do{
			mobileNumber = scanner.next();
			if(mobileNumber.length()!=10) {
				System.out.print("Mobile Number should be of 10 characters");
			}
		} while(mobileNumber.length()!=10);
		
		scanner.nextLine();
		user.setMobileNumber(mobileNumber);
		
		System.out.print("Enter your social media url");
		String url = scanner.nextLine();
		user.setUrl(url);
		
		System.out.print("Enter your date of birth (dd/MM/yyyy) ");
		Date dobDate = null;
		while(dobDate == null){
			String dob = scanner.next();
			dobDate = validateDOBAndReturn(dob);
			scanner.nextLine();
		}
		user.setDob(dobDate);
		System.out.print("Enter your gender (M/F) ");
		String gender = null;
		do{
			gender = scanner.next();
			if(gender == null || (gender.length()!=1  && !Arrays.asList("M","F").contains(gender))) {
				System.out.print("Invalid gender entry, please try again");
			}
		} while(gender == null || (gender.length()!=1  && !Arrays.asList("M","F").contains(gender)));
		scanner.nextLine();
		user.setGender(gender);
		String password = getPassword(scanner);
		user.setPassword(password);
		loggedOutDao.register(user);
	}

	private String getPassword(Scanner scanner) {
		String password = null;
		boolean passwordMatch = false;
		while(!passwordMatch) {
			System.out.print("Enter the password for the account ");
			password = scanner.nextLine();
			System.out.print("Confirm password ");
			String confirmPassword = scanner.nextLine();
			passwordMatch = password != null && confirmPassword != null && password.equals(confirmPassword);
			if(!passwordMatch) {
				System.out.println("Passwords do not match");
			}
		}
		return password;
	}
	
	public String login() {
		String userId = null;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter user id ");
		String loginUserId = scanner.next();
		scanner.nextLine();
		System.out.print("Enter password ");
		String password = scanner.nextLine();
		if(loggedOutDao.isValidUser(loginUserId, password)) {
			userId = loginUserId;
			loggedOutDao.updateLoginStatus(loginUserId, true);
		}else {
			System.out.println("Invalid login. Please try again");
		}
//		scanner.close();
		return userId;
	}
	
	public void resetPassword() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter user id ");
		String userId = scanner.next();
		System.out.print("Enter your date of birth (dd/MM/yyyy) ");
		Date dobDate = null;
		while(dobDate == null){
			String dob = scanner.next();
			dobDate = validateDOBAndReturn(dob);
			scanner.nextLine();
		}
		boolean isValidUser = loggedOutDao.isValidUser(userId, dobDate);
		if(isValidUser) {
			String password = getPassword(scanner);
			loggedOutDao.updatePassword(userId, password);
		}else {
			System.out.println("Verification failed. Try again!!");
		}
	}
	
	public Date validateDOBAndReturn(String dob) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = simpleDateFormat.parse(dob);
			return date;
		}catch(ParseException exception) {
			System.out.println("Invalid date format. Please try again in dd/MM/yyyy format");
			return null;
		}
	}
	
	public boolean validateEmail(String email) {
		Pattern emailPattern = Pattern.compile(TweetAppConstants.EMAIL_REGEX);
		return emailPattern.matcher(email).matches();
	}

}
