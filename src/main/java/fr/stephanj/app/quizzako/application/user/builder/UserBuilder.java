package fr.stephanj.app.quizzako.application.user.builder;

import fr.stephanj.app.quizzako.application.user.service.EncryptionService;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.presentation.user.request.CreateUserRequest;

public class UserBuilder {

	public static User build(CreateUserRequest userForm, EncryptionService encryptionService) {
		return new User(userForm.getFirstname(), userForm.getName(), userForm.getEmail(), encryptionService.encode(userForm.getPassword1()),
				Role.USER);
	}
}
