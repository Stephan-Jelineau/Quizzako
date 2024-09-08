package fr.stephanj.app.quizzako.domain.exception.user;

public final class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String string) {
		super(string);
	}

}
