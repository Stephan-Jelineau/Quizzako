package fr.stephanj.app.quizzako.infrastructure.quiz.mapper;

import java.util.List;

import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.infrastructure.category.mapper.CategoryEntityMapper;
import fr.stephanj.app.quizzako.infrastructure.question.entity.QuestionEntity;
import fr.stephanj.app.quizzako.infrastructure.question.mapper.QuestionEntityMapper;
import fr.stephanj.app.quizzako.infrastructure.quiz.entity.QuizEntity;
import fr.stephanj.app.quizzako.infrastructure.user.mapper.UserEntityMapper;

public class QuizEntityMapper {

	public static Quiz toDomain(QuizEntity entity) {
		return new Quiz(entity.getId(), entity.getName(),
				entity.getOwner() != null ? UserEntityMapper.toDomain(entity.getOwner()) : null,
				entity.getCreationDate(), entity.getQuestions().stream().map(QuestionEntityMapper::toDomain).toList(),
				entity.getCategory() != null ? CategoryEntityMapper.toDomain(entity.getCategory()) : null);
	}

	public static QuizEntity toEntity(Quiz quiz) {

		List<QuestionEntity> questionEntities = quiz.getQuestions().stream().map(QuestionEntityMapper::toEntity)
				.toList();

		QuizEntity quizEntity = new QuizEntity(quiz.getId(), quiz.getName(), null, quiz.getCreationDate(),
				questionEntities,
				quiz.getCategory() != null ? CategoryEntityMapper.toEntity(quiz.getCategory()) : null);

		questionEntities.forEach(questionEntity -> questionEntity.setQuiz(quizEntity));
		quizEntity.setQuestions(questionEntities);

		return quizEntity;
	}
}
