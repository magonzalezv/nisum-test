package com.nisum.nisumtest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nisum.nisumtest.model.User2;

@Repository
public interface UserRepository2 extends CrudRepository<User2, Long> {
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
	public boolean existsByEmail(@Param("email") String email);
	
	public User2 findByEmail(String email);

}
