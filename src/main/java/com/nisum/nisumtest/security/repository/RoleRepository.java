package com.nisum.nisumtest.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisum.nisumtest.security.entity.Role;
import com.nisum.nisumtest.security.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByRoleName(RoleName roleName);

}
