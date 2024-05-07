package fr.stephanj.app.quizzako.domain;

import java.util.Objects;

public final class User {

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
		Objects.requireNonNull(id, "User with null id not allowed");
		validateData(firstName, name, email, password, role);
	}

	private void validateData(String firstName, String name, String email, String password, Role role) {
		validateData(firstName, name, email);
		Objects.requireNonNull(password, "User with null password not allowed");
		Objects.requireNonNull(role, "User with null role not allowed");
	}

	private void validateData(String firstName, String name, String email) {
		validateData(firstName, name);
		Objects.requireNonNull(email, "User with null email not allowed");
	}

	private void validateData(String firstname, String name) {
		Objects.requireNonNull(firstname, "User with null firstName not allowed");
		Objects.requireNonNull(name, "User with null name not allowed");
	}

	public void updateRole(Role role) {
		Objects.requireNonNull(role, "User with null role not allowed");
		this.role = role;
	}

	public void updateNames(String firstname, String name) {
		validateData(firstname, name);
		this.firstname = firstname;
		this.name = name;
	}

	public void updateNamesAndMail(String firstname, String name, String email) {
		validateData(firstname, name, email);
		this.firstname = firstname;
		this.name = name;
		this.email = email;
	}

	public String shortDisplay() {
		return name + " " + firstname;
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
