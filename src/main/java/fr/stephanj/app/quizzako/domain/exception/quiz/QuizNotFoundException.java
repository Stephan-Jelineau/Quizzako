package fr.stephanj.app.quizzako.domain.exception.quiz;

public class QuizNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public QuizNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuizNotFoundException(String message) {
		super(message);
	}
}
