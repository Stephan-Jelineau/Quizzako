package fr.stephanj.app.quizzako.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class Question {

	public static final String GOOD_ANSWER = "goodAnswer";

	private Long id;
	private String question;
	private Map<String, String> answers;

	public Question(Long id, String question, Map<String, String> answers) {
		validateData(id, question, answers);
		this.id = id;
		this.question = question;
		this.answers = answers;
	}

	public Question(String question, Map<String, String> answers) {
		validateData(question, answers);
		this.question = question;
		this.answers = answers;
	}

	private void validateData(Long id, String question, Map<String, String> answers) {
		Objects.requireNonNull(id, "Question with null id not allowed");
		validateData(question, answers);
	}

	private void validateData(String question, Map<String, String> answers) {
		Objects.requireNonNull(question, "Question with null question not allowed");
		Objects.requireNonNull(answers, "Question with null answers not allowed");
		validateAnswers(answers);
	}

	private void validateAnswers(Map<String, String> answers) {
		if (answers.size() < 2)
			throw new IllegalArgumentException("Question must have at least two answers");
		validateGoodAnswer(answers);
	}

	private void validateGoodAnswer(Map<String, String> answers) {
		if (!answers.containsKey(GOOD_ANSWER))
			throw new IllegalArgumentException("Missing \"" + GOOD_ANSWER + "\" key in answer Map");
	}

	public String getQuestion() {
		return question;
	}

	public void updateQuestion(String question) {
		Objects.requireNonNull(question, "Question with null question not allowed");
		this.question = question;
	}

	public Map<String, String> getAnswers() {
		return Collections.unmodifiableMap(answers);
	}

	public void updateAnswers(Map<String, String> answers) {
		validateAnswers(answers);
		this.answers = answers;
	}

	public Long getId() {
		return id;
	}
}
