package fr.stephanj.app.quizzako.infrastructure.score.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stephanj.app.quizzako.infrastructure.score.entity.ScoreEntity;

public interface JpaScoreRepository extends JpaRepository<ScoreEntity, Long> {
	Optional<ScoreEntity> findByQuizIdAndUserId(Long quizId, Long userId);

	Boolean existsByQuizIdAndUserId(Long quizId, Long userId);
}
