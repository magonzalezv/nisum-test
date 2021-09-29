package com.nisum.nisumtest.security.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	private String email;

	@NotNull
	private String password;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	List<Phone> phones;
	
	@CreationTimestamp
	@Column(updatable = false)
	Timestamp created;

	@UpdateTimestamp
	Timestamp modified;
	
	@CreationTimestamp
	@Column(updatable = true)
	Timestamp lastLogin;
	
	boolean isActive;

	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User(@NotNull String name, @NotNull String email, @NotNull String password, List<Phone> phones, boolean isActive) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = phones;
		this.isActive = isActive;
	}
	
	

}
