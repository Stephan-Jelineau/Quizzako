package fr.stephanj.app.quizzako.domain.exception.requestrole;

public class RequestRoleNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public RequestRoleNotFoundException(String string) {
		super(string);
	}

}
