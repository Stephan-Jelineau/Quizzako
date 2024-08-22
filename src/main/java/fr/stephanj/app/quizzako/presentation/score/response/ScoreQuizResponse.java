package fr.stephanj.app.quizzako.presentation.score.response;

public class ScoreQuizResponse {
	
	private String quizName;
	private String score;
	
	public ScoreQuizResponse(String quizName, String score) {
		this.quizName = quizName;
		this.score = score;
	}

	public String getQuizName() {
		return quizName;
	}

	public String getScore() {
		return score;
	}
}
