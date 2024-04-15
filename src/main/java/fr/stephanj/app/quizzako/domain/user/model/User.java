package fr.stephanj.app.quizzako.domain.user.model;

import java.util.Objects;

import fr.stephanj.app.quizzako.domain.user.service.EncryptionService;

public class User {

	private Long id;
	private String firstname;
	private String name;
	private String email;
	private String password;
	private Role role;

	public User(String firstName, String name, String email, String password, Role role) {
		validateData(firstName, name, email, password, role);
		this.firstname = firstName;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User(Long id, String firstName, String name, String email, String password, Role role) {
		validateData(id, firstName, name, email, password, role);
		this.id = id;
		this.firstname = firstName;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	private void validateData(Long id, String firstName, String name, String email, String password, Role role) {
		Objects.requireNonNull(id, "User with null data not allowed");
		validateData(firstName, name, email, password, role);
	}

	private void validateData(String firstName, String name, String email, String password, Role role) {
		Objects.requireNonNull(firstName, "User with null firstName not allowed");
		Objects.requireNonNull(name, "User with null name not allowed");
		Objects.requireNonNull(email, "User with null email not allowed");
		Objects.requireNonNull(password, "User with null password not allowed");
		Objects.requireNonNull(role, "User with null role not allowed");
	}

	public void encryptPassword(EncryptionService encryptionService) {
		Objects.requireNonNull(encryptionService, "EncryptionService is null");
		this.password = encryptionService.encode(password);
	}

	public String getFirstname() {
		return firstname;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}

	public Long getId() {
		return id;
	}
}
