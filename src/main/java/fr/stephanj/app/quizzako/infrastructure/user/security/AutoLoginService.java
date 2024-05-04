package fr.stephanj.app.quizzako.infrastructure.user.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Keep this class NOT A BEAN, safer to instanciate a new one for each registration
public class AutoLoginService {

	private @NotNull HttpServletRequest request;
	private @NotNull HttpServletResponse response;
	private @NotBlank String email;
	private @NotNull UserDetailsService userDetailsService;
	private @NotNull SecurityContextRepository securityContextRepository;

	public AutoLoginService(HttpServletRequest request, HttpServletResponse response, String email,
			UserDetailsService userDetailsService, SecurityContextRepository securityContextRepository) {
		this.request = request;
		this.response = response;
		this.email = email;
		this.userDetailsService = userDetailsService;
		this.securityContextRepository = securityContextRepository;
	}

	public void autoLogin() {
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());
		SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
		SecurityContext context = securityContextHolderStrategy.createEmptyContext();
		context.setAuthentication(authentication);
		securityContextHolderStrategy.setContext(context);
		securityContextRepository.saveContext(context, request, response);
	}

}
