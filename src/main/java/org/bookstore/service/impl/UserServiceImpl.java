package org.bookstore.service.impl;

import java.util.Set;

import javax.transaction.Transactional;

import org.bookstore.domain.User;
import org.bookstore.domain.security.UserRole;
import org.bookstore.repository.RoleRepository;
import org.bookstore.repository.UserRepository;
import org.bookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) {

		User localUser = userRepository.findByUsername(user.getUsername());
		if(localUser != null) {
			LOG.info("User with username{} already exist. Nothing wil be done",user.getUsername());
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
		}else {
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			localUser = userRepository.save(user);
		}
		return localUser;
	}

	
}
