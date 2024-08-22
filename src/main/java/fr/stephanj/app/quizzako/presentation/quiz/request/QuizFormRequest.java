package fr.stephanj.app.quizzako.presentation.quiz.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class QuizFormRequest {

	@NotNull
	private Long id;
	
	@NotEmpty
	private String name;

	@NotEmpty
	private String categoryName;

	@NotEmpty
	@Valid
	private List<@Valid QuestionAnswersFormRequest> questions;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<QuestionAnswersFormRequest> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionAnswersFormRequest> questions) {
		this.questions = questions;
	}
}
