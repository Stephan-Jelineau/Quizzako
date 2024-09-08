package fr.stephanj.app.quizzako.domain.exception.cohort;

public class CohortNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CohortNotFoundException(String message) {
		super(message);
	}

}
