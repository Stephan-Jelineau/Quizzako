package fr.stephanj.app.quizzako.presentation.question.request;

import java.util.Map;

import fr.stephanj.app.quizzako.presentation.question.common.ContainsGoodAnswer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class QuestionWithGoodAnswerFormRequest {

	private Long id;

	@NotBlank(message = "Define a question name ")
	private String question;

	@Size(min = 1, message = "Minimum 3 answers are required")
	@ContainsGoodAnswer
	private Map<@NotBlank(message = "Name of answer cannot be empty") String, @NotBlank(message = "Answer cannot be empty") String> answers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Map<String, String> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<String, String> answers) {
		this.answers = answers;
	}
}
