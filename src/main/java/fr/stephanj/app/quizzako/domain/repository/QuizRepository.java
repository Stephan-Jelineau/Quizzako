package fr.stephanj.app.quizzako.domain.repository;

import java.util.List;

import fr.stephanj.app.quizzako.domain.Quiz;

public interface QuizRepository {
	List<Quiz> getNumberRequestedOfQuiz(int numberOfQuiz);

	void saveQuiz(Quiz quiz);

	void deleteQuizById(Long id);

	Quiz getById(Long id);

	boolean isOwnerAdmin(Long id);

	List<Quiz> getQuizzesByOwnerId(Long userId);

	Quiz getQuizByIdOwnedByUserId(Long idQuiz, Long idUser);

	List<Quiz> getAssignedQuizzesByCohortId(Long id);

	List<Quiz> getAssignedQuizzesByUserId(Long userId);

	List<Long> getQuizzesIdAssignedByCohortId(Long cohortId);

	boolean isOwner(Long userId, Long quizId);

	void deleteAllQuizAssignedToCohort(Long cohortId);

	void assignQuizToCohort(Long quizId, Long cohortId);
}
