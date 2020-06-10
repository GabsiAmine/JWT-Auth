package org.bookstore.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bookstore.config.JWTUtility;
import org.bookstore.domain.User;
import org.bookstore.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTFilter extends OncePerRequestFilter{

	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private UserSecurityService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorizationheader = request.getHeader("Authorization");
		String token = null;
		String username = null;
///// added for cros origin probelem in angular 
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers",
        "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
		// Bearer
		// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU4NjUwNDc0MSwiaWF0IjoxNTg2NDY4NzQxfQ.fWX-_G7IcSBNBhCveZEt998AiTBYY6tI9W6mG63ymCY
		if (authorizationheader != null && authorizationheader.startsWith("Bearer ")) {
			token = authorizationheader.substring(7);
			username = jwtUtility.getUserNameFromJwtToken(token);
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			User user = (User) userService.loadUserByUsername(username);

			if (jwtUtility.validateJwtToken(token)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						user, null, user.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
		}
		filterChain.doFilter(request, response);
	}
}
