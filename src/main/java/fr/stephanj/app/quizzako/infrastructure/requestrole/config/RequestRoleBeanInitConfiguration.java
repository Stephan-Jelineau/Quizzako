package fr.stephanj.app.quizzako.infrastructure.requestrole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.stephanj.app.quizzako.application.requestrole.outbound.RequestRoleRepository;
import fr.stephanj.app.quizzako.infrastructure.requestrole.persistence.RequestRoleRepositoryImpl;

@Configuration
public class RequestRoleBeanInitConfiguration {

	// ---------------------------- Repository

	@Bean
	RequestRoleRepository getRequestRoleRepositoryImpl() {
		return new RequestRoleRepositoryImpl();
	}
}
