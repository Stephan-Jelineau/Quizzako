package fr.stephanj.app.quizzako.infrastructure.question.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.stephanj.app.quizzako.domain.Question;
import fr.stephanj.app.quizzako.domain.exception.question.QuestionNotFoundException;
import fr.stephanj.app.quizzako.domain.repository.QuestionRepository;
import fr.stephanj.app.quizzako.infrastructure.question.entity.QuestionEntity;
import fr.stephanj.app.quizzako.infrastructure.question.mapper.QuestionEntityMapper;
import fr.stephanj.app.quizzako.infrastructure.question.presistence.JpaQuestionRepository;

public class QuestionRepositoryImpl implements QuestionRepository {

	@Autowired
	JpaQuestionRepository jpaQuestionRepo;

	@Override
	public Question getById(Long id) {
		Optional<QuestionEntity> questionEntity = jpaQuestionRepo.findById(id);
		if (questionEntity.isEmpty())
			throw new QuestionNotFoundException("The Question with id " + id + " was not found");
		return QuestionEntityMapper.toDomain(questionEntity.get());
	}
}
