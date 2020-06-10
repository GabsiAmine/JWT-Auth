package org.bookstore.service;

import java.util.Set;

import org.bookstore.domain.User;
import org.bookstore.domain.security.UserRole;

public interface UserService {

	User createUser(User user , Set<UserRole> userRoles);
}
