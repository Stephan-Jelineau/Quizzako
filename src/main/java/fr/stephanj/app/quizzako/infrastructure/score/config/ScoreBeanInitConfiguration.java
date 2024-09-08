package fr.stephanj.app.quizzako.infrastructure.score.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.stephanj.app.quizzako.domain.repository.ScoreRepository;
import fr.stephanj.app.quizzako.infrastructure.score.persistence.ScoreRepositoryImpl;

@Configuration
public class ScoreBeanInitConfiguration {

	// ---------------------------- Repository

	@Bean
	ScoreRepository getScoreRepositoryImpl() {
		return new ScoreRepositoryImpl();
	}
	
}
