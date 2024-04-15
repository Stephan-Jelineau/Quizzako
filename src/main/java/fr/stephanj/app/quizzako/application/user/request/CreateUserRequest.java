package fr.stephanj.app.quizzako.application.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {

	@NotBlank(message = "mandatory")
	@Size(max = 50)
	private String firstname;

	@NotBlank(message = "mandatory")
	@Size(max = 50)
	private String name;

	@NotBlank(message = "mandatory")
	@Size(min = 3, max = 32)
	private String password1;

	@NotBlank(message = "mandatory")
	@Size(min = 3, max = 32)
	private String password2;

	@NotNull
	@Size(max = 255)
	@Email
	private String email;

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

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getPassword1() {
		return password1;
	}

	public String getPassword2() {
		return password2;
	}

}
