package fr.stephanj.app.quizzako.infrastructure.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(unique = true)
	@Email
	private String email;

	@NotBlank
	private String firstname;

	@NotBlank
	private String name;

	@NotBlank
	private String password;

	@NotBlank
	private String role;

	public UserEntity() {

	}

	public UserEntity(Long id, @NotBlank @Email String email, @NotBlank String firstname, @NotBlank String name,
			@NotBlank String password, @NotBlank String role) {
		this.id = id;
		this.email = email;
		this.firstname = firstname;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

}
