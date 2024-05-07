package fr.stephanj.app.quizzako.infrastructure.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;

import fr.stephanj.app.quizzako.application.user.outbound.AutoLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AutoLoginServiceImpl implements AutoLoginService{

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SecurityContextRepository securityContextRepository;

	public void login(HttpServletRequest request, HttpServletResponse response, String email) {
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
