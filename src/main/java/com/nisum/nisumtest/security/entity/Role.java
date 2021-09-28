package com.nisum.nisumtest.security.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.nisum.nisumtest.security.enums.RoleName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	public Role(@NotNull RoleName roleName) {
		this.roleName = roleName;
	}

}
