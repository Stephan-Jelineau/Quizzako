package fr.stephanj.app.quizzako.application.user.mapper;

import fr.stephanj.app.quizzako.application.user.outbound.EncryptionService;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.presentation.user.request.CreateUserRequest;

public class UserMapper {

	public static User toDomain(CreateUserRequest userForm, EncryptionService encryptionService) {
		return new User(userForm.getFirstname(), userForm.getName(), userForm.getEmail(), encryptionService.encode(userForm.getPassword1()),
				Role.USER);
	}
}
