package fr.stephanj.app.quizzako.infrastructure.question.presistence;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stephanj.app.quizzako.infrastructure.question.entity.QuestionEntity;

public interface JpaQuestionRepository extends JpaRepository<QuestionEntity, Long> {

}
