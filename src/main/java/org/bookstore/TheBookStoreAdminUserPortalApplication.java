package org.bookstore;
import java.util.HashSet;
import java.util.Set;

import org.bookstore.config.SecurityUtility;
import org.bookstore.domain.User;
import org.bookstore.domain.security.Role;
import org.bookstore.domain.security.UserRole;
import org.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheBookStoreAdminUserPortalApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userservice;
	
	public static void main(String[] args) {
		SpringApplication.run(TheBookStoreAdminUserPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		 
		
		User user1 = new User();
		user1.setFirstname("amine");
		user1.setLastname("gabsi");
		user1.setUsername("amine");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("amine@gmail.com");
		Set<UserRole> userRoles= new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1L);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1,role1));
		
		userservice.createUser(user1,userRoles);
		
		userRoles.clear();
		
		User user2 = new User();
		user2.setFirstname("admin");
		user2.setLastname("admin");
		user2.setUsername("admin");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user2.setEmail("adams@gmail.com");
		Role role2 = new Role();
		role2.setRoleId(2L);
		role2.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user2,role2));
		
		userservice.createUser(user2,userRoles);
	}

}
