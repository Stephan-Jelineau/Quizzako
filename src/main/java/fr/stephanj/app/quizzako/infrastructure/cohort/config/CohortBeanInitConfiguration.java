package fr.stephanj.app.quizzako.infrastructure.cohort.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.stephanj.app.quizzako.domain.repository.CohortRepository;
import fr.stephanj.app.quizzako.infrastructure.cohort.persistence.CohortRepositoryImpl;

@Configuration
public class CohortBeanInitConfiguration {
	
	
	// ---------------------------- Repository
	@Bean
	CohortRepository getCohortRepositoryImpl() {
		return new CohortRepositoryImpl();
	}
}
