package com.nisum.nisumtest.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "User")
public class User2 {

	@Id
	UUID id = UUID.randomUUID();

	String name;
	
	String email;

	String password;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	List<Phone2> phone;

	@CreationTimestamp
	@Column(updatable = false)
	Timestamp dateCreated;

	@UpdateTimestamp
	Timestamp lastModified;
}
