package com.nisum.nisumtest.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nisum.nisumtest.security.entity.Role;
import com.nisum.nisumtest.security.enums.RoleName;
import com.nisum.nisumtest.security.service.RoleService;

@Component
public class CreateRoles implements CommandLineRunner {

	@Autowired
	RoleService roleService;
	
	@Override
	public void run(String... args) throws Exception {
		Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
		Role roleUser = new Role(RoleName.ROLE_USER);
		
		roleService.createRole(roleUser);
		roleService.createRole(roleAdmin);


		
	}

}
