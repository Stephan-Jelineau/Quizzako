package fr.stephanj.app.quizzako.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.user.outbound.AutoLoginService;
import fr.stephanj.app.quizzako.application.user.outbound.UserRepository;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.presentation.user.request.ViewAndUpdateUserRequest;
import fr.stephanj.app.quizzako.presentation.user.response.BasicUserFullNameResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserViewAndUpdateUseCase {

	@Autowired
	private AutoLoginService autoLoginService;

	@Autowired
	private UserRepository userRepository;

	public ViewAndUpdateUserRequest getFormUserInfoFor(String email) {
		User user = userRepository.getUserByEmail(email);
		return new ViewAndUpdateUserRequest(user.getFirstname(), user.getName(), user.getEmail());
	}

	public BasicUserFullNameResponse updateUserNamesByUpdateView(ViewAndUpdateUserRequest userRequest) {
		User user = userRepository.getUserByEmail(userRequest.getEmail());
		user.updateNames(userRequest.getFirstname(), userRequest.getName());
		userRepository.updateUserWithoutNewMail(user);
		return new BasicUserFullNameResponse(user.getFirstname(), user.getName());
	}

	public BasicUserFullNameResponse updateUserByUpdateViewWithNewMail(ViewAndUpdateUserRequest userRequest,
			String oldEmail, HttpServletRequest request, HttpServletResponse response) {
		User user = userRepository.getUserByEmail(oldEmail);
		user.updateNamesAndMail(userRequest.getFirstname(), userRequest.getName(), userRequest.getEmail());
		userRepository.updateUserWithNewMail(user, oldEmail);
		autoLoginService.login(request, response, user.getEmail());
		return new BasicUserFullNameResponse(user.getFirstname(), user.getName());
	}

}
