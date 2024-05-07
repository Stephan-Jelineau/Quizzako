package fr.stephanj.app.quizzako.infrastructure.config.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.presentation.HomeConstants;
import fr.stephanj.app.quizzako.presentation.admin.controller.common.AdminConstants;
import fr.stephanj.app.quizzako.presentation.requestrole.common.RequestRoleConstants;
import fr.stephanj.app.quizzako.presentation.user.common.UserConstants;

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
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/login").anonymous();
			auth.requestMatchers(UserConstants.USER_REGISTER_URL).anonymous();
			auth.requestMatchers(UserConstants.USER_ACCOUNT_URL + "/**")
					.access(SecurityConfig::notAdminAndAuthenticated);
			auth.requestMatchers(RequestRoleConstants.ROLE_URL + "/**")
					.access(SecurityConfig::notAdminAndAuthenticated);
			auth.requestMatchers(AdminConstants.ADMIN_HOME_URL + "/**").hasRole(Role.ADMIN.toString());
			auth.requestMatchers(HomeConstants.HOME_URL).permitAll();
			auth.anyRequest().denyAll();
		});

		http.formLogin(l -> l.defaultSuccessUrl(HomeConstants.HOME_URL));
		http.logout(logout -> logout.logoutSuccessUrl(HomeConstants.HOME_URL));

		http.formLogin(login -> login.successHandler((request, response, authentication) -> {

			Map<String, String> roleTargetUrlMap = getMappingOnLoginSuccess();

			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				String authorityName = grantedAuthority.getAuthority();
				if (roleTargetUrlMap.containsKey(authorityName)) {
					response.sendRedirect(roleTargetUrlMap.get(authorityName));
				}
			}
		}));

		return http.build();
	}

	private Map<String, String> getMappingOnLoginSuccess() {
		Map<String, String> roleTargetUrlMap = new HashMap<>();
		roleTargetUrlMap.put("ROLE_" + Role.ADMIN.toString(), AdminConstants.ADMIN_HOME_URL);
		roleTargetUrlMap.put("ROLE_" + Role.USER.toString(), HomeConstants.HOME_URL);
		roleTargetUrlMap.put("ROLE_" + Role.TEACHER.toString(), HomeConstants.HOME_URL);
		roleTargetUrlMap.put("ROLE_" + Role.STUDENT.toString(), HomeConstants.HOME_URL);
		return roleTargetUrlMap;
	}

	private static AuthorizationDecision notAdminAndAuthenticated(Supplier<Authentication> supplier,
			RequestAuthorizationContext context) {
		SimpleGrantedAuthority adminRole = new SimpleGrantedAuthority("ROLE_" + Role.ADMIN.toString());
		Collection<? extends GrantedAuthority> authorities = supplier.get().getAuthorities();
		boolean notAdmin = !authorities.contains(adminRole);
		boolean notAdminAndAuth = notAdmin && !authorities.isEmpty();
		return new AuthorizationDecision(notAdminAndAuth);
	}
}
