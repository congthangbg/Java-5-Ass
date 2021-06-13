package com.vn.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Integer id;
		@Column(name = "username")
		private String username;
		@Column(name = "password")
		private String password;
		@Column(name = "email")
		private String email;
		@Column(name = "photo")
		private String photo;
		@Column(name = "activated")
		private Integer activated;
		@Column(name = "admin")
		private Integer admin;
		
		@JsonIgnore
		@OneToMany(mappedBy = "account")
		List<Order> orders;


}
