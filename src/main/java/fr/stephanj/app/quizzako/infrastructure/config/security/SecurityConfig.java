package fr.stephanj.app.quizzako.infrastructure.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import fr.stephanj.app.quizzako.presentation.HomeConstants;
import fr.stephanj.app.quizzako.presentation.requestrole.common.RequestRoleConstants;
import fr.stephanj.app.quizzako.presentation.user.controller.common.UserConstants;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityContextRepository securityContextRepository() {
		return new DelegatingSecurityContextRepository(new RequestAttributeSecurityContextRepository(),
				new HttpSessionSecurityContextRepository());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() throws Exception {
		return (WebSecurity web) -> web.ignoring().requestMatchers("/css/**");
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HomeConstants.HOME_URL).permitAll();
			auth.requestMatchers(UserConstants.USER_REGISTER_URL).anonymous();
			auth.requestMatchers(UserConstants.USER_ACCOUNT_URL + "/**").authenticated();
			auth.requestMatchers(RequestRoleConstants.ROLE_URL + "/**").authenticated();
			auth.anyRequest().denyAll();
		}).formLogin(l -> l.defaultSuccessUrl(HomeConstants.HOME_URL))
				.logout(logout -> logout.logoutSuccessUrl(HomeConstants.HOME_URL));
		return http.build();
	}
}
