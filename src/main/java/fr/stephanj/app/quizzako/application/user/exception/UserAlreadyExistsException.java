package fr.stephanj.app.quizzako.application.user.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String string) {
		super(string);
	}

}
