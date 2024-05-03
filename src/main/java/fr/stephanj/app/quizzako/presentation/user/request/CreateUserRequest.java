package fr.stephanj.app.quizzako.presentation.user.request;

import jakarta.validation.constraints.Size;

public class CreateUserRequest extends AbstractUserRequest {

	@Size(min = 3, max = 32)
	private String password1;

	@Size(min = 3, max = 32)
	private String password2;

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
