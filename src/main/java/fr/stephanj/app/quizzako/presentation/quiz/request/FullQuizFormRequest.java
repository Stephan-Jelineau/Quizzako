package fr.stephanj.app.quizzako.presentation.quiz.request;

import java.util.List;

import fr.stephanj.app.quizzako.presentation.question.request.QuestionWithGoodAnswerFormRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FullQuizFormRequest {
	
	@NotNull
	private Long id;
	
	@NotBlank(message="Empty quiz name forbidden")
	private String name;
	
	@NotBlank(message="Empty category name forbidden")
	private String categoryName;
	
	@Valid
	@Size(min = 1, message = "Minimum 1 question is required")
	private List<QuestionWithGoodAnswerFormRequest> questions;
	
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
	public List<QuestionWithGoodAnswerFormRequest> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionWithGoodAnswerFormRequest> questions) {
		this.questions = questions;
	}
}
