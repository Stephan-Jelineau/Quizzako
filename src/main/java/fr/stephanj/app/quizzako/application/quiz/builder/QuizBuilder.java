package fr.stephanj.app.quizzako.application.quiz.builder;

import java.util.List;

import fr.stephanj.app.quizzako.domain.Category;
import fr.stephanj.app.quizzako.domain.Question;
import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.User;

public class QuizBuilder {
	public static Quiz build(String name, User owner,List<Question> questions, Category category) {
		return new Quiz(name, owner, questions, category);
	}
}
