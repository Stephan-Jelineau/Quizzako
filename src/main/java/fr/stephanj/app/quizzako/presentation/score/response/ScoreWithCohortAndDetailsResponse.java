package fr.stephanj.app.quizzako.presentation.score.response;

import java.time.LocalDateTime;

public class ScoreWithCohortAndDetailsResponse {

	private Long quizId;
	private String quizName;
	private String score;
	private String cohortName;
	private int numberOfQuestion;
	private LocalDateTime assignedDate;

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCohortName() {
		return cohortName;
	}

	public void setCohortName(String cohortName) {
		this.cohortName = cohortName;
	}

	public int getNumberOfQuestion() {
		return numberOfQuestion;
	}

	public void setNumberOfQuestion(int numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}

	public LocalDateTime getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(LocalDateTime localDateTime) {
		this.assignedDate = localDateTime;
	}

}
