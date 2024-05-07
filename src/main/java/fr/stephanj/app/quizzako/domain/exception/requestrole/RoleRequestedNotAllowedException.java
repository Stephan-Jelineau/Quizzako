package fr.stephanj.app.quizzako.domain.exception.requestrole;

public class RoleRequestedNotAllowedException extends RuntimeException {

	public RoleRequestedNotAllowedException(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;

}
