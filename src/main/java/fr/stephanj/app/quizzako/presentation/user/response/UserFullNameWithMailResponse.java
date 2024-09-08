package fr.stephanj.app.quizzako.presentation.user.response;

public class UserFullNameWithMailResponse extends BasicUserFullNameResponse {

	private String mail;

	public UserFullNameWithMailResponse(String firstname, String name, String mail) {
		super(firstname, name);
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
