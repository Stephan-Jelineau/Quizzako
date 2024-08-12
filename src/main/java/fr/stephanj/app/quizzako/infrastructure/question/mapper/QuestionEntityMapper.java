package fr.stephanj.app.quizzako.infrastructure.question.mapper;

import fr.stephanj.app.quizzako.domain.Question;
import fr.stephanj.app.quizzako.infrastructure.question.entity.QuestionEntity;

public class QuestionEntityMapper {
	public static Question toDomain(QuestionEntity entity) {
		return new Question(entity.getId(), entity.getQuestion(), entity.getAnswers());
	}

	public static QuestionEntity toEntity(Question question) {
		return new QuestionEntity(question.getId(), question.getQuestion(), question.getAnswers());
	}
}
