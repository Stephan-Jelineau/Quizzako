package fr.stephanj.app.quizzako.presentation.cohort.response;

import java.util.List;

import fr.stephanj.app.quizzako.presentation.quiz.response.QuizToAssignResponse;

public class CohortQuizReponse {
	private List<QuizToAssignResponse> quizzes;
	private List<Long> ids;
	private Long cohortId;
	private String cohortName;

	public List<QuizToAssignResponse> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<QuizToAssignResponse> quizzes) {
		this.quizzes = quizzes;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Long getCohortId() {
		return cohortId;
	}

	public void setCohortId(Long cohortId) {
		this.cohortId = cohortId;
	}

	public String getCohortName() {
		return cohortName;
	}

	public void setCohortName(String cohortName) {
		this.cohortName = cohortName;
	}

}
