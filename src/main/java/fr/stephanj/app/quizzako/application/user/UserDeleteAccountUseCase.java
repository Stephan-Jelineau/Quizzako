package fr.stephanj.app.quizzako.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.user.outbound.UserRepository;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class UserDeleteAccountUseCase {
	@Autowired
	UserRepository userRepository;

	public void deleteAccount(String email, HttpServletRequest request) {
		new SecurityContextLogoutHandler().logout(request, null, null);
		userRepository.deleteUserByEmail(email);
	}
}
