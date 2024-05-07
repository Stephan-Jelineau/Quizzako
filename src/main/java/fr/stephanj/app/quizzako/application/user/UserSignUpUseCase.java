package fr.stephanj.app.quizzako.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.user.mapper.UserMapper;
import fr.stephanj.app.quizzako.application.user.outbound.AutoLoginService;
import fr.stephanj.app.quizzako.application.user.outbound.EncryptionService;
import fr.stephanj.app.quizzako.application.user.outbound.UserRepository;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.presentation.user.request.CreateUserRequest;
import fr.stephanj.app.quizzako.presentation.user.response.BasicUserFullNameResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserSignUpUseCase {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AutoLoginService autoLoginService;

	@Autowired
	private EncryptionService encryptionService;

	public BasicUserFullNameResponse registerNewUserAndConnect(CreateUserRequest userRequest,
			HttpServletRequest request, HttpServletResponse response) {
		User user = UserMapper.toDomain(userRequest, encryptionService);
		String email = userRepository.registerNewUser(user);
		autoLoginService.login(request, response, email);
		return new BasicUserFullNameResponse(user.getFirstname(), user.getName());
	}

}
