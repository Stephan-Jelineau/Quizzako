package fr.stephanj.app.quizzako.infrastructure.config.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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
import fr.stephanj.app.quizzako.presentation.cohort.common.CohortConstants;
import fr.stephanj.app.quizzako.presentation.dashboard.common.DashboardConstants;
import fr.stephanj.app.quizzako.presentation.quiz.common.QuizConstants;
import fr.stephanj.app.quizzako.presentation.requestrole.common.RequestRoleConstants;
import fr.stephanj.app.quizzako.presentation.score.common.ScoreConstants;
import fr.stephanj.app.quizzako.presentation.user.common.UserConstants;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
	@Bean
	SecurityContextRepository securityContextRepository() {
		return new DelegatingSecurityContextRepository(new RequestAttributeSecurityContextRepository(),
				new HttpSessionSecurityContextRepository());
	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() throws Exception {
		return (WebSecurity web) -> web.ignoring().requestMatchers("/css/**", "/js/**");
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
			auth.requestMatchers(DashboardConstants.DASHBOARD_URL + "/**").hasAnyRole(Role.STUDENT.toString(),
					Role.TEACHER.toString());
			auth.requestMatchers(QuizConstants.MY_QUIZZES_URL + "/**").hasRole(Role.TEACHER.toString());
			auth.requestMatchers(QuizConstants.MY_COHORTS_URL + "/**").hasRole(Role.TEACHER.toString());
			auth.requestMatchers(QuizConstants.UPDATE_MY_QUIZ_URL + "/**").hasRole(Role.TEACHER.toString());
			auth.requestMatchers(CohortConstants.COHORT_MANAGE_URL + "/**").hasRole(Role.TEACHER.toString());
			auth.requestMatchers(CohortConstants.COHORT_DETAIL_URL + "/**").hasRole(Role.TEACHER.toString());
			auth.requestMatchers(CohortConstants.COHORT_UPDATE_URL + "/**").hasRole(Role.TEACHER.toString());
			auth.requestMatchers(CohortConstants.COHORT_SCORES_URL + "/**").hasRole(Role.TEACHER.toString());
			auth.requestMatchers(QuizConstants.QUIZZES_URL).access(SecurityConfig::onlyUserStudentAndAnonymous);
			auth.requestMatchers(QuizConstants.QUIZ_URL).access(SecurityConfig::onlyUserStudentAndAnonymous);
			auth.requestMatchers(QuizConstants.SUBMIT_QUIZ_URL).access(SecurityConfig::onlyUserStudentAndAnonymous);
			auth.requestMatchers(ScoreConstants.SCORE_QUIZ_URL).access(SecurityConfig::onlyUserStudentAndAnonymous);
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

	private static AuthorizationDecision onlyUserStudentAndAnonymous(Supplier<Authentication> supplier,
			RequestAuthorizationContext context) {
		SimpleGrantedAuthority studentRole = new SimpleGrantedAuthority("ROLE_" + Role.STUDENT.toString());
		SimpleGrantedAuthority userRole = new SimpleGrantedAuthority("ROLE_" + Role.USER.toString());
		SimpleGrantedAuthority anonymous = new SimpleGrantedAuthority(ROLE_ANONYMOUS);
		Collection<? extends GrantedAuthority> authorities = supplier.get().getAuthorities();
		if (authorities.contains(studentRole) || authorities.contains(userRole) || authorities.contains(anonymous))
			return new AuthorizationDecision(true);
		return new AuthorizationDecision(false);
	}
}
