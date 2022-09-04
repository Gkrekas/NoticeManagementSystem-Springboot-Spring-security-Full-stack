package com.example.notice.entity;

import javax.persistence.*;

@Entity
@Table(name="user")
public class UserDtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="full_name", nullable = false)
	private String name;
	@Column(name="email",nullable = false)
	private String email;
	@Column(name="password",nullable = false)
	private String password;
	@Column(name="about",nullable = false)
	private String about;
	@Column(name="role", nullable = false)
	private String role;
	
	public UserDtls() {}

	public UserDtls(String name, String email, String password, String about, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
