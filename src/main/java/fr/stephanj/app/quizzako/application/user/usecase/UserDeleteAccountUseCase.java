package fr.stephanj.app.quizzako.application.user.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class UserDeleteAccountUseCase {
	@Autowired
	UserService userService;

	public void deleteAccount(String email, HttpServletRequest request) {
		new SecurityContextLogoutHandler().logout(request, null, null);
		userService.deleteUserByEmail(email);
	}
}
