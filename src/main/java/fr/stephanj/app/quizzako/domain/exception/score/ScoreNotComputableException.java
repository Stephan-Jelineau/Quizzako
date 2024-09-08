package fr.stephanj.app.quizzako.domain.exception.score;

public class ScoreNotComputableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ScoreNotComputableException(String message) {
		super(message);
	}
}
