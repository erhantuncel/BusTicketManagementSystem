package com.erhan.busticket.utilities;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodedPasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String encodedPassword = passwordEncoder.encode("1234");
		System.out.println("obsProject => " + encodedPassword);
		
		EncodedPasswordGenerator passGenerator = new EncodedPasswordGenerator();
		String password = passGenerator.generateRandomPassword();
		System.out.println(password + " => " + passwordEncoder.encode(password));
		
//		System.out.println("\n\n=============================================================\n\n");
//		
//		List<String> passwordList = new ArrayList<String>(10);
//		for(int i=0; i<500; i++) {
//			passwordList.add(passwordEncoder.encode(passGenerator.generateRandomPassword()));
////			System.out.println(passwordEncoder.encode(passGenerator.generateRandomPassword()));
//		}
//		System.out.println("Password list created");
//		
//		File file = new File(ClassLoader.getSystemResource(".").getFile() + "test.txt");
//		try {
//			if(file.createNewFile()) {
//				System.out.println("File created");
//			} else {
//				System.out.println("File not created");
//			}
//			PrintWriter pw = new PrintWriter(file);
//			for(String passwordString : passwordList) {
//				pw.println(passwordString);
//			}
//			pw.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		System.out.println(passwordList.size() + " password saved to " + file.getAbsolutePath()); 
		
	}

	private String generateRandomPassword() {
		String saltChars = "abcdefghijklmanoprstuvyz";
		StringBuilder sb = new StringBuilder();

		sb.append(randBetween(0, 9));
		while (sb.length() < 8) {
			int index = (int) (Math.random() * randBetween(0, 10));
			sb.append(saltChars.charAt(index));
		}
		String pass = sb.toString();
		return pass;
	}
	
	private int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
}
