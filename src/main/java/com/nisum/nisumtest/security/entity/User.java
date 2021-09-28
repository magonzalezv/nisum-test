package com.nisum.nisumtest.security.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

	@Id
	private UUID id = UUID.randomUUID();

	@NotNull
	private String name;

	@NotNull
	@Column(unique = true)
	private String userName;

	@NotNull
	private String email;

	@NotNull
	private String password;

	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User(@NotNull String name, @NotNull String userName, @NotNull String email, @NotNull String password) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

}
