package org.bookstore.config;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtility {

private static final String SALT = "salt";//salt should be protected carfelly like "ehiusdf!:ù*$^dg!ù*54"
	
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12,new SecureRandom(SALT.getBytes()));
	}
	
	@Bean
	public static String randomPassword() {
		
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while(salt.length()<18) {
			
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltstr = salt.toString();
		return saltstr;
	}
}
