package fr.stephanj.app.quizzako.domain.exception.score;

public class ScoreNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ScoreNotFoundException(String message) {
		super(message);
	}

}
