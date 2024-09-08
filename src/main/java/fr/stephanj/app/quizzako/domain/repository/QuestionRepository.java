package fr.stephanj.app.quizzako.domain.repository;

import fr.stephanj.app.quizzako.domain.Question;

public interface QuestionRepository {
	public Question getById(Long id);
}
