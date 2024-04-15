package fr.stephanj.app.quizzako.application.user.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.user.request.CreateUserRequest;
import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.domain.user.service.UserService;
import fr.stephanj.app.quizzako.infrastructure.user.mapper.UserMapper;
import fr.stephanj.app.quizzako.infrastructure.user.security.AutoLoginHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserRegistrationUseCase {

	@Autowired
	private UserService userService;

	@Autowired
	private AutoLoginHandler autoLoginHandler;

	public User registerNewUserAndConnect(CreateUserRequest userRequest, HttpServletRequest request,
			HttpServletResponse response) {
		User domainUser = UserMapper.toDomainUser(userRequest);
		userService.registerNewUser(domainUser);
		autoLoginHandler.autoLogin(request, response, domainUser.getEmail());
		return domainUser;
	}

}
