package fr.stephanj.app.quizzako.infrastructure.question.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.stephanj.app.quizzako.domain.repository.QuestionRepository;

@Configuration
public class QuestionBeanInitConfiguration {

	// ---------------------------- Repository

	@Bean
	QuestionRepository getQuestionRepositoryImpl() {
		return new QuestionRepositoryImpl();
	}
}
