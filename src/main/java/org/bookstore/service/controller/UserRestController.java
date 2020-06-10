package org.bookstore.service.controller;

import org.bookstore.config.JWTUtility;
import org.bookstore.config.JwtResponse;
import org.bookstore.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*") 
@RestController
public class UserRestController {
	
	public String jwt = "";
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/welcome")
	public String getUserLoginPage() {
		return "You Welcome";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> generateToken(@RequestBody User user) throws Exception {
		
		try {
			 Authentication authentication =
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
				);
			 jwt = jwtUtility.generateToken(authentication);		

		}catch (Exception e) {
			throw new Exception("Invalide username/password");
		}
		
		return ResponseEntity.ok(new JwtResponse(jwt));

	}
}
