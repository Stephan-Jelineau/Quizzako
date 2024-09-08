package fr.stephanj.app.quizzako.infrastructure.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.infrastructure.quiz.persistence.QuizRepositoryImpl;

@Configuration
public class QuizBeanInitConfiguration {

	// ---------------------------- Repository

	@Bean
	QuizRepository getQuizRepositoryImpl() {
		return new QuizRepositoryImpl();
	}
}
