package com.nisum.nisumtest.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nisum.nisumtest.security.entity.Role;
import com.nisum.nisumtest.security.enums.RoleName;
import com.nisum.nisumtest.security.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Optional<Role> getByRoleName(RoleName roleName) {
		return roleRepository.findByRoleName(roleName);
	}
	
	public void createRole(Role role) {
		roleRepository.save(role);
	}

}
