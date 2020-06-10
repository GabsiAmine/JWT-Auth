package org.bookstore.repository;

import org.bookstore.domain.security.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@Repository
public interface RoleRepository extends CrudRepository<Role,Long>{

}
