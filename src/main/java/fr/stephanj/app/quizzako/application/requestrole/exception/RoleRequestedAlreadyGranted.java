package fr.stephanj.app.quizzako.application.requestrole.exception;

public class RoleRequestedAlreadyGranted extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RoleRequestedAlreadyGranted(String string) {
		super(string);
	}
}
