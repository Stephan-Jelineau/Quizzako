package fr.stephanj.app.quizzako.infrastructure.score.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.stephanj.app.quizzako.domain.Score;
import fr.stephanj.app.quizzako.domain.repository.ScoreRepository;
import fr.stephanj.app.quizzako.infrastructure.score.entity.ScoreEntity;
import fr.stephanj.app.quizzako.infrastructure.score.mapper.ScoreEntityMapper;

public class ScoreRepositoryImpl implements ScoreRepository {

	@Autowired
	JpaScoreRepository jpaScoreRepo;

	@Override
	public void saveScore(Score score) {
		Optional<ScoreEntity> existingScore = jpaScoreRepo.findByQuizIdAndUserId(score.getQuiz().getId(),
				score.getUser().getId());

		if (existingScore.isPresent()) {
			ScoreEntity scoreEntity = existingScore.get();
			scoreEntity.setScore(String.valueOf(score.getScore()));
			scoreEntity.setSubmissionDate(score.getSubmissionDate());
			jpaScoreRepo.save(scoreEntity);
		} else {
			jpaScoreRepo.save(ScoreEntityMapper.toEntity(score));
		}
	}

	@Override
	public Boolean existByQuizIdAndUserID(Long quizId, Long userId) {
		return jpaScoreRepo.existsByQuizIdAndUserId(quizId, userId);
	}

}
