package fr.stephanj.app.quizzako.infrastructure.quiz.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.stephanj.app.quizzako.infrastructure.quiz.entity.QuizEntity;

public interface JpaQuizRepository extends JpaRepository<QuizEntity, Long> {

	@Query(value = "SELECT * FROM quiz WHERE owner_id = :adminId ORDER BY RAND() LIMIT :numberOfQuizz", nativeQuery = true)
	List<QuizEntity> findRandomQuizzes(@Param("adminId") Long adminId, @Param("numberOfQuizz") int numberOfQuizz);

	@Query(value = "SELECT * FROM quiz WHERE owner_id = :id", nativeQuery = true)
	List<QuizEntity> findQuizzesByOwner(@Param("id") Long id);

	@Query(value = "SELECT * FROM quiz WHERE owner_id = :userId AND id = :quizId", nativeQuery = true)
	Optional<QuizEntity> findQuizByIdWithOwnerId(@Param("quizId") Long quizId, @Param("userId") Long userId);

	@Query(value = "SELECT q.* FROM quiz q JOIN cohort_quiz cq ON q.id = cq.quiz_id WHERE cq.cohort_id = :cohortId", nativeQuery = true)
	List<QuizEntity> findAllQuizByCohortId(@Param("cohortId") Long cohortId);

	@Query(value = "SELECT q.* FROM quiz q JOIN cohort_quiz cq ON q.id = cq.quiz_id JOIN cohort_user cu ON cu.cohort_id = cq.cohort_id WHERE cu.user_id = :userId", nativeQuery = true)
	List<QuizEntity> findAllAssignedQuizByUserId(@Param("userId") Long userId);

}
