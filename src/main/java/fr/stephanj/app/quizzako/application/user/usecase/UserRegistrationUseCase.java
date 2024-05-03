package fr.stephanj.app.quizzako.application.user.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.user.service.UserService;
import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.infrastructure.user.mapper.UserMapper;
import fr.stephanj.app.quizzako.infrastructure.user.security.AutoLoginHandler;
import fr.stephanj.app.quizzako.presentation.user.request.CreateUserRequest;
import fr.stephanj.app.quizzako.presentation.user.response.BasicUserFullNameResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserRegistrationUseCase {

	@Autowired
	private UserService userService;

	@Autowired
	private AutoLoginHandler autoLoginHandler;

	public BasicUserFullNameResponse registerNewUserAndConnect(CreateUserRequest userRequest, HttpServletRequest request,
			HttpServletResponse response) {
		User user = UserMapper.toDomainUser(userRequest);
		userService.registerNewUser(user);
		autoLoginHandler.autoLogin(request, response, user.getEmail());
		return new BasicUserFullNameResponse(user.getFirstname(), user.getName());
	}

}
