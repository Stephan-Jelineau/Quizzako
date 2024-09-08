package fr.stephanj.app.quizzako.domain.exception.quiz;

public class QuizNotAllowedAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QuizNotAllowedAccessException(String message) {
		super(message);
	}
}
