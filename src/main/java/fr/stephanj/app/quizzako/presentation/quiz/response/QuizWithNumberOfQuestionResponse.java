package fr.stephanj.app.quizzako.presentation.quiz.response;

public class QuizWithNumberOfQuestionResponse extends BasicQuizResponse {

	private int numberOfQuestion;
	private String creationDate;

	public QuizWithNumberOfQuestionResponse(Long id, String name, String categoryName, int numberOfQuestion,
			String creationDate) {
		super(id, name, categoryName);
		this.numberOfQuestion = numberOfQuestion;
		this.creationDate = creationDate;
	}

	public int getNumberOfQuestion() {
		return numberOfQuestion;
	}

	public void setNumberOfQuestion(int numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
}
