package fr.stephanj.app.quizzako.presentation.user.request;

public class ViewAndUpdateUserRequest extends AbstractUserRequest {

	public ViewAndUpdateUserRequest() {
	}

	public ViewAndUpdateUserRequest(String firstname, String name, String email) {
		this.firstname = firstname;
		this.name = name;
		this.email = email;
	}
}
