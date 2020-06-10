package org.bookstore.repository;

import java.util.List;

import org.bookstore.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsername(String username);	
	User findByEmail(String email);
	List<User> findAll();
	
}
