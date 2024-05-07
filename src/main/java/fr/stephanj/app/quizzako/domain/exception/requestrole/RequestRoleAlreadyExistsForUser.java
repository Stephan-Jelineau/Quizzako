package fr.stephanj.app.quizzako.domain.exception.requestrole;

public class RequestRoleAlreadyExistsForUser extends RuntimeException {

	public RequestRoleAlreadyExistsForUser(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;

}
