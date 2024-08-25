package fr.stephanj.app.quizzako.infrastructure.quiz.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.stephanj.app.quizzako.infrastructure.quiz.entity.QuizEntity;

public interface JpaQuizRepository extends JpaRepository<QuizEntity, Long> {
	
	@Query(value = "SELECT * FROM quiz ORDER BY RAND() LIMIT :numberOfQuizz", nativeQuery = true)
    List<QuizEntity> findRandomQuizzes(@Param("numberOfQuizz") int numberOfQuizz);
	
	@Query(value = "SELECT * FROM quiz WHERE owner_id = :id", nativeQuery = true)
	List<QuizEntity> findQuizzesByOwner (@Param("id") Long id);
}
