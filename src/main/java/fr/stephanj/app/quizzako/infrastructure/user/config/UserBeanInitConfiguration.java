package fr.stephanj.app.quizzako.infrastructure.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import fr.stephanj.app.quizzako.application.user.service.AutoLoginService;
import fr.stephanj.app.quizzako.application.user.service.EncryptionService;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;
import fr.stephanj.app.quizzako.infrastructure.user.persistence.UserRepositoryImpl;
import fr.stephanj.app.quizzako.infrastructure.user.security.AutoLoginServiceImpl;
import fr.stephanj.app.quizzako.infrastructure.user.security.EncryptionServiceImpl;
import fr.stephanj.app.quizzako.infrastructure.user.security.UserDetailsServiceImpl;

@Configuration
public class UserBeanInitConfiguration {

	// ---------------------------- Services

	@Bean
	AutoLoginService getAutoLoginServiceImpl() {
		return new AutoLoginServiceImpl();
	}

	@Bean
	EncryptionService getEncryptionServiceImpl() {
		return new EncryptionServiceImpl();
	}

	@Bean
	UserDetailsService getUserDetailsServiceImpl() {
		return new UserDetailsServiceImpl();
	}

	// ---------------------------- Repository

	@Bean
	UserRepository getUserRepositoryImpl() {
		return new UserRepositoryImpl();
	}
}
