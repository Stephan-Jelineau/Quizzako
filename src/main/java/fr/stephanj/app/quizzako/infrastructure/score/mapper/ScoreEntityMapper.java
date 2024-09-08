package fr.stephanj.app.quizzako.infrastructure.score.mapper;

import fr.stephanj.app.quizzako.domain.Score;
import fr.stephanj.app.quizzako.infrastructure.quiz.mapper.QuizEntityMapper;
import fr.stephanj.app.quizzako.infrastructure.score.entity.ScoreEntity;
import fr.stephanj.app.quizzako.infrastructure.user.mapper.UserEntityMapper;

public class ScoreEntityMapper {

	public static ScoreEntity toEntity(Score score) {
		return new ScoreEntity(score.getId(), UserEntityMapper.toEntity(score.getUser()),
				QuizEntityMapper.toEntity(score.getQuiz()), String.valueOf(score.getScore()), score.getSubmissionDate());
	}

}
