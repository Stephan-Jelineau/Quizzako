package fr.stephanj.app.quizzako.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Quiz {

	private Long id;
	private String name;
	private User owner;
	private LocalDate creationDate;
	private List<Question> questions;
	private Category category;

	public Quiz(Long id, String name, User owner, LocalDate creationDate, List<Question> questions,
			Category category) {
		validateData(id, name, creationDate, questions);
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.creationDate = creationDate;
		this.questions = questions;
		this.category = category;
	}

	public Quiz(String name, List<Question> questions, Category category) {
		validateData(name, questions);
		this.name = name;
		this.creationDate = LocalDate.now();
		this.questions = questions;
		this.category = category;
	}

	private void validateData(Long id, String name, LocalDate creationDate, List<Question> questions) {
		Objects.requireNonNull(id, "Questionnaire with null id not allowed");
		validateData(name, questions);
		Objects.requireNonNull(creationDate, "Questionnaire with null creationDate not allowed");
	}

	private void validateData(String name, List<Question> questions) {
		Objects.requireNonNull(name, "Questionnaire with null name not allowed");
		validateQuestions(questions);
	}

	private void validateQuestions(List<Question> questions) {
		Objects.requireNonNull(questions, "Questionnaire with null questions not allowed");
		if (questions.isEmpty())
			throw new IllegalArgumentException("Questionnaire must have at least one Question");
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public User getOwner() {
		return owner;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public List<Question> getQuestions() {
		return Collections.unmodifiableList(questions);
	}

	public Category getCategory() {
		return category;
	}

	public void updateName(String name) {
		Objects.requireNonNull(name, "Questionnaire with null name not allowed");
		this.name = name;
	}

	public void updateQuestions(List<Question> questions) {
		validateQuestions(questions);
		this.questions = questions;
	}

	public void updateCategory(Category category) {
		Objects.requireNonNull(name, "Questionnaire with null category not allowed");
		this.category = category;
	}

}
