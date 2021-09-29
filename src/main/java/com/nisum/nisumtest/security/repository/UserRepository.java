package com.nisum.nisumtest.security.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisum.nisumtest.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findById(UUID id);

	Optional<User> findByEmail(String email);

	void deleteById(UUID id);

	boolean existsByEmail(String email);
	
	boolean existsById(UUID id);


}
