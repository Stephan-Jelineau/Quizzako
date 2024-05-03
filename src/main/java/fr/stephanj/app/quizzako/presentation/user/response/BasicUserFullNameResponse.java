package fr.stephanj.app.quizzako.presentation.user.response;

public class BasicUserFullNameResponse {

	private String firstname;
	private String name;

	public BasicUserFullNameResponse(String firstname, String name) {
		this.firstname = firstname;
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getName() {
		return name;
	}
}
