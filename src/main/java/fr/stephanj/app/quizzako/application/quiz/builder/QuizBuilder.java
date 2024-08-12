package fr.stephanj.app.quizzako.application.quiz.builder;

import java.util.List;

import fr.stephanj.app.quizzako.domain.Category;
import fr.stephanj.app.quizzako.domain.Question;
import fr.stephanj.app.quizzako.domain.Quiz;

public class QuizBuilder {
	public static Quiz build(String name, List<Question> questions, Category category) {
		return new Quiz(name, questions, category);
	}
}
