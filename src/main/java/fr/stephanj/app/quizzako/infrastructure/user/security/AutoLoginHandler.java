package fr.stephanj.app.quizzako.infrastructure.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutoLoginHandler {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SecurityContextRepository securityContextRepository;

	public void autoLogin(HttpServletRequest request, HttpServletResponse response, String email) {
		AutoLoginService service = new AutoLoginService(request, response, email, userDetailsService,
				securityContextRepository);
		service.autoLogin();
	}
}