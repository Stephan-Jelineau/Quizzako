package fr.stephanj.app.quizzako.domain.repository;

import java.util.List;

import fr.stephanj.app.quizzako.domain.Quiz;

public interface QuizRepository {
	List<Quiz> getNumberRequestedOfQuiz(int numberOfQuiz);

	void saveQuiz(Quiz quiz);

	void deleteQuizById(Long id);

	Quiz getById(Long id);

	boolean isOwnerDefined(Long id);

	List<Quiz> getQuizzesByOwnerId(Long userId);
}
