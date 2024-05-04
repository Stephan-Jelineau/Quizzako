package fr.stephanj.app.quizzako.presentation.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public abstract class AbstractUserRequest {
	@NotBlank(message = "Mandatory")
	@Size(max = 50)
	protected String firstname;

	@NotBlank(message = "Mandatory")
	@Size(max = 50)
	protected String name;

	@NotBlank(message = "Mandatory")
	@Size(max = 255)
	@Email
	protected String email;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

}
