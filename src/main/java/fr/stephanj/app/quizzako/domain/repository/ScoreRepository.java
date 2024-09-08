package fr.stephanj.app.quizzako.domain.repository;

import fr.stephanj.app.quizzako.domain.Score;

public interface ScoreRepository {

	void saveScore(Score score);

	Boolean existByQuizIdAndUserID(Long quizId, Long userId);

}
