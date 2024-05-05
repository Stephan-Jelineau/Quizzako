package fr.stephanj.app.quizzako.application.user.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.user.exception.UserAlreadyExistsException;
import fr.stephanj.app.quizzako.application.user.mapper.UserMapper;
import fr.stephanj.app.quizzako.application.user.service.UserService;
import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.presentation.user.request.ViewAndUpdateUserRequest;
import fr.stephanj.app.quizzako.presentation.user.response.BasicUserFullNameResponse;

@Component
public class UserViewAndUpdateUseCase {

	@Autowired
	UserService userService;

	public ViewAndUpdateUserRequest getFormUserInfoFor(String email) {
		User user = userService.getUserByEmail(email);
		ViewAndUpdateUserRequest dto = UserMapper.toViewAndUpdateUserRequest(user);
		return dto;
	}

	public BasicUserFullNameResponse updateUserByUpdateView(ViewAndUpdateUserRequest userRequest, String email) {
		User user = userService.getUserByEmail(email);
		if(!email.equals(userRequest.getEmail()) && userService.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyExistsException("The email " + email + " is already associated to an account");
		user.setFirstname(userRequest.getFirstname());
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		userService.updateUser(user);
		return new BasicUserFullNameResponse(user.getFirstname(), user.getName());
	}

}
